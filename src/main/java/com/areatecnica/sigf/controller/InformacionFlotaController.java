/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.AdministradorFlota;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "informacionFlotaController")
@ViewScoped
public class InformacionFlotaController implements Serializable {

    private List<Flota> items;
    private Flota selected;
    private Usuario currentUser;

    /**
     * Creates a new instance of InformacionBusesController
     */
    public InformacionFlotaController() {
        super();
        init();
    }

    private void init() {
        this.items = new ArrayList<>();

        this.currentUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");

        Administrador admin = this.currentUser.getAdministradorList().get(0);
//verificar que no tenga las dos listas
        if (!admin.getAdministradorFlotaList().isEmpty()) {
            for (AdministradorFlota f : admin.getAdministradorFlotaList()) {
                this.items.add(f.getAdministradorFlotaIdFlota());
            }
        }

    }

    public List<Flota> getItems() {
        return items;
    }

    public void setItems(List<Flota> items) {
        this.items = items;
    }

    public Flota getSelected() {
        return selected;
    }

    public void setSelected(Flota selected) {
        this.selected = selected;
    }

}
