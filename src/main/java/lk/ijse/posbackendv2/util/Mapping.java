package lk.ijse.posbackendv2.util;

import lk.ijse.posbackendv2.dto.impl.CustomerDTO;
import lk.ijse.posbackendv2.dto.impl.productDTO;
import lk.ijse.posbackendv2.entity.impl.Customer;
import lk.ijse.posbackendv2.entity.impl.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //Customer mapping
    public Customer toCustomerEntity(CustomerDTO customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }
    public CustomerDTO toCustomerDto(Customer customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
    public List<CustomerDTO> asCustomerDTO(List<Customer> customerEntityList){
        return modelMapper.map(customerEntityList, new TypeToken<List<CustomerDTO>>(){}.getType());
    }

    // Product mapping
    public productDTO toProductDTO(Product product) {
        return modelMapper.map(product, productDTO.class);
    }
    public Product toProductEntity(productDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
    public List<productDTO> asProductDTOList(List<Product> productEntities) {
        return modelMapper.map(productEntities, new TypeToken<List<productDTO>>() {}.getType());
    }

    //Order mapping

}
