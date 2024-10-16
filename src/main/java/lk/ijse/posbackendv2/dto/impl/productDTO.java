package lk.ijse.posbackendv2.dto.impl;

import lk.ijse.posbackendv2.dto.productStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class productDTO implements productStatus {
    private String productId;
    private String productName;
    private String productType;
    private int productQty;
    private double productPrice;
}
