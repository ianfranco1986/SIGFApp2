/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.AdministradorBus;
import com.areatecnica.sigf.entities.AdministradorFlota;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.util.CurrentDate;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "estadisticaConsumoCombustibleoController")
@ViewScoped
public class EstadisticaConsumoCombustibleController implements Serializable {

    private Usuario currentUser;
    private List<Bus> items;
    private Bus selected;
    private List<VentaCombustible> ventasItems;
    private IVentaCombustibleDao ventaCombustibleDao;
    private Date fecha;
    private int mes;
    private int anio;
    private CurrentDate currentDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private LineChartModel chartModel;

    /**
     * Creates a new instance of InformeRegistroBoletoController
     */
    public EstadisticaConsumoCombustibleController() {
        this.items = new ArrayList<>();
        this.currentUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");

        Administrador admin = this.currentUser.getAdministradorList().get(0);

        if (!admin.getAdministradorBusList().isEmpty()) {
            for (AdministradorBus b : admin.getAdministradorBusList()) {
                this.items.add(b.getAdministradorBusIdBus());
            }
        } else if (!admin.getAdministradorFlotaList().isEmpty()) {
            for (AdministradorFlota f : admin.getAdministradorFlotaList()) {
                this.items.addAll(f.getAdministradorFlotaIdFlota().getBusList());
            }
        }

        Calendar calendar = GregorianCalendar.getInstance();
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

        try {
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.currentDate = new CurrentDate(1, mes, anio);
        } catch (ParseException ex) {
            Logger.getLogger(EstadisticaConsumoCombustibleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.chartModel = new LineChartModel();
        this.chartModel.setTitle("Consumo de Combustible");
        this.chartModel.setLegendPosition("e");

        Axis yAxis = chartModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        
    }

    public void handleBusChange() {
        if (this.selected != null) {
            this.ventaCombustibleDao = new IVentaCombustibleDaoImpl();
            this.ventasItems = this.ventaCombustibleDao.findByBusBetweenDates(selected, this.fecha, this.currentDate.getMaxDate());
            setChart();
        }
    }

    public void load() {
        if (this.selected != null) {
            //this.model = new RegistroBoletoTableModel(guia);
        }
    }

    public List<Bus> getItems() {
        return items;
    }

    public void setItems(List<Bus> items) {
        this.items = items;
    }

    public Bus getSelected() {
        return selected;
    }

    public void setSelected(Bus selected) {
        this.selected = selected;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getMes() {
        return mes;
    }

    public List<VentaCombustible> getVentasItems() {
        return ventasItems;
    }

    public void setVentasItems(List<VentaCombustible> ventasItems) {
        this.ventasItems = ventasItems;
    }

    public void setFecha() {
        try {
            System.err.println("NUEVA FECHA:");
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.currentDate = new CurrentDate(1, mes, anio);

            handleBusChange();
        } catch (ParseException ex) {
            Logger.getLogger(EstadisticaConsumoCombustibleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public LineChartModel getChartModel() {
        return chartModel;
    }

    private void setChart() {
        this.chartModel = initLinearModel();
        this.chartModel.setTitle("Consumo de Combustible");
        this.chartModel.setLegendPosition("e");

        Axis yAxis = chartModel.getAxis(AxisType.Y);
        Axis xAxis = chartModel.getAxis(AxisType.X);
        //yAxis.setMax(800000);
        yAxis.setMin(0);
        xAxis.setMax(1);
        xAxis.setMax(31);

    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries serie = new LineChartSeries();
        serie.setLabel("$ Consumo diario");

        for (VentaCombustible v : this.ventasItems) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(v.getVentaCombustibleFecha());
            System.err.println("DIA: "+calendar.get(Calendar.DATE)+" vALOR:"+ v.getVentaCombustibleTotal());
            serie.set(calendar.get(Calendar.DATE), v.getVentaCombustibleTotal());
        }

        model.addSeries(serie);
        System.err.println("consumo diario");
        return model;
    }

}
