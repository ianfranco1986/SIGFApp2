package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.FrecuenciaServicio;
import com.areatecnica.sigf.entities.PeriodoFrecuencia;
import com.areatecnica.sigf.facade.PeriodoFrecuenciaFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "periodoFrecuenciaController")
@ViewScoped
public class PeriodoFrecuenciaController extends AbstractController<PeriodoFrecuencia> {

    @Inject
    private CuentaController periodoFrecuenciaIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isFrecuenciaServicioListEmpty;

    public PeriodoFrecuenciaController() {
        // Inform the Abstract parent controller of the concrete PeriodoFrecuencia Entity
        super(PeriodoFrecuencia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        periodoFrecuenciaIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsFrecuenciaServicioListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePeriodoFrecuenciaIdCuenta(ActionEvent event) {
        PeriodoFrecuencia selected = this.getSelected();
        if (selected != null && periodoFrecuenciaIdCuentaController.getSelected() == null) {
            periodoFrecuenciaIdCuentaController.setSelected(selected.getPeriodoFrecuenciaIdCuenta());
        }
    }

    public boolean getIsFrecuenciaServicioListEmpty() {
        return this.isFrecuenciaServicioListEmpty;
    }

    private void setIsFrecuenciaServicioListEmpty() {
        PeriodoFrecuencia selected = this.getSelected();
        if (selected != null) {
            PeriodoFrecuenciaFacade ejbFacade = (PeriodoFrecuenciaFacade) this.getFacade();
            this.isFrecuenciaServicioListEmpty = ejbFacade.isFrecuenciaServicioListEmpty(selected);
        } else {
            this.isFrecuenciaServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of FrecuenciaServicio
     * entities that are retrieved from PeriodoFrecuencia and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FrecuenciaServicio page
     */
    public String navigateFrecuenciaServicioList() {
        PeriodoFrecuencia selected = this.getSelected();
        if (selected != null) {
            PeriodoFrecuenciaFacade ejbFacade = (PeriodoFrecuenciaFacade) this.getFacade();
            List<FrecuenciaServicio> selectedFrecuenciaServicioList = ejbFacade.findFrecuenciaServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FrecuenciaServicio_items", selectedFrecuenciaServicioList);
        }
        return "/app/frecuenciaServicio/index";
    }

}
