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

import Model.Bank;
import Model.Manipulation;
import Model.account.Account;
import Model.account.SaverAccount;
import UI.MainPage;

/**
 * Created by Pauli on 2017/5/8.
 */
public class loginTest {
    public static void main(String[] args) throws Exception {
        new Bank();
        MainPage mainPage = new MainPage();

//        mainPage.login();
//        mainPage.openAccount();

        Bank.openAccount("123", "234", 700, Bank.parseDate("1996.6.6"), "345", "current account");
        mainPage.account = Bank.openAccount("aoe", "oeu", 700, Bank.parseDate("1996.10.10"), "eui", "saver account");

        boolean t = mainPage.transfer();
        System.out.println("=============================");

        Bank.clearFund();

        for (Account temp : Bank.accounts) {
            System.out.println(temp);
        }

        if (mainPage.account instanceof SaverAccount) {
            SaverAccount saverAccount = (SaverAccount) mainPage.account;

            for (Manipulation temp : saverAccount.getSubscription()) {
                System.out.println(temp);
            }
        }
    }
}
