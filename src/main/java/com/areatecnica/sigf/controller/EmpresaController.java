package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.IEmpresaDao;
import com.areatecnica.sigf.dao.impl.IEmpresaDaoImpl;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.RepresentanteEmpresa;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.entities.ReconocimientoDeuda;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RelacionLaboral;
import java.util.List;
import com.areatecnica.sigf.facade.EmpresaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "empresaController")
@ViewScoped
public class EmpresaController extends AbstractController<Empresa> {

    @Inject
    private CajaCompensacionController empresaIdCajaCompensacionController;
    @Inject
    private CuentaController empresaIdCuentaController;
    @Inject
    private MutualController empresaIdMutualController;

    private List<Empresa> items;

    private IEmpresaDao dao;

    // Flags to indicate if child collections are empty
    private boolean isLiquidacionEmpresaListEmpty;
    private boolean isRepresentanteEmpresaListEmpty;
    private boolean isCuentaBancoEmpresaListEmpty;
    private boolean isLiquidacionSueldoListEmpty;
    private boolean isReconocimientoDeudaListEmpty;
    private boolean isBusListEmpty;
    private boolean isRelacionLaboralListEmpty;

    public EmpresaController() {
        // Inform the Abstract parent controller of the concrete Empresa Entity
        super(Empresa.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Empresa.findAllByCuenta");
        this.dao = new IEmpresaDaoImpl();
        this.items = this.dao.findByCuenta(this.getUserCount());
    }

    public void setItems(List<Empresa> items) {
        this.items = items;
    }

    @Override
    public List<Empresa> getItems() {
        return items;
    }

    @Override
    public Empresa prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setEmpresaIdCuenta(this.getUserCount());
        return getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empresaIdCajaCompensacionController.setSelected(null);
        empresaIdCuentaController.setSelected(null);
        empresaIdMutualController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsLiquidacionEmpresaListEmpty();
        this.setIsRepresentanteEmpresaListEmpty();
        //this.setIsCuentaBancoEmpresaListEmpty();
        this.setIsLiquidacionSueldoListEmpty();
        this.setIsReconocimientoDeudaListEmpty();
        this.setIsBusListEmpty();
        this.setIsRelacionLaboralListEmpty();
    }

    public boolean getIsLiquidacionEmpresaListEmpty() {
        return this.isLiquidacionEmpresaListEmpty;
    }

    private void setIsLiquidacionEmpresaListEmpty() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            this.isLiquidacionEmpresaListEmpty = ejbFacade.isLiquidacionEmpresaListEmpty(selected);
        } else {
            this.isLiquidacionEmpresaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionEmpresa
     * entities that are retrieved from Empresa and returns the navigation
     * outcome.
     *
     * @return navigation outcome for LiquidacionEmpresa page
     */
    public String navigateLiquidacionEmpresaList() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            List<LiquidacionEmpresa> selectedLiquidacionEmpresaList = ejbFacade.findLiquidacionEmpresaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionEmpresa_items", selectedLiquidacionEmpresaList);
        }
        return "/app/liquidacionEmpresa/index";
    }

    public boolean getIsRepresentanteEmpresaListEmpty() {
        return this.isRepresentanteEmpresaListEmpty;
    }

    private void setIsRepresentanteEmpresaListEmpty() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            this.isRepresentanteEmpresaListEmpty = ejbFacade.isRepresentanteEmpresaListEmpty(selected);
        } else {
            this.isRepresentanteEmpresaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RepresentanteEmpresa
     * entities that are retrieved from Empresa and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RepresentanteEmpresa page
     */
    public String navigateRepresentanteEmpresaList() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            List<RepresentanteEmpresa> selectedRepresentanteEmpresaList = ejbFacade.findRepresentanteEmpresaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RepresentanteEmpresa_items", selectedRepresentanteEmpresaList);
        }
        return "/app/representanteEmpresa/index";
    }

    public boolean getIsCuentaBancoEmpresaListEmpty() {
        return this.isCuentaBancoEmpresaListEmpty;
    }

//    private void setIsCuentaBancoEmpresaListEmpty() {
//        Empresa selected = this.getSelected();
//        if (selected != null) {
//            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
//            this.isCuentaBancoEmpresaListEmpty = ejbFacade.isCuentaBancoEmpresaListEmpty(selected);
//        } else {
//            this.isCuentaBancoEmpresaListEmpty = true;
//        }
//    }
    /**
     * Sets the "items" attribute with a collection of CuentaBancoEmpresa
     * entities that are retrieved from Empresa and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CuentaBancoEmpresa page
     */
//    public String navigateCuentaBancoEmpresaList() {
//        Empresa selected = this.getSelected();
//        if (selected != null) {
//            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
//            List<CuentaBancoEmpresa> selectedCuentaBancoEmpresaList = ejbFacade.findCuentaBancoEmpresaList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancoEmpresa_items", selectedCuentaBancoEmpresaList);
//        }
//        return "/app/cuentaBancoEmpresa/index";
//    }
    public boolean getIsLiquidacionSueldoListEmpty() {
        return this.isLiquidacionSueldoListEmpty;
    }

    private void setIsLiquidacionSueldoListEmpty() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            this.isLiquidacionSueldoListEmpty = ejbFacade.isLiquidacionSueldoListEmpty(selected);
        } else {
            this.isLiquidacionSueldoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from Empresa and returns the navigation
     * outcome.
     *
     * @return navigation outcome for LiquidacionSueldo page
     */
    public String navigateLiquidacionSueldoList() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            List<LiquidacionSueldo> selectedLiquidacionSueldoList = ejbFacade.findLiquidacionSueldoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionSueldo_items", selectedLiquidacionSueldoList);
        }
        return "/app/liquidacionSueldo/index";
    }

    public boolean getIsReconocimientoDeudaListEmpty() {
        return this.isReconocimientoDeudaListEmpty;
    }

    private void setIsReconocimientoDeudaListEmpty() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            this.isReconocimientoDeudaListEmpty = ejbFacade.isReconocimientoDeudaListEmpty(selected);
        } else {
            this.isReconocimientoDeudaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ReconocimientoDeuda
     * entities that are retrieved from Empresa and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ReconocimientoDeuda page
     */
    public String navigateReconocimientoDeudaList() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            List<ReconocimientoDeuda> selectedReconocimientoDeudaList = ejbFacade.findReconocimientoDeudaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ReconocimientoDeuda_items", selectedReconocimientoDeudaList);
        }
        return "/app/reconocimientoDeuda/index";
    }

    public boolean getIsBusListEmpty() {
        return this.isBusListEmpty;
    }

    private void setIsBusListEmpty() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            this.isBusListEmpty = ejbFacade.isBusListEmpty(selected);
        } else {
            this.isBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from Empresa and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            List<Bus> selectedBusList = ejbFacade.findBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", selectedBusList);
        }
        return "/app/bus/index";
    }

    public boolean getIsRelacionLaboralListEmpty() {
        return this.isRelacionLaboralListEmpty;
    }

    private void setIsRelacionLaboralListEmpty() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            this.isRelacionLaboralListEmpty = ejbFacade.isRelacionLaboralListEmpty(selected);
        } else {
            this.isRelacionLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from Empresa and returns the navigation outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            List<RelacionLaboral> selectedRelacionLaboralList = ejbFacade.findRelacionLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", selectedRelacionLaboralList);
        }
        return "/app/relacionLaboral/index";
    }

    /**
     * Sets the "selected" attribute of the CajaCompensacion controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpresaIdCajaCompensacion(ActionEvent event) {
        Empresa selected = this.getSelected();
        if (selected != null && empresaIdCajaCompensacionController.getSelected() == null) {
            empresaIdCajaCompensacionController.setSelected(selected.getEmpresaIdCajaCompensacion());
        }
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpresaIdCuenta(ActionEvent event) {
        Empresa selected = this.getSelected();
        if (selected != null && empresaIdCuentaController.getSelected() == null) {
            empresaIdCuentaController.setSelected(selected.getEmpresaIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the Mutual controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpresaIdMutual(ActionEvent event) {
        Empresa selected = this.getSelected();
        if (selected != null && empresaIdMutualController.getSelected() == null) {
            empresaIdMutualController.setSelected(selected.getEmpresaIdMutual());
        }
    }

}
