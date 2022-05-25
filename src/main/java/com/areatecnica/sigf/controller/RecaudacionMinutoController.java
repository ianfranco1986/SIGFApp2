package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.IRegistroMinutoDaoImpl;
import com.areatecnica.sigf.entities.*;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Named(value = "recaudacionMinutoController")
@ViewScoped
public class RecaudacionMinutoController extends AbstractController<RecaudacionMinuto> {

    private List<CajaRecaudacion> cajaRecaudacionItems;
    private List<RecaudacionMinuto> items;
    private List<RecaudacionMinuto> selectedItems;
    private RegistroMinuto registroMinuto;
    private CajaRecaudacion cajaRecaudacion;
    private Date fecha;
    private int totalRecaudacion = 0;
    private boolean print;
    private Privilegio privilegio;
    private int guiasAnuladas = 0;

    private final NumberFormat nf = NumberFormat.getInstance();
    LocalDate f;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM", new Locale("es", "PE"));

    @Inject
    private RegistroMinutoController recaudacionMinutoIdRegistroMinutoController;

    public RecaudacionMinutoController() {
        // Inform the Abstract parent controller of the concrete RecaudacionMinuto Entity
        super(RecaudacionMinuto.class);
    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.cajaRecaudacionItems = new ICajaRecaudacionDaoImpl().findAllActive();
        this.privilegio = new Privilegio();
    }

    public void load() {
        if (this.cajaRecaudacion != null) {

            this.items = new IRecaudacionMinutoDaoImpl().findByCajaDate(cajaRecaudacion, fecha);
            if (!this.items.isEmpty()) {
                this.totalRecaudacion = 0;
                for (RecaudacionMinuto r : this.items) {
                    this.totalRecaudacion = this.totalRecaudacion + r.getRecaudacionMinutoMonto();
                }
            } else {
                JsfUtil.addErrorMessage("No se han encontrado recaudaciones");
            }

//            } 
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la caja de recaudación");
        }
    }

    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                //

                Recaudacion r = this.getSelected().getRecaudacionMinutoIdRecaudacion();

                StringBuilder folios = new StringBuilder();

                for (RecaudacionMinuto m : r.getRecaudacionMinutoList()) {
                    folios.append(", ").append(m.getRecaudacionMinutoIdRegistroMinuto().getRegistroMinutoId());
                    m.getRecaudacionMinutoIdRegistroMinuto().setRegistroMinutoRecaudado(Boolean.FALSE);
                    new IRegistroMinutoDaoImpl().update(m.getRecaudacionMinutoIdRegistroMinuto());
                    this.items.remove(m);
                    new IRecaudacionMinutoDaoImpl().delete(m);
                }

                //this.registroMinuto = this.getSelected().getRecaudacionMinutoIdRegistroMinuto();
                //this.registroMinuto.setRegistroMinutoRecaudado(Boolean.FALSE);
                //new IRegistroMinutoDaoImpl().update(registroMinuto);
                //super.delete(event);
                JsfUtil.addSuccessMessage("Se ha eliminado la recaudación");
            } catch (NullPointerException e) {
                JsfUtil.addErrorMessage("Ocurrió un error al intentar eliminar la recaudación");
            }

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la recaudación");
        }
    }

    /**
     * Sets the "selected" attribute of the RegistroMinuto controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionMinutoIdRegistroMinuto(ActionEvent event) {
        RecaudacionMinuto selected = this.getSelected();
        if (selected != null && recaudacionMinutoIdRegistroMinutoController.getSelected() == null) {
            recaudacionMinutoIdRegistroMinutoController.setSelected(selected.getRecaudacionMinutoIdRegistroMinuto());
        }
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedGuias()) {
            int size = this.selectedItems.size();
            return size > 1 ? size + " recaudaciones seleccionadas" : "1 recaudación seleccionada";
        }

        return "Eliminar";
    }
    
    public void selectAll(){
        if(this.getSelected()!=null){
            this.selectedItems.addAll(this.getSelected().getRecaudacionMinutoIdRecaudacion().getRecaudacionMinutoList());
        }
    }
    
    public void deSelectAll(){
        if(this.getSelected()!=null){
            this.selectedItems.removeAll(this.getSelected().getRecaudacionMinutoIdRecaudacion().getRecaudacionMinutoList());
        }
    }

    public boolean hasSelectedGuias() {
        return this.selectedItems != null && !this.selectedItems.isEmpty();
    }

    public void setCajaRecaudacionItems(List<CajaRecaudacion> cajaRecaudacionItems) {
        this.cajaRecaudacionItems = cajaRecaudacionItems;
    }

    public List<CajaRecaudacion> getCajaRecaudacionItems() {
        return cajaRecaudacionItems;
    }

    public List<RecaudacionMinuto> getItems() {
        return items;
    }

    public void setItems(List<RecaudacionMinuto> items) {
        this.items = items;
    }

    public RegistroMinuto getRegistroMinuto() {
        return registroMinuto;
    }

    public void setRegistroMinuto(RegistroMinuto registroMinuto) {
        this.registroMinuto = registroMinuto;
    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public String getFechaCompleta() {
        LocalDate date = LocalDate.from(this.fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return date.format(formatter);
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void setGuiasAnuladas(int guiasAnuladas) {
        this.guiasAnuladas = guiasAnuladas;
    }

    public int getGuiasAnuladas() {
        return guiasAnuladas;
    }

    public void setSelectedItems(List<RecaudacionMinuto> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<RecaudacionMinuto> getSelectedItems() {
        return selectedItems;
    }

    public void deleteSelectedGuias() {
        if (hasSelectedGuias()) {
            for (RecaudacionMinuto r : this.selectedItems) {

                try {

                    Recaudacion rr = r.getRecaudacionMinutoIdRecaudacion();

                    String folios = "";

                    for (RecaudacionMinuto m : rr.getRecaudacionMinutoList()) {
                        folios = folios + ", " + m.getRecaudacionMinutoIdRegistroMinuto().getRegistroMinutoId();
                        m.getRecaudacionMinutoIdRegistroMinuto().setRegistroMinutoRecaudado(Boolean.FALSE);
                        new IRegistroMinutoDaoImpl().update(m.getRecaudacionMinutoIdRegistroMinuto());
                        this.items.remove(m);
                        new IRecaudacionMinutoDaoImpl().delete(m);
                        JsfUtil.addSuccessMessage("Se ha eliminado la recaudación de minutos #" + rr.getRecaudacionId());
                    }

                } catch (NullPointerException e) {
                    JsfUtil.addErrorMessage("Se ha producido un error: " + e.getLocalizedMessage());
                    this.setSelected(null);
                }

            }
            this.items.removeAll(this.selectedItems);
            this.selectedItems = new ArrayList<>();
            load();
        }
    }
}
