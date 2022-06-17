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
@Named(value = "reportControllerRecaudacionAIO")
@ViewScoped
public class ReportControllerRecaudacionAIO implements Serializable {

    private List<CajaRecaudacion> items;
    private CajaRecaudacion selected;
    private Date fecha;
    private final String informe = "inf-recaudacion_caja_general";
    private final String informeArqueo = "inf-recaudacion_arqueo_caja";
    private final String informeGuias = "inf-recaudacion_caja_fecha";
    private final String informePetroleo = "inf-recaudacion_petroleo_caja_fecha";
    private final String informeMinutos = "inf-recaudacion_minuto_caja_fecha";
    private final String informeBoletos = "inf-recaudacion_boleto_caja";
    private final String informeExtras = "inf-recaudacion_extra_caja_fecha";

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerRecaudacionAIO() {

    }

    @PostConstruct
    private void init() {
        this.items = new CajaRecaudacionDaoImpl().findAllActive();
        this.fecha = new Date();
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("fecha", fecha);
        map.put("caja", selected.getCajaRecaudacionId());
        map.put("nombreCaja", selected.getCajaRecaudacionNombre());
        map.put("nombreUsuario", "");

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

    public String getInforme() {
        return informe;
    }

    public String getInformeArqueo() {
        return informeArqueo;
    }
    
    public String getInformeGuias() {
        return informeGuias;
    }

    public String getInformePetroleo() {
        return informePetroleo;
    }

    public String getInformeMinutos() {
        return informeMinutos;
    }

    public String getInformeBoletos() {
        return informeBoletos;
    }

    public String getInformeExtras() {
        return informeExtras;
    }

    public void falta() {
        JsfUtil.addErrorMessage("No se encuentran registros");
    }
}
