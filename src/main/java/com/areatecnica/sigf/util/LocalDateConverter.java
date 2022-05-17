/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ianfrancoconcha
 */
public class LocalDateConverter {

    private ZoneId defaultZoneId = ZoneId.systemDefault();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM", new Locale("es", "PE"));
    private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMMM'/'yyyy", new Locale("es", "PE"));

    private LocalDate date;
    private Date toDate;

    public LocalDateConverter(LocalDate date) {
        this.date = date;
        this.toDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
    }

    public Date getDate() {
        return this.toDate;
    }

    public Date getLastDayOfMonth() {
        return Date.from(date.withDayOfMonth(date.getMonth().length(date.isLeapYear())).atStartOfDay(defaultZoneId).toInstant());
    }

    public String getMonthYearString() {
        return date.format(formatter2).toUpperCase();
    }

    public String getCurrentDateName() {
        return date.format(formatter).toUpperCase();
    }

}
