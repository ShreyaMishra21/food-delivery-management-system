package com.abes.fdms.dto;

import java.util.Map;

public class OrderDto {
    private CustomerDto customer;
    private DeliveryPersonDto deliveryPerson;
    private Map<FoodItemDto, Integer> itemsOrdered;
    private String status;

    public OrderDto(CustomerDto customer, DeliveryPersonDto deliveryPerson, Map<FoodItemDto, Integer> itemsOrdered) {
        setCustomer(customer);
        setDeliveryPerson(deliveryPerson);
        setItemsOrdered(itemsOrdered);
        setStatus("Placed");
        deliveryPerson.setAvailable(false);
    }

    public void completeOrder() {
        this.status = "Completed";
        deliveryPerson.setAvailable(true);
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
    
    public CustomerDto getCustomer() {
        return customer;
    }
    
	public void setDeliveryPerson(DeliveryPersonDto deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}
	
	public DeliveryPersonDto getDeliveryPerson() {
        return deliveryPerson;
    }

	public void setItemsOrdered(Map<FoodItemDto, Integer> itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}
	
    public Map<FoodItemDto, Integer> getItemsOrdered() {
        return itemsOrdered;
    }
    
    
}
