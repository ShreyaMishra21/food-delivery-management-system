package com.abes.fdms.service;

import java.util.Map;

import com.abes.fdms.dao.UserDao;
import com.abes.fdms.dao.UserDaoImpl;
import com.abes.fdms.dto.CustomerDto;

public class CustomerService {
    private final UserDao userDao = new UserDaoImpl();

    public boolean registerCustomer(String id, String name, String email, String phoneNumber, String password) {
        // Check for duplicate ID
        if (userDao.getCustomers().containsKey(id)) {
            return false;
        }
        // Check for duplicate email or phone
        for (CustomerDto c : userDao.getCustomers().values()) {
            if (c.getEmail().equalsIgnoreCase(email) || c.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }
        userDao.addCustomer(new CustomerDto(id, name, email, phoneNumber, password));
        return true;
    }

    public CustomerDto loginCustomer(String id, String password) {
        CustomerDto customer = userDao.getCustomerById(id);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }
    public Map<String, CustomerDto> getAllCustomers() {
        return userDao.getCustomers();
    }
}
