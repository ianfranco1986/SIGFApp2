package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import java.util.List;
import com.areatecnica.sigf.facade.LiquidacionEmpresaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "liquidacionEmpresaController")
@ViewScoped
public class LiquidacionEmpresaController extends AbstractController<LiquidacionEmpresa> {

    @Inject
    private EmpresaController liquidacionEmpresaIdEmpresaController;

    // Flags to indicate if child collections are empty
    private boolean isCargoLiquidacionListEmpty;
    private boolean isAbonoLiquidacionListEmpty;

    public LiquidacionEmpresaController() {
        // Inform the Abstract parent controller of the concrete LiquidacionEmpresa Entity
        super(LiquidacionEmpresa.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        liquidacionEmpresaIdEmpresaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCargoLiquidacionListEmpty();
        this.setIsAbonoLiquidacionListEmpty();
    }

    public boolean getIsCargoLiquidacionListEmpty() {
        return this.isCargoLiquidacionListEmpty;
    }

    private void setIsCargoLiquidacionListEmpty() {
        LiquidacionEmpresa selected = this.getSelected();
        if (selected != null) {
            LiquidacionEmpresaFacade ejbFacade = (LiquidacionEmpresaFacade) this.getFacade();
            this.isCargoLiquidacionListEmpty = ejbFacade.isCargoLiquidacionListEmpty(selected);
        } else {
            this.isCargoLiquidacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargoLiquidacion entities
     * that are retrieved from LiquidacionEmpresa and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CargoLiquidacion page
     */
    public String navigateCargoLiquidacionList() {
        LiquidacionEmpresa selected = this.getSelected();
        if (selected != null) {
            LiquidacionEmpresaFacade ejbFacade = (LiquidacionEmpresaFacade) this.getFacade();
            List<CargoLiquidacion> selectedCargoLiquidacionList = ejbFacade.findCargoLiquidacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargoLiquidacion_items", selectedCargoLiquidacionList);
        }
        return "/app/cargoLiquidacion/index";
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionEmpresaIdEmpresa(ActionEvent event) {
        LiquidacionEmpresa selected = this.getSelected();
        if (selected != null && liquidacionEmpresaIdEmpresaController.getSelected() == null) {
            liquidacionEmpresaIdEmpresaController.setSelected(selected.getLiquidacionEmpresaIdEmpresa());
        }
    }

    public boolean getIsAbonoLiquidacionListEmpty() {
        return this.isAbonoLiquidacionListEmpty;
    }

    private void setIsAbonoLiquidacionListEmpty() {
        LiquidacionEmpresa selected = this.getSelected();
        if (selected != null) {
            LiquidacionEmpresaFacade ejbFacade = (LiquidacionEmpresaFacade) this.getFacade();
            this.isAbonoLiquidacionListEmpty = ejbFacade.isAbonoLiquidacionListEmpty(selected);
        } else {
            this.isAbonoLiquidacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of AbonoLiquidacion entities
     * that are retrieved from LiquidacionEmpresa and returns the navigation
     * outcome.
     *
     * @return navigation outcome for AbonoLiquidacion page
     */
    public String navigateAbonoLiquidacionList() {
        LiquidacionEmpresa selected = this.getSelected();
        if (selected != null) {
            LiquidacionEmpresaFacade ejbFacade = (LiquidacionEmpresaFacade) this.getFacade();
            List<AbonoLiquidacion> selectedAbonoLiquidacionList = ejbFacade.findAbonoLiquidacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AbonoLiquidacion_items", selectedAbonoLiquidacionList);
        }
        return "/app/abonoLiquidacion/index";
    }

}
