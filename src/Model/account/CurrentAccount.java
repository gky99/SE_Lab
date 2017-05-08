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

import java.util.Date;

public class CurrentAccount extends Account implements Overdraftable {
    private double overdraftLimit;

    public CurrentAccount(int accountNum, String PIN, Date birthday, String name, String address, double money) {
        super(accountNum, PIN, name, address, birthday, money);
        this.overdraftLimit = 0;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Compile Success");

        String date = "1999.5.8";
        System.out.println(Bank.parseDate(date));
        Date d = Bank.parseDate(date);
        System.out.println(JuniorAccount.checkAge(d));
    }

    @Override
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}