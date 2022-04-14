/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.sigf.entities.Trabajador;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Named(value = "reportControllerDetalleRecaudacionConductor")
@ViewScoped
public class ReportControllerDetalleRecaudacionConductor implements Serializable {

    private Date fecha;
    private Date desde;
    private Date hasta;
    private String informe = "inf-detalle_recaudacion_conductor";
    private int mes;
    private int anio;
    private String list;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/01");
    private List<Integer> array;
    private Boolean tipoInforme;
    private List<Trabajador> itemsTrabajador;
    private List<Trabajador> selectedTrabajador;
    //private 

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportControllerDetalleRecaudacionConductor() {

    }

    @PostConstruct
    private void init() {
        this.tipoInforme = Boolean.TRUE;
        //this.items = new IBusDaoImpl().findAll();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);
        setFecha();
        System.err.println("primera fecha: " + this.fecha);
        this.desde = this.fecha;
        this.hasta = this.fecha;
        this.itemsTrabajador = new TrabajadorDaoImpl().findNandu();
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        Trabajador trabajador = null;
        if (!selectedTrabajador.isEmpty()) {
            System.err.println("TAMAÑO DE LOS ITEMS SELECTOS: " + selectedTrabajador.size());
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

        map.put("fechaCompleta", getFechaCompleta());
        map.put("desde", desde);
        map.put("hasta", hasta);
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

    public void setInforme(String informe) {
        this.informe = informe;
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
            this.desde = this.fecha;
            this.hasta = this.fecha;
        } catch (ParseException ex) {
        }
    }

}
