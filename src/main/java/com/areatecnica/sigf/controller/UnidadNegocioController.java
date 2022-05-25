package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.facade.UnidadNegocioFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.List;

@Named(value = "unidadNegocioController")
@ViewScoped
public class UnidadNegocioController extends AbstractController<UnidadNegocio> {

    @Inject
    private CuentaController unidadNegocioIdCuentaController;
    @Inject
    private OperadorTransporteController unidadNegocioIdOperadorTransporteController;
    @Inject
    private RegionController unidadNegocioIdRegionController;

    // Flags to indicate if child collections are empty
    private boolean isContratoUnidadListEmpty;
    private boolean isBusListEmpty;
    private boolean isValorRolloUnidadListEmpty;
    private boolean isServicioListEmpty;

    public UnidadNegocioController() {
        // Inform the Abstract parent controller of the concrete UnidadNegocio Entity
        super(UnidadNegocio.class);
    }

    @Override
    public UnidadNegocio prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setUnidadNegocioIdCuenta(this.getUserCount());
        return getSelected();
    }

    @Override
    public Collection<UnidadNegocio> getItems() {
        return super.getItems(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        unidadNegocioIdCuentaController.setSelected(null);
        unidadNegocioIdOperadorTransporteController.setSelected(null);
        unidadNegocioIdRegionController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsContratoUnidadListEmpty();
        this.setIsBusListEmpty();
        this.setIsValorRolloUnidadListEmpty();
        this.setIsServicioListEmpty();
    }

    public boolean getIsContratoUnidadListEmpty() {
        return this.isContratoUnidadListEmpty;
    }

    private void setIsContratoUnidadListEmpty() {
        UnidadNegocio selected = this.getSelected();
        if (selected != null) {
            UnidadNegocioFacade ejbFacade = (UnidadNegocioFacade) this.getFacade();
            this.isContratoUnidadListEmpty = ejbFacade.isContratoUnidadListEmpty(selected);
        } else {
            this.isContratoUnidadListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ContratoUnidad entities
     * that are retrieved from UnidadNegocio and returns the navigation outcome.
     *
     * @return navigation outcome for ContratoUnidad page
     */
    public String navigateContratoUnidadList() {
        UnidadNegocio selected = this.getSelected();
        if (selected != null) {
            UnidadNegocioFacade ejbFacade = (UnidadNegocioFacade) this.getFacade();
            List<ContratoUnidad> selectedContratoUnidadList = ejbFacade.findContratoUnidadList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ContratoUnidad_items", selectedContratoUnidadList);
        }
        return "/app/contratoUnidad/index";
    }

    public boolean getIsBusListEmpty() {
        return this.isBusListEmpty;
    }

    private void setIsBusListEmpty() {
        UnidadNegocio selected = this.getSelected();
        if (selected != null) {
            UnidadNegocioFacade ejbFacade = (UnidadNegocioFacade) this.getFacade();
            this.isBusListEmpty = ejbFacade.isBusListEmpty(selected);
        } else {
            this.isBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from UnidadNegocio and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        UnidadNegocio selected = this.getSelected();
        if (selected != null) {
            UnidadNegocioFacade ejbFacade = (UnidadNegocioFacade) this.getFacade();
            List<Bus> selectedBusList = ejbFacade.findBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", selectedBusList);
        }
        return "/app/bus/index";
    }

    public boolean getIsValorRolloUnidadListEmpty() {
        return this.isValorRolloUnidadListEmpty;
    }

    private void setIsValorRolloUnidadListEmpty() {
        UnidadNegocio selected = this.getSelected();
        if (selected != null) {
            UnidadNegocioFacade ejbFacade = (UnidadNegocioFacade) this.getFacade();
            this.isValorRolloUnidadListEmpty = ejbFacade.isValorRolloUnidadListEmpty(selected);
        } else {
            this.isValorRolloUnidadListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ValorRolloUnidad entities
     * that are retrieved from UnidadNegocio and returns the navigation outcome.
     *
     * @return navigation outcome for ValorRolloUnidad page
     */
    public String navigateValorRolloUnidadList() {
        UnidadNegocio selected = this.getSelected();
        if (selected != null) {
            UnidadNegocioFacade ejbFacade = (UnidadNegocioFacade) this.getFacade();
            List<ValorRolloUnidad> selectedValorRolloUnidadList = ejbFacade.findValorRolloUnidadList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ValorRolloUnidad_items", selectedValorRolloUnidadList);
        }
        return "/app/valorRolloUnidad/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUnidadNegocioIdCuenta(ActionEvent event) {
        UnidadNegocio selected = this.getSelected();
        if (selected != null && unidadNegocioIdCuentaController.getSelected() == null) {
            unidadNegocioIdCuentaController.setSelected(selected.getUnidadNegocioIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the OperadorTransporte controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUnidadNegocioIdOperadorTransporte(ActionEvent event) {
        UnidadNegocio selected = this.getSelected();
        if (selected != null && unidadNegocioIdOperadorTransporteController.getSelected() == null) {
            unidadNegocioIdOperadorTransporteController.setSelected(selected.getUnidadNegocioIdOperadorTransporte());
        }
    }

    /**
     * Sets the "selected" attribute of the Region controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUnidadNegocioIdRegion(ActionEvent event) {
        UnidadNegocio selected = this.getSelected();
        if (selected != null && unidadNegocioIdRegionController.getSelected() == null) {
            unidadNegocioIdRegionController.setSelected(selected.getUnidadNegocioIdRegion());
        }
    }

    public boolean getIsServicioListEmpty() {
        return this.isServicioListEmpty;
    }

    private void setIsServicioListEmpty() {
        UnidadNegocio selected = this.getSelected();
        if (selected != null) {
            UnidadNegocioFacade ejbFacade = (UnidadNegocioFacade) this.getFacade();
            this.isServicioListEmpty = ejbFacade.isServicioListEmpty(selected);
        } else {
            this.isServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from UnidadNegocio and returns the navigation outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        UnidadNegocio selected = this.getSelected();
        if (selected != null) {
            UnidadNegocioFacade ejbFacade = (UnidadNegocioFacade) this.getFacade();
            List<Servicio> selectedServicioList = ejbFacade.findServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", selectedServicioList);
        }
        return "/app/servicio/index";
    }

}
