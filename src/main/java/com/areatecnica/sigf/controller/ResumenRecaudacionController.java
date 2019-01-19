package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ResumenRecaudacion;
import com.areatecnica.sigf.entities.DetalleResumenRecaudacion;
import com.areatecnica.sigf.entities.DetalleResumenCheque;
import com.areatecnica.sigf.entities.CtvResumen;
import java.util.List;
import com.areatecnica.sigf.facade.ResumenRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "resumenRecaudacionController")
@ViewScoped
public class ResumenRecaudacionController extends AbstractController<ResumenRecaudacion> {

    @Inject
    private CajaRecaudacionController resumenRecaudacionIdCajaController;

    // Flags to indicate if child collections are empty
    private boolean isDetalleResumenRecaudacionListEmpty;
    private boolean isDetalleResumenChequeListEmpty;
    private boolean isCtvResumenListEmpty;

    public ResumenRecaudacionController() {
        // Inform the Abstract parent controller of the concrete ResumenRecaudacion Entity
        super(ResumenRecaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        resumenRecaudacionIdCajaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDetalleResumenRecaudacionListEmpty();
        this.setIsDetalleResumenChequeListEmpty();
        this.setIsCtvResumenListEmpty();
    }

    public boolean getIsDetalleResumenRecaudacionListEmpty() {
        return this.isDetalleResumenRecaudacionListEmpty;
    }

    private void setIsDetalleResumenRecaudacionListEmpty() {
        ResumenRecaudacion selected = this.getSelected();
        if (selected != null) {
            ResumenRecaudacionFacade ejbFacade = (ResumenRecaudacionFacade) this.getFacade();
            this.isDetalleResumenRecaudacionListEmpty = ejbFacade.isDetalleResumenRecaudacionListEmpty(selected);
        } else {
            this.isDetalleResumenRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleResumenRecaudacion
     * entities that are retrieved from ResumenRecaudacion and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleResumenRecaudacion page
     */
    public String navigateDetalleResumenRecaudacionList() {
        ResumenRecaudacion selected = this.getSelected();
        if (selected != null) {
            ResumenRecaudacionFacade ejbFacade = (ResumenRecaudacionFacade) this.getFacade();
            List<DetalleResumenRecaudacion> selectedDetalleResumenRecaudacionList = ejbFacade.findDetalleResumenRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleResumenRecaudacion_items", selectedDetalleResumenRecaudacionList);
        }
        return "/app/detalleResumenRecaudacion/index";
    }

    /**
     * Sets the "selected" attribute of the CajaRecaudacion controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareResumenRecaudacionIdCaja(ActionEvent event) {
        ResumenRecaudacion selected = this.getSelected();
        if (selected != null && resumenRecaudacionIdCajaController.getSelected() == null) {
            resumenRecaudacionIdCajaController.setSelected(selected.getResumenRecaudacionIdCaja());
        }
    }

    public boolean getIsDetalleResumenChequeListEmpty() {
        return this.isDetalleResumenChequeListEmpty;
    }

    private void setIsDetalleResumenChequeListEmpty() {
        ResumenRecaudacion selected = this.getSelected();
        if (selected != null) {
            ResumenRecaudacionFacade ejbFacade = (ResumenRecaudacionFacade) this.getFacade();
            this.isDetalleResumenChequeListEmpty = ejbFacade.isDetalleResumenChequeListEmpty(selected);
        } else {
            this.isDetalleResumenChequeListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleResumenCheque
     * entities that are retrieved from ResumenRecaudacion and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleResumenCheque page
     */
    public String navigateDetalleResumenChequeList() {
        ResumenRecaudacion selected = this.getSelected();
        if (selected != null) {
            ResumenRecaudacionFacade ejbFacade = (ResumenRecaudacionFacade) this.getFacade();
            List<DetalleResumenCheque> selectedDetalleResumenChequeList = ejbFacade.findDetalleResumenChequeList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleResumenCheque_items", selectedDetalleResumenChequeList);
        }
        return "/app/detalleResumenCheque/index";
    }

    public boolean getIsCtvResumenListEmpty() {
        return this.isCtvResumenListEmpty;
    }

    private void setIsCtvResumenListEmpty() {
        ResumenRecaudacion selected = this.getSelected();
        if (selected != null) {
            ResumenRecaudacionFacade ejbFacade = (ResumenRecaudacionFacade) this.getFacade();
            this.isCtvResumenListEmpty = ejbFacade.isCtvResumenListEmpty(selected);
        } else {
            this.isCtvResumenListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CtvResumen entities that
     * are retrieved from ResumenRecaudacion and returns the navigation outcome.
     *
     * @return navigation outcome for CtvResumen page
     */
    public String navigateCtvResumenList() {
        ResumenRecaudacion selected = this.getSelected();
        if (selected != null) {
            ResumenRecaudacionFacade ejbFacade = (ResumenRecaudacionFacade) this.getFacade();
            List<CtvResumen> selectedCtvResumenList = ejbFacade.findCtvResumenList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CtvResumen_items", selectedCtvResumenList);
        }
        return "/app/ctvResumen/index";
    }

}
