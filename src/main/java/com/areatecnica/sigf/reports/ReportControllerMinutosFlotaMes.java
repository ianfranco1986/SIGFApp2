/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.dao.IFlotaDao;
import com.areatecnica.sigf.dao.impl.IEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.IUnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.util.CurrentDate;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "reportControllerMinutosFlotaMes")
@ViewScoped
public class ReportControllerMinutosFlotaMes implements Serializable {

    private List<Flota> items;
    private List<Empresa> itemsEmpresa;
    private List<Bus> busItems;
    private Empresa selected;
    private IFlotaDao dao;
    private UnidadNegocio unidad;

    private int mes;
    private int anio;
    private String informe;

    /**
     * Creates a new instance of ReportControllerFlotaMes
     */
    public ReportControllerMinutosFlotaMes() {
    }

    @PostConstruct
    private void init() {
        this.busItems = new IUnidadNegocioDaoImpl().findById(2).getBusList();
        this.items = new ArrayList<>();

        this.itemsEmpresa = new IEmpresaDaoImpl().findByNandu();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

    }

    public List<Empresa> getItemsEmpresa() {
        return itemsEmpresa;
    }

    public void setItemsEmpresa(List<Empresa> itemsEmpresa) {
        this.itemsEmpresa = itemsEmpresa;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        CurrentDate fecha = new CurrentDate(1, mes, anio);

        map.put("fecha", fecha.date());
        map.put("flota", selected.getEmpresaId());
        map.put("empresaNombre", selected.getEmpresaNombre());

        return map;
    }

    public List<Flota> getItems() {
        return items;
    }

    public void setItems(List<Flota> items) {
        this.items = items;
    }

    public Empresa getSelected() {
        return selected;
    }

    public void setSelected(Empresa selected) {
        this.selected = selected;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

}
