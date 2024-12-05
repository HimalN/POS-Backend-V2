package lk.ijse.posbackendv2.controller;

import lk.ijse.posbackendv2.dto.impl.CombinedOrderDto;
import lk.ijse.posbackendv2.dto.impl.CustomerDTO;
import lk.ijse.posbackendv2.dto.impl.OrderDTO;
import lk.ijse.posbackendv2.dto.impl.ProductDTO;
import lk.ijse.posbackendv2.exception.DataPersistException;
import lk.ijse.posbackendv2.exception.OrderNotFoundException;
import lk.ijse.posbackendv2.services.CustomerService;
import lk.ijse.posbackendv2.services.OrderService;
import lk.ijse.posbackendv2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/orders")
public class orderController {

    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO searchCustomers(@PathVariable("customerID") String customerID) {
        return customerService.getCustomers(customerID);
    }

    @GetMapping(value = "/{itemID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO searchItemByID(@PathVariable("itemID") String itemID) {
        return productService.searchItems(itemID);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody CombinedOrderDto combinedOrderDto) {
        try {
            OrderDTO orderDto = new OrderDTO();
            orderDto.setOrderId(combinedOrderDto.getOrderId());
            orderDto.setOrderDate(combinedOrderDto.getOrderDate());
            orderDto.setCustomerId(combinedOrderDto.getCustomerId());
            orderDto.setCustomerName(combinedOrderDto.getCustomerName());

            orderService.saveOrder(orderDto,combinedOrderDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{orderID}/{itemID}/{orderQty}")
    public ResponseEntity<Void> deleteItems(@PathVariable("orderID") String orderID, @PathVariable("itemID") String itemID, @PathVariable("orderQty") int orderQty) {
        try {
            orderService.deleteItems(orderID,itemID,orderQty);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{orderID}/{itemID}/{qtyOnHand}")
    public ResponseEntity<Void> updateOrder(@PathVariable("orderID") String orderID, @PathVariable("itemID") String itemID, @PathVariable("qtyOnHand") int qtyOnHand, @RequestBody CombinedOrderDto combinedOrderDto) {
        try {
            orderService.updateOrder(orderID, itemID, qtyOnHand, combinedOrderDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
