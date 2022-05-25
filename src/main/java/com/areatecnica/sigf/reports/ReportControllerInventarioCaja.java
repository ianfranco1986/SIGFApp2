/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerInventarioCaja")
@ViewScoped
public class ReportControllerInventarioCaja implements Serializable {

    private List<CajaRecaudacion> items;
    private CajaRecaudacion selected;
    private String informe;

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerInventarioCaja() {

    }

    @PostConstruct
    private void init() {
        this.items = new ICajaRecaudacionDaoImpl().findAllActive();
        this.informe = "inf-inventario_caja";
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("caja", selected.getCajaRecaudacionId());
        map.put("nombreCaja", selected.getCajaRecaudacionNombre());
        
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

    public String getInforme() {
        return informe;
    }

    public void falta() {
        JsfUtil.addErrorMessage("No se encuentran registros");
    }
}
