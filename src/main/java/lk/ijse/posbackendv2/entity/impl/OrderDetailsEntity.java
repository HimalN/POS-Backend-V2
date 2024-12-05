package lk.ijse.posbackendv2.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OrderDetails")
public class OrderDetailsEntity {
    @Id
    private int orderDetailsID;
    private String productName;
    private int productQTYNeeded;
    private double productPrice;
    private double productTotal;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private Order order;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "productId",referencedColumnName = "productId")
    private Product product;
}
