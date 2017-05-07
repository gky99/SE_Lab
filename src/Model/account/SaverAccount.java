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
import java.util.Vector;

public class SaverAccount extends Account {

    private Vector<manipulation> subscription;

    public SaverAccount(int accountNum, double money, String name, String address, Date birthday) {
        super(accountNum, money, name, address, birthday);
    }

    public void subscribe() {
        // TODO - implement SaverAccount.subscribe
        throw new UnsupportedOperationException();
    }

}