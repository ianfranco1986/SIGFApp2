package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.IntervalosAdministracion;
import com.areatecnica.sigf.entities.DetalleIntervalosMensual;
import java.util.List;
import com.areatecnica.sigf.facade.IntervalosAdministracionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "intervalosAdministracionController")
@ViewScoped
public class IntervalosAdministracionController extends AbstractController<IntervalosAdministracion> {

    @Inject
    private CuentaController intervalosAdministracionIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isDetalleIntervalosMensualListEmpty;

    public IntervalosAdministracionController() {
        // Inform the Abstract parent controller of the concrete IntervalosAdministracion Entity
        super(IntervalosAdministracion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        intervalosAdministracionIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDetalleIntervalosMensualListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIntervalosAdministracionIdCuenta(ActionEvent event) {
        IntervalosAdministracion selected = this.getSelected();
        if (selected != null && intervalosAdministracionIdCuentaController.getSelected() == null) {
            intervalosAdministracionIdCuentaController.setSelected(selected.getIntervalosAdministracionIdCuenta());
        }
    }

    public boolean getIsDetalleIntervalosMensualListEmpty() {
        return this.isDetalleIntervalosMensualListEmpty;
    }

    private void setIsDetalleIntervalosMensualListEmpty() {
        IntervalosAdministracion selected = this.getSelected();
        if (selected != null) {
            IntervalosAdministracionFacade ejbFacade = (IntervalosAdministracionFacade) this.getFacade();
            this.isDetalleIntervalosMensualListEmpty = ejbFacade.isDetalleIntervalosMensualListEmpty(selected);
        } else {
            this.isDetalleIntervalosMensualListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleIntervalosMensual
     * entities that are retrieved from IntervalosAdministracion and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleIntervalosMensual page
     */
    public String navigateDetalleIntervalosMensualList() {
        IntervalosAdministracion selected = this.getSelected();
        if (selected != null) {
            IntervalosAdministracionFacade ejbFacade = (IntervalosAdministracionFacade) this.getFacade();
            List<DetalleIntervalosMensual> selectedDetalleIntervalosMensualList = ejbFacade.findDetalleIntervalosMensualList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleIntervalosMensual_items", selectedDetalleIntervalosMensualList);
        }
        return "/app/detalleIntervalosMensual/index";
    }

}
