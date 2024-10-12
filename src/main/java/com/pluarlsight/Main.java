package com.pluarlsight;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static boolean runHomeScreen = true;
    static boolean runLedgerScreen = true;
    static boolean runReportsMenu = true;
    static boolean isNotFirstTimeStartingApp = false; //add to all Home menu options

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
                break;
            default:
                System.out.println("Invalid input, try again");
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
        while (runHomeScreen) { //loop is creating an issue where l from last input isnt being eaten so
        //need scanNext somewhere
            //String backHomeInput = scan.nextLine();
            switch (Character.toUpperCase(backHomeInput.charAt(0))) {
                case 'X':
                    System.out.println("Goodbye!");
                    runHomeScreen = false;
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
                    /*if (scan.hasNextLine()) {
                        backHomeInput = "s";// Could work!
                    }*/
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    backHomeInput = scan.nextLine(); //Guess this isnt being read since the switch isnt in a loop?
                    break;
            }
        }
    }

    public static void ledger() {
        runLedgerScreen = true;
        runHomeScreen = false; //Probably wont work either
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
                    runHomeScreen = true;//probably not gonna work
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
                    ledgerInput = scan.nextLine();
            }

        }
    }


    public static void reports () {
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
                    runLedgerScreen = false;
                    runHomeScreen = false;
                }
            }
        }






    }


}