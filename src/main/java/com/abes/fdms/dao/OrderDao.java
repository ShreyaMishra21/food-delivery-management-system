package com.abes.fdms.dao;

import com.abes.fdms.dto.OrderDto;
import java.util.List;

public interface OrderDao {
    List<OrderDto> getOrders();
    void addOrder(OrderDto order);
}
