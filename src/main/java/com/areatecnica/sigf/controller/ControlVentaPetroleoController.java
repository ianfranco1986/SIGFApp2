package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.IControlVentaPetroleoDaoImpl;
import com.areatecnica.sigf.entities.ControlVentaPetroleo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.joda.time.DateTime;

@Named(value = "controlVentaPetroleoController")
@ViewScoped
public class ControlVentaPetroleoController extends AbstractController<ControlVentaPetroleo> {

    private List<ControlVentaPetroleo> items;
    private int mes;
    private int anio;
    private Date fecha;
    private DateTime dateTime;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public ControlVentaPetroleoController() {
        // Inform the Abstract parent controller of the concrete ControlVentaPetroleo Entity
        super(ControlVentaPetroleo.class);
    }

    @Override
    @PostConstruct
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.
        Calendar calendar = Calendar.getInstance();

        this.anio = calendar.get(Calendar.YEAR);
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.items = new ArrayList();
    }

    public void load() {
        setDate();
        if (this.fecha != null) {
            this.dateTime = new DateTime(fecha);
            DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();
            this.items = new IControlVentaPetroleoDaoImpl().findByDates(fecha, _maxDate.toDate());

            if (this.items.isEmpty()) {
                JsfUtil.addSuccessMessage("No se han encontrado registros");
                this.items = new ArrayList();
            } else {
                JsfUtil.addSuccessMessage("Mostrando registros a la fecha");
            }

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

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {

    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {

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

}
