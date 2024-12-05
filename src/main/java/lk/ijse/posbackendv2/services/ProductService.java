package lk.ijse.posbackendv2.services;

import lk.ijse.posbackendv2.dto.impl.ProductDTO;
import lk.ijse.posbackendv2.dto.productStatus;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    productStatus getProduct(String productId);
    void deleteProduct(String productId);
    void updateProduct(String productId, ProductDTO productDTO);
    ProductDTO searchItems(String itemID);
}
