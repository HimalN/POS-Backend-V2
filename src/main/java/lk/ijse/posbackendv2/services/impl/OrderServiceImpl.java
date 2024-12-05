package lk.ijse.posbackendv2.services.impl;

import lk.ijse.posbackendv2.dao.OrderDAO;
import lk.ijse.posbackendv2.dao.OrderDetailsDAO;
import lk.ijse.posbackendv2.dao.ProductDAO;
import lk.ijse.posbackendv2.dto.impl.CombinedOrderDto;
import lk.ijse.posbackendv2.dto.impl.OrderDTO;
import lk.ijse.posbackendv2.dto.impl.OrderDetailsDTO;
import lk.ijse.posbackendv2.dto.impl.ProductDTO;
import lk.ijse.posbackendv2.entity.impl.Order;
import lk.ijse.posbackendv2.entity.impl.OrderDetailsEntity;
import lk.ijse.posbackendv2.entity.impl.Product;
import lk.ijse.posbackendv2.exception.DataPersistException;
import lk.ijse.posbackendv2.exception.OrderNotFoundException;
import lk.ijse.posbackendv2.exception.ProductNotFoundException;
import lk.ijse.posbackendv2.services.OrderService;
import lk.ijse.posbackendv2.services.ProductService;
import lk.ijse.posbackendv2.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private Mapping mapper;
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDAO productDAO;

    @Override
    public void saveOrder(OrderDTO orderDto, CombinedOrderDto combinedOrderDto) {
        Order save = orderDAO.save(mapper.toOrderEntity(orderDto));
        if (save == null) {
            throw new DataPersistException("Order NOT Saved");
        } else {
            OrderDetailsDTO dto = getOrderDetailsDto(orderDto, combinedOrderDto);
            boolean isSaved = saveOrderDetails(dto);
            if (isSaved) {
                ProductDTO productDTO = productService.searchItems(combinedOrderDto.getItemId());
                int newQty = productDTO.getProductQty() - combinedOrderDto.getOrderQty();
                if (newQty < 0) {
                    throw new IllegalArgumentException("Insufficient item quantity.");
                }
                productDTO.setProductQty(newQty);
                productService.updateProduct(combinedOrderDto.getItemId(),productDTO);
            }
        }
    }

    @Override
    public void deleteItems(String orderID, String itemID, int orderQty) {
        Optional<OrderDetailsEntity> odIdFound = orderDetailsDAO.findById(orderID);
        Optional<Order> oIdFound = orderDAO.findById(orderID);
        if (odIdFound.isEmpty()) {
            throw new OrderNotFoundException("Order Not Found");
        }
        orderDetailsDAO.deleteById(orderID);
        if (oIdFound.isEmpty()) {
            throw new OrderNotFoundException("Order Not Found");
        }
        orderDAO.deleteById(orderID);
        ProductDTO productDTO = productService.searchItems(itemID);
        int newQty = productDTO.getProductQty() + orderQty;
        if (newQty < 0) {
            throw new IllegalArgumentException("Insufficient item quantity.");
        }
        productDTO.setProductQty(newQty);
        productService.updateProduct(itemID,productDTO);
    }

    @Override
    public void updateOrder(String orderID, String itemID, int qtyOnHand, CombinedOrderDto combinedOrderDto) {
        Optional<OrderDetailsEntity> orderFound = orderDetailsDAO.findById(orderID);
        if (orderFound.isEmpty()) {
            throw new OrderNotFoundException("Order Not Found");
        } else {
            OrderDetailsEntity orderDetails = orderFound.get();
            orderDetails.setProductName(combinedOrderDto.getItemName());
            orderDetails.setProductPrice(combinedOrderDto.getItemPrice());
            orderDetails.setProductQTYNeeded(qtyOnHand);
            orderDetails.setProductTotal(combinedOrderDto.getTotalPrice());

            Optional<Product> existingItem = productDAO.findById(itemID);
            if (existingItem.isPresent()) {
                orderDetails.setProduct(existingItem.get());
            } else {
                throw new ProductNotFoundException("Product Not Found");
            }
            ProductDTO productDTO = productService.searchItems(itemID);
            productDTO.setProductQty(qtyOnHand);
            productService.updateProduct(itemID, productDTO);
        }
    }

    private boolean saveOrderDetails(OrderDetailsDTO dto) {
        OrderDetailsEntity save = orderDetailsDAO.save(mapper.toOrderDetailsEntity(dto));
        if (save == null) {
            return false;
        } else {
            return true;
        }
    }

    private OrderDetailsDTO getOrderDetailsDto(OrderDTO orderDto, CombinedOrderDto combinedOrderDto) {
        OrderDetailsDTO dto = new OrderDetailsDTO();
        dto.setDetailsID(orderDto.getOrderId());
        dto.setOrderId(orderDto.getOrderId());
        dto.setOrderDate(combinedOrderDto.getOrderDate());
        dto.setCustomerId(combinedOrderDto.getCustomerId());
        dto.setCustomerName(combinedOrderDto.getCustomerName());
        dto.setItemId(combinedOrderDto.getItemId());
        dto.setItemName(combinedOrderDto.getItemName());
        dto.setItemName(String.valueOf(combinedOrderDto.getItemPrice()));
        dto.setItemQty(combinedOrderDto.getItemQty());
        dto.setOrderQty(combinedOrderDto.getOrderQty());
        return dto;
    }
}
