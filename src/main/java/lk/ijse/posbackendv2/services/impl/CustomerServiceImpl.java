package lk.ijse.posbackendv2.services.impl;

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
        return List.of();
    }

    @Override
    public CustomerStatus getCustomer(String customerId) {
        return null;
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {

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
