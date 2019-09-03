/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.IAbonoBusDao;
import com.areatecnica.sigf.dao.ICargoBusDao;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.models.AbonoBusDataModel;
import com.areatecnica.sigf.models.CargoBusDataModel;
import com.areatecnica.sigf.models.RecaudacionLiquidacionEmpresaDataModel;
import com.areatecnica.sigf.util.CurrentDate;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;

/**
 *
 * @author ianfr
 */
@Named(value = "registroLiquidacionEmpresaController")
@SessionScoped
public class RegistroLiquidacionEmpresa implements Serializable {

    private Empresa empresa;
    private CargoLiquidacion cargoLiquidacion;
    private AbonoLiquidacion abonoLiquidacion;
    private LiquidacionEmpresa liquidacionEmpresa;

    private RecaudacionLiquidacionEmpresaDataModel model;

    private CargoBus cargoBus;
    private AbonoBus abonoBus;
    private CargoBusDataModel cargoDataModel;
    private AbonoBusDataModel abonoDataModel;
    private ICargoBusDao cargoBusDao;
    private IAbonoBusDao abonoBusDao;

    private int mes;
    private int anio;
    private int totalAdministracion = 0;
    private int totalCuotaExtra = 0;
    private int totalBoletos = 0;
    private int totalImposiciones = 0;
    private int totalRecaudacion = 0;
    private int totalMinutos = 0;
    private int totalAbonos = 0;
    private int totalCargos = 0;
    private int saldo = 0;

    private Date desde;
    private Date hasta;
    private Date fecha;
    private CurrentDate currentDate;
    private String informe;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private NumberFormat nf = NumberFormat.getInstance();

    /**
     * Creates a new instance of RegistroLiquidacionEmpresa
     */
    public RegistroLiquidacionEmpresa() {
    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.currentDate = new CurrentDate();

        Calendar calendar = Calendar.getInstance();

        this.anio = calendar.get(Calendar.YEAR);
        this.mes = calendar.get(Calendar.MONTH) + 1;

        this.informe = "inf-detalle_ingresos_bus_empty";

        System.err.println("primera fecha: " + this.fecha);
        setFecha();
        this.desde = this.fecha;
        this.hasta = this.fecha;
    }

    public RecaudacionLiquidacionEmpresaDataModel getModel() {
        return model;
    }

    public void setModel(RecaudacionLiquidacionEmpresaDataModel model) {
        this.model = model;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public CargoLiquidacion getCargoLiquidacion() {
        return cargoLiquidacion;
    }

    public void setCargoLiquidacion(CargoLiquidacion cargoLiquidacion) {
        this.cargoLiquidacion = cargoLiquidacion;
    }

    public AbonoLiquidacion getAbonoLiquidacion() {
        return abonoLiquidacion;
    }

    public void setAbonoLiquidacion(AbonoLiquidacion abonoLiquidacion) {
        this.abonoLiquidacion = abonoLiquidacion;
    }

    public LiquidacionEmpresa getLiquidacionEmpresa() {
        return liquidacionEmpresa;
    }

    public void setLiquidacionEmpresa(LiquidacionEmpresa liquidacionEmpresa) {
        this.liquidacionEmpresa = liquidacionEmpresa;
    }

    public CargoBus getCargoBus() {
        return cargoBus;
    }

    public void setCargoBus(CargoBus cargoBus) {
        this.cargoBus = cargoBus;
    }

    public CargoBusDataModel getCargoDataModel() {
        return cargoDataModel;
    }

    public void setCargoDataModel(CargoBusDataModel cargoDataModel) {
        this.cargoDataModel = cargoDataModel;
    }

    public AbonoBusDataModel getAbonoDataModel() {
        return abonoDataModel;
    }

    public void setAbonoDataModel(AbonoBusDataModel abonoDataModel) {
        this.abonoDataModel = abonoDataModel;
    }

    public int getTotalAdministracion() {
        return totalAdministracion;
    }

    public void setTotalAdministracion(int totalAdministracion) {
        this.totalAdministracion = totalAdministracion;
    }

    public int getTotalCuotaExtra() {
        return totalCuotaExtra;
    }

    public void setTotalCuotaExtra(int totalCuotaExtra) {
        this.totalCuotaExtra = totalCuotaExtra;
    }

    public int getTotalBoletos() {
        return totalBoletos;
    }

    public void setTotalBoletos(int totalBoletos) {
        this.totalBoletos = totalBoletos;
    }

    public int getTotalImposiciones() {
        return totalImposiciones;
    }

    public void setTotalImposiciones(int totalImposiciones) {
        this.totalImposiciones = totalImposiciones;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getTotalMinutos() {
        return totalMinutos;
    }

    public void setTotalMinutos(int totalMinutos) {
        this.totalMinutos = totalMinutos;
    }

    public int getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalAbonos(int totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public int getTotalCargos() {
        return totalCargos;
    }

    public void setTotalCargos(int totalCargos) {
        this.totalCargos = totalCargos;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CurrentDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(CurrentDate currentDate) {
        this.currentDate = currentDate;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        RegistroLiquidacionEmpresa.sdf = sdf;
    }

    public void setFecha() {
        try {

            this.fecha = this.sdf.parse(this.anio + "/" + this.mes + "/01");
        } catch (ParseException ex) {
        }
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

}
