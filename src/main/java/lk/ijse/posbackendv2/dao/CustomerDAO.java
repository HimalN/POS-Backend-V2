package lk.ijse.posbackendv2.dao;

import lk.ijse.posbackendv2.entity.impl.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, String> {

}
