package lk.ijse.posbackendv2.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lk.ijse.posbackendv2.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product implements SuperEntity {
    @Id
    private String productId;
    private String productName;
    private String productType;
    private String productWeight;
    private double productPrice;
    private int productQty;
    @OneToMany(mappedBy = "product")
    private List<Order> orders;
}
