package com.abes.fdms.dto;

public class CustomerDto extends UserDto {
    private String password;

    public CustomerDto(String id, String name, String email, String phoneNumber, String password) {
        super(id, name, email, phoneNumber);
        setPassword(password);
    }
    
    public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
        return password;
    }

    @Override
    public void showProfile() {
        System.out.println("Customer ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber);
    }
}
