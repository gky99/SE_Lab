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

import Model.exceptions.IllegalInitialValueException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class JuniorAccount extends Account {
    private static int ageLimit = 16;

    /**
     * {@inheritDoc}
     *
     * @see Account#Account(int, String, String, String, Date, double)
     */
    public JuniorAccount(int accountNum, String PIN, Date birthday, String name, String address, double money) throws IllegalInitialValueException {
        super(accountNum, PIN, name, address, birthday, money);
    }

    /**
     * Check the birthday to find whether the age of the user is allowed to open a junior account.
     *
     * @return if age limitation is satisfied, return true.
     */
    public static boolean checkAge(Date birthday) {
        Calendar today = new GregorianCalendar();
        Calendar birth = new GregorianCalendar();
        birth.setTime(birthday);

        birth.add(Calendar.YEAR, ageLimit);
        return today.before(birth);
    }

}