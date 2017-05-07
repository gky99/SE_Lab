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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Manipulation {
    private static final Map<Integer, String> virtualAccounts;

    static {
        virtualAccounts = new HashMap<Integer, String>();
        virtualAccounts.put(-1, "cash");
        virtualAccounts.put(-2, "cheque");
    }

    private final Date createTime = new Date();

    private final int origin;
    private final double money;
    private final int destination;
    private final boolean changeFlag;
    private Date finishTime;
    private Date subscribeTime;
    private String result;


    public Manipulation(int origin, int destination, double money) {
        this.origin = origin;
        this.destination = destination;
        this.money = money;
        this.changeFlag = true;

        Bank.manipulations.add(this);
    }

    public Manipulation(int origin, int destination, double money, Date subscribeTime) {
        this(origin, destination, money);
        this.subscribeTime = subscribeTime;

        Bank.suspended.add(this);
    }

    public boolean execute() throws Exception {
        if (origin < 0) {
            if (destination < 0) {

            } else {

            }


        } else {
            if (destination < 0) {

            } else {

            }
        }
        Account origin = Bank.findAccountByID(this.origin);
        Account destination = Bank.findAccountByID(this.destination);

        origin.draw(money);
        destination.save(money);

    }

    private void setFinishTime() throws Exception {
        if (changeFlag) {
            this.finishTime = new Date();
        } else {
            throw new UnchangeableException("Manipulation already finished.");
        }
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public int getOrigin() {
        return origin;
    }

    public double getMoney() {
        return money;
    }

    public int getDestination() {
        return destination;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) throws Exception {
        if (changeFlag) {
            this.result = result;
            setFinishTime();
        } else {
            throw new UnchangeableException("Manipulation already finished.");
        }
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }
}