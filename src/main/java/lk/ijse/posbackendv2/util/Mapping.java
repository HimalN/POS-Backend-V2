package lk.ijse.posbackendv2.util;

import lk.ijse.posbackendv2.dto.impl.*;
import lk.ijse.posbackendv2.entity.impl.Customer;
import lk.ijse.posbackendv2.entity.impl.Order;
import lk.ijse.posbackendv2.entity.impl.OrderDetailsEntity;
import lk.ijse.posbackendv2.entity.impl.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //Customer mapping
    public Customer toCustomerEntity(CustomerDTO customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }
    public CustomerDTO toCustomerDto(Customer customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
    public List<CustomerDTO> asCustomerDTO(List<Customer> customerEntityList){
        return modelMapper.map(customerEntityList, new TypeToken<List<CustomerDTO>>(){}.getType());
    }

    // Product mapping
    public ProductDTO toProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
    public Product toProductEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
    public List<ProductDTO> asProductDTOList(List<Product> productEntities) {
        return modelMapper.map(productEntities, new TypeToken<List<ProductDTO>>() {}.getType());
    }

    /*order mapping*/
    public Order toOrderEntity(OrderDTO orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }

    /*order details mapping*/
    public OrderDetailsEntity toOrderDetailsEntity(OrderDetailsDTO orderDetailsDto) {
        return modelMapper.map(orderDetailsDto, OrderDetailsEntity.class);
    }
    public CombinedOrderDto toOrderDetailsDto(OrderDetailsEntity orderDetailsEntity) {
        return modelMapper.map(orderDetailsEntity, CombinedOrderDto.class);
    }
    public List<CombinedOrderDto> toOrderDetailsDtoLists(List<OrderDetailsEntity> orderDetailsEntities) {
        return modelMapper.map(orderDetailsEntities, new TypeToken<List<CombinedOrderDto>>() {}.getType());
    }

}
