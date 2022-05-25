package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Calle;
import com.areatecnica.sigf.entities.CalleServicio;
import com.areatecnica.sigf.facade.CalleFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "calleController")
@ViewScoped
public class CalleController extends AbstractController<Calle> {

    @Inject
    private ComunaController calleIdComunaController;

    // Flags to indicate if child collections are empty
    private boolean isCalleServicioListEmpty;

    public CalleController() {
        // Inform the Abstract parent controller of the concrete Calle Entity
        super(Calle.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        calleIdComunaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCalleServicioListEmpty();
    }

    public boolean getIsCalleServicioListEmpty() {
        return this.isCalleServicioListEmpty;
    }

    private void setIsCalleServicioListEmpty() {
        Calle selected = this.getSelected();
        if (selected != null) {
            CalleFacade ejbFacade = (CalleFacade) this.getFacade();
            this.isCalleServicioListEmpty = ejbFacade.isCalleServicioListEmpty(selected);
        } else {
            this.isCalleServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CalleServicio entities
     * that are retrieved from Calle and returns the navigation outcome.
     *
     * @return navigation outcome for CalleServicio page
     */
    public String navigateCalleServicioList() {
        Calle selected = this.getSelected();
        if (selected != null) {
            CalleFacade ejbFacade = (CalleFacade) this.getFacade();
            List<CalleServicio> selectedCalleServicioList = ejbFacade.findCalleServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CalleServicio_items", selectedCalleServicioList);
        }
        return "/app/calleServicio/index";
    }

    /**
     * Sets the "selected" attribute of the Comuna controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCalleIdComuna(ActionEvent event) {
        Calle selected = this.getSelected();
        if (selected != null && calleIdComunaController.getSelected() == null) {
            calleIdComunaController.setSelected(selected.getCalleIdComuna());
        }
    }

}
