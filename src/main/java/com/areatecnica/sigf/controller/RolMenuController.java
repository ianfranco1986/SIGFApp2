package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RolMenu;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "rolMenuController")
@ViewScoped
public class RolMenuController extends AbstractController<RolMenu> {

    @Inject
    private MenuController2 rolMenuIdMenuController;
    @Inject
    private RolController rolMenuIdRolController;

    public RolMenuController() {
        // Inform the Abstract parent controller of the concrete RolMenu Entity
        super(RolMenu.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        rolMenuIdMenuController.setSelected(null);
        rolMenuIdRolController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Menu controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRolMenuIdMenu(ActionEvent event) {
        RolMenu selected = this.getSelected();
        if (selected != null && rolMenuIdMenuController.getSelected() == null) {
            rolMenuIdMenuController.setSelected(selected.getRolMenuIdMenu());
        }
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRolMenuIdRol(ActionEvent event) {
        RolMenu selected = this.getSelected();
        if (selected != null && rolMenuIdRolController.getSelected() == null) {
            rolMenuIdRolController.setSelected(selected.getRolMenuIdRol());
        }
    }

}
