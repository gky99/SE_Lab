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

    private double money = 0;
    private String name;
    private String address;
    private Date birthday;
    private int accountNumber;
    private double overdraftLimit;
    private boolean suspend;

    /**
     * @param money
     * @param name
     * @param address
     * @param birthday
     */
    public Account(double money, String name, String address, Date birthday) {
        // TODO - implement Account.Account
        throw new UnsupportedOperationException();
    }

    /**
     * @param man
     */
    public int save(manipulation man) {
        // TODO - implement Account.save
        throw new UnsupportedOperationException();
    }

    /**
     * @param man
     */
    public int draw(manipulation man) {
        // TODO - implement Account.draw
        throw new UnsupportedOperationException();
    }

    public boolean checkCredit() {
        // TODO - implement Account.checkCredit
        throw new UnsupportedOperationException();
    }

}