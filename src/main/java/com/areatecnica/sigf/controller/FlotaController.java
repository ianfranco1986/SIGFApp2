package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.IFlotaDao;
import com.areatecnica.sigf.dao.impl.IFlotaDaoImpl;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.EgresoFlota;
import java.util.List;
import com.areatecnica.sigf.facade.FlotaFacade;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "flotaController")
@ViewScoped
public class FlotaController extends AbstractController<Flota> {

    private List<Flota> items;
    private IFlotaDao dao;

    @Inject
    private CuentaController flotaIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isBusListEmpty;
    private boolean isEgresoFlotaListEmpty;

    public FlotaController() {
        // Inform the Abstract parent controller of the concrete Flota Entity
        super(Flota.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        this.dao = new IFlotaDaoImpl();
        this.items = this.dao.findByCuenta(this.getUserCount());
    }

    @Override
    public Flota prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setFlotaIdCuenta(this.getUserCount());
        return getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        flotaIdCuentaController.setSelected(null);
    }

    public List<Flota> getItems() {
        return items;
    }

    public void setItems(List<Flota> items) {
        this.items = items;
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsBusListEmpty();
        this.setIsEgresoFlotaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFlotaIdCuenta(ActionEvent event) {
        Flota selected = this.getSelected();
        if (selected != null && flotaIdCuentaController.getSelected() == null) {
            flotaIdCuentaController.setSelected(selected.getFlotaIdCuenta());
        }
    }

    public boolean getIsBusListEmpty() {
        return this.isBusListEmpty;
    }

    private void setIsBusListEmpty() {
        Flota selected = this.getSelected();
        if (selected != null) {
            FlotaFacade ejbFacade = (FlotaFacade) this.getFacade();
            this.isBusListEmpty = ejbFacade.isBusListEmpty(selected);
        } else {
            this.isBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from Flota and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        Flota selected = this.getSelected();
        if (selected != null) {
            FlotaFacade ejbFacade = (FlotaFacade) this.getFacade();
            List<Bus> selectedBusList = ejbFacade.findBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", selectedBusList);
        }
        return "/app/bus/index";
    }

    public boolean getIsEgresoFlotaListEmpty() {
        return this.isEgresoFlotaListEmpty;
    }

    private void setIsEgresoFlotaListEmpty() {
        Flota selected = this.getSelected();
        if (selected != null) {
            FlotaFacade ejbFacade = (FlotaFacade) this.getFacade();
            this.isEgresoFlotaListEmpty = ejbFacade.isEgresoFlotaListEmpty(selected);
        } else {
            this.isEgresoFlotaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoFlota entities that
     * are retrieved from Flota and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoFlota page
     */
    public String navigateEgresoFlotaList() {
        Flota selected = this.getSelected();
        if (selected != null) {
            FlotaFacade ejbFacade = (FlotaFacade) this.getFacade();
            List<EgresoFlota> selectedEgresoFlotaList = ejbFacade.findEgresoFlotaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoFlota_items", selectedEgresoFlotaList);
        }
        return "/app/egresoFlota/index";
    }

}
