package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.Mutual;
import com.areatecnica.sigf.facade.MutualFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "mutualController")
@ViewScoped
public class MutualController extends AbstractController<Mutual> {

    @Inject
    private CuentaController mutualIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isEmpresaListEmpty;

    public MutualController() {
        // Inform the Abstract parent controller of the concrete Mutual Entity
        super(Mutual.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        mutualIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEmpresaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareMutualIdCuenta(ActionEvent event) {
        Mutual selected = this.getSelected();
        if (selected != null && mutualIdCuentaController.getSelected() == null) {
            mutualIdCuentaController.setSelected(selected.getMutualIdCuenta());
        }
    }

    public boolean getIsEmpresaListEmpty() {
        return this.isEmpresaListEmpty;
    }

    private void setIsEmpresaListEmpty() {
        Mutual selected = this.getSelected();
        if (selected != null) {
            MutualFacade ejbFacade = (MutualFacade) this.getFacade();
            this.isEmpresaListEmpty = ejbFacade.isEmpresaListEmpty(selected);
        } else {
            this.isEmpresaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Empresa entities that are
     * retrieved from Mutual and returns the navigation outcome.
     *
     * @return navigation outcome for Empresa page
     */
    public String navigateEmpresaList() {
        Mutual selected = this.getSelected();
        if (selected != null) {
            MutualFacade ejbFacade = (MutualFacade) this.getFacade();
            List<Empresa> selectedEmpresaList = ejbFacade.findEmpresaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empresa_items", selectedEmpresaList);
        }
        return "/app/empresa/index";
    }

}
