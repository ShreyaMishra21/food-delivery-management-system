package com.abes.fdms.dao;

import com.abes.fdms.dto.CustomerDto;
import com.abes.fdms.dto.ManagerDto;
import java.util.Map;

public interface UserDao {
    Map<String, CustomerDto> getCustomers();
    void addCustomer(CustomerDto customer);
    CustomerDto getCustomerById(String id);
    ManagerDto getManagerById(String id);
}
