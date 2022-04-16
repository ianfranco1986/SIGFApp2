/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.models;

import java.io.Serializable;
import java.time.temporal.Temporal;

/**
 *
 * @author ianfrancoconcha
 */
public class ColumnModel implements Serializable {

        private String header;
        private String property;
        private String type;
        private Class<?> klazz;

        public ColumnModel(String header, String property, Class klazz) {
            this.header = header;
            this.property = property;
            this.klazz = klazz;
            initType();
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }

        public String getType() {
            return type;
        }

        public Class<?> getKlazz() {
            return klazz;
        }

        private void initType() {
            if (Temporal.class.isAssignableFrom(klazz)) {
                type = "date";
            }
            else if (klazz.isEnum()) {
                type = "enum";
            }
        }
    }
