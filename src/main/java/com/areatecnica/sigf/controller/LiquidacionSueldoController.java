package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DescuentoLiquidacion;
import com.areatecnica.sigf.entities.HaberLiquidacion;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.facade.LiquidacionSueldoFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "liquidacionSueldoController")
@ViewScoped
public class LiquidacionSueldoController extends AbstractController<LiquidacionSueldo> {

    @Inject
    private EmpresaController liquidacionSueldoIdEmpresaController;
    @Inject
    private TipoMovimientoPersonalController liquidacionSueldoIdMovimientoPersonalController;
    @Inject
    private TerminalController liquidacionSueldoIdTerminalController;
    @Inject
    private TipoContratoController liquidacionSueldoIdTipoContratoController;
    @Inject
    private TrabajadorController liquidacionSueldoIdTrabajadorController;

    // Flags to indicate if child collections are empty
    private boolean isHaberLiquidacionListEmpty;
    private boolean isDescuentoLiquidacionListEmpty;

    public LiquidacionSueldoController() {
        // Inform the Abstract parent controller of the concrete LiquidacionSueldo Entity
        super(LiquidacionSueldo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        liquidacionSueldoIdEmpresaController.setSelected(null);
        liquidacionSueldoIdMovimientoPersonalController.setSelected(null);
        liquidacionSueldoIdTerminalController.setSelected(null);
        liquidacionSueldoIdTipoContratoController.setSelected(null);
        liquidacionSueldoIdTrabajadorController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsHaberLiquidacionListEmpty();
        this.setIsDescuentoLiquidacionListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdEmpresa(ActionEvent event) {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null && liquidacionSueldoIdEmpresaController.getSelected() == null) {
            liquidacionSueldoIdEmpresaController.setSelected(selected.getLiquidacionSueldoIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoMovimientoPersonal controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdMovimientoPersonal(ActionEvent event) {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null && liquidacionSueldoIdMovimientoPersonalController.getSelected() == null) {
            liquidacionSueldoIdMovimientoPersonalController.setSelected(selected.getLiquidacionSueldoIdMovimientoPersonal());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdTerminal(ActionEvent event) {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null && liquidacionSueldoIdTerminalController.getSelected() == null) {
            liquidacionSueldoIdTerminalController.setSelected(selected.getLiquidacionSueldoIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoContrato controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdTipoContrato(ActionEvent event) {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null && liquidacionSueldoIdTipoContratoController.getSelected() == null) {
            liquidacionSueldoIdTipoContratoController.setSelected(selected.getLiquidacionSueldoIdTipoContrato());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdTrabajador(ActionEvent event) {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null && liquidacionSueldoIdTrabajadorController.getSelected() == null) {
            liquidacionSueldoIdTrabajadorController.setSelected(selected.getLiquidacionSueldoIdTrabajador());
        }
    }

    public boolean getIsHaberLiquidacionListEmpty() {
        return this.isHaberLiquidacionListEmpty;
    }

    private void setIsHaberLiquidacionListEmpty() {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null) {
            LiquidacionSueldoFacade ejbFacade = (LiquidacionSueldoFacade) this.getFacade();
            this.isHaberLiquidacionListEmpty = ejbFacade.isHaberLiquidacionListEmpty(selected);
        } else {
            this.isHaberLiquidacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HaberLiquidacion entities
     * that are retrieved from LiquidacionSueldo and returns the navigation
     * outcome.
     *
     * @return navigation outcome for HaberLiquidacion page
     */
    public String navigateHaberLiquidacionList() {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null) {
            LiquidacionSueldoFacade ejbFacade = (LiquidacionSueldoFacade) this.getFacade();
            List<HaberLiquidacion> selectedHaberLiquidacionList = ejbFacade.findHaberLiquidacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HaberLiquidacion_items", selectedHaberLiquidacionList);
        }
        return "/app/haberLiquidacion/index";
    }

    public boolean getIsDescuentoLiquidacionListEmpty() {
        return this.isDescuentoLiquidacionListEmpty;
    }

    private void setIsDescuentoLiquidacionListEmpty() {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null) {
            LiquidacionSueldoFacade ejbFacade = (LiquidacionSueldoFacade) this.getFacade();
            this.isDescuentoLiquidacionListEmpty = ejbFacade.isDescuentoLiquidacionListEmpty(selected);
        } else {
            this.isDescuentoLiquidacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoLiquidacion
     * entities that are retrieved from LiquidacionSueldo and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DescuentoLiquidacion page
     */
    public String navigateDescuentoLiquidacionList() {
        LiquidacionSueldo selected = this.getSelected();
        if (selected != null) {
            LiquidacionSueldoFacade ejbFacade = (LiquidacionSueldoFacade) this.getFacade();
            List<DescuentoLiquidacion> selectedDescuentoLiquidacionList = ejbFacade.findDescuentoLiquidacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoLiquidacion_items", selectedDescuentoLiquidacionList);
        }
        return "/app/descuentoLiquidacion/index";
    }

}
