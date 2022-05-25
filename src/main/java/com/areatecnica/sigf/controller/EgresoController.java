package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.facade.EgresoFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "egresoController")
@ViewScoped
public class EgresoController extends AbstractController<Egreso> {

    @Inject
    private CuentaController egresoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isEgresoCajaRecaudacionListEmpty;
    private boolean isEgresoProcesoRecaudacionListEmpty;
    private boolean isRecaudacionGuiaListEmpty;
    private boolean isEgresoBusListEmpty;
    private boolean isEgresoFlotaListEmpty;

    public EgresoController() {
        // Inform the Abstract parent controller of the concrete Egreso Entity
        super(Egreso.class);
//        this.setLimitedByCuenta(Boolean.TRUE);
//        this.setNamedQuery("Egreso.findByCuenta");
    }

    @Override
    public Egreso prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setEgresoIdCuenta(this.getUserCount());
        return this.getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        egresoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEgresoCajaRecaudacionListEmpty();
        this.setIsEgresoProcesoRecaudacionListEmpty();
        this.setIsRecaudacionGuiaListEmpty();
        this.setIsEgresoBusListEmpty();
        this.setIsEgresoFlotaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoIdCuenta(ActionEvent event) {
        Egreso selected = this.getSelected();
        if (selected != null && egresoIdCuentaController.getSelected() == null) {
            egresoIdCuentaController.setSelected(selected.getEgresoIdCuenta());
        }
    }

    public boolean getIsEgresoCajaRecaudacionListEmpty() {
        return this.isEgresoCajaRecaudacionListEmpty;
    }

    private void setIsEgresoCajaRecaudacionListEmpty() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            this.isEgresoCajaRecaudacionListEmpty = ejbFacade.isEgresoCajaRecaudacionListEmpty(selected);
        } else {
            this.isEgresoCajaRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoCajaRecaudacion
     * entities that are retrieved from Egreso and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EgresoCajaRecaudacion page
     */
    public String navigateEgresoCajaRecaudacionList() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            List<EgresoCajaRecaudacion> selectedEgresoCajaRecaudacionList = ejbFacade.findEgresoCajaRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoCajaRecaudacion_items", selectedEgresoCajaRecaudacionList);
        }
        return "/app/egresoCajaRecaudacion/index";
    }

    public boolean getIsEgresoProcesoRecaudacionListEmpty() {
        return this.isEgresoProcesoRecaudacionListEmpty;
    }

    private void setIsEgresoProcesoRecaudacionListEmpty() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            this.isEgresoProcesoRecaudacionListEmpty = ejbFacade.isEgresoProcesoRecaudacionListEmpty(selected);
        } else {
            this.isEgresoProcesoRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoProcesoRecaudacion
     * entities that are retrieved from Egreso and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EgresoProcesoRecaudacion page
     */
    public String navigateEgresoProcesoRecaudacionList() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            List<EgresoProcesoRecaudacion> selectedEgresoProcesoRecaudacionList = ejbFacade.findEgresoProcesoRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoProcesoRecaudacion_items", selectedEgresoProcesoRecaudacionList);
        }
        return "/app/egresoProcesoRecaudacion/index";
    }

    public boolean getIsRecaudacionGuiaListEmpty() {
        return this.isRecaudacionGuiaListEmpty;
    }

    private void setIsRecaudacionGuiaListEmpty() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            this.isRecaudacionGuiaListEmpty = ejbFacade.isRecaudacionGuiaListEmpty(selected);
        } else {
            this.isRecaudacionGuiaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionGuia entities
     * that are retrieved from Egreso and returns the navigation outcome.
     *
     * @return navigation outcome for RecaudacionGuia page
     */
    public String navigateRecaudacionGuiaList() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            List<RecaudacionGuia> selectedRecaudacionGuiaList = ejbFacade.findRecaudacionGuiaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionGuia_items", selectedRecaudacionGuiaList);
        }
        return "/app/recaudacionGuia/index";
    }

    public boolean getIsEgresoBusListEmpty() {
        return this.isEgresoBusListEmpty;
    }

    private void setIsEgresoBusListEmpty() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            this.isEgresoBusListEmpty = ejbFacade.isEgresoBusListEmpty(selected);
        } else {
            this.isEgresoBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoBus entities that
     * are retrieved from Egreso and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoBus page
     */
    public String navigateEgresoBusList() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            List<EgresoBus> selectedEgresoBusList = ejbFacade.findEgresoBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoBus_items", selectedEgresoBusList);
        }
        return "/app/egresoBus/index";
    }

    public boolean getIsEgresoFlotaListEmpty() {
        return this.isEgresoFlotaListEmpty;
    }

    private void setIsEgresoFlotaListEmpty() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            this.isEgresoFlotaListEmpty = ejbFacade.isEgresoFlotaListEmpty(selected);
        } else {
            this.isEgresoFlotaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoFlota entities that
     * are retrieved from Egreso and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoFlota page
     */
    public String navigateEgresoFlotaList() {
        Egreso selected = this.getSelected();
        if (selected != null) {
            EgresoFacade ejbFacade = (EgresoFacade) this.getFacade();
            List<EgresoFlota> selectedEgresoFlotaList = ejbFacade.findEgresoFlotaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoFlota_items", selectedEgresoFlotaList);
        }
        return "/app/egresoFlota/index";
    }

}
