package com.scc;

import com.scc.dao.UsersDao;
import com.scc.model.Users;
import com.scc.ui.AdminMenu;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final UsersDao UsersDao = new UsersDao();

    public static void main(String[] args) {

        System.out.println("\n==================================");
        System.out.println(" Teachers Data Management");
        System.out.println("==================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                handleLogin();
                break;
            case "2":
                System.out.print("Enter new username: ");
                String user = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                System.out.print("Select Role [1] TEACHER | [2] ADMIN: ");
                int roleChoice = sc.nextInt();
                String role;
                switch (roleChoice) {
                    case 1:
                        role = "TEACHER";
                        break;
                    case 2:
                        role = "STUDENT";
                        break;
                    default:
                        role = null;
                }

                if (role != null) {
                    UsersDao.registerUser(user, pass, role);

                }else{

                    System.out.println("invalid role!");

                }
        }
    }

    private static void handleLogin() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Users user = UsersDao.login(username, password);

        if (user != null) {
            System.out.println("\n -> Welcome " + user.username() + " | ROLE: " + user.role()+"");
            switch (user.role()) {
                case "ADMIN" -> new AdminMenu(UsersDao, sc).display(user);
                default -> System.out.println("Account roles not found");
            }
        }



    }

}