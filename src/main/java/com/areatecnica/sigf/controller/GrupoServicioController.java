package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Servicio;
import com.areatecnica.sigf.entities.TarifaGrupoServicio;
import java.util.List;
import com.areatecnica.sigf.facade.GrupoServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "grupoServicioController")
@ViewScoped
public class GrupoServicioController extends AbstractController<GrupoServicio> {

    @Inject
    private CuentaController grupoServicioIdCuentaController;
    @Inject
    private TerminalController grupoServicioIdTerminalController;

    // Flags to indicate if child collections are empty
    private boolean isBusListEmpty;
    private boolean isServicioListEmpty;
    private boolean isTarifaGrupoServicioListEmpty;

    public GrupoServicioController() {
        // Inform the Abstract parent controller of the concrete GrupoServicio Entity
        super(GrupoServicio.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("GrupoServicio.findByCuenta");
    }

    @Override
    public GrupoServicio prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setGrupoServicioIdCuenta(this.getUserCount());
        return this.getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        grupoServicioIdCuentaController.setSelected(null);
        grupoServicioIdTerminalController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsBusListEmpty();
        this.setIsServicioListEmpty();
        this.setIsTarifaGrupoServicioListEmpty();
    }

    public boolean getIsBusListEmpty() {
        return this.isBusListEmpty;
    }

    private void setIsBusListEmpty() {
        GrupoServicio selected = this.getSelected();
        if (selected != null) {
            GrupoServicioFacade ejbFacade = (GrupoServicioFacade) this.getFacade();
            this.isBusListEmpty = ejbFacade.isBusListEmpty(selected);
        } else {
            this.isBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from GrupoServicio and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        GrupoServicio selected = this.getSelected();
        if (selected != null) {
            GrupoServicioFacade ejbFacade = (GrupoServicioFacade) this.getFacade();
            List<Bus> selectedBusList = ejbFacade.findBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", selectedBusList);
        }
        return "/app/bus/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGrupoServicioIdCuenta(ActionEvent event) {
        GrupoServicio selected = this.getSelected();
        if (selected != null && grupoServicioIdCuentaController.getSelected() == null) {
            grupoServicioIdCuentaController.setSelected(selected.getGrupoServicioIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGrupoServicioIdTerminal(ActionEvent event) {
        GrupoServicio selected = this.getSelected();
        if (selected != null && grupoServicioIdTerminalController.getSelected() == null) {
            grupoServicioIdTerminalController.setSelected(selected.getGrupoServicioIdTerminal());
        }
    }

    public boolean getIsServicioListEmpty() {
        return this.isServicioListEmpty;
    }

    private void setIsServicioListEmpty() {
        GrupoServicio selected = this.getSelected();
        if (selected != null) {
            GrupoServicioFacade ejbFacade = (GrupoServicioFacade) this.getFacade();
            this.isServicioListEmpty = ejbFacade.isServicioListEmpty(selected);
        } else {
            this.isServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from GrupoServicio and returns the navigation outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        GrupoServicio selected = this.getSelected();
        if (selected != null) {
            GrupoServicioFacade ejbFacade = (GrupoServicioFacade) this.getFacade();
            List<Servicio> selectedServicioList = ejbFacade.findServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", selectedServicioList);
        }
        return "/app/servicio/index";
    }

    public boolean getIsTarifaGrupoServicioListEmpty() {
        return this.isTarifaGrupoServicioListEmpty;
    }

    private void setIsTarifaGrupoServicioListEmpty() {
        GrupoServicio selected = this.getSelected();
        if (selected != null) {
            GrupoServicioFacade ejbFacade = (GrupoServicioFacade) this.getFacade();
            this.isTarifaGrupoServicioListEmpty = ejbFacade.isTarifaGrupoServicioListEmpty(selected);
        } else {
            this.isTarifaGrupoServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TarifaGrupoServicio
     * entities that are retrieved from GrupoServicio and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TarifaGrupoServicio page
     */
    public String navigateTarifaGrupoServicioList() {
        GrupoServicio selected = this.getSelected();
        if (selected != null) {
            GrupoServicioFacade ejbFacade = (GrupoServicioFacade) this.getFacade();
            List<TarifaGrupoServicio> selectedTarifaGrupoServicioList = ejbFacade.findTarifaGrupoServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TarifaGrupoServicio_items", selectedTarifaGrupoServicioList);
        }
        return "/app/tarifaGrupoServicio/index";
    }

}
