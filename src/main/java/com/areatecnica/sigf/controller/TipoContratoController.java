package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.TipoContrato;
import com.areatecnica.sigf.facade.TipoContratoFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoContratoController")
@ViewScoped
public class TipoContratoController extends AbstractController<TipoContrato> {

    @Inject
    private CuentaController tipoContratoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isLiquidacionSueldoListEmpty;
    private boolean isRelacionLaboralListEmpty;

    public TipoContratoController() {
        // Inform the Abstract parent controller of the concrete TipoContrato Entity
        super(TipoContrato.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoContratoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsLiquidacionSueldoListEmpty();
        this.setIsRelacionLaboralListEmpty();
    }

    public boolean getIsLiquidacionSueldoListEmpty() {
        return this.isLiquidacionSueldoListEmpty;
    }

    private void setIsLiquidacionSueldoListEmpty() {
        TipoContrato selected = this.getSelected();
        if (selected != null) {
            TipoContratoFacade ejbFacade = (TipoContratoFacade) this.getFacade();
            this.isLiquidacionSueldoListEmpty = ejbFacade.isLiquidacionSueldoListEmpty(selected);
        } else {
            this.isLiquidacionSueldoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from TipoContrato and returns the navigation
     * outcome.
     *
     * @return navigation outcome for LiquidacionSueldo page
     */
    public String navigateLiquidacionSueldoList() {
        TipoContrato selected = this.getSelected();
        if (selected != null) {
            TipoContratoFacade ejbFacade = (TipoContratoFacade) this.getFacade();
            List<LiquidacionSueldo> selectedLiquidacionSueldoList = ejbFacade.findLiquidacionSueldoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionSueldo_items", selectedLiquidacionSueldoList);
        }
        return "/app/liquidacionSueldo/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoContratoIdCuenta(ActionEvent event) {
        TipoContrato selected = this.getSelected();
        if (selected != null && tipoContratoIdCuentaController.getSelected() == null) {
            tipoContratoIdCuentaController.setSelected(selected.getTipoContratoIdCuenta());
        }
    }

    public boolean getIsRelacionLaboralListEmpty() {
        return this.isRelacionLaboralListEmpty;
    }

    private void setIsRelacionLaboralListEmpty() {
        TipoContrato selected = this.getSelected();
        if (selected != null) {
            TipoContratoFacade ejbFacade = (TipoContratoFacade) this.getFacade();
            this.isRelacionLaboralListEmpty = ejbFacade.isRelacionLaboralListEmpty(selected);
        } else {
            this.isRelacionLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from TipoContrato and returns the navigation outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        TipoContrato selected = this.getSelected();
        if (selected != null) {
            TipoContratoFacade ejbFacade = (TipoContratoFacade) this.getFacade();
            List<RelacionLaboral> selectedRelacionLaboralList = ejbFacade.findRelacionLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", selectedRelacionLaboralList);
        }
        return "/app/relacionLaboral/index";
    }

}
