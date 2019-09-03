package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.IRegistroMinutoDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.models.RecaudacionMinutoDataModel;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "recaudacionMinutoController")
@ViewScoped
public class RecaudacionMinutoController extends AbstractController<RecaudacionMinuto> {

    private List<CajaRecaudacion> cajasItems;
    private List<RecaudacionMinuto> items;
    private RecaudacionMinutoDataModel recaudacionCombustibleDataModel;
    //private VentaCombustibleModel deudasModel;
    private RegistroMinuto registroMinuto;
    private CajaRecaudacion cajaRecaudacion;
    private Date fecha;
    private int totalRecaudacion = 0;
    private boolean print;

    private NumberFormat nf = NumberFormat.getInstance();

    @Inject
    private RecaudacionController recaudacionMinutoIdRecaudacionController;
    @Inject
    private RegistroMinutoController recaudacionMinutoIdRegistroMinutoController;

    public RecaudacionMinutoController() {
        // Inform the Abstract parent controller of the concrete RecaudacionMinuto Entity
        super(RecaudacionMinuto.class);
    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.cajasItems = new ICajaRecaudacionDaoImpl().findAll();
    }

    public void load() {
        if (this.cajaRecaudacion != null) {

            this.items = new IRecaudacionMinutoDaoImpl().findByCajaDate(cajaRecaudacion, fecha);
            if (!this.items.isEmpty()) {
                this.recaudacionCombustibleDataModel = new RecaudacionMinutoDataModel(items);
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

                for (RecaudacionMinuto m : r.getRecaudacionMinutoList()) {
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

            }

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la recaudación");
        }
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        recaudacionMinutoIdRecaudacionController.setSelected(null);
        recaudacionMinutoIdRegistroMinutoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionMinutoIdRecaudacion(ActionEvent event) {
        RecaudacionMinuto selected = this.getSelected();
        if (selected != null && recaudacionMinutoIdRecaudacionController.getSelected() == null) {
            recaudacionMinutoIdRecaudacionController.setSelected(selected.getRecaudacionMinutoIdRecaudacion());
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

    public void setCajasItems(List<CajaRecaudacion> cajasItems) {
        this.cajasItems = cajasItems;
    }

    public List<CajaRecaudacion> getCajasItems() {
        return cajasItems;
    }

    public List<RecaudacionMinuto> getItems() {
        return items;
    }

    public void setItems(List<RecaudacionMinuto> items) {
        this.items = items;
    }

    public RecaudacionMinutoDataModel getRecaudacionCombustibleDataModel() {
        return recaudacionCombustibleDataModel;
    }

    public void setRecaudacionCombustibleDataModel(RecaudacionMinutoDataModel recaudacionCombustibleDataModel) {
        this.recaudacionCombustibleDataModel = recaudacionCombustibleDataModel;
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

}
