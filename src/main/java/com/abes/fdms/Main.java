package com.abes.fdms;

import java.util.Scanner;

import com.abes.fdms.ui.CustomerConsoleUi;
import com.abes.fdms.ui.ManagerConsoleUi;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ManagerConsoleUi managerUI = new ManagerConsoleUi();
        CustomerConsoleUi customerUI = new CustomerConsoleUi();
        System.out.println("======================================================================================================");
        System.out.println("                       --- Welcome to Online Food Delivery Management System ---");
        while (true) {
        	System.out.println("======================================================================================================");
            System.out.println("Select Role:\n1. Manager\n2. User\n3. Exit");
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
                    managerUI.showMenu();
                    break;
                case 2:
                    customerUI.showMenu();
                    break;
                case 3:
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
