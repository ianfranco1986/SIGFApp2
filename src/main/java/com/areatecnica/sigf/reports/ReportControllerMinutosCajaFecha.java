/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.CajaRecaudacionDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerMinutosCajaFecha")
@ViewScoped
public class ReportControllerMinutosCajaFecha implements Serializable {

    private List<CajaRecaudacion> items;
    private CajaRecaudacion selected;
    private Date fecha;
    private String informe;

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerMinutosCajaFecha() {

    }

    @PostConstruct
    private void init() {
        this.items = new CajaRecaudacionDaoImpl().findAllActive();
        this.fecha = new Date();
        this.informe = "inf-recaudacion_minuto_caja_fecha";
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("fecha", fecha);
        map.put("caja", selected.getCajaRecaudacionId());

        return map;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getInforme() {
        return informe;
    }

    public void falta() {
        JsfUtil.addErrorMessage("No se encuentran registros");
    }
}
