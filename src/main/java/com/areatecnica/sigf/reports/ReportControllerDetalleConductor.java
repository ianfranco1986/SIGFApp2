/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.util.LocalDateConverter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerDetalleConductor")
@ViewScoped
public class ReportControllerDetalleConductor implements Serializable {

    private LocalDate date;
    private LocalDateConverter dc;
    private String informe = "inf-detalle_imposiciones_conductor";
    private String list;
    private List<Integer> array;
    private Boolean tipoInforme;
    private List<Trabajador> itemsTrabajador;
    private List<Trabajador> selectedTrabajador;
    //private 

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerDetalleConductor() {

    }

    @PostConstruct
    private void init() {
        this.date = LocalDate.now();
        this.tipoInforme = Boolean.TRUE;
        this.itemsTrabajador = new TrabajadorDaoImpl().findNandu();
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        Trabajador trabajador = null;
        if (!selectedTrabajador.isEmpty()) {
            trabajador = selectedTrabajador.get(0);
            list = String.valueOf(trabajador.getTrabajadorId());
            array = new ArrayList<>();

            for (Trabajador b : selectedTrabajador) {
                list = list + "," + b.getTrabajadorId();
                array.add(b.getTrabajadorId());
            }
        } else {
            trabajador = itemsTrabajador.get(0);

            list = String.valueOf(trabajador.getTrabajadorId());
            array = new ArrayList<>();

            for (Trabajador b : itemsTrabajador) {
                list = list + "," + b.getTrabajadorId();
                array.add(b.getTrabajadorId());
            }
        }

        map.put("fechaCompleta", this.dc.getMonthYearString());
        map.put("desde", this.dc.getDate());
        map.put("hasta", this.dc.getLastDayOfMonth());
        map.put("list", array);

        return map;
    }

    public void setSelectedTrabajador(List<Trabajador> selectedTrabajador) {
        this.selectedTrabajador = selectedTrabajador;
    }

    public List<Trabajador> getSelectedTrabajador() {
        return selectedTrabajador;
    }

    public List<Trabajador> getItemsTrabajador() {
        return itemsTrabajador;
    }

    public void setItemsTrabajador(List<Trabajador> itemsTrabajador) {
        this.itemsTrabajador = itemsTrabajador;
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

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

}
