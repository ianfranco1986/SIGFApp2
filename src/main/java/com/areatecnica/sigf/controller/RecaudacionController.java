package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.facade.RecaudacionFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "recaudacionController")
@ViewScoped
public class RecaudacionController extends AbstractController<Recaudacion> {

    @Inject
    private CajaRecaudacionController recaudacionIdCajaController;

    // Flags to indicate if child collections are empty
    private boolean isRecaudacionBoletoListEmpty;
    private boolean isRecaudacionMinutoListEmpty;
    private boolean isRecaudacionCombustibleListEmpty;
    private boolean isRecaudacionDescuentoExtraListEmpty;
    private boolean isRecaudacionGuiaListEmpty;
    private boolean isRecaudacionExtraListEmpty;

    public RecaudacionController() {
        // Inform the Abstract parent controller of the concrete Recaudacion Entity
        super(Recaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        recaudacionIdCajaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRecaudacionBoletoListEmpty();
        this.setIsRecaudacionMinutoListEmpty();
        this.setIsRecaudacionCombustibleListEmpty();
        this.setIsRecaudacionDescuentoExtraListEmpty();
        this.setIsRecaudacionGuiaListEmpty();
        this.setIsRecaudacionExtraListEmpty();
    }

    public boolean getIsRecaudacionBoletoListEmpty() {
        return this.isRecaudacionBoletoListEmpty;
    }

    private void setIsRecaudacionBoletoListEmpty() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            this.isRecaudacionBoletoListEmpty = ejbFacade.isRecaudacionBoletoListEmpty(selected);
        } else {
            this.isRecaudacionBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionBoleto
     * entities that are retrieved from Recaudacion and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RecaudacionBoleto page
     */
    public String navigateRecaudacionBoletoList() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            List<RecaudacionBoleto> selectedRecaudacionBoletoList = ejbFacade.findRecaudacionBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionBoleto_items", selectedRecaudacionBoletoList);
        }
        return "/app/recaudacionBoleto/index";
    }

    public boolean getIsRecaudacionMinutoListEmpty() {
        return this.isRecaudacionMinutoListEmpty;
    }

    private void setIsRecaudacionMinutoListEmpty() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            this.isRecaudacionMinutoListEmpty = ejbFacade.isRecaudacionMinutoListEmpty(selected);
        } else {
            this.isRecaudacionMinutoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionMinuto
     * entities that are retrieved from Recaudacion and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RecaudacionMinuto page
     */
    public String navigateRecaudacionMinutoList() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            List<RecaudacionMinuto> selectedRecaudacionMinutoList = ejbFacade.findRecaudacionMinutoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionMinuto_items", selectedRecaudacionMinutoList);
        }
        return "/app/recaudacionMinuto/index";
    }

    public boolean getIsRecaudacionCombustibleListEmpty() {
        return this.isRecaudacionCombustibleListEmpty;
    }

    private void setIsRecaudacionCombustibleListEmpty() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            this.isRecaudacionCombustibleListEmpty = ejbFacade.isRecaudacionCombustibleListEmpty(selected);
        } else {
            this.isRecaudacionCombustibleListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionCombustible
     * entities that are retrieved from Recaudacion and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RecaudacionCombustible page
     */
    public String navigateRecaudacionCombustibleList() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            List<RecaudacionCombustible> selectedRecaudacionCombustibleList = ejbFacade.findRecaudacionCombustibleList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionCombustible_items", selectedRecaudacionCombustibleList);
        }
        return "/app/recaudacionCombustible/index";
    }

    /**
     * Sets the "selected" attribute of the CajaRecaudacion controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionIdCaja(ActionEvent event) {
        Recaudacion selected = this.getSelected();
        if (selected != null && recaudacionIdCajaController.getSelected() == null) {
            recaudacionIdCajaController.setSelected(selected.getRecaudacionIdCaja());
        }
    }

    public boolean getIsRecaudacionDescuentoExtraListEmpty() {
        return this.isRecaudacionDescuentoExtraListEmpty;
    }

    private void setIsRecaudacionDescuentoExtraListEmpty() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            this.isRecaudacionDescuentoExtraListEmpty = ejbFacade.isRecaudacionDescuentoExtraListEmpty(selected);
        } else {
            this.isRecaudacionDescuentoExtraListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionDescuentoExtra
     * entities that are retrieved from Recaudacion and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RecaudacionDescuentoExtra page
     */
    public String navigateRecaudacionDescuentoExtraList() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            List<RecaudacionDescuentoExtra> selectedRecaudacionDescuentoExtraList = ejbFacade.findRecaudacionDescuentoExtraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionDescuentoExtra_items", selectedRecaudacionDescuentoExtraList);
        }
        return "/app/recaudacionDescuentoExtra/index";
    }

    public boolean getIsRecaudacionGuiaListEmpty() {
        return this.isRecaudacionGuiaListEmpty;
    }

    private void setIsRecaudacionGuiaListEmpty() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            this.isRecaudacionGuiaListEmpty = ejbFacade.isRecaudacionGuiaListEmpty(selected);
        } else {
            this.isRecaudacionGuiaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionGuia entities
     * that are retrieved from Recaudacion and returns the navigation outcome.
     *
     * @return navigation outcome for RecaudacionGuia page
     */
    public String navigateRecaudacionGuiaList() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            List<RecaudacionGuia> selectedRecaudacionGuiaList = ejbFacade.findRecaudacionGuiaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionGuia_items", selectedRecaudacionGuiaList);
        }
        return "/app/recaudacionGuia/index";
    }

    public boolean getIsRecaudacionExtraListEmpty() {
        return this.isRecaudacionExtraListEmpty;
    }

    private void setIsRecaudacionExtraListEmpty() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            this.isRecaudacionExtraListEmpty = ejbFacade.isRecaudacionExtraListEmpty(selected);
        } else {
            this.isRecaudacionExtraListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionExtra entities
     * that are retrieved from Recaudacion and returns the navigation outcome.
     *
     * @return navigation outcome for RecaudacionExtra page
     */
    public String navigateRecaudacionExtraList() {
        Recaudacion selected = this.getSelected();
        if (selected != null) {
            RecaudacionFacade ejbFacade = (RecaudacionFacade) this.getFacade();
            List<RecaudacionExtra> selectedRecaudacionExtraList = ejbFacade.findRecaudacionExtraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionExtra_items", selectedRecaudacionExtraList);
        }
        return "/app/recaudacionExtra/index";
    }

}
