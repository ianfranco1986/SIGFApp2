package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Compra;
import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.facade.CuentaMayorFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "cuentaMayorController")
@ViewScoped
public class CuentaMayorController extends AbstractController<CuentaMayor> {

    @Inject
    private PlanCuentaSubTipoController cuentaMayorSubTipoIdController;

    // Flags to indicate if child collections are empty
    private boolean isCompraListEmpty;

    public CuentaMayorController() {
        // Inform the Abstract parent controller of the concrete CuentaMayor Entity
        super(CuentaMayor.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cuentaMayorSubTipoIdController.setSelected(null);
    }


    public boolean getIsCompraListEmpty() {
        return this.isCompraListEmpty;
    }

    /**
     * Sets the "items" attribute with a collection of Compra entities that are
     * retrieved from CuentaMayor and returns the navigation outcome.
     *
     * @return navigation outcome for Compra page
     */
    public String navigateCompraList() {
        CuentaMayor selected = this.getSelected();
        if (selected != null) {
            CuentaMayorFacade ejbFacade = (CuentaMayorFacade) this.getFacade();
            List<Compra> selectedCompraList = ejbFacade.findCompraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Compra_items", selectedCompraList);
        }
        return "/app/compra/index";
    }

    /**
     * Sets the "selected" attribute of the PlanCuentaSubTipo controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaMayorSubTipoId(ActionEvent event) {
        CuentaMayor selected = this.getSelected();
        if (selected != null && cuentaMayorSubTipoIdController.getSelected() == null) {
            cuentaMayorSubTipoIdController.setSelected(selected.getCuentaMayorSubTipoId());
        }
    }

}
