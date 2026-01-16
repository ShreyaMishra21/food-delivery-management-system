package com.abes.fdms.ui;

import com.abes.fdms.dto.ManagerDto;
import com.abes.fdms.dto.DeliveryPersonDto;
import com.abes.fdms.dto.OrderDto;
import com.abes.fdms.exception.DuplicateItemException;
import com.abes.fdms.exception.DuplicateDeliveryPersonException;
import com.abes.fdms.dto.CustomerDto;
import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.service.ManagerService;
import com.abes.fdms.service.DeliveryService;
import com.abes.fdms.service.OrderService;
import com.abes.fdms.service.CustomerService;
import com.abes.fdms.service.FoodService;
import com.abes.fdms.util.InputValidationUtil;
import com.abes.fdms.util.OrderFormatterUtil;

import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class ManagerConsoleUi {
    private final ManagerService managerService = new ManagerService();
    private final DeliveryService deliveryService = new DeliveryService();
    private final OrderService orderService = new OrderService();
    private final CustomerService customerService = new CustomerService();
    private final FoodService foodService = new FoodService();

    public void showMenu() {
        Scanner sc = new Scanner(System.in);

        // Only login allowed for manager
        ManagerDto manager = null;
        while (manager == null) {
            System.out.println("Manager Login");
            System.out.print("Enter Manager ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            if (!InputValidationUtil.isValidPassword(password)) {
                System.out.println("Invalid password format. Password must be at least 6 characters and include 1 uppercase, 1 lowercase, 1 number, and 1 special character.");
                continue;
            }
            manager = managerService.loginManager(id, password);
            if (manager == null) {
                System.out.println("Invalid credentials. Try again.");
            }
        }
        System.out.println("======================================================================================================");
        System.out.println("                       --- Login successful. Welcome, " + manager.getId() + "! ---");

        while (true) {
            	System.out.println("======================================================================================================");
                System.out.println("--- Manager Menu ---");
                System.out.println("1. Add Food Item");
                System.out.println("2. Show Food Items");
                System.out.println("3. Restock Food Item");
                System.out.println("4. Remove Food Item");
                System.out.println("5. Show All Orders");
                System.out.println("6. Change Order Status");
                System.out.println("7. Show All Users");
                System.out.println("8. Register Delivery Person");
                System.out.println("9. Remove Delivery Person");
                System.out.println("10. Show All Delivery Persons");
                System.out.println("11. Logout");
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
                        String name;
                        do {
                            System.out.print("Enter Item Name: ");
                            name = sc.nextLine();
                            if (!InputValidationUtil.isValidName(name)) {
                                System.out.println("Item name cannot be numeric or empty.");
                            }
                        } while (!InputValidationUtil.isValidName(name));
                        double price = 0;
                        while (true) {
                            System.out.print("Enter Price: ");
                            String priceInput = sc.nextLine();
                            try {
                                price = Double.parseDouble(priceInput);
                                if (price <= 0) {
                                    System.out.println("Price must be positive.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid price. Please enter a numeric value.");
                            }
                        }
                        int qty = 0;
                        while (true) {
                            System.out.print("Enter Quantity: ");
                            String qtyInput = sc.nextLine();
                            try {
                                qty = Integer.parseInt(qtyInput);
                                if (qty < 0) {
                                    System.out.println("Quantity cannot be negative.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid quantity. Please enter a number.");
                            }
                        }
                        try {
                            foodService.addNewItem(name, price, qty);
                            System.out.println("Item added.");
                        } catch (DuplicateItemException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 2:
                        Map<FoodItemDto, Integer> menu = foodService.getMenu();
                        if (menu.isEmpty()) {
                            System.out.println("No food items found.");
                        } else {
                            System.out.println("--- Food Items ---");
                            menu.forEach((item, quantity) -> System.out.println(item + " | Quantity: " + quantity));
                        }
                        break;
                    case 3:
                        System.out.print("Enter Item Name: ");
                        String restockName = sc.nextLine();
                        FoodItemDto restockItem = foodService.getFoodItemByName(restockName);
                        if (restockItem == null) {
                            System.out.println("Item does not exist.");
                            break;
                        }
                        int restockQty = 0;
                        while (true) {
                            System.out.print("Enter Quantity: ");
                            String restockQtyInput = sc.nextLine();
                            try {
                                restockQty = Integer.parseInt(restockQtyInput);
                                if (restockQty < 0) {
                                    System.out.println("Quantity cannot be negative.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid quantity. Please enter a number.");
                            }
                        }
                        foodService.restockItem(restockName, restockQty);
                        System.out.println("Stock updated.");
                        break;
                    case 4:
                        System.out.print("Enter Food Item Name to remove: ");
                        String removeItem = sc.nextLine();
                        FoodItemDto itemToRemove = foodService.getFoodItemByName(removeItem);
                        if (itemToRemove == null) {
                            System.out.println("Item does not exist.");
                        } else {
                            foodService.removeItem(removeItem);
                            System.out.println("Item removed.");
                        }
                        break;
                    case 5:
                        if(orderService.getAllOrders().isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            System.out.println("--- All Orders ---");
                        }
                        for (OrderDto order : orderService.getAllOrders()) {
                            System.out.println(OrderFormatterUtil.formatOrderDetails(order) + "\n");
                        }
                        break;
                    case 6:
                        // Change Order Status
                        List<OrderDto> allOrders = orderService.getAllOrders();
                        if (allOrders.isEmpty()) {
                            System.out.println("No orders to update.");
                            break;
                        }
                        for (int i = 0; i < allOrders.size(); i++) {
                            System.out.println((i + 1) + ". " + OrderFormatterUtil.formatOrderDetails(allOrders.get(i)));
                        }
                        int orderIdx = -1;
                        while (true) {
                            System.out.print("Select order number to update status: ");
                            String idxInput = sc.nextLine();
                            try {
                                orderIdx = Integer.parseInt(idxInput) - 1;
                                if (orderIdx < 0 || orderIdx >= allOrders.size()) {
                                    System.out.println("Invalid order selection.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid order number.");
                            }
                        }
                        OrderDto selectedOrder = allOrders.get(orderIdx);
                        System.out.print("Enter new status (Preparing/Completed/Cancelled): ");
                        String newStatus = sc.nextLine();
                        if (newStatus.equalsIgnoreCase("Preparing")) {
                            selectedOrder.setStatus("Preparing");
                            System.out.println("Order status set to Preparing.");
                        } else if (newStatus.equalsIgnoreCase("Completed")) {
                            selectedOrder.completeOrder();
                            System.out.println("Order status set to Completed.");
                        } else if (newStatus.equalsIgnoreCase("Cancelled")) {
                            selectedOrder.setStatus("Cancelled");
                            Map<FoodItemDto,Integer> m = selectedOrder.getItemsOrdered();
                            for(Map.Entry<FoodItemDto, Integer> entry:m.entrySet()) {
                            	FoodItemDto foodItem = entry.getKey();
                            	int q=entry.getValue();
                            	foodService.restockItem(foodItem.getName(), q);
                            }
                              
                            DeliveryPersonDto deliveryPerson = selectedOrder.getDeliveryPerson();
                            if (deliveryPerson != null) {
                                deliveryPerson.setAvailable(true);
                            }
                            System.out.println("Order status set to Cancelled.");
                            
                        } 
                        else {
                            System.out.println("Invalid status.");
                        }
                        break;
                    case 7:
                        System.out.println("--- All Customers ---");
                        Map<String, CustomerDto> customers = customerService.getAllCustomers();
                        if (customers.isEmpty()) {
                            System.out.println("No customers found.");
                        } else {
                            customers.values().forEach(c -> System.out.println("Customer ID: " + c.getId() + ", Name: " + c.getName() + ", Email: " + c.getEmail() + ", Phone: " + c.getPhoneNumber()));
                        }
                        break;
                    case 8:
                        String dpId;
                        do {
                            System.out.print("Enter Delivery Person ID: ");
                            dpId = sc.nextLine();
                            if (dpId.trim().isEmpty()) {
                                System.out.println("ID cannot be empty.");
                            }
                        } while (dpId.trim().isEmpty());
                        String dpName;
                        do {
                            System.out.print("Enter Delivery Person Name: ");
                            dpName = sc.nextLine();
                            if (!InputValidationUtil.isValidName(dpName)) {
                                System.out.println("Name cannot be numeric or empty.");
                            }
                        } while (!InputValidationUtil.isValidName(dpName));
                        String dpEmail;
                        do {
                            System.out.print("Enter Delivery Person Email: ");
                            dpEmail = sc.nextLine();
                            if (!InputValidationUtil.isValidEmail(dpEmail)) {
                                System.out.println("Invalid email format.");
                            }
                        } while (!InputValidationUtil.isValidEmail(dpEmail));
                        String dpPhone;
                        do {
                            System.out.print("Enter Delivery Person Phone: ");
                            dpPhone = sc.nextLine();
                            if (!InputValidationUtil.isValidPhoneNumber(dpPhone)) {
                                System.out.println("Invalid phone number format.");
                            }
                        } while (!InputValidationUtil.isValidPhoneNumber(dpPhone));
                        try {
                            deliveryService.registerDeliveryPerson(dpId, dpName, dpEmail, dpPhone);
                            System.out.println("Delivery person registered.");
                        } catch (DuplicateDeliveryPersonException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 9:
                        System.out.print("Enter Delivery Person ID to remove: ");
                        String removeDpId = sc.nextLine();
                        DeliveryPersonDto dpToRemove = deliveryService.getAllDeliveryPersons().get(removeDpId);
                        if (dpToRemove == null) {
                            System.out.println("Delivery person does not exist.");
                        } else {
                            deliveryService.removeDeliveryPerson(removeDpId);
                            System.out.println("Delivery person removed.");
                        }
                        break;
                    case 10:
                        Map<String, DeliveryPersonDto> dps = deliveryService.getAllDeliveryPersons();
                        if (dps.isEmpty()) {
                            System.out.println("No delivery persons found.");
                        } else {
                            dps.values().forEach(dp -> System.out.println(dp.getId() + " - " + dp.getName() + " (Email: " + dp.getEmail() + ", Phone: " + dp.getPhoneNumber() + ", Available: " + dp.isAvailable() + ")"));
                        }
                        break;
                    case 11:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
        }
    }
}