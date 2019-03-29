/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerPetroleoFecha")
@ViewScoped
public class ReportControllerPetroleoFecha implements Serializable {

    private Date fecha;
    private String informe;

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerPetroleoFecha() {

    }

    @PostConstruct
    private void init() {
        this.fecha = new Date();
        this.informe = "inf-venta_petroleo";
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        map.put("fecha", fecha);
        return map;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
