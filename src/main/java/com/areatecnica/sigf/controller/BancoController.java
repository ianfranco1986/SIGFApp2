package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Banco;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.DetalleResumenCheque;
import java.util.List;
import com.areatecnica.sigf.facade.BancoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "bancoController")
@ViewScoped
public class BancoController extends AbstractController<Banco> {

    @Inject
    private CuentaController bancoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isCuentaBancariaListEmpty;
    private boolean isDetalleResumenChequeListEmpty;

    public BancoController() {
        // Inform the Abstract parent controller of the concrete Banco Entity
        super(Banco.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        bancoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCuentaBancariaListEmpty();
        this.setIsDetalleResumenChequeListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBancoIdCuenta(ActionEvent event) {
        Banco selected = this.getSelected();
        if (selected != null && bancoIdCuentaController.getSelected() == null) {
            bancoIdCuentaController.setSelected(selected.getBancoIdCuenta());
        }
    }

    public boolean getIsCuentaBancariaListEmpty() {
        return this.isCuentaBancariaListEmpty;
    }

    private void setIsCuentaBancariaListEmpty() {
        Banco selected = this.getSelected();
        if (selected != null) {
            BancoFacade ejbFacade = (BancoFacade) this.getFacade();
            this.isCuentaBancariaListEmpty = ejbFacade.isCuentaBancariaListEmpty(selected);
        } else {
            this.isCuentaBancariaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CuentaBancaria entities
     * that are retrieved from Banco and returns the navigation outcome.
     *
     * @return navigation outcome for CuentaBancaria page
     */
    public String navigateCuentaBancariaList() {
        Banco selected = this.getSelected();
        if (selected != null) {
            BancoFacade ejbFacade = (BancoFacade) this.getFacade();
            List<CuentaBancaria> selectedCuentaBancariaList = ejbFacade.findCuentaBancariaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancaria_items", selectedCuentaBancariaList);
        }
        return "/app/cuentaBancaria/index";
    }

    public boolean getIsDetalleResumenChequeListEmpty() {
        return this.isDetalleResumenChequeListEmpty;
    }

    private void setIsDetalleResumenChequeListEmpty() {
        Banco selected = this.getSelected();
        if (selected != null) {
            BancoFacade ejbFacade = (BancoFacade) this.getFacade();
            this.isDetalleResumenChequeListEmpty = ejbFacade.isDetalleResumenChequeListEmpty(selected);
        } else {
            this.isDetalleResumenChequeListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleResumenCheque
     * entities that are retrieved from Banco and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleResumenCheque page
     */
    public String navigateDetalleResumenChequeList() {
        Banco selected = this.getSelected();
        if (selected != null) {
            BancoFacade ejbFacade = (BancoFacade) this.getFacade();
            List<DetalleResumenCheque> selectedDetalleResumenChequeList = ejbFacade.findDetalleResumenChequeList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleResumenCheque_items", selectedDetalleResumenChequeList);
        }
        return "/app/detalleResumenCheque/index";
    }

    @Override
    public Banco prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setBancoIdCuenta(this.getUserCount());
        return this.getSelected();
    }
    
    

}
