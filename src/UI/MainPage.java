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

import Model.account.Account;

import java.util.Date;

public class MainPage {

    private Account account;

    /**
     * @param accountNumber
     * @param PIN
     */
    public boolean login(int accountNumber, int PIN) {
        // TODO - implement MainFrame.login
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
        // TODO - implement MainFrame.openAccount
        throw new UnsupportedOperationException();
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