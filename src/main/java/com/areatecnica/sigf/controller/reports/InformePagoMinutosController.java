/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.dao.IRegistroMinutoDao;
import com.areatecnica.sigf.dao.impl.RegistroMinutoDaoImpl;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.util.CurrentDate;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "informePagoMinutosController")
@ViewScoped
public class InformePagoMinutosController implements Serializable {

    private final Usuario currentUser;
    private List<Bus> items;
    private Bus selected;
    private List<RegistroMinuto> registroMinutosItems;
    private IRegistroMinutoDao registoMinutoDao;
    private Date fecha;
    private int mes;
    private int anio;
    private CurrentDate currentDate;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private LineChartModel chartModel;
    private Map sumas;

    /**
     * Creates a new instance of InformeRegistroBoletoController
     */
    public InformePagoMinutosController() {
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
            Logger.getLogger(InformePagoMinutosController.class.getName()).log(Level.SEVERE, null, ex);
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
            this.registoMinutoDao = new RegistroMinutoDaoImpl();
            this.registroMinutosItems = this.registoMinutoDao.findByBusPagaDates(selected, this.fecha, this.currentDate.getMaxDate());
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

    public Map getSumas() {
        return sumas;
    }

    public List<RegistroMinuto> getVentasItems() {
        return registroMinutosItems;
    }

    public void setVentasItems(List<RegistroMinuto> ventasItems) {
        this.registroMinutosItems = ventasItems;
    }

    public void setFecha() {
        try {
            System.err.println("NUEVA FECHA:");
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.currentDate = new CurrentDate(1, mes, anio);

            handleBusChange();
        } catch (ParseException ex) {
            Logger.getLogger(InformePagoMinutosController.class.getName()).log(Level.SEVERE, null, ex);
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

        this.sumas = new HashMap();

        for (RegistroMinuto v : this.registroMinutosItems) {
            int val = 0;

            if (sumas.containsKey(v.getRegistroMinutoFechaMinuto())) {
                val = (Integer) this.sumas.get(v.getRegistroMinutoFechaMinuto());
                val = val + v.getRegistroMinutoMonto();
                sumas.put(v.getRegistroMinutoFechaMinuto(), val);
            }
        }

        sumas.forEach((k, v) -> {
            Date fecha = (Date) k;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            int valor = (Integer)v;
            serie.set(calendar.get(Calendar.DATE), valor);
            System.out.println("Key: " + k + ": Value: " + v);
        }
        );

        model.addSeries(serie);
        System.err.println("consumo diario");
        return model;
    }

}
