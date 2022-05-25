package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Menu;
import com.areatecnica.sigf.entities.Privilegio;
import com.areatecnica.sigf.entities.RolMenu;
import com.areatecnica.sigf.facade.MenuFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "menuController2")
@ViewScoped
public class MenuController2 extends AbstractController<Menu> {

    // Flags to indicate if child collections are empty
    private boolean isRolMenuListEmpty;
    private boolean isPrivilegioListEmpty;

    public MenuController2() {
        // Inform the Abstract parent controller of the concrete Menu Entity
        super(Menu.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRolMenuListEmpty();
        this.setIsPrivilegioListEmpty();
    }

    public boolean getIsRolMenuListEmpty() {
        return this.isRolMenuListEmpty;
    }

    private void setIsRolMenuListEmpty() {
        Menu selected = this.getSelected();
        if (selected != null) {
            MenuFacade ejbFacade = (MenuFacade) this.getFacade();
            this.isRolMenuListEmpty = ejbFacade.isRolMenuListEmpty(selected);
        } else {
            this.isRolMenuListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RolMenu entities that are
     * retrieved from Menu and returns the navigation outcome.
     *
     * @return navigation outcome for RolMenu page
     */
    public String navigateRolMenuList() {
        Menu selected = this.getSelected();
        if (selected != null) {
            MenuFacade ejbFacade = (MenuFacade) this.getFacade();
            List<RolMenu> selectedRolMenuList = ejbFacade.findRolMenuList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RolMenu_items", selectedRolMenuList);
        }
        return "/app/rolMenu/index";
    }

    public boolean getIsPrivilegioListEmpty() {
        return this.isPrivilegioListEmpty;
    }

    private void setIsPrivilegioListEmpty() {
        Menu selected = this.getSelected();
        if (selected != null) {
            MenuFacade ejbFacade = (MenuFacade) this.getFacade();
            this.isPrivilegioListEmpty = ejbFacade.isPrivilegioListEmpty(selected);
        } else {
            this.isPrivilegioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Privilegio entities that
     * are retrieved from Menu and returns the navigation outcome.
     *
     * @return navigation outcome for Privilegio page
     */
    public String navigatePrivilegioList() {
        Menu selected = this.getSelected();
        if (selected != null) {
            MenuFacade ejbFacade = (MenuFacade) this.getFacade();
            List<Privilegio> selectedPrivilegioList = ejbFacade.findPrivilegioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Privilegio_items", selectedPrivilegioList);
        }
        return "/app/privilegio/index";
    }

}
