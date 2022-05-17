/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.util.Name2DateValuePOJO;
import com.areatecnica.sigf.util.Name2ValuePOJO;
import com.areatecnica.sigf.util.Name3ValuePOJO;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.chartistjsf.model.chart.PieChartModel;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author ianfr
 */
@Named(value = "blankPageController")
@ViewScoped
public class BlankPageController implements Serializable {

    private int totalRecaudacion = 0;
    private int totalRecaudacionMes = 0;
    private int totalAdministracion = 0;
    private int totalCuotaExtra = 0;
    private int totalBoletos = 0;
    private int totalImposiciones = 0;
    private int totalCombustible = 0;
    private int totalMinutos = 0;
    private int numeroImposiciones = 0;
    private int totalVentaPetroleo = 0;
    private int totalVentaPetroleoAnterior = 0;
    private int numeroDeudasPetroleo = 0;
    private String numeroDeudasBusPetroleo = "0";
    private int totalVentaPrestadores = 0;

    private float cantidadLitros = 0;

    private Recaudacion ultimaRecaudacion;
    private Date from;
    private List<Name2DateValuePOJO> list;
    private List<VentaCombustible> ventaCombustibleItems; 
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfFirst = new SimpleDateFormat("yyyy-MM-01");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a");
    private final static NumberFormat nf = NumberFormat.getInstance();
    private final static DecimalFormat df = new DecimalFormat("#.00");

    private String leyendaVentaPetroleo = "";
    private String leyendaBusDeudasPetroleo = "";

    private PieChartModel pieModel;
    private LineChartModel animatedModel1;

    private String nombreMes;
    private int mes;
    private int anio;
    private WebTarget target;
    private Client client;

    /**
     * Creates a new instance of BlankPageController
     */
    public BlankPageController() {
        this.from = new Date();
        

    }

    @PostConstruct
    public void init() {
        
        int mes = LocalDate.now().getMonth().getValue();
        
        this.nombreMes = new DateFormatSymbols().getMonths()[mes - 1];
        this.list = new ArrayList<>();
        
//        pieModel = new PieChartModel();
//        pieModel.setWidth("100%");
//
//        pieModel.setShowTooltip(true);

        client = ClientBuilder.newClient();

////        //Total Recaudación General 
////        String totalRecaudacionGeneralURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacion/totalRecaudacionGeneral/" + sdf.format(from);//NO OLVIDAR MODIFICAR
////        target = client.target(totalRecaudacionGeneralURL);
////        Name3ValuePOJO ppp = null;
////        Name2ValuePOJO pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalRecaudacion = Integer.parseInt(pp.getValue());
////
////          //Total Recaudación Administración
////        String totalRecaudacionAdministracionURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacionguia/totalRecaudacionAdministracion/" + sdfFirst.format(from) + "/" + sdf.format(to.dayOfMonth().withMaximumValue().toDate());
////        target = client.target(totalRecaudacionAdministracionURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalAdministracion = Integer.parseInt(pp.getValue());
////
////        //Total Recaudación CuotaExtra
////        String totalRecaudacionCuotaExtraURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacionguia/totalRecaudacionCuotaExtra/" + sdfFirst.format(from) + "/" + sdf.format(to.dayOfMonth().withMaximumValue().toDate());
////        target = client.target(totalRecaudacionCuotaExtraURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalCuotaExtra = Integer.parseInt(pp.getValue());
////
////        //Total Recaudación Imposiciones
////        String totalRecaudacionImposicionesURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacionguia/totalRecaudacionImposiciones/" + sdfFirst.format(from) + "/" + sdf.format(to.dayOfMonth().withMaximumValue().toDate());
////        target = client.target(totalRecaudacionImposicionesURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalImposiciones = Integer.parseInt(pp.getValue());
////
////        //Total Recaudación Boletos
////        String totalRecaudacionBoletosURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacionguia/totalRecaudacionBoletos/" + sdfFirst.format(from) + "/" + sdf.format(to.dayOfMonth().withMaximumValue().toDate());
////        target = client.target(totalRecaudacionBoletosURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalBoletos = Integer.parseInt(pp.getValue());
////
////        //Total Recaudación Mensual
////        String totalRecaudacionMensualURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacion/totalRecaudacionMes/" + sdfFirst.format(from) + "/" + sdf.format(to.dayOfMonth().withMaximumValue().toDate());
////
////        target = client.target(totalRecaudacionMensualURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalRecaudacionMes = Integer.parseInt(pp.getValue());
////
////        //Total Recaudación Combustible
////        String totalRecaudacionCombustibleURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacioncombustible/totalRecaudacionCombustible/" + sdfFirst.format(from) + "/" + sdf.format(to.dayOfMonth().withMaximumValue().toDate());
////
////        target = client.target(totalRecaudacionCombustibleURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalCombustible = Integer.parseInt(pp.getValue());
////
////        //Total Recaudación Minuto
////        String totalRecaudacionMinutoURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacionminuto/totalRecaudacionMinuto/" + sdfFirst.format(from) + "/" + sdf.format(to.dayOfMonth().withMaximumValue().toDate());
////
////        target = client.target(totalRecaudacionMinutoURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalMinutos = Integer.parseInt(pp.getValue());
////
////        //N° de Imposiciones Recaudadas
////        String numeroImposicionesURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacionguia/cantidadRecaudacionImposiciones/" + sdf.format(from);
////
////        target = client.target(numeroImposicionesURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.numeroImposiciones = Integer.parseInt(pp.getValue());
////
////        //Total Venta de Petroleo 
////        String totalVentaPetroleoURL = "http://localhost:8080/SIGFRest-1.0/webresources/ventacombustible/totalVentaCombustibleFecha/" + sdf.format(from);
////
////        target = client.target(totalVentaPetroleoURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalVentaPetroleo = Integer.parseInt(pp.getValue());
////
////        //Total Venta de Petroleo Prestadores
////        String totalVentaPetroleoPrestadoresURL = "http://localhost:8080/SIGFRest-1.0/webresources/ventacombustible/totalVentaCombustibleFechaPrestador/" + sdf.format(from);
////
////        target = client.target(totalVentaPetroleoPrestadoresURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalVentaPrestadores = Integer.parseInt(pp.getValue());
////
////        //Total Venta de Petroleo Anterior
////        String totalVentaPetroleoAnteriorURL = "http://localhost:8080/SIGFRest-1.0/webresources/ventacombustible/totalVentaCombustibleFecha/" + sdf.format(new DateTime(from).minusDays(1).toDate());
////
////        target = client.target(totalVentaPetroleoAnteriorURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.totalVentaPetroleoAnterior = Integer.parseInt(pp.getValue());
////
////        if (this.totalVentaPetroleo == this.totalVentaPetroleoAnterior) {
////            this.leyendaVentaPetroleo = "No existe variación entre las ventas de ayer y hoy";
////        } else if (this.totalVentaPetroleo > this.totalVentaPetroleoAnterior) {
////            this.leyendaVentaPetroleo = "Ventas un " + getPorcentajeSuperior(this.totalVentaPetroleoAnterior, this.totalVentaPetroleo ) + " superiores al día anterior (" + formattedValue(String.valueOf(this.totalVentaPetroleoAnterior));
////        } else {
////            this.leyendaVentaPetroleo = "Ventas un " + getPorcentaje(this.totalVentaPetroleoAnterior, this.totalVentaPetroleo) + " inferiores al día anterior (" + formattedValue(String.valueOf(this.totalVentaPetroleoAnterior));
////        }
////
////        //N° de Deudas Petroleo
////        String numeroDeudasPetroleoURL = "http://localhost:8080/SIGFRest-1.0/webresources/ventacombustible/numeroDeudasVentaCombustible/";
////
////        target = client.target(numeroDeudasPetroleoURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.numeroDeudasPetroleo = Integer.parseInt(pp.getValue());
////
////        //Cantidad de Litros
////        String cantidadLitrosURL = "http://localhost:8080/SIGFRest-1.0/webresources/ventacombustible/totalLitrosVentasFecha/" + sdf.format(from);
////
////        target = client.target(cantidadLitrosURL);
////        pp = target.request(MediaType.APPLICATION_JSON).get(Name2ValuePOJO.class);
////        this.cantidadLitros = Float.parseFloat(pp.getValue());
////
////        //Bus con más de Deudas Petroleo
////        String busDeudasPetroleoURL = "http://localhost:8080/SIGFRest-1.0/webresources/ventacombustible/numeroDeudasBusVentaCombustible/";
////
////        target = client.target(busDeudasPetroleoURL);
////        ppp = target.request(MediaType.APPLICATION_JSON).get(Name3ValuePOJO.class);
////
////        String _bus = String.valueOf(ppp.getName());
////        this.numeroDeudasBusPetroleo = String.valueOf(ppp.getValue());
////
////        this.leyendaBusDeudasPetroleo = "El Bus N° " + _bus + " posee " + ppp.getValue().toString() + " deudas ($ " + formattedValue(ppp.getOther().toString()) + ") ";
////
////        //Última Recaudación 
////        String ultimaRecaudacionURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacion/ultimaRecaudacion/";
//
//        target = client.target(ultimaRecaudacionURL);
//        this.ultimaRecaudacion = target.request(MediaType.APPLICATION_JSON).get(Recaudacion.class);

        //this.animatedModel1 = initLinearModel();
        //createAnimatedModels();
        //this.ventaCombustibleItems = new IVentaCombustibleDaoImpl().findByDate(from);
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getTotalVentaPrestadores() {
        return totalVentaPrestadores;
    }

    public void setTotalVentaPrestadores(int totalVentaPrestadores) {
        this.totalVentaPrestadores = totalVentaPrestadores;
    }

    public int getTotalRecaudacionMes() {
        return totalRecaudacionMes;
    }

    public void setTotalRecaudacionMes(int totalRecaudacionMes) {
        this.totalRecaudacionMes = totalRecaudacionMes;
    }

    public Recaudacion getUltimaRecaudacion() {
        return ultimaRecaudacion;
    }

    public void setUltimaRecaudacion(Recaudacion ultimaRecaudacion) {
        this.ultimaRecaudacion = ultimaRecaudacion;
    }

    public void setVentaCombustibleItems(List<VentaCombustible> ventaCombustibleItems) {
        this.ventaCombustibleItems = ventaCombustibleItems;
    }

    public List<VentaCombustible> getVentaCombustibleItems() {
        return ventaCombustibleItems;
    }

    public LineChartModel getAnimatedModel() {
        return animatedModel1;
    }

    public void setAnimatedModel(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }

    public List<Name2DateValuePOJO> getList() {
        return list;
    }

    public void setNumeroDeudasBusPetroleo(String numeroDeudasBusPetroleo) {
        this.numeroDeudasBusPetroleo = numeroDeudasBusPetroleo;
    }

    public String getNumeroDeudasBusPetroleo() {
        return numeroDeudasBusPetroleo;
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

    public void setTotalVentaPetroleo(int totalVentaPetroleo) {
        this.totalVentaPetroleo = totalVentaPetroleo;
    }

    public int getTotalVentaPetroleo() {
        return totalVentaPetroleo;
    }

    public void setNumeroImposiciones(int numeroImposiciones) {
        this.numeroImposiciones = numeroImposiciones;
    }

    public int getNumeroImposiciones() {
        return numeroImposiciones;
    }

    public String getLeyendaVentaPetroleo() {
        return leyendaVentaPetroleo;
    }

    public void setLeyendaVentaPetroleo(String leyendaVentaPetroleo) {
        this.leyendaVentaPetroleo = leyendaVentaPetroleo;
    }

    public void setNumeroDeudasPetroleo(int numeroDeudasPetroleo) {
        this.numeroDeudasPetroleo = numeroDeudasPetroleo;
    }

    public int getNumeroDeudasPetroleo() {
        return numeroDeudasPetroleo;
    }

    public void setList(List<Name2DateValuePOJO> list) {
        this.list = list;
    }

    public float getCantidadLitros() {
        return cantidadLitros;
    }

    public void setCantidadLitros(float cantidadLitros) {
        this.cantidadLitros = cantidadLitros;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public int getAnio() {
        return anio;
    }

    public int getMes() {
        return mes;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getLeyendaBusDeudasPetroleo() {
        return leyendaBusDeudasPetroleo;
    }

    public void setLeyendaBusDeudasPetroleo(String leyendaBusDeudasPetroleo) {
        this.leyendaBusDeudasPetroleo = leyendaBusDeudasPetroleo;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public String formattedValue(String value) {
        int _val = 0;
        try {
            _val = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return "$ 0";
        }
        return nf.format(_val);
    }

    public String formattedTime(Date time) {
        return sdfTime.format(time);
    }

    public String formattedFloat(float val) {
        return df.format(val);
    }
    
//    public float getPorcentaje(int part, int total) {
//        System.err.println("PARTE: " + part);
//        System.err.println("TOTAL: " + total);
//
//        float val = (part * 100.0f) / total;// (part * 100) / total;
//
//        System.err.println("Porcentaje: " + val);
//        return val;
//    }    
//    
//    public float getPorcentajeSuperior(int hoy, int ayer) {
//        System.err.println("PARTE: " + hoy);
//        System.err.println("TOTAL: " + ayer);
//
//        float val = (hoy * 100.0f) / ayer;// (part * 100) / total;
//
//        System.err.println("Porcentaje: " + val);
//        return val;
//    }

    private void createPieModel(Name2ValuePOJO n) {
        String serie = n.getName();
        Integer value = Integer.valueOf(n.getValue());

        pieModel.addLabel(serie);
        pieModel.set(value);
    }

//    private LineChartModel initLinearModel() {
//        LineChartModel model = new LineChartModel();
//
//        LineChartSeries serie = new LineChartSeries();
//        serie.setLabel("Recaudación");
//        model.setAnimate(true);
//        model.setMouseoverHighlight(true);
//
//        //Total Recaudación Diaria x Mes
//        String totalRecaudacionDiariaURL = "http://localhost:8080/SIGFRest-1.0/webresources/recaudacion/totalRecaudacionDiaria/" + sdfFirst.format(from) + "/" + sdf.format(to.dayOfMonth().withMaximumValue().toDate());
//
//        target = client.target(totalRecaudacionDiariaURL);
//
//        JsonArray response = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
//
//        for (JsonValue j : response) {
//
//            JSONObject o = new JSONObject(j.toString());
//            Name2DateValuePOJO n = new Name2DateValuePOJO(new Date((Long) o.get("name")), o.get("value"));
//
//            serie.set(n.getDayPart(), Integer.parseInt(n.getValue()));
//            System.err.println("DIA: " + n.getDayPart() + " Valor: " + n.getValue());
//            list.add(n);
//        }
//
//        model.addSeries(serie);
//        return model;
//    }

    private void createAnimatedModels() {
        //animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Recaudación Diaria");
        animatedModel1.setAnimate(true);
        animatedModel1.setShadow(true);
        //animatedModel1.setDatatipFormat();
        animatedModel1.setLegendPosition("se");
        Axis x = animatedModel1.getAxis(AxisType.X);

        x.setTickInterval("1");
        x.setMin(0);
        x.setMax(31);

        Axis y = animatedModel1.getAxis(AxisType.Y);

    }

}
