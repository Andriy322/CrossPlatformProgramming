package com.company;
import java.util.Scanner;

public class Main {

    private static ILabManager lab3Manager;
    private static Scanner scanner;

    public static void main(String[] args) {

        lab3Manager = new LabManager();
        scanner = new Scanner(System.in);

        boolean continueLab = true;
        while (continueLab) {
            printMenu();
            continueLab = switchMenuOption();
        }
    }

    private static boolean switchMenuOption() {
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter n: ");
                int n = Integer.parseInt(scanner.nextLine());
                lab3Manager.firstTask(n);
                break;
            case "2":
                lab3Manager.secondTask();
                break;
            case "3":
                System.out.println("Enter min price: ");
                int minPrice = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter max price: ");
                int maxPrice = Integer.parseInt(scanner.nextLine());
                lab3Manager.thirdTask(minPrice, maxPrice);
                break;
            case "0":
                return false;
            default:
                return true;
        }
        return true;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1 - Task 1 ");
        System.out.println("2 - Task 2 ");
        System.out.println("3 - Task 3 ");
        System.out.println("0 - Quit");
    }
}
