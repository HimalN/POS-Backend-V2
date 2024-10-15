package lk.ijse.posbackendv2.services.impl;

import lk.ijse.posbackendv2.customStatusCode.selectedCustomerErrorCodes;
import lk.ijse.posbackendv2.dao.CustomerDAO;
import lk.ijse.posbackendv2.dto.CustomerStatus;
import lk.ijse.posbackendv2.dto.impl.CustomerDTO;
import lk.ijse.posbackendv2.entity.impl.Customer;
import lk.ijse.posbackendv2.exception.CustomerNotFoundException;
import lk.ijse.posbackendv2.services.CustomerService;
import lk.ijse.posbackendv2.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private Mapping mapper;


    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer saveCustomer = customerDAO.save(mapper.toCustomerEntity(customerDTO));
        if (saveCustomer == null) {
            throw new CustomerNotFoundException("Customer Not Saved");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers =customerDAO.findAll();
        return mapper.asCustomerDTO(allCustomers);
    }

    @Override
    public CustomerStatus getCustomer(String customerId) {
        if (customerDAO.existsById(customerId)){
            Customer selectedCustomer = customerDAO.getReferenceById(customerId);
            return mapper.toCustomerDto(selectedCustomer);
        }else {
            return new selectedCustomerErrorCodes(2,"customer with productId "+customerId+" not found!");
        }
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        Optional<Customer> customer =customerDAO.findById(customerId);
        if (customer.isPresent()){
            customer.get().setId(customerDTO.getId());
            customer.get().setName(customerDTO.getName());
            customer.get().setAddress(customerDTO.getAddress());
            customer.get().setPhone(customerDTO.getPhone());
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<Customer> existCustomer = customerDAO.findById(customerId); // <1>
        if(!existCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer with: "+customerId+" Not Found");
        } else {
            customerDAO.deleteById(customerId);
        }
    }
}
