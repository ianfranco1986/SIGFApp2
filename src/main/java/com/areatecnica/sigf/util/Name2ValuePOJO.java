/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.util;

/**
 *
 * @author ianfr
 */
public class Name2ValuePOJO {

    private Object name;
    private Object value;

    public Name2ValuePOJO() {
    }

    public Name2ValuePOJO(Object name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name.toString();
    }

    public String getValue() {
        return value.toString();
    }

    public void setName(Object name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }    
}
