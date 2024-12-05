package lk.ijse.posbackendv2.dao;

import lk.ijse.posbackendv2.entity.impl.Customer;
import lk.ijse.posbackendv2.entity.impl.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsDAO  extends JpaRepository<OrderDetailsEntity, String> {
}
