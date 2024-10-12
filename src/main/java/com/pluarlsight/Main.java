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
        //Home Menu Options
        while (runHomeScreen) {
            //Welcome Back message if coming back another menu
            if (isNotFirstTimeStartingApp) {
                welcomeBackHomeMenu();
            } else {
                firstTimeSwitch();
            }
        }

        scan.close();
    }

    public static void firstTimeSwitch() {
        String homeInput = scan.nextLine();
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
                runHomeScreen = true;//Unnccessary to turn off?
                break;
            default:
                System.out.println("Invalid input, try again");
        }
    }

    public static void welcomeBackHomeMenu() {
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
                runHomeScreen = true;//Unnccessary to turn off?
                break;
            default:
                System.out.println("Invalid input, try again");
        }
    }

    public static void ledger() {
        runHomeScreen = false;
        runLedgerScreen = true;
        isNotFirstTimeStartingApp = true;

        System.out.println("Welcome to the Ledger Menu\n" +
                "\nInput one of the following to get started!\n" +
                "A) All\n" +
                "D) Deposits\n" +
                "P) Payments\n" +
                "R) Reports\n" +
                "H) Home");
        String ledgerInput = scan.nextLine();
        //Ledger Menu Options
        while (runLedgerScreen) {
            switch (Character.toUpperCase(ledgerInput.charAt(0))) {
                case 'H': //Home Screen
                    runLedgerScreen = false;
                    welcomeBackHomeMenu(); //If ledger has issue, delete this // Dont do this,
                    // makes 2 Home Menu "instances", so need to close twice, just let it break and return to main class
                    break;
                case 'A':
                    //method
                    break;
                case 'D':
                    //method
                    break;
                case 'P':
                    //method
                    break;
                case 'R':
                    reports();
                    break;
                default:
                    System.out.println("Invalid input, try again");
            }

        }
    }


    public static void reports () {
        boolean runReportsMenu = true; //need to test if this is necessary to turn off
        System.out.println("Welcome to the Reports Menu\n" +
                "\nInput one of the following to start searching!\n" +
                "1) All\n" +
                "2) Deposits\n" +
                "3) Payments\n" +
                "4) Reports\n" +
                "5) Home\n" +
                "0) Back\n" +
                "H) Home");
        //Integer.parseInt?   if hasNext else?
        while (runReportsMenu) {
            if (scan.hasNextLine()) {
                String reportsInput = scan.nextLine();
                if (Character.toUpperCase(reportsInput.charAt(0)) == 'H') {
                    runReportsMenu = false;
                    welcomeBackHomeMenu();
                }
            }
        }






    }


}