package com.pluarlsight;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static boolean runHomeScreen = true;
    static boolean runLedgerScreen = true;
    static boolean isNotFirstTimeStartingApp = false; //add to all menus

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
        String homeInput = scan.nextLine();
        //Menu Options
        while (runHomeScreen) {
            //An if for coming back to the Home Screen show Welcome Back!
            if (isNotFirstTimeStartingApp) {
                welcomeBackHomeMenu();
            } else {
                switch (Character.toUpperCase(homeInput.charAt(0))) {
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
                        //scan.nextLine();//???
                        runHomeScreen = true;//Unnccessary to turn off?
                        //homeInput = scan.nextLine(); necessary?
                        break;
                    default:
                        System.out.println("Invalid input, try again");
                }

            }
        }

        scan.close();
    }

    public static void ledger() {
        runHomeScreen = false;
        isNotFirstTimeStartingApp = true;
        //runLedgerScreen = true; //May need to be after sout
        System.out.println("Welcome to the Ledger Menu\n" +
                "\nInput one of the following to get started!\n" +
                "A) All\n" +
                "D) Deposits\n" +
                "P) Payments\n" +
                "R) Reports\n" +
                "H) Home");
        String ledgerInput = scan.nextLine();
        //Menu Options
        while (runLedgerScreen) {
            switch (Character.toUpperCase(ledgerInput.charAt(0))) {
                case 'H': //Home Screen
                    runLedgerScreen = false;
                    //return;
                    //runHomeScreen = true;
                    break;
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

    public static void welcomeBackHomeMenu () {
        System.out.println("Welcome Back!\n" +
                "\nHome Screen\n" +
                "-----------\n" +
                "D) Add a deposit\n" +
                "P) Make a payment (debit)\n" +
                "L) Ledger\n" +
                "X) Exit\n");
        String backHomeInput = scan.nextLine();

        switch (Character.toUpperCase(backHomeInput.charAt(0))) {
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
                //scan.nextLine();//???
                runHomeScreen = true;//Unnccessary to turn off?
                backHomeInput = scan.nextLine();
                break;
            default:
                System.out.println("Invalid input, try again");
        }
    }
}