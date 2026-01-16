package com.abes.fdms.dto;

public abstract class UserDto {
    protected String id;
    protected String name;
    protected String email;
    protected String phoneNumber;

    public UserDto(String id, String name, String email, String phoneNumber) {
    	setId(id);
    	setName(name);
    	setEmail(email);
    	setPhoneNumber(phoneNumber);
    }

    public abstract void showProfile();
    
    
    public void setId(String id) {
		this.id = id;
	}
    public String getId() {
        return id;
    }
    
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
        return name;
    }

	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
        return email;
    }

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
