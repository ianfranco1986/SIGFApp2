/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.VentaCombustibleDaoImpl;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "informeConsumoCombustibleFlotaController")
@ViewScoped
public class InformeConsumoCombustibleControllerFlota implements Serializable {

    private final Usuario currentUser;
    private List<Bus> items;
    private Bus selected;
    private List<VentaCombustible> ventasItems;
    private IVentaCombustibleDao ventaCombustibleDao;
    private Date fecha;
    private int mes;
    private int anio;
    private Integer promedioDiario;
    private Object promedioMensual;
    private CurrentDate currentDate;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    private LineChartModel chartModel;
    private AbstractDynamicReport report;
//    private AbstractDynamicReport reportSingle;

    private boolean flota = false;

    /**
     * Creates a new instance of InformeRegistroBoletoController
     */
    public InformeConsumoCombustibleControllerFlota() {
        this.items = new ArrayList<>();
        this.currentUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");

        Administrador admin = this.currentUser.getAdministradorList().get(0);

        if (!admin.getAdministradorBusList().isEmpty()) {
            for (AdministradorBus b : admin.getAdministradorBusList()) {
                this.items.add(b.getAdministradorBusIdBus());
            }
        } else if (!admin.getAdministradorFlotaList().isEmpty()) {
            this.flota = true;
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
        }

        this.chartModel = new LineChartModel();
        this.chartModel.setTitle("Consumo de Combustible x Flota");
        this.chartModel.setLegendPosition("e");

        Axis yAxis = chartModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

    }

    public void handleBusChange() {
        if (this.selected != null) {
            this.ventaCombustibleDao = new VentaCombustibleDaoImpl();
            this.ventasItems = this.ventaCombustibleDao.findByBusBetweenDates(selected, this.fecha, this.currentDate.getMaxDate());
            setChart();
        }
    }

    public void load() {
        if (!this.items.isEmpty()) {
            for (Bus b : this.items) {
                this.ventaCombustibleDao = new VentaCombustibleDaoImpl();
                this.ventasItems = this.ventaCombustibleDao.findByBusBetweenDates(selected, this.fecha, this.currentDate.getMaxDate());
                setChart();
            }
        }

        report = new AbstractDynamicReport();

        String sql = "SELECT \n"
                + "    bus.bus_numero,\n"
                + "    operador_transporte.operador_transporte_nombre, \n"
                + "    bus.bus_patente,\n"
                + "    bus.bus_anio,\n"
                + "    SUM(venta_combustible.venta_combustible_total) AS total, \n"
                + "    COUNT(DISTINCT venta_combustible_fecha) AS DT, "
                + "   (SUM(venta_combustible.venta_combustible_total)/COUNT(DISTINCT venta_combustible_fecha)) AS promedio "
                + "   FROM\n"
                + "    venta_combustible\n"
                + "        LEFT JOIN\n"
                + "    bus ON venta_combustible_id_bus = bus.bus_id\n"
                + "		LEFT JOIN \n"
                + "     unidad_negocio ON bus.bus_id_unidad_negocio = unidad_negocio.unidad_negocio_id\n"
                + "		LEFT JOIN \n"
                + "     operador_transporte ON unidad_negocio.unidad_negocio_id_operador_transporte = operador_transporte.operador_transporte_id \n"
                + "WHERE"
                + "    venta_combustible_fecha BETWEEN '" + df.format(this.fecha) + "' AND LAST_DAY('" + df.format(this.fecha) + "') \n"
                + "    AND bus.bus_id IN (" + this.items.stream().flatMap(p -> Stream.of(p.getBusId())).collect(Collectors.toList()) + ")"
                + "GROUP BY bus.bus_id\n"
                + "ORDER BY total DESC";

        sql = sql.replace('[', ' ');
        sql = sql.replace(']', ' ');
        report.setSql(sql);

//        reportSingle = new AbstractDynamicReport();
//        sql = "SELECT \n"
//                + "    AVG(venta_combustible.venta_combustible_total)\n"
//                + "FROM\n"
//                + "    venta_combustible\n"
//                + "WHERE\n"
//                + "    venta_combustible_fecha";
//        sql = sql.replace('[', ' ');
//        sql = sql.replace(']', ' ');
//        reportSingle.setSql(sql);
//        
//        this.promedioDiario = (Integer)reportSingle.loadSingle();

        report.load();
    }

    public AbstractDynamicReport getAbstractDynamicReport() {
        return report;
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

    public Object getPromedioMensual() {
        return promedioMensual;
    }

    public Object getPromedioDiario() {
        return promedioDiario;
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

            load();
        } catch (ParseException ex) {
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

        try {
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
        } catch (ParseException ex) {
        }
        this.currentDate = new CurrentDate(1, mes, anio);

        for (Bus b : this.items) {

            LineChartSeries serie = new LineChartSeries();
            serie.setSmoothLine(true);

            this.ventaCombustibleDao = new VentaCombustibleDaoImpl();
            this.ventasItems = this.ventaCombustibleDao.findByBusBetweenDates(b, this.currentDate.date(), this.currentDate.getMaxDate());
            serie.setLabel("Bus: " + b.getBusNumero());
            for (VentaCombustible v : this.ventasItems) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(v.getVentaCombustibleFecha());

                serie.set(calendar.get(Calendar.DATE), v.getVentaCombustibleTotal());

            }
            model.addSeries(serie);
        }

        return model;
    }

}
