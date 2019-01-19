package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.AbonoBus;
import java.util.List;
import com.areatecnica.sigf.facade.TipoAbonoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoAbonoController")
@ViewScoped
public class TipoAbonoController extends AbstractController<TipoAbono> {

    @Inject
    private CuentaController tipoAbonoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isAbonoBusListEmpty;

    public TipoAbonoController() {
        // Inform the Abstract parent controller of the concrete TipoAbono Entity
        super(TipoAbono.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoAbonoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsAbonoBusListEmpty();
    }

    public boolean getIsAbonoBusListEmpty() {
        return this.isAbonoBusListEmpty;
    }

    private void setIsAbonoBusListEmpty() {
        TipoAbono selected = this.getSelected();
        if (selected != null) {
            TipoAbonoFacade ejbFacade = (TipoAbonoFacade) this.getFacade();
            this.isAbonoBusListEmpty = ejbFacade.isAbonoBusListEmpty(selected);
        } else {
            this.isAbonoBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of AbonoBus entities that
     * are retrieved from TipoAbono and returns the navigation outcome.
     *
     * @return navigation outcome for AbonoBus page
     */
    public String navigateAbonoBusList() {
        TipoAbono selected = this.getSelected();
        if (selected != null) {
            TipoAbonoFacade ejbFacade = (TipoAbonoFacade) this.getFacade();
            List<AbonoBus> selectedAbonoBusList = ejbFacade.findAbonoBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AbonoBus_items", selectedAbonoBusList);
        }
        return "/app/abonoBus/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoAbonoIdCuenta(ActionEvent event) {
        TipoAbono selected = this.getSelected();
        if (selected != null && tipoAbonoIdCuentaController.getSelected() == null) {
            tipoAbonoIdCuentaController.setSelected(selected.getTipoAbonoIdCuenta());
        }
    }

    @Override
    public TipoAbono prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.getSelected().setTipoAbonoIdCuenta(this.getUserCount());

        return getSelected();
    }

}
