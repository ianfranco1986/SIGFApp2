/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Menu;
import com.areatecnica.sigf.entities.RolMenu;
import com.areatecnica.sigf.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "helpVideoController")
@ViewScoped
public class HelpVideoController implements Serializable {

    private Usuario currentUser;
    private List<RolMenu> rolMenuItems;
    private List<Menu> items;

    /**
     * Creates a new instance of HelpVideoController
     */
    public HelpVideoController() {
    }

    @PostConstruct
    private void init() {
        this.currentUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
        loadMenu();
    }

    private void loadMenu() {
        this.rolMenuItems = this.currentUser.getUsuarioIdRol().getRolMenuList();
        this.items = new ArrayList<>();
        for (RolMenu r : this.rolMenuItems) {
            this.items.add(r.getRolMenuIdMenu());
        }
    }

    public List<Menu> getItems() {
        return items;
    }

    public void setItems(List<Menu> items) {
        this.items = items;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

}
