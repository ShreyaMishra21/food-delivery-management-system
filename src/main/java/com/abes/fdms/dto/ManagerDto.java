package com.abes.fdms.dto;
import com.abes.fdms.annotation.RoleCheck;

@RoleCheck(role = "Manager")
public class ManagerDto extends UserDto {
    private String password;

    public ManagerDto(String id, String name, String email, String phoneNumber, String password) {
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
        System.out.println("Manager ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber);
    }
}
