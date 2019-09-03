/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.dao.impl.ITipoCargoDaoImpl;
import com.areatecnica.sigf.entities.TipoCargo;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerCargoBus")
@ViewScoped
public class ReportControllerCargoBus implements Serializable {

    private List<TipoCargo> items;
    private TipoCargo selected;
    private Date desde;
    private Date hasta;
    private Date fecha; 
    private int mes;
    private int anio;
    private String informe = "inf-detalle_cargo_bus";

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/01");
    /**
     * Creates a new instance of ReportControllerCargoBus
     */
    public ReportControllerCargoBus() {
        this.items = new ITipoCargoDaoImpl().findAll();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);
        setFecha();
        System.err.println("primera fecha: " + this.fecha);
        this.desde = this.fecha;
        this.hasta = this.fecha;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("tipoCargo", selected.getTipoCargoId());
        map.put("desde", desde);
        map.put("hasta", hasta);

        return map;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getInforme() {
        return informe;
    }

    public List<TipoCargo> getItems() {
        return items;
    }

    public void setItems(List<TipoCargo> items) {
        this.items = items;
    }

    public TipoCargo getSelected() {
        return selected;
    }

    public void setSelected(TipoCargo selected) {
        this.selected = selected;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public void setFecha() {
        try {

            this.fecha = this.sdf.parse(this.anio + "/" + this.mes + "/01");
            System.err.println("NUEVA FECHA:" + this.fecha);
        } catch (ParseException ex) {
        }
    }
}
