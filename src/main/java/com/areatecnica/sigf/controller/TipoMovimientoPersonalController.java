package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.entities.TipoMovimientoPersonal;
import com.areatecnica.sigf.facade.TipoMovimientoPersonalFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoMovimientoPersonalController")
@ViewScoped
public class TipoMovimientoPersonalController extends AbstractController<TipoMovimientoPersonal> {

    // Flags to indicate if child collections are empty
    private boolean isLiquidacionSueldoListEmpty;

    public TipoMovimientoPersonalController() {
        // Inform the Abstract parent controller of the concrete TipoMovimientoPersonal Entity
        super(TipoMovimientoPersonal.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsLiquidacionSueldoListEmpty();
    }

    public boolean getIsLiquidacionSueldoListEmpty() {
        return this.isLiquidacionSueldoListEmpty;
    }

    private void setIsLiquidacionSueldoListEmpty() {
        TipoMovimientoPersonal selected = this.getSelected();
        if (selected != null) {
            TipoMovimientoPersonalFacade ejbFacade = (TipoMovimientoPersonalFacade) this.getFacade();
            this.isLiquidacionSueldoListEmpty = ejbFacade.isLiquidacionSueldoListEmpty(selected);
        } else {
            this.isLiquidacionSueldoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from TipoMovimientoPersonal and returns the
     * navigation outcome.
     *
     * @return navigation outcome for LiquidacionSueldo page
     */
    public String navigateLiquidacionSueldoList() {
        TipoMovimientoPersonal selected = this.getSelected();
        if (selected != null) {
            TipoMovimientoPersonalFacade ejbFacade = (TipoMovimientoPersonalFacade) this.getFacade();
            List<LiquidacionSueldo> selectedLiquidacionSueldoList = ejbFacade.findLiquidacionSueldoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionSueldo_items", selectedLiquidacionSueldoList);
        }
        return "/app/liquidacionSueldo/index";
    }

}
