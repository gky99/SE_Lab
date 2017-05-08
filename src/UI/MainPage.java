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
import Model.account.Account;
import Model.exceptions.AccountNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class MainPage {
    private Account account = null;
    private String piy = "Please input your ";


    public static String readLine() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public static String stringInput(String prompt) {
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


    public static int optionChoose(String[] options) {
        int temp;
        int i = 0;
        while (i < options.length) {
            options[i] = "Input " + (i + 1) + " to " + options[i];
            i++;
        }

        while (true) {
            temp = integerInput(options);

            if (temp < options.length + 1 && temp > 0) {
                return temp;
            } else {
                System.out.println("Illegal input. Please try again.");
            }
        }
    }

    public static int integerInput(String prompt) {
        int temp;
        do {
            try {
                System.out.println(prompt);
                temp = Integer.parseInt(readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Illegal acconut number format.");
            }
        } while (true);
        return temp;
    }

    public static int integerInput(String[] prompt) {
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
                System.out.println("Illegal acconut number format.");
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
            accountNumber = integerInput(piy + "account number:");

//            Get PIN
            PIN = stringInput(piy + "PIN:");

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
            selection = optionChoose(options);

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
        String PIN;
        String name;
        String address;
        double money;
        String accountType;
        Date birthday;
        while (true) {
            try {
                System.out.println("Please input your account number:");
                PIN = readLine();
                if (PIN != null) {
                    break;
                } else {

                }
            } catch (NumberFormatException e) {
                System.out.println("Illegal acconut number format.");
            }
        }
        return false;
    }

    /**
     * @param amount
     */
    public boolean draw(double amount) {
        // TODO - implement MainFrame.draw
        throw new UnsupportedOperationException();
    }

    /**
     * @param amount
     */
    public boolean instanceDeposit(double amount) {
        // TODO - implement MainFrame.instanceDeposit
        throw new UnsupportedOperationException();
    }

    /**
     * @param amount
     */
    public boolean suspendDeposit(double amount) {
        // TODO - implement MainFrame.suspendDeposit
        throw new UnsupportedOperationException();
    }

    public boolean suspend() {
        // TODO - implement MainFrame.suspend
        throw new UnsupportedOperationException();
    }

    /**
     * @param amount
     * @param time
     */
    public void subscribe(double amount, Date time) {
        // TODO - implement MainFrame.subscribe
        throw new UnsupportedOperationException();
    }

    public void actions() {
        // TODO - implement MainFrame.actions
        throw new UnsupportedOperationException();
    }

    /**
     * @param amount
     * @param accountNumber
     */
    public boolean transfer(double amount, int accountNumber) {
        // TODO - implement MainFrame.transfer
        throw new UnsupportedOperationException();
    }
}