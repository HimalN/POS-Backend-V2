package lk.ijse.posbackendv2.dto.impl;

import lk.ijse.posbackendv2.dto.OrderStatus;
import lk.ijse.posbackendv2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO implements SuperDTO, OrderStatus {
    private String orderId;
    private String orderDate;
    private String customerId;
    private String customerName;
}
