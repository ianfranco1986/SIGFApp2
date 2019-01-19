package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.AdministracionMensual;
import com.areatecnica.sigf.entities.DetalleIntervalosMensual;
import java.util.List;
import com.areatecnica.sigf.facade.AdministracionMensualFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "administracionMensualController")
@ViewScoped
public class AdministracionMensualController extends AbstractController<AdministracionMensual> {

    @Inject
    private CuentaController administracionMensualIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isDetalleIntervalosMensualListEmpty;

    public AdministracionMensualController() {
        // Inform the Abstract parent controller of the concrete AdministracionMensual Entity
        super(AdministracionMensual.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        administracionMensualIdCuentaController.setSelected(null);
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
    public void prepareAdministracionMensualIdCuenta(ActionEvent event) {
        AdministracionMensual selected = this.getSelected();
        if (selected != null && administracionMensualIdCuentaController.getSelected() == null) {
            administracionMensualIdCuentaController.setSelected(selected.getAdministracionMensualIdCuenta());
        }
    }

    public boolean getIsDetalleIntervalosMensualListEmpty() {
        return this.isDetalleIntervalosMensualListEmpty;
    }

    private void setIsDetalleIntervalosMensualListEmpty() {
        AdministracionMensual selected = this.getSelected();
        if (selected != null) {
            AdministracionMensualFacade ejbFacade = (AdministracionMensualFacade) this.getFacade();
            this.isDetalleIntervalosMensualListEmpty = ejbFacade.isDetalleIntervalosMensualListEmpty(selected);
        } else {
            this.isDetalleIntervalosMensualListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleIntervalosMensual
     * entities that are retrieved from AdministracionMensual and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleIntervalosMensual page
     */
    public String navigateDetalleIntervalosMensualList() {
        AdministracionMensual selected = this.getSelected();
        if (selected != null) {
            AdministracionMensualFacade ejbFacade = (AdministracionMensualFacade) this.getFacade();
            List<DetalleIntervalosMensual> selectedDetalleIntervalosMensualList = ejbFacade.findDetalleIntervalosMensualList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleIntervalosMensual_items", selectedDetalleIntervalosMensualList);
        }
        return "/app/detalleIntervalosMensual/index";
    }

}
