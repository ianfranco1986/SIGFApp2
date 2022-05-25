package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.ObservacionTipoCuenta;
import com.areatecnica.sigf.entities.TipoCuenta;
import com.areatecnica.sigf.facade.TipoCuentaFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoCuentaController")
@ViewScoped
public class TipoCuentaController extends AbstractController<TipoCuenta> {

    // Flags to indicate if child collections are empty
    private boolean isObservacionTipoCuentaListEmpty;
    private boolean isCuentaListEmpty;

    public TipoCuentaController() {
        // Inform the Abstract parent controller of the concrete TipoCuenta Entity
        super(TipoCuenta.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsObservacionTipoCuentaListEmpty();
        this.setIsCuentaListEmpty();
    }

    public boolean getIsObservacionTipoCuentaListEmpty() {
        return this.isObservacionTipoCuentaListEmpty;
    }

    private void setIsObservacionTipoCuentaListEmpty() {
        TipoCuenta selected = this.getSelected();
        if (selected != null) {
            TipoCuentaFacade ejbFacade = (TipoCuentaFacade) this.getFacade();
            this.isObservacionTipoCuentaListEmpty = ejbFacade.isObservacionTipoCuentaListEmpty(selected);
        } else {
            this.isObservacionTipoCuentaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ObservacionTipoCuenta
     * entities that are retrieved from TipoCuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ObservacionTipoCuenta page
     */
    public String navigateObservacionTipoCuentaList() {
        TipoCuenta selected = this.getSelected();
        if (selected != null) {
            TipoCuentaFacade ejbFacade = (TipoCuentaFacade) this.getFacade();
            List<ObservacionTipoCuenta> selectedObservacionTipoCuentaList = ejbFacade.findObservacionTipoCuentaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ObservacionTipoCuenta_items", selectedObservacionTipoCuentaList);
        }
        return "/app/observacionTipoCuenta/index";
    }

    public boolean getIsCuentaListEmpty() {
        return this.isCuentaListEmpty;
    }

    private void setIsCuentaListEmpty() {
        TipoCuenta selected = this.getSelected();
        if (selected != null) {
            TipoCuentaFacade ejbFacade = (TipoCuentaFacade) this.getFacade();
            this.isCuentaListEmpty = ejbFacade.isCuentaListEmpty(selected);
        } else {
            this.isCuentaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Cuenta entities that are
     * retrieved from TipoCuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Cuenta page
     */
    public String navigateCuentaList() {
        TipoCuenta selected = this.getSelected();
        if (selected != null) {
            TipoCuentaFacade ejbFacade = (TipoCuentaFacade) this.getFacade();
            List<Cuenta> selectedCuentaList = ejbFacade.findCuentaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Cuenta_items", selectedCuentaList);
        }
        return "/app/cuenta/index";
    }

}
