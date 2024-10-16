package lk.ijse.posbackendv2.entity.impl;

import jakarta.persistence.*;
import lk.ijse.posbackendv2.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order implements SuperEntity {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
    private String customerName;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    private String productName;
    private String productType;
    private int productQTYNeeded;
    private double productPrice;
    private double productTotal;
}
