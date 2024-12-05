package lk.ijse.posbackendv2.dao;

import lk.ijse.posbackendv2.entity.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO  extends JpaRepository<Order, String> {
}
