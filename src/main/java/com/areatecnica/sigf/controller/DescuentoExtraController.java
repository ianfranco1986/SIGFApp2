package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DescuentoExtra;
import com.areatecnica.sigf.entities.DescuentoExtraBus;
import com.areatecnica.sigf.facade.DescuentoExtraFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named(value = "descuentoExtraController")
@ViewScoped
public class DescuentoExtraController extends AbstractController<DescuentoExtra> {

    @Inject
    private CuentaController descuentoExtraIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isDescuentoExtraBusListEmpty;

    public DescuentoExtraController() {
        // Inform the Abstract parent controller of the concrete DescuentoExtra Entity
        super(DescuentoExtra.class);
    }

    @Override
    public DescuentoExtra prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setDescuentoExtraIdCuenta(this.getUserCount());
        this.getSelected().setDescuentoExtraFecha(new Date());
        return this.getSelected();
    }

    
    
    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        descuentoExtraIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDescuentoExtraBusListEmpty();
    }

    public boolean getIsDescuentoExtraBusListEmpty() {
        return this.isDescuentoExtraBusListEmpty;
    }

    private void setIsDescuentoExtraBusListEmpty() {
        DescuentoExtra selected = this.getSelected();
        if (selected != null) {
            DescuentoExtraFacade ejbFacade = (DescuentoExtraFacade) this.getFacade();
            this.isDescuentoExtraBusListEmpty = ejbFacade.isDescuentoExtraBusListEmpty(selected);
        } else {
            this.isDescuentoExtraBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoExtraBus
     * entities that are retrieved from DescuentoExtra and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DescuentoExtraBus page
     */
    public String navigateDescuentoExtraBusList() {
        DescuentoExtra selected = this.getSelected();
        if (selected != null) {
            DescuentoExtraFacade ejbFacade = (DescuentoExtraFacade) this.getFacade();
            List<DescuentoExtraBus> selectedDescuentoExtraBusList = ejbFacade.findDescuentoExtraBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoExtraBus_items", selectedDescuentoExtraBusList);
        }
        return "/app/descuentoExtraBus/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoExtraIdCuenta(ActionEvent event) {
        DescuentoExtra selected = this.getSelected();
        if (selected != null && descuentoExtraIdCuentaController.getSelected() == null) {
            descuentoExtraIdCuentaController.setSelected(selected.getDescuentoExtraIdCuenta());
        }
    }

}
