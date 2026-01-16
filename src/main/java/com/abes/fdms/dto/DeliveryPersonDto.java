package com.abes.fdms.dto;

public class DeliveryPersonDto extends UserDto {
    private boolean available = true;

    public DeliveryPersonDto(String id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void showProfile() {
        System.out.println("Delivery Person ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber + ", Available: " + available);
    }
}
