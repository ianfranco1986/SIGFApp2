/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.reports.InformeConsumoCombustibleController;
import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IUnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "reportControllerIngresosBus")
@ViewScoped
public class ReportControllerIngresosBus implements Serializable {

    private List<Bus> items;
    private List<Bus> selectedItems;
    private List<UnidadNegocio> unidadItems;
    private Bus selected;
    private Date fecha;
    private Date desde;
    private Date hasta;
    private String informe = "inf-resumen_ingresos_bus";
    private Flota flota;
    private UnidadNegocio unidadNegocio;
    private int mes;
    private int anio;
    private String list;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/01");
    private List<Integer> array;
    private Boolean tipoInforme;
    //private 

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerIngresosBus() {

    }

    @PostConstruct
    private void init() {
        this.tipoInforme = Boolean.TRUE;
        //this.items = new IBusDaoImpl().findAll();
        this.unidadItems = new IUnidadNegocioDaoImpl().findAll();
        this.unidadItems.remove(0);
        this.unidadNegocio = this.unidadItems.get(0);

        this.items = this.unidadNegocio.getBusList();

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

        Bus bus = null;
        if (!selectedItems.isEmpty()) {
            System.err.println("TAMAÑO DE LOS ITEMS SELECTOS: " + selectedItems.size());
            bus = selectedItems.get(0);
            list = String.valueOf(bus.getBusId());
            array = new ArrayList<>();

            for (Bus b : selectedItems) {
                list = list + "," + b.getBusId();
                array.add(b.getBusId());
            }
        } else {
            bus = items.get(0);

            list = String.valueOf(bus.getBusId());
            array = new ArrayList<>();

            for (Bus b : items) {
                list = list + "," + b.getBusId();
                array.add(b.getBusId());
            }
        }

        map.put("fechaCompleta", getFechaCompleta());
        map.put("desde", desde);
        map.put("hasta", hasta);
        map.put("list", array);

        return map;
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

    private String getFechaCompleta() {
        String fechaCompleta = "";
        switch (mes) {
            case 1:
                fechaCompleta = "Enero ";
                break;
            case 2:
                fechaCompleta = "Febrero ";
                break;
            case 3:
                fechaCompleta = "Marzo ";
                break;
            case 4:
                fechaCompleta = "Abril ";
                break;
            case 5:
                fechaCompleta = "Mayo ";
                break;
            case 6:
                fechaCompleta = "Junio ";
                break;
            case 7:
                fechaCompleta = "Julio ";
                break;
            case 8:
                fechaCompleta = "Agosto ";
                break;
            case 9:
                fechaCompleta = "Septiembre ";
                break;
            case 10:
                fechaCompleta = "Octubre ";
                break;
            case 11:
                fechaCompleta = "Noviembre ";
                break;
            case 12:
                fechaCompleta = "Diciembre ";
                break;

        }

        return fechaCompleta + " " + anio;
    }

    public void setTipoInforme(Boolean tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public Boolean getTipoInforme() {
        return tipoInforme;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Bus getSelected() {
        return selected;
    }

    public void setSelected(Bus selected) {
        this.selected = selected;
    }

    public List<Bus> getItems() {
        return items;
    }

    public void setItems(List<Bus> items) {
        this.items = items;
    }

    public List<Bus> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<Bus> selectedItems) {
        this.selectedItems = selectedItems;
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

    public void setUnidadItems(List<UnidadNegocio> unidadItems) {
        this.unidadItems = unidadItems;
    }

    public List<UnidadNegocio> getUnidadItems() {
        return unidadItems;
    }

    public void setFlota(Flota flota) {
        this.flota = flota;
    }

    public Flota getFlota() {
        return flota;
    }

    public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public UnidadNegocio getUnidadNegocio() {
        return unidadNegocio;
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

    public void handleFlotaChange() {
        this.items = new ArrayList<>();
        this.selectedItems = new ArrayList<>();
        if (this.flota != null) {
            if (this.unidadNegocio != null) {
                this.items = new IBusDaoImpl().findAllByFlotaUnidad(flota, unidadNegocio);
            } else {
                this.items = new IBusDaoImpl().findAllByFlota(flota);
            }
        } else {
            if (this.unidadNegocio != null) {
                this.items = new IBusDaoImpl().findByUnidad(unidadNegocio);
            } else {
                this.items = new IBusDaoImpl().findAll();
            }
        }
    }

    public void handleUnidadChange() {
        this.selectedItems = new ArrayList<>();
        if (this.unidadNegocio != null) {
            if (this.flota != null) {
                this.items = new IBusDaoImpl().findAllByFlotaUnidad(flota, unidadNegocio);
            } else {
                this.items = new IBusDaoImpl().findByUnidad(unidadNegocio);
            }
        } else if (this.flota != null) {
            this.items = new IBusDaoImpl().findAllByFlota(flota);
        } else {
            this.items = new IBusDaoImpl().findAll();
        }
    }

    public void setFecha() {
        try {

            this.fecha = this.sdf.parse(this.anio + "/" + this.mes + "/01");
        } catch (ParseException ex) {
            Logger.getLogger(InformeConsumoCombustibleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
