package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.facade.BoletoFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "boletoController")
@ViewScoped
public class BoletoController extends AbstractController<Boleto> {

    @Inject
    private CuentaController boletoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isInventarioInternoListEmpty;
    private boolean isDetalleCompraBoletoListEmpty;
    private boolean isRegistroBoletoListEmpty;
    private boolean isTarifaGrupoServicioListEmpty;

    public BoletoController() {
        // Inform the Abstract parent controller of the concrete Boleto Entity
        super(Boleto.class);
////////        this.setLimitedByCuenta(Boolean.TRUE);
////////        this.setNamedQuery("Boleto.findByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        boletoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsInventarioInternoListEmpty();
        this.setIsDetalleCompraBoletoListEmpty();
        this.setIsRegistroBoletoListEmpty();
        this.setIsTarifaGrupoServicioListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBoletoIdCuenta(ActionEvent event) {
        Boleto selected = this.getSelected();
        if (selected != null && boletoIdCuentaController.getSelected() == null) {
            boletoIdCuentaController.setSelected(selected.getBoletoIdCuenta());
        }
    }

    public boolean getIsInventarioInternoListEmpty() {
        return this.isInventarioInternoListEmpty;
    }

    private void setIsInventarioInternoListEmpty() {
        Boleto selected = this.getSelected();
        if (selected != null) {
            BoletoFacade ejbFacade = (BoletoFacade) this.getFacade();
            this.isInventarioInternoListEmpty = ejbFacade.isInventarioInternoListEmpty(selected);
        } else {
            this.isInventarioInternoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of InventarioInterno
     * entities that are retrieved from Boleto and returns the navigation
     * outcome.
     *
     * @return navigation outcome for InventarioInterno page
     */
    public String navigateInventarioInternoList() {
        Boleto selected = this.getSelected();
        if (selected != null) {
            BoletoFacade ejbFacade = (BoletoFacade) this.getFacade();
            List<InventarioInterno> selectedInventarioInternoList = ejbFacade.findInventarioInternoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InventarioInterno_items", selectedInventarioInternoList);
        }
        return "/app/inventarioInterno/index";
    }

    public boolean getIsDetalleCompraBoletoListEmpty() {
        return this.isDetalleCompraBoletoListEmpty;
    }

    private void setIsDetalleCompraBoletoListEmpty() {
        Boleto selected = this.getSelected();
        if (selected != null) {
            BoletoFacade ejbFacade = (BoletoFacade) this.getFacade();
            this.isDetalleCompraBoletoListEmpty = ejbFacade.isDetalleCompraBoletoListEmpty(selected);
        } else {
            this.isDetalleCompraBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleCompraBoleto
     * entities that are retrieved from Boleto and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleCompraBoleto page
     */
    public String navigateDetalleCompraBoletoList() {
        Boleto selected = this.getSelected();
        if (selected != null) {
            BoletoFacade ejbFacade = (BoletoFacade) this.getFacade();
            List<DetalleCompraBoleto> selectedDetalleCompraBoletoList = ejbFacade.findDetalleCompraBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleCompraBoleto_items", selectedDetalleCompraBoletoList);
        }
        return "/app/detalleCompraBoleto/index";
    }

    public boolean getIsRegistroBoletoListEmpty() {
        return this.isRegistroBoletoListEmpty;
    }

    private void setIsRegistroBoletoListEmpty() {
        Boleto selected = this.getSelected();
        if (selected != null) {
            BoletoFacade ejbFacade = (BoletoFacade) this.getFacade();
            this.isRegistroBoletoListEmpty = ejbFacade.isRegistroBoletoListEmpty(selected);
        } else {
            this.isRegistroBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RegistroBoleto entities
     * that are retrieved from Boleto and returns the navigation outcome.
     *
     * @return navigation outcome for RegistroBoleto page
     */
    public String navigateRegistroBoletoList() {
        Boleto selected = this.getSelected();
        if (selected != null) {
            BoletoFacade ejbFacade = (BoletoFacade) this.getFacade();
            List<RegistroBoleto> selectedRegistroBoletoList = ejbFacade.findRegistroBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroBoleto_items", selectedRegistroBoletoList);
        }
        return "/app/registroBoleto/index";
    }

    public boolean getIsTarifaGrupoServicioListEmpty() {
        return this.isTarifaGrupoServicioListEmpty;
    }

    private void setIsTarifaGrupoServicioListEmpty() {
        Boleto selected = this.getSelected();
        if (selected != null) {
            BoletoFacade ejbFacade = (BoletoFacade) this.getFacade();
            this.isTarifaGrupoServicioListEmpty = ejbFacade.isTarifaGrupoServicioListEmpty(selected);
        } else {
            this.isTarifaGrupoServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TarifaGrupoServicio
     * entities that are retrieved from Boleto and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TarifaGrupoServicio page
     */
    public String navigateTarifaGrupoServicioList() {
        Boleto selected = this.getSelected();
        if (selected != null) {
            BoletoFacade ejbFacade = (BoletoFacade) this.getFacade();
            List<TarifaGrupoServicio> selectedTarifaGrupoServicioList = ejbFacade.findTarifaGrupoServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TarifaGrupoServicio_items", selectedTarifaGrupoServicioList);
        }
        return "/app/tarifaGrupoServicio/index";
    }

}
