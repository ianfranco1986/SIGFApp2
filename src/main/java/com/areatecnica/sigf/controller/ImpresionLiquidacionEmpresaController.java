/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.EmpresaDaoImpl;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.util.LocalDateConverter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author ianfr
 */
@Named(value = "impresionLiquidacionEmpresaController")
@ViewScoped
public class ImpresionLiquidacionEmpresaController implements Serializable {

    private Empresa selected;
    private List<Empresa> empresaItems;
    private List<Empresa> selectedEmpresaItems;

    private final String grupal = "inf-liquidacion_empresa_grupal";

    private String informe;
    
    private LocalDate date;
    private LocalDateConverter dc;

    private boolean generarLiquidacionNoMovimiento;
//    private boolean 

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private NumberFormat nf = NumberFormat.getInstance();

    private List<Integer> keys;

    private boolean caratula = Boolean.TRUE;
    private boolean ingresosbus = Boolean.TRUE;
    private boolean minutos = Boolean.TRUE;
    private boolean boletos = Boolean.TRUE;

    /**
     * Creates a new instance of LiquidacionEmpresaLoteController
     */
    public ImpresionLiquidacionEmpresaController() {
    }

    @PostConstruct
    public void init() {
        this.setDate(LocalDate.now());

        this.informe = "inf-liquidacion_empresa_general";

        this.empresaItems = new EmpresaDaoImpl().findByNandu();
    }

    public Map<String, Object> getSingleMap() {
        this.keys = new ArrayList<>();
        Map<String, Object> map = new HashMap();

        if (this.selected != null) {

            map.put("fechaCompleta", this.dc.getMonthYearString());

            map.put("desde", this.dc.getFirstDateOfMonth());//
            map.put("hasta", this.dc.getLastDayOfMonth());//
            map.put("empresa", this.selected.getEmpresaNombre());
            map.put("codigo", this.selected.getEmpresaId());
            
            map.put("caratula", caratula);
            map.put("boletos", boletos);
            map.put("minutos", minutos);
            map.put("ingresosbus", ingresosbus);

            return map;
        }

        return null;
    }

    public Map<String, Object> getMap() {
        this.keys = new ArrayList<>();
        Map<String, Object> map = new HashMap();

        String aux = this.keys.toString().replace('[', ' ').replace(']', ' ');

        map.put("fechaCompleta", this.dc.getMonthYearString());
        map.put("list", "");
        map.put("desde", this.dc.getFirstDateOfMonth());//
        map.put("hasta", this.dc.getLastDayOfMonth());//

        return map;
    }

    public void setSelected(Empresa selected) {
        this.selected = selected;
    }

    public Empresa getSelected() {
        return selected;
    }

    public String getGrupal() {
        return grupal;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public List<Empresa> getSelectedEmpresaItems() {
        return selectedEmpresaItems;
    }

    public void setSelectedEmpresaItems(List<Empresa> listEmpresa) {
        this.selectedEmpresaItems = listEmpresa;
    }

    public List<Empresa> getEmpresaItems() {
        return empresaItems;
    }

    public void setEmpresaItems(List<Empresa> listEmpresa) {
        this.empresaItems = listEmpresa;
    }

    public void setGenerarLiquidacionNoMovimiento(boolean generarLiquidacionNoMovimiento) {
        this.generarLiquidacionNoMovimiento = generarLiquidacionNoMovimiento;
    }

    public boolean isGenerarLiquidacionNoMovimiento() {
        return generarLiquidacionNoMovimiento;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public NumberFormat getNf() {
        return nf;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

    public boolean getCaratula() {
        return caratula;
    }

    public void setCaratula(boolean caratula) {
        this.caratula = caratula;
    }

    public boolean getIngresosbus() {
        return ingresosbus;
    }

    public void setIngresosbus(boolean ingresosbus) {
        this.ingresosbus = ingresosbus;
    }

    public boolean getMinutos() {
        return minutos;
    }

    public void setMinutos(boolean minutos) {
        this.minutos = minutos;
    }

    public boolean getBoletos() {
        return boletos;
    }

    public void setBoletos(boolean boletos) {
        this.boletos = boletos;
    }

}
