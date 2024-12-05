package lk.ijse.posbackendv2.entity.impl;

import jakarta.persistence.*;
import lk.ijse.posbackendv2.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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
    @OneToMany(mappedBy = "order")
    private List<OrderDetailsEntity> orderDetails;
}
