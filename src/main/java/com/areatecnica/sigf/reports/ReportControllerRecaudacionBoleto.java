/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Named(value = "reportControllerRecaudacionBoleto")
@ViewScoped
public class ReportControllerRecaudacionBoleto implements Serializable {

    private List<CajaRecaudacion> items;
    private CajaRecaudacion selected;
    private Date fecha;
    private String informe;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/01");

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerRecaudacionBoleto() {

    }

    @PostConstruct
    private void init() {
        this.items = new ICajaRecaudacionDaoImpl().findAll();
        this.fecha = new Date();
        this.informe = "inf-recaudacion_boleto_caja";
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("fecha", fecha);
        map.put("caja", selected.getCajaRecaudacionId());
        map.put("nombreCaja", selected.getCajaRecaudacionNombre());
        map.put("nombreUsuario", selected.getCajaRecaudacionIdUsuario().getUsuarioNombres()+" "+selected.getCajaRecaudacionIdUsuario().getUsuarioApellidoPaterno()+" "+selected.getCajaRecaudacionIdUsuario().getUsuarioApellidoMaterno());

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
