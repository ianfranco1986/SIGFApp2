package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.IFlotaDao;
import com.areatecnica.sigf.dao.impl.FlotaDaoImpl;
import com.areatecnica.sigf.entities.Flota;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "flotaController")
@ViewScoped
public class FlotaController extends AbstractController<Flota> {

    private IFlotaDao dao;

    @Inject
    private CuentaController flotaIdCuentaController;

    private List<Flota> items;

    // Flags to indicate if child collections are empty
    private boolean isBusListEmpty;
    private boolean isEgresoFlotaListEmpty;

    public FlotaController() {
        // Inform the Abstract parent controller of the concrete Flota Entity
        super(Flota.class);
        setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Flota.findAllByCuenta");
    }

    @PostConstruct
    @Override
    public void initParams() {
        this.dao = new FlotaDaoImpl();
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

}
