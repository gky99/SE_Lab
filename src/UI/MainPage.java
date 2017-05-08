/*
 * Copyright (c) 2017, Pauli Guan.
 *
 * Licensed under the General Public License, Version 2.0.
 * You may not use this file except in compliance with the Licese.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl.txt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 */

package UI;

import Model.Bank;
import Model.Manipulation;
import Model.account.Account;
import Model.account.SaverAccount;
import Model.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class MainPage {
    public Account account = null;
    private String piy = "Please input your ";


    public static String readLine() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public static String inputString(String prompt) {
        String temp;
        do {
            System.out.println(prompt);
            temp = readLine();
            if (temp != null) {
                break;
            } else {
                System.out.println("Input stream error.");
            }
        } while (true);
        return temp;
    }

    public static double inputDouble(String prompt, String parseErrorMessage) {
        double temp;
        do {
            try {
                System.out.println(prompt);
                temp = Double.parseDouble(readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(parseErrorMessage);
                System.out.println(" Please try again.");
            }
        } while (true);
        return temp;
    }

    public static double inputDouble(String prompt) {
        return inputDouble(prompt, "Illegal input format.");
    }

    public static Date inputDate(String prompt) {
        Date temp;
        do {
            try {
                System.out.println(prompt);
                System.out.println("Date should be in format \"YYYY.MM.DD\".");
                temp = Bank.parseDate(readLine());
                break;
            } catch (Exception e) {
                System.out.print(e.getMessage());
                System.out.println(" Please try again.");
            }
        } while (true);
        return temp;
    }

    public static int inputOptionChoose(String[] options) {
        int temp;
        int i = 0;
        while (i < options.length) {
            options[i] = "Input " + (i + 1) + " to " + options[i];
            i++;
        }

        while (true) {
            temp = inputInt(options);

            if (temp < options.length + 1 && temp > 0) {
                return temp;
            } else {
                System.out.print("Illegal input.");
                System.out.println(" Please try again.");
            }
        }
    }

    public static int inputInt(String prompt, String parseErrorMessage) {
        int temp;
        do {
            try {
                System.out.println(prompt);
                temp = Integer.parseInt(readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print(parseErrorMessage);
                System.out.println(" Please try again.");
            }
        } while (true);
        return temp;
    }

    public static int inputInt(String prompt) {
        return inputInt(prompt, "Illegal input format.");
    }

    public static int inputInt(String[] prompt) {
        int temp;
        do {
            try {
                int i = 0;
                while (i < prompt.length) {
                    System.out.println(prompt[i++]);
                }
                temp = Integer.parseInt(readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Illegal input format.");
                System.out.println(" Please try again.");
            }
        } while (true);
        return temp;
    }

    /**
     *
     */
    public boolean login() {
        int accountNumber;
        String PIN;

        while (true) {
//            Get account number.
            accountNumber = inputInt(piy + "account number:");

//            Get PIN
            PIN = inputString(piy + "PIN:");

//            Try to find the account.
            Account temp = Bank.findAccountByID(accountNumber);
//            Check whether the pin is right.
            if (temp != null && temp.getPIN().equals(PIN)) {
                this.account = temp;
                return true;
            } else {
                System.out.println("Wrong account number or PIN.");
            }

            int selection;
            String[] options = {"try again.", "exit."};
            selection = inputOptionChoose(options);

            if (selection == 1) {

            } else if (selection == 2) {
                return false;
            }
        }
    }

    /**
     *
     */
    public boolean openAccount() {
        String accountType;
        String PIN;
        String name;
        String address;
        double money;
        Date birthday;


        int temp = 0;
        String[] options = {
                "current account",
                "junior account",
                "saver account"
        };
        String[] tempOps = new String[3];
        for (int i = 0; i < options.length; i++) {
            tempOps[i] = "open a " + options[i] + ".";
        }
        temp = inputOptionChoose(tempOps);
        accountType = options[temp - 1];

        PIN = inputString(piy + "PIN.");
        name = inputString(piy + "name.");
        address = inputString(piy + "address.");
        money = inputDouble(piy + "money.");
        birthday = inputDate(piy + "birthday.");

        try {
            this.account = Bank.openAccount(PIN, name, money, birthday, address, accountType);
            return true;
        } catch (OverAgeException e) {
            System.out.println(e.getMessage());
        } catch (IllegalInitialValueException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     */
    public boolean draw() {
        double money;
        money = inputDouble("Please input the amount of money you want to draw:");
        Manipulation manipulation = new Manipulation(account, -1, money);

        return doManipulation(manipulation);
    }

    /**
     *
     */
    public boolean deposit() {
        double money;
        money = inputDouble("Please input the amount of money you deposit:");
        Manipulation manipulation = new Manipulation(-1, account, money);

        return doManipulation(manipulation);
    }

    private boolean doManipulation(Manipulation manipulation) {
        try {
            if (manipulation.confirm()) {
                System.out.println(manipulation.getResult());
                return true;
            } else {
                System.out.println(manipulation.getResult());
                return false;
            }
        } catch (IllegalTimeException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (UnchangeableException e) {
            System.out.println("Manipulation unable to be changed");
            return false;
        }
    }

    /**
     *
     */
    public boolean depositFromCheque() {
        double money;
        money = inputDouble("Please input the amount of money you deposit:");
        Manipulation manipulation = new Manipulation(-2, account, money);

        return doManipulation(manipulation);
    }

    public boolean suspend() {
        account.setSuspend();
        if (account.isSuspend()) {
            System.out.println("Your account is suspended.");
        } else {
            System.out.println("Your account is recovered from suspended.");
        }
        return account.isSuspend();
    }

    /**
     *
     */
    public boolean subscribe() {
        Date time;
        double amount;
        time = inputDate("Please input the date you want to withdraw your money:");
        amount = inputDouble("Please input the amount of money you want to withdraw:");
        Manipulation manipulation = new Manipulation(account, -1, amount, time);

        return doManipulation(manipulation);
    }

    public void actions() {
        while (true) {
            while (true) {
                String[] options = {
                        "log into your account.",
                        "open a new account.",
                        "exit."
                };
                int option = inputOptionChoose(options);
                if (option == 1) {
                    if (login())
                        break;
                } else if (option == 2) {
                    if (openAccount())
                        break;
                } else if (option == 3) {
                    System.exit(0);
                }
            }

            while (true) {
                if (account instanceof SaverAccount) {
                    String[] options = {
                            "draw.",
                            "deposit.",
                            "deposit from cheque.",
                            "suspend you account.",
                            "transfer.",
                            "subscribe.",
                            "exit."
                    };
                    boolean exitFlag = false;
                    int option = inputOptionChoose(options);
                    switch (option) {
                        case 1:
                            subscribedDraw((SaverAccount) account);
                            break;
                        case 2:
                            deposit();
                            break;
                        case 3:
                            depositFromCheque();
                            break;
                        case 4:
                            suspend();
                            break;
                        case 5:
                            transfer();
                            break;
                        case 6:
                            subscribe();
                            break;
                        case 7:
                            exitFlag = true;
                            break;
                    }
                    if (exitFlag) {
                        break;
                    }
                } else {
                    String[] options = {
                            "draw.",
                            "deposit.",
                            "deposit from cheque.",
                            "suspend you account.",
                            "transfer.",
                            "exit."
                    };
                    boolean exitFlag = false;
                    int option = inputOptionChoose(options);

                    switch (option) {
                        case 1:
                            draw();
                            break;
                        case 2:
                            deposit();
                            break;
                        case 3:
                            depositFromCheque();
                            break;
                        case 4:
                            suspend();
                            break;
                        case 5:
                            transfer();
                            break;
                        case 6:
                            exitFlag = true;
                            break;
                    }
                    if (exitFlag) {
                        break;
                    }
                }


            }
            account = null;
            try {
                Bank.clearFund();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean subscribedDraw(SaverAccount account) {
        int size = account.getSubscription().size();
        if (size == 0) {
            System.out.println("No subscription found.");
            return false;
        } else {
            String[] options = new String[size + 1];
            for (Manipulation temp : account.getSubscription()) {
                options[account.getSubscription().indexOf(temp)] = "choose subscription:\n" + temp.toString();
            }
            options[size] = "exit.";
            int temp = inputOptionChoose(options);
            if (temp == size + 1) {
                return false;
            }
            return doManipulation(account.getSubscription().get(temp - 1));
        }
    }

    public boolean closeAccount() {
        return account.closeAccount();
    }

    /**
     *
     */
    public boolean transfer() {
        int destination;
        double amount;
        destination = inputInt("Please input the target account number:");
        amount = inputDouble("Please input the amount of money you want to transfer:");
        Manipulation manipulation;
        try {
            manipulation = new Manipulation(account, destination, amount);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return doManipulation(manipulation);
    }
}