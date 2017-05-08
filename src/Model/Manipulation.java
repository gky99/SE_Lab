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
import Model.account.SaverAccount;
import Model.exceptions.*;

import java.util.Date;

public class Manipulation {
    private final Date createTime = new Date();

    private final Account origin;
    private final double money;
    private final Account destination;
    private final boolean changeFlag;
    private Date finishTime;
    private Date subscribeTime;
    private String result;


    public Manipulation(Account origin, Account destination, double money) {
        this.origin = origin;
        this.destination = destination;
        this.money = money;
        this.changeFlag = true;

        Bank.manipulations.add(this);
    }

    public Manipulation(Account origin, int destination, double money) throws AccountNotFoundException {
        this.origin = origin;
        this.destination = Bank.findAccountByID(destination);
        this.money = money;
        this.changeFlag = true;
        if (this.destination == null || this.origin == null) {
            throw new AccountNotFoundException("No such account.");
        }
        Bank.manipulations.add(this);
    }

    public Manipulation(int origin, Account destination, double money) throws AccountNotFoundException {
        this.origin = Bank.findAccountByID(origin);
        this.destination = destination;
        this.money = money;
        this.changeFlag = true;
        if (this.destination == null || this.origin == null) {
            throw new AccountNotFoundException("No such account.");
        }
        Bank.manipulations.add(this);
    }

    public Manipulation(int origin, int destination, double money) throws AccountNotFoundException {
        this.origin = Bank.findAccountByID(origin);
        this.destination = Bank.findAccountByID(destination);
        this.money = money;
        this.changeFlag = true;
        if (this.destination == null || this.origin == null) {
            throw new AccountNotFoundException("No such account.");
        }
        Bank.manipulations.add(this);
    }

    public Manipulation(int origin, int destination, double money, Date subscribeTime) throws IllegalTimeException {
        this(origin, destination, money);
        this.subscribeTime = subscribeTime;

        Account account = Bank.findAccountByID(origin);
        if (account instanceof SaverAccount) {
            ((SaverAccount) account).subscribe(this);
        } else {
            if (subscribeTime.after(new Date())) {
                Bank.suspended.add(this);
            } else {
                throw new IllegalTimeException("Illegal subscribe time. Subscribed time already passes");
            }
        }
    }

    public boolean execute() throws Exception {
        try {
            origin.draw(money);
            destination.save(money);
            this.setResult("Success.");

            return true;
        } catch (AccountSuspendedException e) {
            this.setResult("Failed. " + e.getMessage());
        } catch (OverdraftException e) {
            this.setResult("Failed. " + e.getMessage());
        } finally {
            return false;
        }
    }

    public boolean confirm() throws Exception {
        if (origin.getAccountNumber() == ) {

        }
        if (subscribeTime != null) {
            if (subscribeTime.after(new Date())) {
                return false;
            }
            else {
                return execute();
            }
        }
    }

    private void setFinishTime() throws UnchangeableException {
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

    public Account getOrigin() {
        return origin;
    }

    public double getMoney() {
        return money;
    }

    public Account getDestination() {
        return destination;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) throws UnchangeableException {
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