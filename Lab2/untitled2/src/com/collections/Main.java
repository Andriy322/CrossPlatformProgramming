package com.collections;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final TaskManager manager = new TaskManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter n: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    manager.task1(n);
                    break;
                case "2":
                    manager.task2();
                    break;
                case "3":
                    System.out.println("Enter min price: ");
                    int minPrice = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter max price: ");
                    int maxPrice = Integer.parseInt(scanner.nextLine());

                    manager.task3(minPrice,maxPrice);
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    return;
            }

        }
    }

    private static void printMenu() {
        System.out.println("1: 1 Task");
        System.out.println("2: 2 Task");
        System.out.println("3: 3 Task");
        System.out.println("4: Quit");
    }
}
