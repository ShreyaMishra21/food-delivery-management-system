package com.abes.fdms.dao;

import com.abes.fdms.dto.OrderDto;
import com.abes.fdms.util.CollectionUtil;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public List<OrderDto> getOrders() {
        return CollectionUtil.orders;
    }

    @Override
    public void addOrder(OrderDto order) {
        CollectionUtil.orders.add(order);
    }
}
