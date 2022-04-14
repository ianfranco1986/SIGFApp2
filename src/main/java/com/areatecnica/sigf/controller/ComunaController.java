package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Comuna;
import com.areatecnica.sigf.entities.Calle;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.Terminal;
import java.util.List;
import com.areatecnica.sigf.facade.ComunaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "comunaController")
@ViewScoped
public class ComunaController extends AbstractController<Comuna> {

    @Inject
    private CiudadController comunaIdCiudadController;

    // Flags to indicate if child collections are empty
    private boolean isCalleListEmpty;
    private boolean isTrabajadorListEmpty;
    private boolean isTerminalListEmpty;

    public ComunaController() {
        // Inform the Abstract parent controller of the concrete Comuna Entity
        super(Comuna.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        comunaIdCiudadController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCalleListEmpty();
        this.setIsTrabajadorListEmpty();
        this.setIsTerminalListEmpty();
    }

    public boolean getIsCalleListEmpty() {
        return this.isCalleListEmpty;
    }

    private void setIsCalleListEmpty() {
        Comuna selected = this.getSelected();
        if (selected != null) {
            ComunaFacade ejbFacade = (ComunaFacade) this.getFacade();
            this.isCalleListEmpty = ejbFacade.isCalleListEmpty(selected);
        } else {
            this.isCalleListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Calle entities that are
     * retrieved from Comuna and returns the navigation outcome.
     *
     * @return navigation outcome for Calle page
     */
    public String navigateCalleList() {
        Comuna selected = this.getSelected();
        if (selected != null) {
            ComunaFacade ejbFacade = (ComunaFacade) this.getFacade();
            List<Calle> selectedCalleList = ejbFacade.findCalleList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Calle_items", selectedCalleList);
        }
        return "/app/calle/index";
    }

    public boolean getIsTrabajadorListEmpty() {
        return this.isTrabajadorListEmpty;
    }

    private void setIsTrabajadorListEmpty() {
        Comuna selected = this.getSelected();
        if (selected != null) {
            ComunaFacade ejbFacade = (ComunaFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from Comuna and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        Comuna selected = this.getSelected();
        if (selected != null) {
            ComunaFacade ejbFacade = (ComunaFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

    public boolean getIsTerminalListEmpty() {
        return this.isTerminalListEmpty;
    }

    private void setIsTerminalListEmpty() {
        Comuna selected = this.getSelected();
        if (selected != null) {
            ComunaFacade ejbFacade = (ComunaFacade) this.getFacade();
            this.isTerminalListEmpty = ejbFacade.isTerminalListEmpty(selected);
        } else {
            this.isTerminalListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Terminal entities that
     * are retrieved from Comuna and returns the navigation outcome.
     *
     * @return navigation outcome for Terminal page
     */
    public String navigateTerminalList() {
        Comuna selected = this.getSelected();
        if (selected != null) {
            ComunaFacade ejbFacade = (ComunaFacade) this.getFacade();
            List<Terminal> selectedTerminalList = ejbFacade.findTerminalList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Terminal_items", selectedTerminalList);
        }
        return "/app/terminal/index";
    }

    /**
     * Sets the "selected" attribute of the Ciudad controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareComunaIdCiudad(ActionEvent event) {
        Comuna selected = this.getSelected();
        if (selected != null && comunaIdCiudadController.getSelected() == null) {
            comunaIdCiudadController.setSelected(selected.getComunaIdCiudad());
        }
    }

}
