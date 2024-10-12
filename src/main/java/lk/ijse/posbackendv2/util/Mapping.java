package lk.ijse.posbackendv2.util;

import lk.ijse.posbackendv2.dto.impl.CustomerDTO;
import lk.ijse.posbackendv2.entity.impl.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Customer toCustomerEntity(CustomerDTO customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }
    public CustomerDTO toCustomerDto(Customer customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
    public List<CustomerDTO> asCustomerDTO(List<Customer> customerEntityList){
        return modelMapper.map(customerEntityList, new TypeToken<List<CustomerDTO>>(){}.getType());
    }
}
