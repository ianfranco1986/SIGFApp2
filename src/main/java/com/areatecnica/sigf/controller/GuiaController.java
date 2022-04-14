package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.models.GuiaDataModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.primefaces.event.RowEditEvent;

@Named(value = "guiaController")
@ViewScoped
public class GuiaController extends AbstractController<Guia> {

    @Inject
    private BusController guiaIdBusController;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;

    private List<Trabajador> trabajadorItems;
    private List<Guia> items;
    private List<Bus> itemsBus;
    private Guia selectedGuia;
    private Bus bus;
    private Date fecha;
    private Date desde;
    private Date hasta;
    private DateTime dateTime;
    private int mes;
    private int anio;
    private String formatMes;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/01");
    private final static SimpleDateFormat sdf2 = new SimpleDateFormat("01/MM/yy");

    private GuiaDataModel model;

    public GuiaController() {
        // Inform the Abstract parent controller of the concrete Guia Entity
        super(Guia.class);
    }

    public void setTrabajadorItems(List<Trabajador> trabajadorItems) {
        this.trabajadorItems = trabajadorItems;
    }

    public List<Trabajador> getTrabajadorItems() {
        return trabajadorItems;
    }

    public void setItemsBus(List<Bus> itemsBus) {
        this.itemsBus = itemsBus;
    }

    public List<Bus> getItemsBus() {
        return itemsBus;
    }

    public List<Guia> getItems() {
        return items;
    }

    public void setItems(List<Guia> items) {
        this.items = items;
    }

    public Guia getSelectedGuia() {
        return this.selectedGuia; //To change body of generated methods, choose Tools | Templates.
    }

    public void setSelectedGuia(Guia selectedGuia) {
        this.selectedGuia = selectedGuia;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getDesde() {
        return desde;
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

    public GuiaDataModel getModel() {
        return model;
    }

    public void setModel(GuiaDataModel model) {
        this.model = model;
    }

    @PostConstruct
    public void init() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);
        this.desde = new Date();
        //setFecha();
        this.fecha = new Date();
        try {
            this.desde = this.sdf.parse("01/" + this.mes + "/" + this.anio);
        } catch (ParseException ex) {
            JsfUtil.addErrorMessage("Error al parsear la fecha"+ex.getLocalizedMessage());
        }
        this.hasta = new Date();

        this.itemsBus = new IBusDaoImpl().findByProceso(new IProcesoRecaudacionDaoImpl().findById(2));
        this.trabajadorItems = new TrabajadorDaoImpl().findNanduOrderByCode();
    }

    public void load() {
        if (this.fecha != null && this.bus != null) {
            this.items = new IGuiaDaoImpl().findByBusBetweenFecha(bus, desde, hasta);

            if (!this.items.isEmpty()) {
                JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " guías del bus n° " + this.bus.getBusNumero() + " para el periodo " + this.formatMes);

                this.model = new GuiaDataModel(this.items);

            } else {
                JsfUtil.addWarningMessage("No se han encontrado guías para el periodo " + this.formatMes + " seleccionado " + this.desde + "\n" + this.hasta);
                this.model = new GuiaDataModel();
            }

        } else {
            JsfUtil.addErrorMessage("Ocurrió un error al cargar los datos");
        }
    }

    public void save() {

    }

    public void onRowEdit(RowEditEvent event) {

        Guia aux = null;

        try {
            aux = (Guia) event.getObject();
            new IGuiaDaoImpl().update(aux);
            JsfUtil.addSuccessMessage("Se ha actualizado la guía ");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar la guía");
        }

    }

    public void setFormatMes(String formatMes) {
        this.formatMes = formatMes;
    }

    public String getformatMes() {
        //this.formatMes = sdf2.format(desde) + " y el :" + sdf2.format(hasta);
        return "";
    }
}
