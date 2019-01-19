package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.INumeralSurtidorDao;
import com.areatecnica.sigf.dao.impl.NumeralSurtidorDaoImpl;
import com.areatecnica.sigf.entities.NumeralSurtidor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.joda.time.DateTime;

@Named(value = "numeralSurtidorController")
@ViewScoped
public class NumeralSurtidorController extends AbstractController<NumeralSurtidor> {

    @Inject
    private SurtidorController numeralSurtidorIdSurtidorController;

    private DateTime dateTime;
    private int mes;
    private int anio;
    private Date fecha;
    private INumeralSurtidorDao dao;

    private List<NumeralSurtidor> items;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public NumeralSurtidorController() {
        // Inform the Abstract parent controller of the concrete NumeralSurtidor Entity
        super(NumeralSurtidor.class);
    }

    @Override
    @PostConstruct
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.
        Calendar calendar = Calendar.getInstance();

        this.anio = calendar.get(Calendar.YEAR);
        this.mes = calendar.get(Calendar.MONTH) + 1;
    }

    public void load() {
        setDate();
        if (this.fecha != null) {
            this.dateTime = new DateTime(fecha);
            DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();
            this.items = this.dao.findAllByFecha(fecha, _maxDate.toDate());

            if (this.items.isEmpty()) {
                JsfUtil.addSuccessMessage("No se han encontrado registros");
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
        this.dao = new NumeralSurtidorDaoImpl();
    }

    public void setItems(List<NumeralSurtidor> items) {
        this.items = items;
    }

    public List<NumeralSurtidor> getItems() {
        return items;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        numeralSurtidorIdSurtidorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Surtidor controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareNumeralSurtidorIdSurtidor(ActionEvent event) {
        NumeralSurtidor selected = this.getSelected();
        if (selected != null && numeralSurtidorIdSurtidorController.getSelected() == null) {
            numeralSurtidorIdSurtidorController.setSelected(selected.getNumeralSurtidorIdSurtidor());
        }
    }

}
