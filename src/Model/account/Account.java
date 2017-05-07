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

import Model.manipulation;

import java.util.Date;

public abstract class Account {

    private final int accountNumber;

    private double money = 0;
    private String name;
    private String address;
    private Date birthday;
    private double overdraftLimit;
    private boolean suspend;

    /**
     * @param money
     * @param name
     * @param address
     * @param birthday
     */
    public Account(int accountNumber, double money, String name, String address, Date birthday) {
        this.accountNumber = accountNumber;
        this.money = money;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.overdraftLimit = 0;
        this.suspend = false;
    }

    /**
     * @param man
     */
    public double save(manipulation man) throws Exception{
        if (suspend) {
            throw new AccountSuspendedException("Account is suspended.");
        } else {
            this.money += man.getMoney();
            return this.money;
        }
    }

    /**
     * @param man
     */
    public double draw(manipulation man) throws Exception{
        if (suspend) {
            throw new AccountSuspendedException("Account is suspended.");
        } else {
            if (money+overdraftLimit < man.getMoney()) {
                throw new OverdraftException("Out of overdraft boundary.");
            }
            money -= man.getMoney();
            return this.money;
        }
    }

    public boolean checkCredit() {

        return true;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
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

    public String getAddress() {
        return address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public boolean isSuspend() {
        return suspend;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}