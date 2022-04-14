/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.util;

import java.util.Date;

/**
 *
 * @author ianfr
 */
public class DatePlusValueHelper {

    private Date date;
    private Long value;

    public DatePlusValueHelper() {
    }

    public DatePlusValueHelper(Long value, Date date) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public Long getValue() {
        return value;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
