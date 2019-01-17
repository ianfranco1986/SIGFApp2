/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ianfr
 */
@Entity
public class Name2DateValuePOJO implements Serializable {

    @Id
    @Column(name = "name")
    private Date name;
    @Column(name = "value")
    private Object value;

    public Name2DateValuePOJO() {
    }

    public Name2DateValuePOJO(Date name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Date getName() {
        return name;
    }

    public String getValue() {
        return value.toString();
    }

    public void setName(Date name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getDayPart() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date date = (Date) name;
        return Integer.parseInt(sdf.format(date));
    }

}
