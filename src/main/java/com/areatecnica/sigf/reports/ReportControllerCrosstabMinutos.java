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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@Named(value = "reportControllerCrosstab")
@ViewScoped
public class ReportControllerCrosstabMinutos implements Serializable {

    private List<CajaRecaudacion> items;
    private CajaRecaudacion selected;
    private Date fecha;
    private String informe;
    private int mes;
    private int anio;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/01");

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerCrosstabMinutos() {

    }

    @PostConstruct
    private void init() {
        this.items = new ICajaRecaudacionDaoImpl().findAllActive();
        this.items.add(0, new CajaRecaudacion(0, "Todas", true, true));

        this.informe = "inf-crosstab_minutos";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);
        setFecha();
        System.err.println("primera fecha: " + this.fecha);
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("from", this.fecha);
        map.put("fecha", getStringFecha());
        //map.put("caja", selected.getCajaRecaudacionId());

        return map;
    }

    public String getStringFecha() {

        String mes = "";

        switch (this.mes) {
            case 1:
                mes = "ENERO";
                break;
            case 2:
                mes = "FEBRERO";
                break;
            case 3:
                mes = "MARZO";
                break;
            case 4:
                mes = "ABRIL";
                break;
            case 5:
                mes = "MAYO";
                break;
            case 6:
                mes = "JUNIO";
                break;
            case 7:
                mes = "JULIO";
                break;
            case 8:
                mes = "AGOSTO";
                break;
            case 9:
                mes = "SEPTIEMBRE";
                break;
            case 10:
                mes = "OCTUBRE";
                break;
            case 11:
                mes = "NOVIEMBRE";
                break;
            case 12:
                mes = "DICIEMBRE";
                break;
        }
        return mes + " " + anio;
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

    public void setPath() {
        if (this.selected.getCajaRecaudacionId() == null) {
            this.informe = "inf-crosstab_minutos";
        }
    }

    public String getInforme() {
        return informe;
    }

    public void falta() {
        JsfUtil.addErrorMessage("No se encuentran registros");
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

    public void setFecha() {
        try {

            this.fecha = this.sdf.parse(this.anio + "/" + this.mes + "/01");
            System.err.println("NUEVA FECHA: " + this.fecha);
        } catch (ParseException ex) {
        }
    }

}
