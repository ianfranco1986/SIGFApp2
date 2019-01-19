package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CajaCompensacion;
import com.areatecnica.sigf.entities.Empresa;
import java.util.List;
import com.areatecnica.sigf.facade.CajaCompensacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cajaCompensacionController")
@ViewScoped
public class CajaCompensacionController extends AbstractController<CajaCompensacion> {

    @Inject
    private CuentaController cajaCompensacionIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isEmpresaListEmpty;

    public CajaCompensacionController() {
        // Inform the Abstract parent controller of the concrete CajaCompensacion Entity
        super(CajaCompensacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cajaCompensacionIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEmpresaListEmpty();
    }

    public boolean getIsEmpresaListEmpty() {
        return this.isEmpresaListEmpty;
    }

    private void setIsEmpresaListEmpty() {
        CajaCompensacion selected = this.getSelected();
        if (selected != null) {
            CajaCompensacionFacade ejbFacade = (CajaCompensacionFacade) this.getFacade();
            this.isEmpresaListEmpty = ejbFacade.isEmpresaListEmpty(selected);
        } else {
            this.isEmpresaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Empresa entities that are
     * retrieved from CajaCompensacion and returns the navigation outcome.
     *
     * @return navigation outcome for Empresa page
     */
    public String navigateEmpresaList() {
        CajaCompensacion selected = this.getSelected();
        if (selected != null) {
            CajaCompensacionFacade ejbFacade = (CajaCompensacionFacade) this.getFacade();
            List<Empresa> selectedEmpresaList = ejbFacade.findEmpresaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empresa_items", selectedEmpresaList);
        }
        return "/app/empresa/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCajaCompensacionIdCuenta(ActionEvent event) {
        CajaCompensacion selected = this.getSelected();
        if (selected != null && cajaCompensacionIdCuentaController.getSelected() == null) {
            cajaCompensacionIdCuentaController.setSelected(selected.getCajaCompensacionIdCuenta());
        }
    }

}
