package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Departamento;
import com.areatecnica.sigf.entities.GastoAdministracionMensual;
import java.util.List;
import com.areatecnica.sigf.facade.DepartamentoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "departamentoController")
@ViewScoped
public class DepartamentoController extends AbstractController<Departamento> {

    @Inject
    private CuentaController departamentoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isGastoAdministracionMensualListEmpty;

    public DepartamentoController() {
        // Inform the Abstract parent controller of the concrete Departamento Entity
        super(Departamento.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        departamentoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsGastoAdministracionMensualListEmpty();
    }

    public boolean getIsGastoAdministracionMensualListEmpty() {
        return this.isGastoAdministracionMensualListEmpty;
    }

    private void setIsGastoAdministracionMensualListEmpty() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            this.isGastoAdministracionMensualListEmpty = ejbFacade.isGastoAdministracionMensualListEmpty(selected);
        } else {
            this.isGastoAdministracionMensualListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of
     * GastoAdministracionMensual entities that are retrieved from Departamento
     * and returns the navigation outcome.
     *
     * @return navigation outcome for GastoAdministracionMensual page
     */
    public String navigateGastoAdministracionMensualList() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            List<GastoAdministracionMensual> selectedGastoAdministracionMensualList = ejbFacade.findGastoAdministracionMensualList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("GastoAdministracionMensual_items", selectedGastoAdministracionMensualList);
        }
        return "/app/gastoAdministracionMensual/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDepartamentoIdCuenta(ActionEvent event) {
        Departamento selected = this.getSelected();
        if (selected != null && departamentoIdCuentaController.getSelected() == null) {
            departamentoIdCuentaController.setSelected(selected.getDepartamentoIdCuenta());
        }
    }

}
