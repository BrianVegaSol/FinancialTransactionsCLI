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

import static com.pluarlsight.Transaction.isNotFirstTimeStartingApp;
import static com.pluarlsight.Transaction.runHomeScreen;

//

public class FinancialTransactionsCLI {

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
                Transaction.welcomeBackHomeMenu();
            } else {
                Transaction.firstTimeSwitch();
            }
        }
    }


}
