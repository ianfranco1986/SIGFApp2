/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.*;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "informacionBusesController")
@ViewScoped
public class InformacionBusesController implements Serializable {

    private List<Bus> items;
    private Bus selected;
    private Usuario currentUser;
    private Cuenta userCount;

    /**
     * Creates a new instance of InformacionBusesController
     */
    public InformacionBusesController() {
        super();
        init();
    }

    private void init() {
        this.items = new ArrayList<>();
        
        this.currentUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
        this.userCount = this.currentUser.getUsuarioIdCuenta();
        
        Administrador admin = this.currentUser.getAdministradorList().get(0);
//verificar que no tenga las dos listas
        if (!admin.getAdministradorBusList().isEmpty()) {
            for (AdministradorBus b : admin.getAdministradorBusList()) {
                this.items.add(b.getAdministradorBusIdBus());
            }
        } else if (!admin.getAdministradorFlotaList().isEmpty()) {
            for (AdministradorFlota f : admin.getAdministradorFlotaList()) {
                this.items.addAll(f.getAdministradorFlotaIdFlota().getBusList());
            }
        }

    }

    public void resetParents(){
        
    }
    
    public List<Bus> getItems() {
        return items;
    }

    public void setItems(List<Bus> items) {
        this.items = items;
    }

    public Bus getSelected() {
        return selected;
    }

    public void setSelected(Bus selected) {
        this.selected = selected;
    }

}
