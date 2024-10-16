package com.pluarlsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

//

public class FinancialTransactionsCLI {
    private String type;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime dateTime;
    private String description;
    private String vendor;
    private double amount;
    private LocalDate timeStamp;

    static ArrayList<FinancialTransactionsCLI> entries = new ArrayList<>();

    public FinancialTransactionsCLI() {
        this.type = type;
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public FinancialTransactionsCLI(String type, LocalDateTime dateTime, String description,
                                    String vendor, double amount) {
        this.type = type;
        this.dateTime = dateTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public FinancialTransactionsCLI(String type, LocalDate date, LocalTime time, String description,
                                    String vendor, double amount) {
        this.type = type;
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        //this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return (type + "|" + date + "|" + time + "|" + description + "|" +
                vendor + "|" + amount);
    }

    public void addEntries(FinancialTransactionsCLI objectName) {
        /*String userData = toString(setAmount(amount);
        entries.add(objectName.setType(vendor) + "|" + objectName.setDate(date) + "|" + objectName.setType(time + "|" +
                objectName.setDescription(description) + "|" + objectName.setVendor(vendor) + "|" +
                objectName.setAmount(amount) + "|" + objectName.setTimeStamp(timeStamp));
        */
    }
    //make toString, then convert to Data types?


    static Scanner scan = new Scanner(System.in);
    static boolean runHomeScreen = true;
    static boolean runLedgerScreen = true;
    static boolean runReportsMenu = true;
    static boolean isNotFirstTimeStartingApp = false; //add to all menus
    static boolean isDepositEntry = false;
    static boolean isPaymentEntry = false;
    static boolean isLedgerAll = false;
    static boolean isLedgerDeposit = false;
    static boolean isLedgerPayment = false;
    static boolean isReportMonthDate = false;
    static boolean isReportPrevMonth = false;
    static boolean isReportYearDate = false;
    static boolean isReportVendor = false;
    static boolean isReportPrevYear = false;


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
        //Pre Instantiating Object //Delete if issues
        FinancialTransactionsCLI obj = new FinancialTransactionsCLI();
        //Home Menu Options
        while (runHomeScreen) {
            //Welcome Back message if coming back another menu
            if (isNotFirstTimeStartingApp) {
                welcomeBackHomeMenu();
            } else {
                firstTimeSwitch();
            }
        }
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
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public static void welcomeBackHomeMenu() {
        while (runHomeScreen) {
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
                    break;
            }
        }
    }

    public static void ledger() {
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
                    isLedgerAll = true;
                    fileReader();
                    isLedgerAll = false;
                    break;
                case 'D':
                    isLedgerDeposit = true;
                    fileReader();
                    isLedgerDeposit = false;
                    break;
                case 'P':
                    isLedgerPayment = true;
                    fileReader();
                    isLedgerPayment = false;
                    break;
                case 'R':
                    reports();
                    break;
                default:
                    System.out.println("Invalid input, try again");
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
                        isReportMonthDate = true;
                        fileReader();
                        isReportMonthDate = false;
                        //All months
                        break;
                    case 2:
                        isReportPrevMonth = true;
                        fileReader();
                        isReportPrevMonth = false;
                        //just last month
                        break;
                    case 3:
                        isReportYearDate = true;
                        fileReader();
                        isReportYearDate = false;
                        //all entries but by year
                        break;
                    case 4:
                        isReportPrevYear = true;
                        fileReader();
                        isReportPrevYear = false;
                        //only previous year
                        break;
                    case 5:
                        isReportVendor = true;
                        fileReader();
                        isReportVendor = false;
                        //filter a-z by vendor
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
    //Used for Writing to .csv
    //TODO Work on cleaning up validation
    public static void fileWriter() {
        isNotFirstTimeStartingApp = true;
        String file = "transactions.csv";

        if (isDepositEntry) {
            String type = "Deposit";
            System.out.println("Enter your Deposit data in the following format: \n" +
                    "description|vendor|amount");
            String userData = scan.nextLine();
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(splitValidation(userData, type) + "\n");
                System.out.println("Deposit Data Added Successfully!");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (isPaymentEntry) {
            String type = "Payment";
            System.out.println("Enter your Payment data in the following format: \n" +
                    "description|vendor|amount");
            String userData = scan.nextLine();
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(splitValidation(userData, type) + "\n");
                System.out.println("Payment Data Added Successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String splitValidation(String userEntry, String type) {
        //add a while until user adds valid data?
        //TODO Add if amount < 0 ask if wanted to do Payment (y/n) and fix if wanted Depo
        //TODO Make Payments with negative value abs(value) if type.equals "Payment" then abs(amount)
        //TODO do while {Continue
        String[] pipeSplit = userEntry.split("\\|");
        //
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = time.format(formatter);
        double amount = 0;
        try {
            amount = Double.parseDouble(pipeSplit[4]);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.err.println("Invalid input: " + amount + " must be a number" +
                    "\nEntry Failed, Returning to Main Menu");
            //add boolean and while loops?
            welcomeBackHomeMenu();
        }
        String validatedEntry = type + "|" + date + "|" + timeNow + "|" + "|" + userEntry;
        return validatedEntry;
    }
    //Used for Ledger and Reports Display
    public static void fileReader() {
        //TODO Make ArrayList
        String file = "transactions.csv";
        FinancialTransactionsCLI transactions = new FinancialTransactionsCLI();
        ArrayList<String> all = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String[] pipeSplit;
            int entryCounter = 0;

            while ((line = reader.readLine()) != null) {
                entryCounter++;
                pipeSplit = line.split("\\|");
                try {
                    //TODO Need to add this to Description
                    String type = pipeSplit[0];
                    //TODO Merge into 1 DateTime and then split here
                    LocalDate date = LocalDate.parse(pipeSplit[1]);
                    LocalTime time = LocalTime.parse(pipeSplit[2]);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime timeNow = LocalTime.parse(time.format(formatter));
                    //LocalDate entryTimeStamp = LocalDate.now();//Do I need a timestamp of when the entry was created???
                    String description = pipeSplit[3];
                    String vendor = pipeSplit[4];
                    double amount = Double.parseDouble(pipeSplit[5]);
                    transactions = new FinancialTransactionsCLI(type, date, timeNow, description, vendor, amount);
                    entries.add(transactions);
                    if (isLedgerAll) {
                        all.add(line);
                    }


                } catch (NumberFormatException e) {
                    System.err.println("Invalid entry retrieved. Contact Admin to Review .csv\nReturning to Main Menu");
                }
            }

            if (isLedgerAll) {
                for (int i = all.size() - 1; i > -1; i--) {
                    System.out.println("Entry #" + (all.size() - i));
                    System.out.println(all.get(i));
                }
                    entries.clear();
            }

            if (isLedgerDeposit) {
                sortTransactionsByAll(entries);
                for (int i = 0; i < entries.size(); i++) {
                    if (entries.get(i).getType().equals("Deposit")) {
                        System.out.println("Entry #" + (i + 1));
                        System.out.println(entries.get(i));
                    }

                }
                entries.clear();
            }

            if (isLedgerPayment) {
                sortTransactionsByAll(entries);
                for (int i = 0; i < entries.size(); i++) {
                    if (entries.get(i).getType().equals("Payment")) {
                        System.out.println("Entry #" + (i + 1));
                        System.out.println(entries.get(i));
                    }

                }
                entries.clear();
            }
            //TODO Need to merge Date and Time from here on out
            if (isReportMonthDate){
                sortTransactionsByAll(entries);
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println("Entry #" + (i + 1));
                    System.out.println(entries.get(i));
                }
                entries.clear();
            }


            if (isReportPrevMonth){

            }

            if (isReportYearDate) {
                sortTransactionsByAll(entries);
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println("Entry #" + (i + 1));
                    System.out.println(entries.get(i));
                }
                entries.clear();
            }

            if (isReportPrevYear){

            }

            if (isReportVendor) {

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Generic example
    /*public class DateComparator implements Comparator<FinancialTransactionsCLI> {
        @Override
        public int compare(FinancialTransactionsCLI t1, FinancialTransactionsCLI t2) {
            return t1.getDate().compareTo(t2.getDate()); // Ascending order } }
        }
    }*/

    //Generic for sorting
    //TODO Make time and date the same
    public static void sortTransactionsByAll(List<FinancialTransactionsCLI> transactions) {
        transactions.sort((line1, line2) -> line2.getDate().compareTo(line1.getDate()));
    }
}
