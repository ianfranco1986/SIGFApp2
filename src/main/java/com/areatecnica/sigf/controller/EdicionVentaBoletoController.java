package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.*;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.models.VentaBoletoRecaudacionDataModel;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

@Named(value = "edicionVentaBoletoController")
@ViewScoped
public class EdicionVentaBoletoController implements Serializable {

    private Date fecha;
    private final int totalRecaudacion = 0;

    private List<VentaBoleto> items;
    private List<InventarioCaja> inventarioCajaItems;
    private List<CajaRecaudacion> cajaRecaudacionItems;
    private List<Bus> busItems;
    private List<Boleto> boletoItems;
    private VentaBoleto ventaBoleto;
    private VentaBoletoRecaudacionDataModel model;
    private InventarioCaja inventario;
    private CajaRecaudacion cajaRecaudacion;
    private Egreso boleto;
    private Bus bus;
    private Boleto selectedBoleto;
    private Boleto newBoleto;

    private final NumberFormat nf = NumberFormat.getInstance();

    private final int totalAdministracion = 0;
    private final int totalCuotaExtra = 0;
    private final int totalBoletos = 0;
    private final int totalImposiciones = 0;
    private int serie = 0;

    public EdicionVentaBoletoController() {

    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.busItems = new BusDaoImpl().findByProceso(new ProcesoRecaudacionDaoImpl().findById(2));
        this.boletoItems = new BoletoDaoImpl().findAll();

        this.cajaRecaudacionItems = new CajaRecaudacionDaoImpl().findAllActive();

    }

    public void load() {
        if (this.bus != null && this.selectedBoleto != null) {

            this.ventaBoleto = new VentaBoletoDaoImpl().findByBusBoletoEstado(bus, selectedBoleto);

            if (this.ventaBoleto != null) {
                JsfUtil.addSuccessMessage("Se encontró la Venta Nº : " + this.ventaBoleto.getVentaBoletoId());
            } else {
                JsfUtil.addErrorMessage("No se han encontrado ventas para el par bus/boleto seleccionado");
            }

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la caja");
        }

    }

    public void save() {
        if (this.inventario != null) {
            this.inventario.setInventarioCajaEstado(Boolean.TRUE);
            InventarioCaja ic = new InventarioCajaDaoImpl().update(inventario);
            this.ventaBoleto.setVentaBoletoIdInventarioCaja(inventario);
            VentaBoleto aux = new VentaBoletoDaoImpl().update(ventaBoleto);

            if (aux != null) {
                JsfUtil.addSuccessMessage("Se ha actualizado la serie: "+this.inventario.getInventarioCajaSerie()+" ("+this.inventario.getInventarioCajaIdInventarioInterno().getInventarioInternoIdBoleto().getBoletoNombre()+") para la Venta Nº "+this.ventaBoleto.getVentaBoletoId());
                this.ventaBoleto = null; 
                this.inventario = null; 
                this.newBoleto = null; 
                this.cajaRecaudacion = null; 
            }
        } else {
            JsfUtil.addErrorMessage("Error al guardar los cambios");
        }
    }

    public void findInventario() {
        if (this.newBoleto != null && this.cajaRecaudacion != null) {
            this.inventarioCajaItems = new InventarioCajaDaoImpl().findByBoletoEstado(cajaRecaudacion, newBoleto, Boolean.FALSE);
        } 
    }

    public void setCajaRecaudacionItems(List<CajaRecaudacion> cajaRecaudacionItems) {
        this.cajaRecaudacionItems = cajaRecaudacionItems;
    }

    public List<CajaRecaudacion> getCajaRecaudacionItems() {
        return cajaRecaudacionItems;
    }

    public void setInventarioCajaItems(List<InventarioCaja> inventarioCajaItems) {
        this.inventarioCajaItems = inventarioCajaItems;
    }

    public List<InventarioCaja> getInventarioCajaItems() {
        return inventarioCajaItems;
    }

    public void setInventario(InventarioCaja inventario) {
        this.inventario = inventario;
    }

    public InventarioCaja getInventario() {
        return inventario;
    }

    public void setNewBoleto(Boleto newBoleto) {
        this.newBoleto = newBoleto;
    }

    public Boleto getNewBoleto() {
        return newBoleto;
    }

    public void setVentaBoleto(VentaBoleto ventaBoleto) {
        this.ventaBoleto = ventaBoleto;
    }

    public VentaBoleto getVentaBoleto() {
        return ventaBoleto;
    }

    public void setSelectedBoleto(Boleto selectedBoleto) {
        this.selectedBoleto = selectedBoleto;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Boleto getSelectedBoleto() {
        return selectedBoleto;
    }

    public List<Boleto> getBoletoItems() {
        return boletoItems;
    }

    public Bus getBus() {
        return bus;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getSerie() {
        return serie;
    }

    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    public List<Bus> getBusItems() {
        return busItems;
    }

    public void setModel(VentaBoletoRecaudacionDataModel model) {
        this.model = model;
    }

    public VentaBoletoRecaudacionDataModel getModel() {
        return model;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public int getTotalImposiciones() {
        return totalImposiciones;
    }

    public int getTotalCuotaExtra() {
        return totalCuotaExtra;
    }

    public int getTotalBoletos() {
        return totalBoletos;
    }

    public int getTotalAdministracion() {
        return totalAdministracion;
    }

    public void onRowEdit(RowEditEvent event) {
        VentaBoleto temp = null;
        try {
            temp = (VentaBoleto) event.getObject();

            new VentaBoletoDaoImpl().update(temp);
            JsfUtil.addSuccessMessage("Se ha actualizado la Venta de Boleto: " + temp.getVentaBoletoNumeroBoleta());

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ha ocurrido un error al registrar los cambios");
        }

    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void loadGuia() {

    }

    public void findFolio() {

    }

    public void handleBusChange() {

    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

}
