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

import Model.account.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class Bank {

    private int accountNumber = 1;
    private Vector<Account> accounts;
    private int minSubscribeTime;
    private Vector<manipulation> suspended;

    public int clearFund() {
        // TODO - implement Model.Bank.clearFund
        throw new UnsupportedOperationException();
    }

    /**
     * @param accountNumber
     * @param pin
     * @param amount
     */
    public int withdraw(int accountNumber, int pin, double amount) {
        // TODO - implement Model.Bank.withdraw
        throw new UnsupportedOperationException();
    }

    /**
     * @param accountNumber
     * @param amount
     */
    public int instanceDeposit(int accountNumber, double amount) {
        // TODO - implement Model.Bank.instanceDeposit
        throw new UnsupportedOperationException();
    }

    public int closeAccount() {
        // TODO - implement Model.Bank.closeAccount
        throw new UnsupportedOperationException();
    }

    /**
     * @param origin
     * @param destination
     * @param amount
     */
    public boolean transfer(int origin, int destination, double amount) {
        // TODO - implement Model.Bank.transfer
        throw new UnsupportedOperationException();
    }

    /**
     * @param money
     * @param name
     * @param address
     * @param birthday
     * @param accountType
     */
    public boolean openAccount(double money, String name, String address, Date birthday, String accountType) {
        // TODO - implement Model.Bank.openAccount
        throw new UnsupportedOperationException();
    }

    /**
     * @param accountNumber
     */
    public boolean changeSuspend(int accountNumber) {
        // TODO - implement Model.Bank.changeSuspend
        throw new UnsupportedOperationException();
    }

    /**
     * @param accountNumber
     * @param amount
     */
    public int suspendedDeposit(int accountNumber, double amount) {
        // TODO - implement Model.Bank.suspendedDeposit
        throw new UnsupportedOperationException();
    }


    public Date parseDate(String s) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            throw new ParseException("Input can not be parsed to a Date", e.getErrorOffset());
        }
    }
}