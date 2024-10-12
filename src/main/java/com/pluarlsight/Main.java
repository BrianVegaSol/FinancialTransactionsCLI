package com.pluarlsight;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static boolean runHomeScreen = true;
    static boolean runLedgerScreen = true;
    static boolean runReportsMenu = true;
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

        //scan.close();
    }

    public static void firstTimeSwitch() {
        String homeInput = scan.nextLine();
        switch (Character.toUpperCase(homeInput.charAt(0))) {
            case 'X':
                System.out.println("Goodbye!");
                runHomeScreen = false;
                scan.close();
                break;
            case 'D':
                //method
                break;
            case 'P':
                //method
                break;
            case 'L':
                ledger();
                break;
            default:
                System.out.println("Invalid input, try again");
                //homeInput = scan.nextLine();
                break;
        }
    }

    public static void welcomeBackHomeMenu() {
        System.out.println("Welcome Back!\n" +
                "\nHome Screen\n" +
                "-----------\n" +
                "D) Add a deposit\n" +
                "P) Make a payment (debit)\n" +
                "L) Ledger\n" +
                "X) Exit");
        String backHomeInput = scan.nextLine();
        //while (runHomeScreen) { //loop is creating an issue where l from last input isnt being eaten so
        //need scanNext somewhere
        switch (Character.toUpperCase(backHomeInput.charAt(0))) {
            case 'X':
                System.out.println("Goodbye!");
                runHomeScreen = false;
                scan.close();
                break;
            case 'D':
                //method
                runHomeScreen = true;//Pre added, delete if issues
                break;
            case 'P':
                //methood
                runHomeScreen = true;//Pre added, delete if issues
                break;
            case 'L':
                ledger();
                runHomeScreen = true;
                break;
            default:
                System.out.println("Invalid input, try again");
                backHomeInput = scan.nextLine(); //Guess this isnt being read since the switch isnt in a loop?
                break;
        }
        //}
    }

    public static void ledger() {
        //runHomeScreen = false; // Is this necessary? Doesnt look like it is, tempted to delete this
        runLedgerScreen = true;
        isNotFirstTimeStartingApp = true;

        //Ledger Menu Options
        while (runLedgerScreen) {
            System.out.println("Welcome to the Ledger Menu\n" +
                    "\nInput one of the following to get started!\n" +
                    "A) All\n" +
                    "D) Deposits\n" +
                    "P) Payments\n" +
                    "R) Reports\n" +
                    "H) Home");
            String ledgerInput = scan.nextLine();
            switch (Character.toUpperCase(ledgerInput.charAt(0))) {
                case 'H': //Home Screen
                    runLedgerScreen = false;
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
                    //ledgerInput = scan.nextLine(); //OPS NEVER ADDED SCAN HERE, CAUSED AN INFINITE LOOP
            }

        }
    }
//

    public static void reports() {
        runReportsMenu = true;
        System.out.println("Welcome to the Reports Menu\n" +
                "\nInput one of the following to start searching!\n" +
                "1) Month To Date\n" +
                "2) Previous Month\n" +
                "3) Year To Date\n" +
                "4) Previous Year\n" +
                "5) Search By Vendor\n" +
                "0) Back\n" +
                "H) Home");
        while (runReportsMenu) {
            String reportStringInput = scan.nextLine();
            //Catch index errors for if()
            try {
                if (Character.toUpperCase(reportStringInput.charAt(0)) == 'H') {
                    runReportsMenu = false;
                    runLedgerScreen = false;
                    runHomeScreen = true;
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input, try again");
            }
            //catch NumberFormat errors for ()
            try {
                switch (Integer.parseInt(reportStringInput)) {
                    case 0:
                        runReportsMenu = false;
                        runLedgerScreen = true;
                        break;
                    case 1:
                        //method
                        //may need to turn a menu on/off
                        break;
                    case 2:
                        //method
                        //may need to turn a menu on/off
                        break;
                    case 3:
                        //method
                        //may need to turn a menu on/off
                        break;
                    case 4:
                        //method
                        //may need to turn a menu on/off
                        break;
                    case 5:
                        //method
                        //may need to turn a menu on/off
                        break;
                    default:
                        System.out.println("Invalid number, try again");
                        reportStringInput = scan.nextLine();
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input, try again");
            }

        }


    }


}