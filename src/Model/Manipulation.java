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
    //    private boolean confirmed = false;
    public boolean confirmed = false;
    private boolean changeFlag;
    private Date finishTime;
    private Date subscribeTime;
    private String result;


    /**
     * Construct a <code>Manipulation</code>.
     *
     * @param origin      the source of the transfer.
     * @param destination the target of the transfer.
     * @param money       the amount of money to be transferred
     * @throws AccountNotFoundException thown when the account can not be found in the {@link Bank#accounts}.
     */
    public Manipulation(Account origin, Account destination, double money) {
        this.origin = origin;
        this.destination = destination;
        this.money = money;
        this.changeFlag = true;
    }

    /**
     * Construct a <code>Manipulation</code>.
     *
     * @param origin      the source of the transfer.
     * @param destination the target of the transfer.
     * @param money       the amount of money to be transferred
     * @throws AccountNotFoundException thown when the account can not be found in the {@link Bank#accounts}.
     */
    public Manipulation(Account origin, int destination, double money) throws AccountNotFoundException {
        this.origin = origin;
        this.destination = Bank.findAccountByID(destination);
        this.money = money;
        this.changeFlag = true;
        if (this.destination == null) {
            throw new AccountNotFoundException("Target account: " + destination + ". No such account.");
        }
    }

    /**
     * Construct a <code>Manipulation</code>.
     *
     * @param origin      the source of the transfer.
     * @param destination the target of the transfer.
     * @param money       the amount of money to be transferred
     * @throws AccountNotFoundException thown when the account can not be found in the {@link Bank#accounts}.
     */
    public Manipulation(int origin, Account destination, double money) throws AccountNotFoundException {
        this.origin = Bank.findAccountByID(origin);
        this.destination = destination;
        this.money = money;
        this.changeFlag = true;
        if (this.origin == null) {
            throw new AccountNotFoundException("Origin account not found.");
        }
    }

    /**
     * Construct a <code>Manipulation</code>.
     *
     * @param origin      the source of the transfer.
     * @param destination the target of the transfer.
     * @param money       the amount of money to be transferred
     * @throws AccountNotFoundException thown when the account can not be found in the {@link Bank#accounts}.
     */
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

    /**
     * Construct a <code>Manipulation</code>.
     *
     * @param origin        the source of the transfer.
     * @param destination   the target of the transfer.
     * @param money         the amount of money to be transferred.
     * @param subscribeTime for subscribed manipulations. Manipulation should be execute at or after the subscribed time.
     * @throws AccountNotFoundException thown when the account can not be found in the {@link Bank#accounts}.
     */
    public Manipulation(int origin, int destination, double money, Date subscribeTime) {
        this(origin, destination, money);
        this.subscribeTime = subscribeTime;
    }

    /**
     * Construct a <code>Manipulation</code>.
     *
     * @param origin        the source of the transfer.
     * @param destination   the target of the transfer.
     * @param money         the amount of money to be transferred.
     * @param subscribeTime for subscribed manipulations. Manipulation should be execute at or after the subscribed time.
     * @throws AccountNotFoundException thown when the account can not be found in the {@link Bank#accounts}.
     */
    public Manipulation(Account origin, int destination, double money, Date subscribeTime) {
        this(origin, destination, money);
        this.subscribeTime = subscribeTime;
    }

    /**
     * Construct a <code>Manipulation</code>.
     *
     * @param origin        the source of the transfer.
     * @param destination   the target of the transfer.
     * @param money         the amount of money to be transferred.
     * @param subscribeTime for subscribed manipulations. Manipulation should be execute at or after the subscribed time.
     * @throws AccountNotFoundException thown when the account can not be found in the {@link Bank#accounts}.
     */
    public Manipulation(int origin, Account destination, double money, Date subscribeTime) {
        this(origin, destination, money);
        this.subscribeTime = subscribeTime;
    }

    @Override
    public String toString() {
        return "From: " + origin.getName() +
                "  To: " + destination.getName() +
                "    Amount: " + money +
                "  Subscribe time:" + subscribeTime;
    }

    public boolean isChangeFlag() {
        return changeFlag;
    }

    /**
     * Directly execute the manipulation without check.
     *
     * @throws UnchangeableException thrown when you try to execute a finished manipulation.
     */
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

    /**
     * Confirm the construct of a manipulation. Save the valid manipulation into {@link Bank#manipulations}.
     * Execute the manipulations that can be executed now.
     * Save the manipulations that should be executed later into corresponding place({@link SaverAccount#subscription} and {@link Bank#suspended}).
     * Remove the manipulation from the place after execute them.
     *
     * @exception IllegalInitialValueException thrown when the money < 0.
     * @exception IllegalTimeException thrown when subscribed time too close to today for {@link SaverAccount} or subscribed time is already passed.
     * @exception UnchangeableException thrown when you try to execute a finished manipulation.
     * */
    public boolean confirm() throws IllegalTimeException, UnchangeableException, IllegalInitialValueException {
        if (!this.confirmed) {
            this.confirmed = true;
            if (money < 0) {
                throw new IllegalInitialValueException("Amount of money should be larger than 0");
            }
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
        } else {
            if (this.origin instanceof SaverAccount) {
                ((SaverAccount) this.origin).getSubscription().remove(this);
            } else {
//                Bank.suspended.remove(this);
            }
            return execute();
        }
    }

    private void setFinishTime() throws UnchangeableException {
        if (changeFlag) {
            this.finishTime = new Date();
            this.changeFlag = false;
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