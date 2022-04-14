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
@Named(value = "reportControllerIngresosExtrasMensual")
@ViewScoped
public class ReportDetalleIngresosExtrasController implements Serializable {

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
    public ReportDetalleIngresosExtrasController() {

    }

    @PostConstruct
    private void init() {
        this.items = new ICajaRecaudacionDaoImpl().findAll();
        this.items.add(0, new CajaRecaudacion(0, "Todas", true, true));

        this.informe = "inf-resumen_ingreso_extra";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);
        setFecha();
        System.err.println("primera fecha: " + this.fecha);
    }

    public String nombreMes(int mes) {
        switch (mes) {

            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
        }
        return "";
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("fecha", this.fecha);
        map.put("nombreMes", nombreMes(mes));
        map.put("anio", String.valueOf(anio));
        //map.put("caja", selected.getCajaRecaudacionId());

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
