package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CentroCosto;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;
import com.areatecnica.sigf.facade.CentroCostoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "centroCostoController")
@ViewScoped
public class CentroCostoController extends AbstractController<CentroCosto> {

    @Inject
    private CuentaController centroCostoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public CentroCostoController() {
        // Inform the Abstract parent controller of the concrete CentroCosto Entity
        super(CentroCosto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        centroCostoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsTrabajadorListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCentroCostoIdCuenta(ActionEvent event) {
        CentroCosto selected = this.getSelected();
        if (selected != null && centroCostoIdCuentaController.getSelected() == null) {
            centroCostoIdCuentaController.setSelected(selected.getCentroCostoIdCuenta());
        }
    }

    public boolean getIsTrabajadorListEmpty() {
        return this.isTrabajadorListEmpty;
    }

    private void setIsTrabajadorListEmpty() {
        CentroCosto selected = this.getSelected();
        if (selected != null) {
            CentroCostoFacade ejbFacade = (CentroCostoFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from CentroCosto and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        CentroCosto selected = this.getSelected();
        if (selected != null) {
            CentroCostoFacade ejbFacade = (CentroCostoFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

}
