package lk.ijse.posbackendv2.services;

import lk.ijse.posbackendv2.dto.impl.productDTO;
import lk.ijse.posbackendv2.dto.productStatus;

import java.util.List;

public interface ProductService {
    void saveProduct(productDTO productDTO);
    List<productDTO> getAllProducts();
    productStatus getProduct(String productId);
    void deleteProduct(String productId);
    void updateProduct(String productId, productDTO productDTO);
}
