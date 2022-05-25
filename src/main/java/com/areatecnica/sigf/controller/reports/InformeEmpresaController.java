/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.entities.*;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author ianfr
 */
@Named(value = "informeEmpresaController")
@ViewScoped
public class InformeEmpresaController implements Serializable {

    private List<Empresa> itemsEmpresas;
    private Empresa selected;
    private final Usuario currentUser;
    private final List<Bus> buses;

    /**
     * Creates a new instance of InformeEmpresaController
     */
    public InformeEmpresaController() {
        this.buses = new ArrayList<>();
        this.itemsEmpresas = new ArrayList<>();
        Map empresas = new HashMap();
        this.currentUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");

        Administrador admin = this.currentUser.getAdministradorList().get(0);

        if (!admin.getAdministradorBusList().isEmpty()) {
            for (AdministradorBus b : admin.getAdministradorBusList()) {
                this.buses.add(b.getAdministradorBusIdBus());
            }
        } else if (!admin.getAdministradorFlotaList().isEmpty()) {
            for (AdministradorFlota f : admin.getAdministradorFlotaList()) {
                this.buses.addAll(f.getAdministradorFlotaIdFlota().getBusList());
            }
        }

        Calendar calendar = GregorianCalendar.getInstance();

        for (Bus b : this.buses) {            
            empresas.put(b.getBusIdEmpresa(), b.getBusIdEmpresa());
        }
        System.err.println("TAMAÑO DE BUSES"+this.buses.size());
        System.err.println("Tamaño de empresas"+this.itemsEmpresas.size());
        
        this.itemsEmpresas.addAll(empresas.values());
    }

    public List<Empresa> getItemsEmpresas() {
        return itemsEmpresas;
    }

    public void setItemsEmpresas(List<Empresa> itemsEmpresas) {
        this.itemsEmpresas = itemsEmpresas;
    }

    public void setSelected(Empresa selected) {
        this.selected = selected;
    }

    public Empresa getSelected() {
        return selected;
    }

}
