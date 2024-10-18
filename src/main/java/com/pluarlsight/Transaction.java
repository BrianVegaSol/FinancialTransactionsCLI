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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//import static com.pluarlsight.FinancialTransactionsCLI.splitValidation;

public class Transaction {
    //Vars
    static Scanner scan = new Scanner(System.in);
    static boolean runHomeScreen = true;
    static boolean runLedgerScreen = true;
    static boolean runReportsMenu = true;
    static boolean isNotFirstTimeStartingApp = false; //add to all menus
    static boolean isDepositEntry = false;
    static boolean isPaymentEntry = false;
    static boolean isNotDeposit = false;
    static boolean isLedgerAll = false;
    static boolean isLedgerDeposit = false;
    static boolean isLedgerPayment = false;
    static boolean isReportMonthDate = false;
    static boolean isReportPrevMonth = false;
    static boolean isReportYearDate = false;
    static boolean isReportVendor = false;
    static boolean isReportPrevYear = false;
    static boolean isReportCustomSearch = false;
    static ArrayList<Constructor> entries = new ArrayList<>();

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

    public static void reports() {
        runReportsMenu = true;
        while (runReportsMenu) {
            System.out.println("Welcome to the Reports Menu\n" +
                    "\nInput one of the following to start searching!\n" +
                    "1) Month To Date\n" +
                    "2) Previous Month\n" +
                    "3) Year To Date\n" +
                    "4) Previous Year\n" +
                    "5) Search By Vendor\n" +
                    "6) Custom Search\n" +
                    "0) Back\n" +
                    "H) Home");
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
                    case 6:
                        isReportCustomSearch = true;
                        fileReader();
                        isReportCustomSearch = false;
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
                if (isNotDeposit) {
                    System.out.println("Payment Data Added Successfully!");
                } else {
                    System.out.println("Deposit Data Added Successfully!");
                }
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
        String[] pipeSplit = userEntry.split("\\|");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String formDateTime = dateTime.format(formatter);
        double amount = 0;
        try {
            amount = Double.parseDouble(pipeSplit[2]);
            if (amount > 0 && type.equals("Payment")) {
                amount = amount * -1;
                String description = pipeSplit[0];
                String vendor = pipeSplit[1];
                amount = Double.parseDouble(pipeSplit[2]);
                String validationEntry = type + "|" + formDateTime + "|" + description + "|" + vendor + "|-" + amount;
                return validationEntry;
            }
            if (amount < 0 && type.equals("Deposit")) {
                System.out.println("Amount is negative\n" +
                        "Did you want to make a Payment instead? (y/n)");
                Scanner scanner = new Scanner(System.in);
                char response = scanner.next().charAt(0);
                if (Character.toUpperCase(response) == 'Y') {
                    type = "Payment";
                    isNotDeposit = true;
                } else {
                    amount = Math.abs(amount);
                }
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            System.err.println("Invalid input: " + amount + " must be a number" +
                    "\nEntry Failed, Returning to Main Menu");
            //add boolean and while loops?
            welcomeBackHomeMenu();
        }
        String validatedEntry = type + "|" + formDateTime + "|" + userEntry;
        return validatedEntry;
    }

    //Used for Ledger and Reports Display
    public static void fileReader() {
        String file = "transactions.csv";
        Constructor transactions = new Constructor();
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
                    LocalDate date = LocalDate.parse(pipeSplit[1]);
                    LocalTime time = LocalTime.parse(pipeSplit[2]);
                    String description = pipeSplit[3];
                    String vendor = pipeSplit[4];
                    double amount = Double.parseDouble(pipeSplit[5]);
                    transactions = new Constructor(type, date, time, description, vendor, amount);

                    entries.add(transactions);
                    if (isLedgerAll) {
                        all.add(line);
                    }


                } catch (NumberFormatException e) {
                    System.err.println("Invalid entry retrieved. Contact Admin to Review .csv\nReturning to Main Menu");
                }
            }

            //Custom Search
            //need to have if null for empty search filters
            //start and end should set()? then reuse some of previousYear() and adapt sorting()
            //may need to make the get.contains

            //potentially lots of && and  maybe ||

            if (isReportCustomSearch) {
                System.out.println("Welcome to the Custom Search Menu\n" +
                        "\nInput one of the following to start searching!\n" +
                        "Start Date");
                String startDate = scan.nextLine();
                //maybe having try catch here? for A L L scans???
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
                String formDateTime = dateTime.format(formatter);

                //LocalDateTime formStartDate = 2024-02-17 17:46:19;
                System.out.println("End Date");
                String endDate = scan.nextLine();
                LocalDateTime formEndDate = LocalDateTime.parse(endDate);
                System.out.println("Description");
                String descrip = scan.nextLine();
                System.out.println("Vendor");
                String vend = scan.nextLine();
                System.out.println("Amount");
                double amoun = scan.nextDouble();
                //need lots of if null then ??? but since Enter is \n then if "\n" or "" then ??
                //not!   ???
                if (startDate.isEmpty()) {
                    //formStartDate = LocalDateTime.parse(startDate);
                } else if (endDate.isEmpty()) {

                }
                else if (descrip.isEmpty()) {

                }
                else if (vend.isEmpty()) {

                }
                else if (amoun == 0) {
                }
                else {

                }

                //might need a lot/some ifs based on what is searched
                sortCustom(entries);
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println("Entry #" + (i + 1));
                    //System.out.println(entries.get(i).getDateTime().compareTo(formStartDate));
                }
                entries.clear();
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
            if (isReportMonthDate) {
                sortTransactionsByMonth(entries);
                for (int i = 0; i < entries.size(); i++) {
                    switch (entries.get(i).getDateTime().getMonthValue()) {
                        case 1:
                            System.out.println("January Entry");
                            break;
                        case 2:
                            System.out.println("February Entry");
                            break;
                        case 3:
                            System.out.println("March Entry");
                            break;
                        case 4:
                            System.out.println("April Entry");
                            break;
                        case 5:
                            System.out.println("May Entry");
                            break;
                        case 6:
                            System.out.println("June Entry");
                            break;
                        case 7:
                            System.out.println("July Entry");
                            break;
                        case 8:
                            System.out.println("August Entry");
                            break;
                        case 9:
                            System.out.println("September Entries");
                            break;
                        case 10:
                            System.out.println("October Entries");
                            break;
                        case 11:
                            System.out.println("November Entries");
                            break;
                        case 12:
                            System.out.println("December Entries");
                            break;

                    }
                    System.out.println(entries.get(i) + "\n");
                }
                entries.clear();
            }

            //Refuses to be called LOL THERE'S NO PREV MONTH ENTRY TO DISPLAY THIS WORKS PERFECTLY!!!
            if (isReportPrevMonth) {
                sortTransactionsByMonth(entries);
                for (int i = 0; i < entries.size(); i++) {
                    int prevMonth = LocalDateTime.now().getMonthValue() - 1;
                    if (entries.get(i).getDateTime().getMonthValue() == prevMonth) {
                        if (entries.get(i).getDateTime().getYear() == LocalDate.now().getYear()) {
                            LocalDate lastMonth = LocalDate.now().minusMonths(1);
                            String lastMonthName = lastMonth.getMonth().name();
                            System.out.println(lastMonthName + " Entries");
                            System.out.println(entries.get(i) + "\n");
                        }
                    }
                }
                entries.clear();
            }


            if (isReportYearDate) {
                sortTransactionsByAll(entries);
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println("Entry #" + (i + 1));
                    System.out.println(entries.get(i));
                }
                entries.clear();
            }


            if (isReportPrevYear) {
                for (int i = 0; i < entries.size(); i++) {
                    if (entries.get(i).getDateTime().getYear() == (LocalDateTime.now().getYear() - 1)) {
                        System.out.println("Entry #" + (i + 1));
                        System.out.println(entries.get(i));
                    }
                }
                entries.clear();
            }


            if (isReportVendor) {
                sortTransactionsByVendor(entries);
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println("Entry #" + (i + 1));
                    System.out.println(entries.get(i));
                }
                entries.clear();
            }

        } catch(
                IOException e)

        {
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
    public static void sortTransactionsByAll(List<Constructor> transactions) {
        transactions.sort((line1, line2) -> line2.getDateTime().compareTo(line1.getDateTime()));
    }

    public static void sortTransactionsByMonth(List<Constructor> transactions) {
        transactions.sort(Comparator.comparing(line -> line.getDateTime().getMonth()));
    }

    public static void sortTransactionsByVendor(List<Constructor> transactions) {
        transactions.sort((line1, line2) -> line1.getVendor().compareTo(line2.getVendor()));
    }


    // Custom Sorting
    public static void sortCustom(ArrayList<Constructor> transactions) {
        transactions.sort((Comparator<? super Constructor>) Comparator.comparing(Constructor::getDate).thenComparing(Constructor::getAmount));
    }





    /*public static void sortTransactionsByAll(List<FinancialTransactionsCLI> transactions) {
        transactions.sort((line1, line2) -> line2.getDate().compareTo(line1.getDate()));
    }*/
}
