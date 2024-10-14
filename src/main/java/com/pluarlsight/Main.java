package com.pluarlsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static boolean runHomeScreen = true;
    static boolean runLedgerScreen = true;
    static boolean runReportsMenu = true;
    static boolean isNotFirstTimeStartingApp = false; //add to all menus
    static boolean isDepositEntry = false;
    static boolean isPaymentEntry = false;

    static ArrayList<String> entries = new ArrayList<>();

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
        try {
            switch (Character.toUpperCase(homeInput.charAt(0))) {
                case 'X':
                    System.out.println("Goodbye!");
                    runHomeScreen = false;
                    scan.close();
                    break;
                case 'D':
                    isDepositEntry = true;
                    fileWriter();
                    isDepositEntry = false;
                    break;
                case 'P':
                    isPaymentEntry = true;
                    fileWriter();
                    isPaymentEntry = false;
                    break;
                case 'L':
                    ledger();
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    //homeInput = scan.nextLine();
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();//?? Dont wanna throw an error message everytime getting out of Depo()
        }
    }

    public static void welcomeBackHomeMenu() {
        //IF THIS WHILE LOOP BREAKS STUFF REVERT TO STABLE BUILD
        while (runHomeScreen) { //loop is creating an issue where l from last input isnt being eaten so
            System.out.println("Welcome Back!\n" +
                    "\nHome Screen\n" +
                    "-----------\n" +
                    "D) Add a deposit\n" +
                    "P) Make a payment (debit)\n" +
                    "L) Ledger\n" +
                    "X) Exit");
            String backHomeInput = scan.nextLine();
            switch (Character.toUpperCase(backHomeInput.charAt(0))) {
                case 'X':
                    System.out.println("Goodbye!");
                    runHomeScreen = false;
                    scan.close();
                    break;
                case 'D':
                    isDepositEntry = true;
                    fileWriter();
                    isDepositEntry = false;
                    break;
                case 'P':
                    isPaymentEntry = true;
                    fileWriter();
                    isPaymentEntry = false;
                    break;
                case 'L':
                    ledger();
                    runHomeScreen = true;
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    //backHomeInput = scan.nextLine(); //Guess this isnt being read since the switch isnt in a loop?
                    break;
            }
        }
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
                    fileReader();
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

    public static void fileWriter() {
        isNotFirstTimeStartingApp = true;
        String file = "transactions.csv";

        if (isDepositEntry) {
            System.out.println("Enter your Deposit data in the following format: \n" +
                    "date|time|description|vendor|amount");
            String userData = scan.nextLine(); //make static?
            //TODO split(); then setvars()
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write("Deposit|" + userData + "|Timestamp" + LocalDate.now() + "\n");
                //TODO Add try{splitVaalidation()}catch{NumFormat
                System.out.println("Deposit Data Added Successfully!");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (isPaymentEntry) {
            System.out.println("Enter your Payment data in the following format: \n" +
                    "date|time|description|vendor|amount");
            String userData = scan.nextLine();
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write("Payment|" + userData + "|Timestamp" + LocalDate.now() + "\n");
                System.out.println("Payment Data Added Successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void splitValidation() {//TODO Need Object to store the splits and use getvars()
        //TODO Make ArrayList here so there can be valiadation, maybe have an
        String line;
        String[] pipeSplit;
        try {
            //LocalDate date = LocalDate.parse(pipeSplit[1]);
           // LocalDate time = LocalDate.parse(pipeSplit[2]);
            LocalDate entryTimeStamp = LocalDate.now();//Do I need a timestamp of when the entry was created???
            //double amount = Double.parseDouble(pipeSplit[5]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: Amount must be a number");
        }


    }

    public static void fileReader() {
        //TODO Make ArrayList
        String file = "transactions.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String[] pipeSplit;
            int entryCounter = 0;

            while ((line = reader.readLine()) != null) {
                entryCounter++;
                pipeSplit = line.split("\\|");
                try {
                    LocalDate date = LocalDate.parse(pipeSplit[1]);
                    LocalDate time = LocalDate.parse(pipeSplit[2]);
                    LocalDate entryTimeStamp = LocalDate.now();//Do I need a timestamp of when the entry was created???
                    double amount = Double.parseDouble(pipeSplit[5]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input: Amount must be a number");
                }
                String description = pipeSplit[3];
                String vendor = pipeSplit[4];

               /* Cant use an array unless there is a limit to how many entries can be make to be able to reverse loop
               Need an ArrayList
                for (int i = ; i < ; i++) {
                //oof
                }*/

                System.out.println("Entry #" + entryCounter);

                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayDeposits() {
        //Pre added, delete if issues
        //if (1amount < 0) {sout(Did you want to make a Payment instead?)
        // if (yes) {take to payment() }

        //try catch for invalid responses
        //Use userData.split and make sure all splits have values?
        // try {if (userData.split[4] == null???)
        // } catch (IndexOOBE) { sout(Invalid try again)


        //confirm amount while (response == no){ loop }
        //then write to .csv
    }


}
