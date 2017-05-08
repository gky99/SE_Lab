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
public class VirtualAccount extends Account {
    private VirtualAccount(int accountNumber, Date birthday, String name, String address, double money) throws Exception {
        super(accountNumber, "123456", name, address, birthday, money);
    }

    public VirtualAccount(int accountNumber, String name) throws Exception {
        this(accountNumber, null, name, null, 99999);
    }


    @Override
    public double draw(double count) {
        try {
            return super.draw(0);
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public double save(double count) {
        try {
            return super.save(0);
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public boolean setSuspend() {
        if (isSuspend()) {
            return super.setSuspend();
        } else
            return isSuspend();
    }
}
