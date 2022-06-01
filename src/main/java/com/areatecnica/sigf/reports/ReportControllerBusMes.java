/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.IUnidadNegocioDao;
import com.areatecnica.sigf.dao.impl.UnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.util.CurrentDate;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerBusMes")
@ViewScoped
public class ReportControllerBusMes implements Serializable {

    private List<Bus> items;
    private int mes;
    private int anio;

    private Bus selected;

    private IBusDao dao;
    private IUnidadNegocioDao unidadNegocioDao;
    private String informe;

    /**
     * Creates a new instance of ReportControllerBusMes
     */
    public ReportControllerBusMes() {
    }

    @PostConstruct
    private void init() {

        this.unidadNegocioDao = new UnidadNegocioDaoImpl();
        this.items = this.unidadNegocioDao.findById(2).getBusList();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        CurrentDate fecha = new CurrentDate(1, mes, anio);

        map.put("fecha", fecha.date());
        map.put("bus", selected.getBusId());

        return map;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public List<Bus> getItems() {
        return items;
    }

    public void setItems(List<Bus> items) {
        this.items = items;
    }

    public Bus getSelected() {
        return selected;
    }

    public void setSelected(Bus selected) {
        this.selected = selected;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

}
