/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
public class Name3ValuePOJO implements Serializable {

    @Id
    @Column(name = "name")
    private Object name;
    @Column(name = "value")
    private Object value;
    @Column(name = "other")
    private Object other;

    public Name3ValuePOJO() {
    }

    public Name3ValuePOJO(Object name, Object value, Object other) {
        if (value != null) {
            this.name = name;
            this.value = value;
            this.other = other;
        } else {
            this.name = "";
            this.value = new Integer(0);
            this.other = new Integer(0);
        }
    }

    public Object getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public Object getOther() {
        return other;
    }

}
