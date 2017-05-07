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

import java.util.Date;

/**
 * Created by gky on 2017/5/7.
 */
public class VirtualAccount extends Account{
    public VirtualAccount(int accountNumber, double money, String name, String address, Date birthday) {
        super(accountNumber, money, name, address, birthday);
    }

    public VirtualAccount(int accountNumber, String name) {
        this(accountNumber, 99999, name, null, null);
    }


    @Override
    public double draw(double count) throws Exception {
        return super.draw(0);
    }

    @Override
    public double save(double count) throws Exception {
        return super.save(0);
    }

    @Override
    public boolean setSuspend() {
        if (isSuspend()) {
            return super.setSuspend();
        } else
            return isSuspend();
    }
}
