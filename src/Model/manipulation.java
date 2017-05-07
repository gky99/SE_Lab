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

import java.util.Calendar;
import java.util.Date;

public class manipulation {
    private final Date createTime;
    private Date finishTime;
    private final int origin;
    private final double money;
    private final int destination;
    private final boolean subscribe;
    private String result;
    private final boolean changeFlag;

    public manipulation(int origin, int destination, double money, boolean subscribe) {
        this.origin = origin;
        this.destination = destination;
        this.money = money;
        this.subscribe = subscribe;
        this.changeFlag = true;

        this.createTime = new Date();
    }

    private void setFinishTime() throws Exception{
        if (changeFlag) {
            this.finishTime = new Date();
        } else {
            throw new UnchangeableException("Manipulation already finished.");
        }
    }

    public void setResult(String result) throws Exception{
        if (changeFlag) {
            this.result = result;
            setFinishTime();
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
}