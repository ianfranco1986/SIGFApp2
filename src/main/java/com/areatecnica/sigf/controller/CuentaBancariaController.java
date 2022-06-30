package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.Empresa;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "cuentaBancariaController")
@ViewScoped
public class CuentaBancariaController extends AbstractController<CuentaBancaria> {

    

    // Flags to indicate if child collections are empty
    private boolean isCuentaBancoProcesoListEmpty;
    private boolean isCuentaBancoEmpresaListEmpty;
    private boolean isCuentaBancoTrabajadorListEmpty;

    private List<CuentaBancaria> items;


    public CuentaBancariaController() {
        // Inform the Abstract parent controller of the concrete CuentaBancaria Entity
        super(CuentaBancaria.class);

    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        //this.setIsCuentaBancoProcesoListEmpty();
        //this.setIsCuentaBancoEmpresaListEmpty();
        //this.setIsCuentaBancoTrabajadorListEmpty();
    }

    public boolean getIsCuentaBancoProcesoListEmpty() {
        return this.isCuentaBancoProcesoListEmpty;
    }

//    private void setIsCuentaBancoProcesoListEmpty() {
//        CuentaBancaria selected = this.getSelected();
//        if (selected != null) {
//            CuentaBancariaFacade ejbFacade = (CuentaBancariaFacade) this.getFacade();
//            this.isCuentaBancoProcesoListEmpty = ejbFacade.isCuentaBancoProcesoListEmpty(selected);
//        } else {
//            this.isCuentaBancoProcesoListEmpty = true;
//        }
//    }
    /**
     * Sets the "items" attribute with a collection of CuentaBancoProceso
     * entities that are retrieved from CuentaBancaria and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CuentaBancoProceso page
     */
//    public String navigateCuentaBancoProcesoList() {
//        CuentaBancaria selected = this.getSelected();
//        if (selected != null) {
//            CuentaBancariaFacade ejbFacade = (CuentaBancariaFacade) this.getFacade();
//            List<CuentaBancoProceso> selectedCuentaBancoProcesoList = ejbFacade.findCuentaBancoProcesoList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancoProceso_items", selectedCuentaBancoProcesoList);
//        }
//        return "/app/cuentaBancoProceso/index";
//    }
    public boolean getIsCuentaBancoEmpresaListEmpty() {
        return this.isCuentaBancoEmpresaListEmpty;
    }

//    private void setIsCuentaBancoEmpresaListEmpty() {
//        CuentaBancaria selected = this.getSelected();
//        if (selected != null) {
//            CuentaBancariaFacade ejbFacade = (CuentaBancariaFacade) this.getFacade();
//            this.isCuentaBancoEmpresaListEmpty = ejbFacade.isCuentaBancoEmpresaListEmpty(selected);
//        } else {
//            this.isCuentaBancoEmpresaListEmpty = true;
//        }
//    }
    /**
     * Sets the "items" attribute with a collection of CuentaBancoEmpresa
     * entities that are retrieved from CuentaBancaria and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CuentaBancoEmpresa page
     */
//    public String navigateCuentaBancoEmpresaList() {
//        CuentaBancaria selected = this.getSelected();
//        if (selected != null) {
//            CuentaBancariaFacade ejbFacade = (CuentaBancariaFacade) this.getFacade();
//            List<CuentaBancoEmpresa> selectedCuentaBancoEmpresaList = ejbFacade.findCuentaBancoEmpresaList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancoEmpresa_items", selectedCuentaBancoEmpresaList);
//        }
//        return "/app/cuentaBancoEmpresa/index";
//    }
    /**
     * Sets the "selected" attribute of the Banco controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaBancariaIdBanco(ActionEvent event) {
        
    }

    /**
     * Sets the "selected" attribute of the TipoCuentaBanco controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaBancariaIdTipoCuenta(ActionEvent event) {
        
    }

    public boolean getIsCuentaBancoTrabajadorListEmpty() {
        return this.isCuentaBancoTrabajadorListEmpty;
    }

    public List<CuentaBancaria> getItems() {

//        List<Empresa> empresas = this.getUserCount().getEmpresaList();
//
//        if (!empresas.isEmpty()) {
//            List<CuentaBancaria> cuentas = new ArrayList<>();
////  corregir
////            for (Empresa e : empresas) {
////                cuentas.addAll(e.getCuentaBancariaList());
////            }
//
//            this.setItems(cuentas);
//        }else{
//            this.setItems(new ArrayList());
//        }

        return this.items;
    }

    public void setItems(List<CuentaBancaria> list){
        this.items = list;
    }

//    private void setIsCuentaBancoTrabajadorListEmpty() {
//        CuentaBancaria selected = this.getSelected();
//        if (selected != null) {
//            CuentaBancariaFacade ejbFacade = (CuentaBancariaFacade) this.getFacade();
//            this.isCuentaBancoTrabajadorListEmpty = ejbFacade.isCuentaBancoTrabajadorListEmpty(selected);
//        } else {
//            this.isCuentaBancoTrabajadorListEmpty = true;
//        }
//    }
    /**
     * Sets the "items" attribute with a collection of CuentaBancoTrabajador
     * entities that are retrieved from CuentaBancaria and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CuentaBancoTrabajador page
     */
//    public String navigateCuentaBancoTrabajadorList() {
//        CuentaBancaria selected = this.getSelected();
//        if (selected != null) {
//            CuentaBancariaFacade ejbFacade = (CuentaBancariaFacade) this.getFacade();
//            List<CuentaBancoTrabajador> selectedCuentaBancoTrabajadorList = ejbFacade.findCuentaBancoTrabajadorList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancoTrabajador_items", selectedCuentaBancoTrabajadorList);
//        }
//        return "/app/cuentaBancoTrabajador/index";
//    }
}
