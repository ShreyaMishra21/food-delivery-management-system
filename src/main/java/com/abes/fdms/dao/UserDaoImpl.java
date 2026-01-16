package com.abes.fdms.dao;

import com.abes.fdms.dto.CustomerDto;
import com.abes.fdms.dto.ManagerDto;
import com.abes.fdms.util.CollectionUtil;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public Map<String, CustomerDto> getCustomers() {
        return CollectionUtil.customers;
    }

    @Override
    public void addCustomer(CustomerDto customer) {
        CollectionUtil.customers.put(customer.getId(), customer);
    }

    @Override
    public CustomerDto getCustomerById(String id) {
        return CollectionUtil.customers.get(id);
    }

    @Override
    public ManagerDto getManagerById(String id) {
        return CollectionUtil.managers.get(id);
    }
}
