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
    private boolean confirmed = false;

    public Manipulation(Account origin, Account destination, double money) {
        this.origin = origin;
        this.destination = destination;
        this.money = money;
        this.changeFlag = true;
    }

    public Manipulation(Account origin, int destination, double money) throws AccountNotFoundException {
        this.origin = origin;
        this.destination = Bank.findAccountByID(destination);
        this.money = money;
        this.changeFlag = true;
        if (this.destination == null) {
            throw new AccountNotFoundException("Target account: " + destination + ". No such account.");
        }
    }

    public Manipulation(int origin, Account destination, double money) throws AccountNotFoundException {
        this.origin = Bank.findAccountByID(origin);
        this.destination = destination;
        this.money = money;
        this.changeFlag = true;
        if (this.origin == null) {
            throw new AccountNotFoundException("Origin account not found.");
        }
    }

    public Manipulation(int origin, int destination, double money) throws AccountNotFoundException {
        this.origin = Bank.findAccountByID(origin);
        this.destination = Bank.findAccountByID(destination);
        this.money = money;
        this.changeFlag = true;
        if (this.destination == null) {
            throw new AccountNotFoundException("Target account: " + destination + ". No such account.");
        } else if (this.origin == null) {
            throw new AccountNotFoundException("Origin account not found.");
        }
    }

    public Manipulation(int origin, int destination, double money, Date subscribeTime) {
        this(origin, destination, money);
        this.subscribeTime = subscribeTime;
    }

    public Manipulation(Account origin, int destination, double money, Date subscribeTime) {
        this(origin, destination, money);
        this.subscribeTime = subscribeTime;
    }

    public Manipulation(int origin, Account destination, double money, Date subscribeTime) {
        this(origin, destination, money);
        this.subscribeTime = subscribeTime;
    }

    @Override
    public String toString() {
        return "From: " + origin.getName() +
                " To: " + destination.getName() +
                "    Amount: " + money +
                "Subscribe time:" + subscribeTime;
    }

    public boolean isChangeFlag() {
        return changeFlag;
    }

    public boolean execute() throws UnchangeableException {
        try {
            origin.draw(money);
            destination.save(money);
            this.setResult("Success.");

            return true;
        } catch (AccountSuspendedException e) {
            this.setResult("Failed. " + e.getMessage());
        } catch (OverdraftException e) {
            this.setResult("Failed. " + e.getMessage());
        }
        return false;
    }

    public boolean confirm() throws IllegalTimeException, UnchangeableException {
        if (!this.confirmed) {
            this.confirmed = true;
            if (this.subscribeTime != null) {
                if (this.origin instanceof SaverAccount) {
                    if (this.destination.getAccountNumber() > 0) {
                        Bank.suspended.add(this);
                        this.result = "Subscribed.";
                    } else {
                        ((SaverAccount) this.origin).subscribe(this);
                        this.result = "Subscribed.";
                    }
                    Bank.manipulations.add(this);
                    return true;
                } else {
                    if (subscribeTime.after(new Date())) {
                        Bank.suspended.add(this);
                        this.result = "Subscribed.";
                        Bank.manipulations.add(this);
                        return true;
                    } else {
                        throw new IllegalTimeException("Illegal subscribe time. Subscribed time already passes");
                    }
                }
            }
            Bank.manipulations.add(this);

            if (origin.getAccountNumber() < -1) {
                Bank.suspended.add(this);
                this.result = "Subscribed.";
                return true;
            }
        }
        if (subscribeTime != null && subscribeTime.after(new Date())) {
            if (this.origin instanceof SaverAccount) {
                this.result = "Subscription time does not come.";
            }
            return false;
        } else
            return execute();
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