package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoObservacion;
import com.areatecnica.sigf.entities.ObservacionTrabajador;
import java.util.List;
import com.areatecnica.sigf.facade.TipoObservacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoObservacionController")
@ViewScoped
public class TipoObservacionController extends AbstractController<TipoObservacion> {

    @Inject
    private CuentaController tipoObservacionIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isObservacionTrabajadorListEmpty;

    public TipoObservacionController() {
        // Inform the Abstract parent controller of the concrete TipoObservacion Entity
        super(TipoObservacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoObservacionIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsObservacionTrabajadorListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoObservacionIdCuenta(ActionEvent event) {
        TipoObservacion selected = this.getSelected();
        if (selected != null && tipoObservacionIdCuentaController.getSelected() == null) {
            tipoObservacionIdCuentaController.setSelected(selected.getTipoObservacionIdCuenta());
        }
    }

    public boolean getIsObservacionTrabajadorListEmpty() {
        return this.isObservacionTrabajadorListEmpty;
    }

    private void setIsObservacionTrabajadorListEmpty() {
        TipoObservacion selected = this.getSelected();
        if (selected != null) {
            TipoObservacionFacade ejbFacade = (TipoObservacionFacade) this.getFacade();
            this.isObservacionTrabajadorListEmpty = ejbFacade.isObservacionTrabajadorListEmpty(selected);
        } else {
            this.isObservacionTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ObservacionTrabajador
     * entities that are retrieved from TipoObservacion and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ObservacionTrabajador page
     */
    public String navigateObservacionTrabajadorList() {
        TipoObservacion selected = this.getSelected();
        if (selected != null) {
            TipoObservacionFacade ejbFacade = (TipoObservacionFacade) this.getFacade();
            List<ObservacionTrabajador> selectedObservacionTrabajadorList = ejbFacade.findObservacionTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ObservacionTrabajador_items", selectedObservacionTrabajadorList);
        }
        return "/app/observacionTrabajador/index";
    }

}
