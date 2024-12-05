package lk.ijse.posbackendv2.dto.impl;

import lk.ijse.posbackendv2.dto.OrderDetailsStatus;
import lk.ijse.posbackendv2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsDTO implements SuperDTO, OrderDetailsStatus {
    private String detailsID;
    private String orderId;
    private String orderDate;
    private String customerId;
    private String customerName;
    private String itemId;
    private String itemName;
    private double itemPrice;
    private int itemQty;
    private int orderQty;
    private double totalPrice;
}
