package com.abes.fdms.ui;

import com.abes.fdms.dto.CustomerDto;
import com.abes.fdms.dto.OrderDto;
import com.abes.fdms.exception.*;
import com.abes.fdms.service.CustomerService;
import com.abes.fdms.service.FoodService;
import com.abes.fdms.service.OrderService;
import com.abes.fdms.util.InputValidationUtil;
import com.abes.fdms.util.OrderFormatterUtil;
import java.util.*;

public class CustomerConsoleUi {
    private final CustomerService customerService = new CustomerService();
    private final FoodService foodService = new FoodService();
    private final OrderService orderService = new OrderService();

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
        	System.out.println("======================================================================================================");
            System.out.println("1. Sign Up\n2. Log In\n3. Back");
            System.out.println("======================================================================================================");
            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }
            switch (choice) {
                case 1:
                    String id;
                    do {
                        System.out.print("Enter ID: ");
                        id = sc.nextLine();
                        if (id.trim().isEmpty()) {
                            System.out.println("ID cannot be empty.");
                        }
                    } while (id.trim().isEmpty());
                    String name;
                    do {
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();
                        if (!InputValidationUtil.isValidName(name)) {
                            System.out.println("Name cannot be numeric or empty.");
                        }
                    } while (!InputValidationUtil.isValidName(name));
                    String email;
                    do {
                        System.out.print("Enter Email: ");
                        email = sc.nextLine();
                        if (!InputValidationUtil.isValidEmail(email)) {
                            System.out.println("Invalid email format.");
                        }
                    } while (!InputValidationUtil.isValidEmail(email));
                    String phone;
                    do {
                        System.out.print("Enter Phone Number: ");
                        phone = sc.nextLine();
                        if (!InputValidationUtil.isValidPhoneNumber(phone)) {
                            System.out.println("Invalid phone number format.");
                        }
                    } while (!InputValidationUtil.isValidPhoneNumber(phone));
                    String password;
                    do {
                        System.out.print("Enter Password: ");
                        password = sc.nextLine();
                        if (!InputValidationUtil.isValidPassword(password)) {
                            System.out.println("Password must be at least 6 characters and include 1 uppercase, 1 lowercase, 1 number, and 1 special character.");
                        }
                    } while (!InputValidationUtil.isValidPassword(password));
                    if (customerService.registerCustomer(id, name, email, phone, password)) {
                        System.out.println("Sign up successful.");
                    } else {
                        System.out.println("ID, Email, or Phone number already exists.");
                    }
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Password: ");
                    password = sc.nextLine();
                    if (!InputValidationUtil.isValidPassword(password)) {
                        System.out.println("Invalid password format.");
                        break;
                    }
                    CustomerDto customer = customerService.loginCustomer(id, password);
                    if (customer != null) {
                    	System.out.println("Log-in Successful");
                        handleLoggedInCustomer(customer, sc);
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void handleLoggedInCustomer(CustomerDto customer, Scanner sc) {
        while (true) {
        	
        	System.out.println("======================================================================================================");
            System.out.println("1. Show Menu\n2. Place Order\n3. Show My Orders\n4. Logout");
            System.out.println("======================================================================================================");
            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }
            switch (choice) {
                case 1:
                    foodService.getMenu().forEach((item, q) -> System.out.println(item + " - Qty: " + q));
                    break;
                case 2:
                    Map<String, Integer> orderMap = new HashMap<>();
                    while (true) {
                        System.out.print("Enter Item Name (or 'done'): ");
                        String itemName = sc.nextLine().trim();
                        if (itemName.equalsIgnoreCase("done")) break;
                        if (!InputValidationUtil.isValidName(itemName)) {
                            System.out.println("Item name cannot be numeric or empty.");
                            continue;
                        }
                        System.out.print("Enter Quantity: ");
                        String qtyInput = sc.nextLine().trim();
                        if (qtyInput.isEmpty()) {
                            System.out.println("Quantity cannot be empty. Please enter a valid quantity.");
                            continue;
                        }
                        int qty;
                        try {
                            qty = Integer.parseInt(qtyInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid quantity. Please enter a number.");
                            continue;
                        }
                        if (qty <= 0) {
                            System.out.println("Quantity must be greater than zero.");
                            continue;
                        }
                        orderMap.put(itemName, qty);
                    }
                    if (orderMap.isEmpty()) {
                        System.out.println("Order must contain at least one item with quantity.");
                        break;
                    }
                    try {
                        OrderDto order = orderService.placeOrder(customer, orderMap);
                        System.out.println("Order placed successfully.");
                        System.out.println(OrderFormatterUtil.formatOrderDetails(order));
                    } catch (InvalidQuantityException e) {
                        System.out.println("Invalid quantity: " + e.getMessage());
                    } catch (ItemNotFoundException e) {
                        System.out.println("Item not found: " + e.getMessage());
                    } catch (DeliveryPersonUnavailableException e) {
                        System.out.println("No delivery person available: " + e.getMessage());
                    } catch (InvalidOrderException e) {
                        System.out.println("Order error: " + e.getMessage());
                    }
                    break;
                case 3:
                    List<OrderDto> orders = orderService.getOrdersByCustomer(customer.getId());
                    if(orders.isEmpty()|| orders == null) {
                    	System.out.println("--- No Orders ---");
                    }
                    for (OrderDto o : orders) {
                        System.out.println(OrderFormatterUtil.formatOrderDetails(o) + "\n");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
