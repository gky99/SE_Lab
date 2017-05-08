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

import Bank.Model.Bank;
import Bank.Model.Manipulation;
import Bank.Model.account.Account;
import Bank.Model.account.CurrentAccount;
import Bank.Model.account.SaverAccount;
import Bank.UI.MainPage;

/**
 * Created by Pauli on 2017/5/8.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        new Bank();
        MainPage mainPage = new MainPage();

        Account account = Bank.openAccount("123", "234", 700, Bank.parseDate("1996.6.6"), "345", "current account");
        if (account instanceof CurrentAccount) {
            ((CurrentAccount) account).setOverdraftLimit(1000);
        }
        mainPage.account = Bank.openAccount("aoe", "oeu", 700, Bank.parseDate("1996.10.10"), "eui", "saver account");
        if (mainPage.account instanceof SaverAccount) {
            Manipulation man = new Manipulation(mainPage.account, -1, 100, Bank.parseDate("2000.0.0"));
            man.confirmed = true;
            ((SaverAccount) mainPage.account).getSubscription().add(man);
        }
        mainPage.actions();

    }
}
