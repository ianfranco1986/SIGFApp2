/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.AdministradorBus;
import com.areatecnica.sigf.entities.AdministradorFlota;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Cuenta;
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
