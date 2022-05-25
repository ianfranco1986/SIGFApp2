/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.util.LocalDateConverter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerResumenConductor")
@ViewScoped
public class ReportControllerResumenConductor implements Serializable {

    private LocalDate date;
    private LocalDateConverter dc;
    private String informe = "inf-resumen_imposiciones_conductor";
    private String list;
    private List<Integer> array;
    private Boolean tipoInforme;
    //private 

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerResumenConductor() {

    }

    @PostConstruct
    private void init() {
        this.date = LocalDate.now();
        this.tipoInforme = Boolean.TRUE;
        
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("fechaCompleta", this.dc.getMonthYearString());
        map.put("desde", this.dc.getDate());
        map.put("hasta", this.dc.getLastDayOfMonth());
        map.put("list", array);

        return map;
    }

    

    public void setTipoInforme(Boolean tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public Boolean getTipoInforme() {
        return tipoInforme;
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

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }
}
