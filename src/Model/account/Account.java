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

package Model.account;

import Model.Bank;
import Model.exceptions.AccountSuspendedException;
import Model.exceptions.IllegalInitialValueException;
import Model.exceptions.OverdraftException;

import java.util.Date;

public abstract class Account {

    private final int accountNumber;

    private double money = 0;
    private String name;
    private String PIN;
    private String address;
    private Date birthday;
    private boolean suspend;

    /**
     * @param PIN
     * @param name
     * @param address
     * @param birthday
     * @param money
     */
    public Account(int accountNumber, String PIN, String name, String address, Date birthday, double money) throws IllegalInitialValueException {
        this.accountNumber = accountNumber;
        this.PIN = PIN;
        this.money = money;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.suspend = false;
        if (PIN.isEmpty()) {
            throw new IllegalInitialValueException("PIN can not be empty.");
        }
        if (money < 0) {
            throw new IllegalInitialValueException("Initial money should not smaller than 0.");
        }
    }

    public static boolean checkCredit() {

        return true;
    }

    @Override
    public String toString() {
        return "account number:" + accountNumber +
                " money:" + money +
                " name:" + name;
    }

    public String getPIN() {

        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    /**
     * @param count
     */
    public double save(double count) throws AccountSuspendedException {

        this.getClass().isInterface();
        if (suspend) {
            throw new AccountSuspendedException("Account is suspended.");
        } else {
            this.money += count;
            return this.money;
        }
    }

    /**
     * @param count
     */
    public double draw(double count) throws AccountSuspendedException, OverdraftException {
        if (suspend) {
            throw new AccountSuspendedException("Account is suspended.");
        } else {
            double overdraftLimit = 0;
            if (this instanceof Overdraftable) {
                ((Overdraftable) this).getOverdraftLimit();
            }

            if (money + overdraftLimit < count) {
                throw new OverdraftException("Out of overdraft boundary.");
            }
            money -= count;
            return this.money;
        }
    }

    public boolean closeAccount() {
        if (this.getMoney() == 0) {
            Bank.accounts.remove(this);
            return true;
        } else
            return false;
    }

    public boolean setSuspend() {
        return suspend = !suspend;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isSuspend() {
        return suspend;
    }
}