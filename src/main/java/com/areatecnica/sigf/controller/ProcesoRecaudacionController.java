package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.CuentaBancoProceso;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.entities.CajaProceso;
import java.util.List;
import com.areatecnica.sigf.facade.ProcesoRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "procesoRecaudacionController")
@ViewScoped
public class ProcesoRecaudacionController extends AbstractController<ProcesoRecaudacion> {

    @Inject
    private CuentaController procesoRecaudacionIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isCuentaBancoProcesoListEmpty;
    private boolean isBusListEmpty;
    private boolean isEgresoProcesoRecaudacionListEmpty;
    private boolean isCajaProcesoListEmpty;

    public ProcesoRecaudacionController() {
        // Inform the Abstract parent controller of the concrete ProcesoRecaudacion Entity
        super(ProcesoRecaudacion.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("ProcesoRecaudacion.findByCuenta");
    }

    @Override
    public ProcesoRecaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setProcesoRecaudacionIdCuenta(this.getUserCount());
        return this.getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        procesoRecaudacionIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCuentaBancoProcesoListEmpty();
        this.setIsBusListEmpty();
        this.setIsEgresoProcesoRecaudacionListEmpty();
        this.setIsCajaProcesoListEmpty();
    }

    public boolean getIsCuentaBancoProcesoListEmpty() {
        return this.isCuentaBancoProcesoListEmpty;
    }

    private void setIsCuentaBancoProcesoListEmpty() {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null) {
            ProcesoRecaudacionFacade ejbFacade = (ProcesoRecaudacionFacade) this.getFacade();
            this.isCuentaBancoProcesoListEmpty = ejbFacade.isCuentaBancoProcesoListEmpty(selected);
        } else {
            this.isCuentaBancoProcesoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CuentaBancoProceso
     * entities that are retrieved from ProcesoRecaudacion and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CuentaBancoProceso page
     */
    public String navigateCuentaBancoProcesoList() {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null) {
            ProcesoRecaudacionFacade ejbFacade = (ProcesoRecaudacionFacade) this.getFacade();
            List<CuentaBancoProceso> selectedCuentaBancoProcesoList = ejbFacade.findCuentaBancoProcesoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancoProceso_items", selectedCuentaBancoProcesoList);
        }
        return "/app/cuentaBancoProceso/index";
    }

    public boolean getIsBusListEmpty() {
        return this.isBusListEmpty;
    }

    private void setIsBusListEmpty() {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null) {
            ProcesoRecaudacionFacade ejbFacade = (ProcesoRecaudacionFacade) this.getFacade();
            this.isBusListEmpty = ejbFacade.isBusListEmpty(selected);
        } else {
            this.isBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from ProcesoRecaudacion and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null) {
            ProcesoRecaudacionFacade ejbFacade = (ProcesoRecaudacionFacade) this.getFacade();
            List<Bus> selectedBusList = ejbFacade.findBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", selectedBusList);
        }
        return "/app/bus/index";
    }

    public boolean getIsEgresoProcesoRecaudacionListEmpty() {
        return this.isEgresoProcesoRecaudacionListEmpty;
    }

    private void setIsEgresoProcesoRecaudacionListEmpty() {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null) {
            ProcesoRecaudacionFacade ejbFacade = (ProcesoRecaudacionFacade) this.getFacade();
            this.isEgresoProcesoRecaudacionListEmpty = ejbFacade.isEgresoProcesoRecaudacionListEmpty(selected);
        } else {
            this.isEgresoProcesoRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoProcesoRecaudacion
     * entities that are retrieved from ProcesoRecaudacion and returns the
     * navigation outcome.
     *
     * @return navigation outcome for EgresoProcesoRecaudacion page
     */
    public String navigateEgresoProcesoRecaudacionList() {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null) {
            ProcesoRecaudacionFacade ejbFacade = (ProcesoRecaudacionFacade) this.getFacade();
            List<EgresoProcesoRecaudacion> selectedEgresoProcesoRecaudacionList = ejbFacade.findEgresoProcesoRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoProcesoRecaudacion_items", selectedEgresoProcesoRecaudacionList);
        }
        return "/app/egresoProcesoRecaudacion/index";
    }

    public boolean getIsCajaProcesoListEmpty() {
        return this.isCajaProcesoListEmpty;
    }

    private void setIsCajaProcesoListEmpty() {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null) {
            ProcesoRecaudacionFacade ejbFacade = (ProcesoRecaudacionFacade) this.getFacade();
            this.isCajaProcesoListEmpty = ejbFacade.isCajaProcesoListEmpty(selected);
        } else {
            this.isCajaProcesoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CajaProceso entities that
     * are retrieved from ProcesoRecaudacion and returns the navigation outcome.
     *
     * @return navigation outcome for CajaProceso page
     */
    public String navigateCajaProcesoList() {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null) {
            ProcesoRecaudacionFacade ejbFacade = (ProcesoRecaudacionFacade) this.getFacade();
            List<CajaProceso> selectedCajaProcesoList = ejbFacade.findCajaProcesoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaProceso_items", selectedCajaProcesoList);
        }
        return "/app/cajaProceso/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProcesoRecaudacionIdCuenta(ActionEvent event) {
        ProcesoRecaudacion selected = this.getSelected();
        if (selected != null && procesoRecaudacionIdCuentaController.getSelected() == null) {
            procesoRecaudacionIdCuentaController.setSelected(selected.getProcesoRecaudacionIdCuenta());
        }
    }

}
