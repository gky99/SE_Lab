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

package Model;

import Model.account.*;
import Model.exceptions.IllegalInitialValueException;
import Model.exceptions.OverAgeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class Bank {

    public static Vector<Account> accounts;
    public static Vector<Manipulation> manipulations;
    public static Vector<Manipulation> suspended;
    private static int accountNumber = 1;

    static {
        Bank.accounts = new Vector<Account>();
        Bank.manipulations = new Vector<Manipulation>();
        Bank.suspended = new Vector<Manipulation>();

        try {
            accounts.add(new VirtualAccount(-1, "cash"));
            accounts.add(new VirtualAccount(-2, "cheque"));
        } catch (Exception e) {
            System.out.println("virtual account init error.");
        }
    }

    public Bank() {
    }

    /**
     * Parase string in formate "yyyy.mm.dd" into {@link Date}
     *
     * @param s input time string.
     * @throws ParseException thrown when the string is in illegal format.
     */
    public static Date parseDate(String s) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            throw new ParseException("Input can not be parsed to a Date", e.getErrorOffset());
        }
    }

    public static int clearFund() throws Exception {
        int i = 0;
        if (suspended.isEmpty()) {
            return i;
        }
        int j = 0;
        while (j < suspended.size()) {
            Manipulation temp = suspended.get(j);
            temp.confirm();
            if (temp.isChangeFlag()) {
                j++;
            } else {
                i++;
                suspended.remove(j);
            }
        }
        return i;
    }

    public static Account findAccountByID(int accountNumber) {
        for (Account account : Bank.accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    /**
     * @param PIN
     * @param name
     * @param money
     * @param birthday
     * @param address
     * @param accountType
     */
    public static Account openAccount(String PIN, String name, double money, Date birthday, String address, String accountType) throws OverAgeException, IllegalInitialValueException {
        Account temp = null;
        if (Account.checkCredit()) {
            if (accountType.equals("current account")) {
                temp = new CurrentAccount(accountNumber, PIN, birthday, name, address, money);
                Bank.accounts.add(temp);
            } else if (accountType.equals("junior account")) {
                if (JuniorAccount.checkAge(birthday)) {
                    temp = new JuniorAccount(accountNumber, PIN, birthday, name, address, money);
                    Bank.accounts.add(temp);
                } else {
                    throw new OverAgeException("Age over 16");
                }
            } else if (accountType.equals("saver account")) {
                temp = new SaverAccount(accountNumber, PIN, birthday, name, address, money);
                Bank.accounts.add(temp);
            }
            accountNumber++;
        }
        return temp;
    }

}