package lk.ijse.posbackendv2.dao;

import lk.ijse.posbackendv2.entity.impl.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product,String> {

}
