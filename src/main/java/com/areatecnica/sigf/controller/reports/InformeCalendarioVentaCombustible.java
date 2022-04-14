/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.util.DatePlusValueHelper;
import java.text.NumberFormat;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.joda.time.DateTime;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author ianfr
 */
@Named(value = "informeCalendarioVentaCombustible")
@ViewScoped
public class InformeCalendarioVentaCombustible implements Serializable {

    private List<DatePlusValueHelper> items;
    private int mes;
    private int anio;
    private Date fecha;
    private DateTime dateTime;

    private ScheduleModel eventModel;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final static SimpleDateFormat sdfM = new SimpleDateFormat("yyyy-MM-dd");
    private final static NumberFormat nf = NumberFormat.getInstance();
    private Client client;
    private WebTarget target;

    /**
     * Creates a new instance of InformeCalendarioVentaCombustible
     */
    public InformeCalendarioVentaCombustible() {

    }

    @PostConstruct
    public void init() {
        Calendar calendar = Calendar.getInstance();

        this.anio = calendar.get(Calendar.YEAR);
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.eventModel = new DefaultScheduleModel();
    }

    public void load() {
        setDate();
        if (this.fecha != null) {
            this.dateTime = new DateTime(fecha);
            DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();

            client = ClientBuilder.newClient();
            target = client.target("http://localhost:8080/SIGFRest-1.0/webresources/ventacombustible/getResumenVentas/" + sdfM.format(fecha))
                    .queryParam("fecha", sdfM.format(fecha));

            Response response = target.request().get();

            System.err.println("TEMAAA"+response);
            
            JsonArray array = response.readEntity(JsonArray.class);

            for (JsonValue a : array) {
                System.err.println("VALOR:" + a.toString());
            }

            //this.items = new IVentaCombustibleDaoImpl().findBetweenDates(fecha, _maxDate.toDate());
            int i = 0;
//            if (this.items.isEmpty()) {
//                JsfUtil.addSuccessMessage("No se han encontrado registros");
//            } else {
//                JsfUtil.addSuccessMessage("Mostrando registros a la fecha");
//
//                for (DatePlusValueHelper d : this.items) {
//                    System.err.println("fecha: " + d.getDate());
//                    System.err.println("valor: " + sdf.format(d.getValue()));
//
//                    eventModel.addEvent(new DefaultScheduleEvent(String.valueOf(d.getValue()), d.getDate(), d.getDate()));
//                    i++;
//                }
//            }

        }
    }

    public void setDate() {
        String _auxFecha = "01/" + this.mes + "/" + this.anio;
        try {
            this.fecha = sdf.parse(_auxFecha);
        } catch (ParseException exception) {
            JsfUtil.addErrorMessage("Error al crear la fecha");
        }
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public List<DatePlusValueHelper> getItems() {
        return items;
    }

    public void setItems(List<DatePlusValueHelper> items) {
        this.items = items;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

}
