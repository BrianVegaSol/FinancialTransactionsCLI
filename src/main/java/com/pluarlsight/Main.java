package com.pluarlsight;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static boolean runHomeScreen = true;
    static boolean runLedgerScreen = true;

    public static void main(String[] args) {
        //Welcome Message
        System.out.println("Welcome to the Financial Transaction CLI!\n" +
                "Input one of the following to get started!\n" +
                "\nHome Screen\n" +
                "-----------\n" +
                "D) Add a deposit\n" +
                "P) Make a payment (debit)\n" +
                "L) Ledger\n" +
                "X) Exit\n");
        String input = scan.nextLine();
        //Menu Options
        while (runHomeScreen) {
            switch (Character.toUpperCase(input.charAt(0))) {
                case 'X':
                    System.out.println("Goodbye!");
                    runHomeScreen = false;
                    break;
                case 'D':
                    //method
                    break;
                case 'P':
                    //method
                    break;
                case 'L':
                    ledger();
                    scan.nextLine();//???
                    runHomeScreen = true;
                    break;
                default:
                    System.out.println("Invalid input, try again");
            }

        }

        scan.close();
    }

    public static void ledger() {
        runHomeScreen = false;
        //runLedgerScreen = true; //May need to be after sout
        System.out.println("Welcome to the Ledger Menu\n" +
                "\nInput one of the following to get started!\n" +
                "A) All\n" +
                "D) Deposits\n" +
                "P) Payments\n" +
                "R) Reports");
        String input = scan.nextLine();
        //Menu Options
        while (runLedgerScreen) {
            switch (Character.toUpperCase(input.charAt(0))) {
                case 'H': //Home Screen
                    runLedgerScreen = false;
                    return;
                    //runHomeScreen = true;
                    //break;
                case 'D':
                    //method
                    break;
                case 'P':
                    //method
                    break;
                case 'L':
                    //method
                    break;
                default:
                    System.out.println("Invalid input, try again");
            }

        }
        //run if else for HomeMenu method???
    }
}