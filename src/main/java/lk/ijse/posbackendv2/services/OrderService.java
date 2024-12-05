package lk.ijse.posbackendv2.services;

import lk.ijse.posbackendv2.dto.impl.CombinedOrderDto;
import lk.ijse.posbackendv2.dto.impl.OrderDTO;

public interface OrderService {
    void saveOrder(OrderDTO orderDto, CombinedOrderDto combinedOrderDto);
    void deleteItems(String orderID, String itemID, int orderQty);
    void updateOrder(String orderID, String itemID, int qtyOnHand, CombinedOrderDto combinedOrderDto);
}
