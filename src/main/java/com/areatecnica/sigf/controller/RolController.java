package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Rol;
import com.areatecnica.sigf.entities.RolMenu;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.facade.RolFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "rolController")
@ViewScoped
public class RolController extends AbstractController<Rol> {

    // Flags to indicate if child collections are empty
    private boolean isRolMenuListEmpty;
    private boolean isUsuarioListEmpty;

    public RolController() {
        // Inform the Abstract parent controller of the concrete Rol Entity
        super(Rol.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRolMenuListEmpty();
        this.setIsUsuarioListEmpty();
    }

    public boolean getIsRolMenuListEmpty() {
        return this.isRolMenuListEmpty;
    }

    private void setIsRolMenuListEmpty() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            this.isRolMenuListEmpty = ejbFacade.isRolMenuListEmpty(selected);
        } else {
            this.isRolMenuListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RolMenu entities that are
     * retrieved from Rol and returns the navigation outcome.
     *
     * @return navigation outcome for RolMenu page
     */
    public String navigateRolMenuList() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            List<RolMenu> selectedRolMenuList = ejbFacade.findRolMenuList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RolMenu_items", selectedRolMenuList);
        }
        return "/app/rolMenu/index";
    }

    public boolean getIsUsuarioListEmpty() {
        return this.isUsuarioListEmpty;
    }

    private void setIsUsuarioListEmpty() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            this.isUsuarioListEmpty = ejbFacade.isUsuarioListEmpty(selected);
        } else {
            this.isUsuarioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Rol and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            List<Usuario> selectedUsuarioList = ejbFacade.findUsuarioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioList);
        }
        return "/app/usuario/index";
    }

}
