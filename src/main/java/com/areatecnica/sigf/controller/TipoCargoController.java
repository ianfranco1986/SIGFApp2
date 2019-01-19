package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.entities.CargoBus;
import java.util.List;
import com.areatecnica.sigf.facade.TipoCargoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoCargoController")
@ViewScoped
public class TipoCargoController extends AbstractController<TipoCargo> {

    @Inject
    private CuentaController tipoCargoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isCargoBusListEmpty;

    public TipoCargoController() {
        // Inform the Abstract parent controller of the concrete TipoCargo Entity
        super(TipoCargo.class);
    }

    @Override
    public TipoCargo prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoCargoIdCuenta(this.getUserCount());
        return getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoCargoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCargoBusListEmpty();
    }

    public boolean getIsCargoBusListEmpty() {
        return this.isCargoBusListEmpty;
    }

    private void setIsCargoBusListEmpty() {
        TipoCargo selected = this.getSelected();
        if (selected != null) {
            TipoCargoFacade ejbFacade = (TipoCargoFacade) this.getFacade();
            this.isCargoBusListEmpty = ejbFacade.isCargoBusListEmpty(selected);
        } else {
            this.isCargoBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargoBus entities that
     * are retrieved from TipoCargo and returns the navigation outcome.
     *
     * @return navigation outcome for CargoBus page
     */
    public String navigateCargoBusList() {
        TipoCargo selected = this.getSelected();
        if (selected != null) {
            TipoCargoFacade ejbFacade = (TipoCargoFacade) this.getFacade();
            List<CargoBus> selectedCargoBusList = ejbFacade.findCargoBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargoBus_items", selectedCargoBusList);
        }
        return "/app/cargoBus/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoCargoIdCuenta(ActionEvent event) {
        TipoCargo selected = this.getSelected();
        if (selected != null && tipoCargoIdCuentaController.getSelected() == null) {
            tipoCargoIdCuentaController.setSelected(selected.getTipoCargoIdCuenta());
        }
    }

}
