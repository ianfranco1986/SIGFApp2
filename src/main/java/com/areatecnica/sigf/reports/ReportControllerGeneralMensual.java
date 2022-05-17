/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.util.LocalDateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerGeneralMensual")
@ViewScoped
public class ReportControllerGeneralMensual implements Serializable {

    private LocalDate date;
    private LocalDateConverter dc;
    private List<CajaRecaudacion> items;
    private CajaRecaudacion selected;
    private String informe;
  
    public ReportControllerGeneralMensual() {

    }

    @PostConstruct
    private void init() {
        this.date = LocalDate.now();
        this.informe = "inf-recaudacion_general";
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        map.put("fecha", this.dc.getDate());
        return map;
    }

    public CajaRecaudacion getSelected() {
        return selected;
    }

    public void setSelected(CajaRecaudacion selected) {
        this.selected = selected;
    }

    public List<CajaRecaudacion> getItems() {
        return items;
    }

    public void setItems(List<CajaRecaudacion> items) {
        this.items = items;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public void setPath() {
        this.informe = "inf-recaudacion_general_caja";
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
