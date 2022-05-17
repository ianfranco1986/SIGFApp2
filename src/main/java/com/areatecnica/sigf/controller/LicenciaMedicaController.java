package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.LicenciaMedica;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "licenciaMedicaController")
@ViewScoped
public class LicenciaMedicaController extends AbstractController<LicenciaMedica> {

    @Inject
    private TrabajadorController licenciaMedicaIdTrabajadorController;

    private int mes;
    private int anio;
    private Date desde;
    private Date hasta;
//    private DateTime dateTime;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private NumberFormat nf = NumberFormat.getInstance();

    public LicenciaMedicaController() {
        // Inform the Abstract parent controller of the concrete LicenciaMedica Entity
        super(LicenciaMedica.class);
    }

    @PostConstruct
    public void init() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.

        Calendar cal = Calendar.getInstance();
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.anio = cal.get(Calendar.YEAR);

        this.desde = new Date();
        setFecha();

        this.prepareCreate(null);
        this.getSelected().setLicenciaMedicaFechaDesdeReposo(desde);
    }

    public void load() {

    }
    
    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ActionEvent event) {
        super.delete(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
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


    public NumberFormat getNf() {
        return nf;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

    public void setFecha() {
//        try {
//            System.err.println("NUEVA FECHA:");
//            this.desde = this.sdf.parse("01/" + this.mes + "/" + this.anio);
//            this.dateTime = new DateTime(this.desde);
//            this.hasta = this.dateTime.dayOfMonth().withMaximumValue().toDate();
//        } catch (ParseException ex) {
//
//        }
    }

}
