package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.ICuentaMayorDaoImpl;
import com.areatecnica.sigf.dao.impl.PlanCuentaSubTipoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoPlanCuentaDaoImpl;
import com.areatecnica.sigf.entities.Compra;
import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.entities.PlanCuentaSubTipo;
import com.areatecnica.sigf.entities.TipoPlanCuenta;
import com.areatecnica.sigf.facade.CuentaMayorFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "cuentaMayorController")
@ViewScoped
public class CuentaMayorController extends AbstractController<CuentaMayor> {

    @Inject
    private PlanCuentaSubTipoController cuentaMayorSubTipoIdController;

    private List<PlanCuentaSubTipo> subTipoItems;
    private List<TipoPlanCuenta> tipoPlanCuentaItems;
    private List<CuentaMayor> items;
    private CuentaMayor selected;
    private TipoPlanCuenta tipoPlanCuenta;
    private PlanCuentaSubTipo subTipo;

    // Flags to indicate if child collections are empty
    private boolean isCompraListEmpty;

    public CuentaMayorController() {
        // Inform the Abstract parent controller of the concrete CuentaMayor Entity
        super(CuentaMayor.class);
        this.items = new ICuentaMayorDaoImpl().findALL();

    }

    public List<CuentaMayor> getItems() {
        return items;
    }

    public void setItems(List<CuentaMayor> items) {
        this.items = items;
    }

    public CuentaMayor getSelected() {
        return selected;
    }

    public void setSelected(CuentaMayor selected) {
        this.selected = selected;
    }

    public PlanCuentaSubTipo getSubTipo() {
        return subTipo;
    }

    public List<PlanCuentaSubTipo> getSubTipoItems() {
        return subTipoItems;
    }

    public void setTipoPlanCuentaItems(List<TipoPlanCuenta> tipoPlanCuentaItems) {
        this.tipoPlanCuentaItems = tipoPlanCuentaItems;
    }

    public void setTipoPlanCuenta(TipoPlanCuenta tipoPlanCuenta) {
        this.tipoPlanCuenta = tipoPlanCuenta;
    }

    public List<TipoPlanCuenta> getTipoPlanCuentaItems() {
        return tipoPlanCuentaItems;
    }

    public TipoPlanCuenta getTipoPlanCuenta() {
        return tipoPlanCuenta;
    }

    public void setSubTipo(PlanCuentaSubTipo subTipo) {
        this.subTipo = subTipo;
    }

    public void setSubTipoItems(List<PlanCuentaSubTipo> subTipoItems) {
        this.subTipoItems = subTipoItems;
    }

    @Override
    public CuentaMayor prepareCreate(ActionEvent event) {
        return super.prepareCreate(event); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public PlanCuentaSubTipo prepareCreateSubTipo(ActionEvent event) {
        this.tipoPlanCuentaItems = new TipoPlanCuentaDaoImpl().find();
        this.tipoPlanCuenta = new TipoPlanCuenta();
//        this.subTipoItems = new PlanCuentaSubTipoDaoImpl().find();
        this.subTipo = new PlanCuentaSubTipo();
        return subTipo;
    }

    public void handleCuentaChange() {
        if (this.tipoPlanCuenta != null) {
            this.subTipoItems = new PlanCuentaSubTipoDaoImpl().findByTipoPlan(tipoPlanCuenta);
            this.subTipo.setPlanCuentaSubTipoCodigoTipo(this.tipoPlanCuenta.getTipoPlanCuentaId());

            if (!this.subTipoItems.isEmpty()) {
                this.subTipo.setPlanCuentaSubTipoCodigo(this.subTipoItems.get(this.subTipoItems.size() - 1).getPlanCuentaSubTipoCodigo());
            } else {
                this.subTipo.setPlanCuentaSubTipoCodigo("01");
            }

        }
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cuentaMayorSubTipoIdController.setSelected(null);
    }

    public boolean getIsCompraListEmpty() {
        return this.isCompraListEmpty;
    }

    /**
     * Sets the "items" attribute with a collection of Compra entities that are
     * retrieved from CuentaMayor and returns the navigation outcome.
     *
     * @return navigation outcome for Compra page
     */
    public String navigateCompraList() {
        CuentaMayor selected = this.getSelected();
        if (selected != null) {
            CuentaMayorFacade ejbFacade = (CuentaMayorFacade) this.getFacade();
            List<Compra> selectedCompraList = ejbFacade.findCompraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Compra_items", selectedCompraList);
        }
        return "/app/compra/index";
    }

    /**
     * Sets the "selected" attribute of the PlanCuentaSubTipo controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaMayorSubTipoId(ActionEvent event) {
        CuentaMayor selected = this.getSelected();
        if (selected != null && cuentaMayorSubTipoIdController.getSelected() == null) {
            cuentaMayorSubTipoIdController.setSelected(selected.getCuentaMayorSubTipoId());
        }
    }

}
