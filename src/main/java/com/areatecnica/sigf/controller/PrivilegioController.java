package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Privilegio;
import java.util.List;
import com.areatecnica.sigf.facade.PrivilegioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "privilegioController")
@ViewScoped
public class PrivilegioController extends AbstractController<Privilegio> {

    @Inject
    private MenuController2 privilegioIdMenuController;

    // Flags to indicate if child collections are empty
    private boolean isLogListEmpty;

    public PrivilegioController() {
        // Inform the Abstract parent controller of the concrete Privilegio Entity
        super(Privilegio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        privilegioIdMenuController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsLogListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Menu controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePrivilegioIdMenu(ActionEvent event) {
        Privilegio selected = this.getSelected();
        if (selected != null && privilegioIdMenuController.getSelected() == null) {
            privilegioIdMenuController.setSelected(selected.getPrivilegioIdMenu());
        }
    }

    public boolean getIsLogListEmpty() {
        return this.isLogListEmpty;
    }

    private void setIsLogListEmpty() {
        Privilegio selected = this.getSelected();
        if (selected != null) {
            PrivilegioFacade ejbFacade = (PrivilegioFacade) this.getFacade();
            this.isLogListEmpty = ejbFacade.isLogListEmpty(selected);
        } else {
            this.isLogListEmpty = true;
        }
    }

    
}
