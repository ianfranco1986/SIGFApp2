package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Surtidor;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.entities.NumeralSurtidor;
import java.util.List;
import com.areatecnica.sigf.facade.SurtidorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "surtidorController")
@ViewScoped
public class SurtidorController extends AbstractController<Surtidor> {

    @Inject
    private TerminalController surtidorIdTerminalController;

    // Flags to indicate if child collections are empty
    private boolean isVentaCombustibleListEmpty;
    private boolean isNumeralSurtidorListEmpty;

    public SurtidorController() {
        // Inform the Abstract parent controller of the concrete Surtidor Entity
        super(Surtidor.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        surtidorIdTerminalController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsVentaCombustibleListEmpty();
        this.setIsNumeralSurtidorListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSurtidorIdTerminal(ActionEvent event) {
        Surtidor selected = this.getSelected();
        if (selected != null && surtidorIdTerminalController.getSelected() == null) {
            surtidorIdTerminalController.setSelected(selected.getSurtidorIdTerminal());
        }
    }

    public boolean getIsVentaCombustibleListEmpty() {
        return this.isVentaCombustibleListEmpty;
    }

    private void setIsVentaCombustibleListEmpty() {
        Surtidor selected = this.getSelected();
        if (selected != null) {
            SurtidorFacade ejbFacade = (SurtidorFacade) this.getFacade();
            this.isVentaCombustibleListEmpty = ejbFacade.isVentaCombustibleListEmpty(selected);
        } else {
            this.isVentaCombustibleListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of VentaCombustible entities
     * that are retrieved from Surtidor and returns the navigation outcome.
     *
     * @return navigation outcome for VentaCombustible page
     */
    public String navigateVentaCombustibleList() {
        Surtidor selected = this.getSelected();
        if (selected != null) {
            SurtidorFacade ejbFacade = (SurtidorFacade) this.getFacade();
            List<VentaCombustible> selectedVentaCombustibleList = ejbFacade.findVentaCombustibleList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VentaCombustible_items", selectedVentaCombustibleList);
        }
        return "/app/ventaCombustible/index";
    }

    public boolean getIsNumeralSurtidorListEmpty() {
        return this.isNumeralSurtidorListEmpty;
    }

    private void setIsNumeralSurtidorListEmpty() {
        Surtidor selected = this.getSelected();
        if (selected != null) {
            SurtidorFacade ejbFacade = (SurtidorFacade) this.getFacade();
            this.isNumeralSurtidorListEmpty = ejbFacade.isNumeralSurtidorListEmpty(selected);
        } else {
            this.isNumeralSurtidorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of NumeralSurtidor entities
     * that are retrieved from Surtidor and returns the navigation outcome.
     *
     * @return navigation outcome for NumeralSurtidor page
     */
    public String navigateNumeralSurtidorList() {
        Surtidor selected = this.getSelected();
        if (selected != null) {
            SurtidorFacade ejbFacade = (SurtidorFacade) this.getFacade();
            List<NumeralSurtidor> selectedNumeralSurtidorList = ejbFacade.findNumeralSurtidorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("NumeralSurtidor_items", selectedNumeralSurtidorList);
        }
        return "/app/numeralSurtidor/index";
    }

}
