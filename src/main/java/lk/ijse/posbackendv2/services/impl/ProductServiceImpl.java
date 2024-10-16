package lk.ijse.posbackendv2.services.impl;

import lk.ijse.posbackendv2.customStatusCode.selectedProductErrorCodes;
import lk.ijse.posbackendv2.dao.ProductDAO;
import lk.ijse.posbackendv2.dto.impl.productDTO;
import lk.ijse.posbackendv2.dto.productStatus;
import lk.ijse.posbackendv2.entity.impl.Product;
import lk.ijse.posbackendv2.exception.ProductNotFoundException;
import lk.ijse.posbackendv2.services.ProductService;
import lk.ijse.posbackendv2.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private Mapping mapper;

    @Override
    public void saveProduct(productDTO productDTO) {
        Product saveProduct = productDAO.save(mapper.toProductEntity(productDTO));
        System.out.println("method called");
        if (saveProduct == null) {
            throw new ProductNotFoundException("Product Not Saved");
        }

    }

    @Override
    public List<productDTO> getAllProducts() {
        List<Product> productList = productDAO.findAll();
        return mapper.asProductDTOList(productList);
    }

    @Override
    public productStatus getProduct(String productId) {
        if (productDAO.existsById(productId)){
            Product selectedProduct = productDAO.getReferenceById(productId);
            return mapper.toProductDTO(selectedProduct);
        }else {
            return new selectedProductErrorCodes(2,"product with productId "+productId+" not found");
        }
    }

    @Override
    public void deleteProduct(String productId) {
        Optional<Product> existProduct = productDAO.findById(productId);
        if(!existProduct.isPresent()){
            throw new ProductNotFoundException("Product with: "+productId+" Not Found");
        }else {
            productDAO.deleteById(productId);
        }
    }

    @Override
    public void updateProduct(String productId, productDTO productDTO) {
        Optional<Product> product = productDAO.findById(productId);
        if (product.isPresent()){
            product.get().setProductId(productDTO.getProductId());
            product.get().setProductName(productDTO.getProductName());
            product.get().setProductType(productDTO.getProductType());
            product.get().setProductQty(productDTO.getProductQty());
            product.get().setProductPrice(productDTO.getProductPrice());
        }
    }
}
