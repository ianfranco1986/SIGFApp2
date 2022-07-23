/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.BoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.BusDaoImpl;
import com.areatecnica.sigf.dao.impl.GuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.ProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.RegistroMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.sigf.dao.impl.VentaBoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.VentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.util.LocalDateConverter;
import javax.inject.Named;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "drController")
@SessionScoped
public class DashboardRecaudacionController implements Serializable {

    private Bus selectedBus;
    private Trabajador selectedTrabajador;
    private Guia selectedGuia;
    private Recaudacion selected;

    private List<Bus> busItems;
    private List<Trabajador> trabajadorItems;
    private List<VentaCombustible> ventasCombustibleItems;
    private List<VentaCombustible> selectedVentaCombustibles;
    private List<RegistroMinuto> minutosItems;
    private List<VentaBoleto> ventasBoletosItems;
    private List<Boleto> boletosItems;
    private List<Egreso> egresoItems;
    private List<RecaudacionGuia> recaudacionItems;

    private int folio = 171797;
    private int totalRecaudacion = 0;
    private int totalGuias = 0;
    private int totalCombustible = 0;
    private int totalMinutos = 0;
    private int totalExtras = 0;

    private int deudaMinutos = 0;
    private int deudaCombustible = 0;

    private int administracion = 0;
    private int cuotaExtra = 0;
    private int imposiciones = 0;

    private LocalDate date;
    private LocalDateConverter dc;
    private boolean flag = Boolean.TRUE;

    private static final ProcesoRecaudacion procesoRecaudacion = new ProcesoRecaudacionDaoImpl().findById(2);
    private final NumberFormat nf = NumberFormat.getInstance();

    /**
     * Creates a new instance of DashboardRecaudacionController
     */
    public DashboardRecaudacionController() {
    }

    @PostConstruct
    public void init() {
        setDate(LocalDate.now());
        this.busItems = new BusDaoImpl().findByProceso(procesoRecaudacion);
        this.trabajadorItems = new TrabajadorDaoImpl().findNandu();
        this.boletosItems = new BoletoDaoImpl().findByCuenta(JsfUtil.getCurrentUser().getUsuarioIdCuenta());
//        this.egresoItems = new EgresoDaoImpl().findAllByCuenta(JsfUtil.getCurrentUser().getUsuarioIdCuenta());

        this.ventasBoletosItems = new ArrayList<>();
        this.ventasCombustibleItems = new ArrayList<>();
        this.minutosItems = new ArrayList<>();
    }

    public void handleFolioChange() {
        if (this.folio > 0) {
            this.selectedGuia = new GuiaDaoImpl().findByCuentaFolio(JsfUtil.getCurrentUser().getUsuarioIdCuenta(), folio);

            if (this.selectedGuia != null) {
                JsfUtil.addSuccessMessage("Se ha encontrado la Guía");
                this.flag = Boolean.TRUE;
                this.selectedBus = this.selectedGuia.getGuiaIdBus();
                this.selectedTrabajador = this.selectedGuia.getGuiaIdTrabajador();
                loadInfoBus();
            } else {
                JsfUtil.addWarningMessage("No se ha encontrado la Guía");
                this.flag = Boolean.FALSE;
                this.selectedGuia = new Guia();
                this.selectedBus = new Bus();
                this.selectedTrabajador = new Trabajador();
                this.ventasCombustibleItems = new ArrayList();
                this.ventasBoletosItems = new ArrayList<>();
                this.deudaCombustible = 0;
                this.deudaMinutos = 0;
            }
        }
    }

    public void handleBusChange() {
        if (this.selectedBus != null) {
            loadInfoBus();
        }
    }

    public void loadInfoBus() {
        this.deudaCombustible = 0;
        this.deudaMinutos = 0;
        this.ventasBoletosItems = new ArrayList<>();

        this.minutosItems = new RegistroMinutoDaoImpl().findByBusSinRecaudar(selectedBus);
        this.ventasCombustibleItems = new VentaCombustibleDaoImpl().findByBusSinRecaudar(selectedBus);
        

        for (RegistroMinuto rm : this.minutosItems) {
            this.deudaMinutos += rm.getRegistroMinutoMonto();
        }

        for (VentaCombustible vc : this.ventasCombustibleItems) {
            this.deudaCombustible += vc.getVentaCombustibleTotal();
        }

        for (Boleto b : this.boletosItems) {
            VentaBoleto v = new VentaBoletoDaoImpl().findByBusGroupByBoleto(this.selectedBus, b);
            if (v != null) {
                ventasBoletosItems.add(v);
            }
        }

        ventasBoletosItems.sort(Comparator.comparing(VentaBoleto::getVentaBoletoFecha));
    }

    public void addVentaCombustible(VentaCombustible v) {
        if (v != null) {
            this.selectedVentaCombustibles.add(v);
        }
    }

    public Bus getSelectedBus() {
        return selectedBus;
    }

    public void setSelectedBus(Bus selectedBus) {
        this.selectedBus = selectedBus;
    }

    public Trabajador getSelectedTrabajador() {
        return selectedTrabajador;
    }

    public void setSelectedTrabajador(Trabajador selectedTrabajador) {
        this.selectedTrabajador = selectedTrabajador;
    }

    public Guia getSelectedGuia() {
        return selectedGuia;
    }

    public void setSelectedGuia(Guia selectedGuia) {
        this.selectedGuia = selectedGuia;
    }

    public Recaudacion getSelected() {
        return selected;
    }

    public void setSelected(Recaudacion selected) {
        this.selected = selected;
    }

    public List<Bus> getBusItems() {
        return busItems;
    }

    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    public List<Trabajador> getTrabajadorItems() {
        return trabajadorItems;
    }

    public void setTrabajadorItems(List<Trabajador> trabajadorItems) {
        this.trabajadorItems = trabajadorItems;
    }

    public List<VentaCombustible> getVentasCombustibleItems() {
        return ventasCombustibleItems;
    }

    public void setVentasCombustibleItems(List<VentaCombustible> ventasCombustibleItems) {
        this.ventasCombustibleItems = ventasCombustibleItems;
    }

    public List<RegistroMinuto> getMinutosItems() {
        return minutosItems;
    }

    public void setMinutosItems(List<RegistroMinuto> minutosItems) {
        this.minutosItems = minutosItems;
    }

    public List<VentaBoleto> getVentasBoletosItems() {
        return ventasBoletosItems;
    }

    public void setVentasBoletosItems(List<VentaBoleto> ventasBoletosItems) {
        this.ventasBoletosItems = ventasBoletosItems;
    }

    public List<Boleto> getBoletosItems() {
        return boletosItems;
    }

    public void setBoletosItems(List<Boleto> boletosItems) {
        this.boletosItems = boletosItems;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getTotalGuias() {
        return totalGuias;
    }

    public void setTotalGuias(int totalGuias) {
        this.totalGuias = totalGuias;
    }

    public int getTotalCombustible() {
        return totalCombustible;
    }

    public void setTotalCombustible(int totalCombustible) {
        this.totalCombustible = totalCombustible;
    }

    public int getTotalMinutos() {
        return totalMinutos;
    }

    public void setTotalMinutos(int totalMinutos) {
        this.totalMinutos = totalMinutos;
    }

    public int getTotalExtras() {
        return totalExtras;
    }

    public void setTotalExtras(int totalExtras) {
        this.totalExtras = totalExtras;
    }

    public int getDeudaMinutos() {
        return deudaMinutos;
    }

    public void setDeudaMinutos(int deudaMinutos) {
        this.deudaMinutos = deudaMinutos;
    }

    public int getDeudaCombustible() {
        return deudaCombustible;
    }

    public List<Egreso> getEgresoItems() {
        return egresoItems;
    }

    public void setEgresoItems(List<Egreso> egresoItems) {
        this.egresoItems = egresoItems;
    }

    public List<RecaudacionGuia> getRecaudacionItems() {
        return recaudacionItems;
    }

    public void setRecaudacionItems(List<RecaudacionGuia> recaudacionItems) {
        this.recaudacionItems = recaudacionItems;
    }

    public int getAdministracion() {
        return administracion;
    }

    public void setAdministracion(int administracion) {
        this.administracion = administracion;
    }

    public int getCuotaExtra() {
        return cuotaExtra;
    }

    public void setCuotaExtra(int cuotaExtra) {
        this.cuotaExtra = cuotaExtra;
    }

    public int getImposiciones() {
        return imposiciones;
    }

    public void setImposiciones(int imposiciones) {
        this.imposiciones = imposiciones;
    }

    public void setDeudaCombustible(int deudaCombustible) {
        this.deudaCombustible = deudaCombustible;
    }

    public List<VentaCombustible> getSelectedVentaCombustibles() {
        return selectedVentaCombustibles;
    }

    public void setSelectedVentaCombustibles(List<VentaCombustible> selectedVentaCombustibles) {
        this.selectedVentaCombustibles = selectedVentaCombustibles;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDateConverter getDc() {
        return dc;
    }

    public void setDc(LocalDateConverter dc) {
        this.dc = dc;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }
    
    

}
