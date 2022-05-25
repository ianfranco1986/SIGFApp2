/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.util.LocalDateConverter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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
@Named(value = "reportControllerLibroVentaCombustible")
@ViewScoped
public class ReportControllerLibroVentaCombustible implements Serializable {

    private String informe = "inf-libro_venta_petroleo";
    private String list;
    private List<Integer> array;
    private Boolean tipoInforme;
    private Usuario currentUser;
    private Cuenta userCount;

    private LocalDate date;
    private LocalDateConverter dc;

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerLibroVentaCombustible() {

    }

    @PostConstruct
    private void init() {
        this.tipoInforme = Boolean.TRUE;
        this.date = LocalDate.now();
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("fechaCompleta", this.dc.getMonthYearString());
        map.put("fecha", this.dc.getDate());
        map.put("idCuenta", JsfUtil.getCurrentUser().getUsuarioIdCuenta());

        return map;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
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

}
