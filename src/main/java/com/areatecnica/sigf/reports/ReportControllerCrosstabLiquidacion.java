/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.CajaRecaudacionDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.util.LocalDateConverter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerCrosstabLiquidacion")
@ViewScoped
public class ReportControllerCrosstabLiquidacion implements Serializable {

    private LocalDate date;
    private LocalDateConverter dc;
    private String informe;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/01");

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerCrosstabLiquidacion() {

    }

    @PostConstruct
    private void init() {
        this.setDate(LocalDate.now());
        this.informe = "inf-crosstab_liquidacion_empresa";

    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("desde", this.dc.getFirstDateOfMonth());
        map.put("hasta", this.dc.getLastDayOfMonth());
        map.put("fecha", this.dc.getMonthYearString());

        return map;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getInforme() {
        return informe;
    }

    public void falta() {
        JsfUtil.addErrorMessage("No se encuentran registros");
    }

    public LocalDateConverter getDc() {
        return dc;
    }

    public void setDc(LocalDateConverter dc) {
        this.dc = dc;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

}
