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
        this.setItems(this.dao.findByCuenta(this.getUserCount()));
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

}
