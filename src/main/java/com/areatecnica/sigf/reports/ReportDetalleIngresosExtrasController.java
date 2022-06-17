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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerIngresosExtrasMensual")
@ViewScoped
public class ReportDetalleIngresosExtrasController implements Serializable {
    
    private LocalDate date;
    private LocalDateConverter dc;
    private List<CajaRecaudacion> items;
    private CajaRecaudacion selected;
    private String informe;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/01");

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportDetalleIngresosExtrasController() {

    }

    @PostConstruct
    private void init() {
        this.date = LocalDate.now();
        this.items = new CajaRecaudacionDaoImpl().findAllActive();
        this.items.add(0, new CajaRecaudacion(0, "Todas", true, true));

        this.informe = "inf-resumen_ingreso_extra";
        
    }


    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("fechaCompleta", this.dc.getMonthYearString());
        map.put("fecha", this.dc.getDate());
        map.put("desde", this.dc.getDate());
        map.put("hasta", this.dc.getLastDayOfMonth());

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
        if (this.selected.getCajaRecaudacionId() == null) {
            this.informe = "inf-resumen_ingreso_extra";
        }
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
