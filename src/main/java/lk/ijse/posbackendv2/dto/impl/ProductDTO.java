package lk.ijse.posbackendv2.dto.impl;

import lk.ijse.posbackendv2.dto.productStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements productStatus {
    private String productId;
    private String productName;
    private String productType;
    private String productWeight;
    private double productPrice;
    private int productQty;
}
