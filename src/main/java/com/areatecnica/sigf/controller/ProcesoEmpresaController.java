/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.util.LocalDateConverter;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author ianfr
 */
@Named(value = "procesoEmpresaController")
@ViewScoped
public class ProcesoEmpresaController implements Serializable {

    private String informe;

    private LocalDate date;
    private LocalDateConverter dc;

    private boolean generarLiquidacionNoMovimiento;

    private boolean resumenFinal = Boolean.TRUE;
    private boolean resumenIngresos = Boolean.TRUE;
    private boolean resumenEgresos = Boolean.TRUE;
    private boolean saldos = Boolean.TRUE;
    private boolean cuentas = Boolean.TRUE;

    private String informeSaldos = "inf-proceso_empresa_saldos";
    private String informeCtasCtes = "inf-crosstab_ctas_ctes";
    private String informeCtasCtesCompleto = "inf-crosstab_liquidacion_empresa";

    /**
     * Creates a new instance of LiquidacionEmpresaLoteController
     */
    public ProcesoEmpresaController() {
    }

    @PostConstruct
    public void init() {
        this.setDate(LocalDate.now());
        this.informe = "inf-proceso_empresa_general";
    }

    public Map<String, Object> getSingleMap() {
        Map<String, Object> map = new HashMap();

        if (this.date != null) {

            map.put("fechaCompleta", this.dc.getMonthYearString());

            map.put("fecha", this.dc.getFirstDateOfMonth());//
            map.put("desde", this.dc.getFirstDateOfMonth());//
            map.put("hasta", this.dc.getLastDayOfMonth());//

            map.put("resumenIngresos", resumenIngresos);
            map.put("resumenEgresos", resumenEgresos);
            map.put("saldos", saldos);
            map.put("cuentas", cuentas);

            return map;
        }

        return null;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getInformeSaldos() {
        return informeSaldos;
    }

    public void setInformeSaldos(String informeSaldos) {
        this.informeSaldos = informeSaldos;
    }

    public void setInformeCtasCtes(String informeCtasCtes) {
        this.informeCtasCtes = informeCtasCtes;
    }

    public String getInformeCtasCtes() {
        return informeCtasCtes;
    }

    public String getInformeCtasCtesCompleto() {
        return informeCtasCtesCompleto;
    }

    public void setInformeCtasCtesCompleto(String informeCtasCtesCompleto) {
        this.informeCtasCtesCompleto = informeCtasCtesCompleto;
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

    public boolean getResumenFinal() {
        return resumenFinal;
    }

    public void setResumenFinal(boolean resumenFinal) {
        this.resumenFinal = resumenFinal;
    }

    public boolean getResumenIngresos() {
        return resumenIngresos;
    }

    public void setResumenIngresos(boolean resumenIngresos) {
        this.resumenIngresos = resumenIngresos;
    }

    public boolean getResumenEgresos() {
        return resumenEgresos;
    }

    public void setResumenEgresos(boolean resumenEgresos) {
        this.resumenEgresos = resumenEgresos;
    }

    public boolean getSaldos() {
        return saldos;
    }

    public void setSaldos(boolean saldos) {
        this.saldos = saldos;
    }

    public boolean getCuentas() {
        return cuentas;
    }

    public void setCuentas(boolean cuentas) {
        this.cuentas = cuentas;
    }

}
