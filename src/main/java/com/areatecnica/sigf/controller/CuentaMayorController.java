package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.CuentaUnicaDaoImpl;
import com.areatecnica.sigf.dao.impl.ICuentaMayorDaoImpl;
import com.areatecnica.sigf.dao.impl.PlanCuentaSubTipoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoPlanCuentaDaoImpl;
import com.areatecnica.sigf.entities.Compra;
import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.entities.CuentaUnica;
import com.areatecnica.sigf.entities.PlanCuenta;
import com.areatecnica.sigf.entities.PlanCuentaSubTipo;
import com.areatecnica.sigf.entities.TipoPlanCuenta;
import com.areatecnica.sigf.facade.CuentaMayorFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import org.primefaces.event.RowEditEvent;

@Named(value = "cuentaMayorController")
@ViewScoped
public class CuentaMayorController extends AbstractController<CuentaMayor> {

    @Inject
    private PlanCuentaSubTipoController cuentaMayorSubTipoIdController;

    private List<PlanCuentaSubTipo> subTipoItems;
    private List<TipoPlanCuenta> tipoPlanCuentaItems;
    private List<CuentaMayor> items;
    private List<CuentaMayor> itemsCuentaMayor;
    private List<CuentaUnica> cuentaUnicaItems;
    private CuentaMayor selected;
    private TipoPlanCuenta tipoPlanCuenta;
    private PlanCuentaSubTipo subTipo;
    private CuentaUnica cuentaUnica;
    private CuentaUnica settedCuentaUnica;
    private PlanCuenta plan;
    private String nombre;
    private String codigo;

    // Flags to indicate if child collections are empty
    private boolean isCompraListEmpty;

    public CuentaMayorController() {
        // Inform the Abstract parent controller of the concrete CuentaMayor Entity
        super(CuentaMayor.class);
        this.items = new ICuentaMayorDaoImpl().findALL();
        this.subTipo = new PlanCuentaSubTipo();

        if (!this.items.isEmpty()) {
            this.plan = this.items.get(0).getCuentaMayorSubTipoId().getPlanCuentaSubTipoIdPlan();
        }
        this.tipoPlanCuentaItems = new TipoPlanCuentaDaoImpl().find();
        this.cuentaUnicaItems = new CuentaUnicaDaoImpl().find();
    }

    public List<CuentaMayor> getItems() {
        return items;
    }

    public void setItems(List<CuentaMayor> items) {
        this.items = items;
    }

    @Override
    public CuentaMayor getSelected() {
        return selected;
    }

    @Override
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

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCuentaUnicaItems(List<CuentaUnica> cuentaUnicaItems) {
        this.cuentaUnicaItems = cuentaUnicaItems;
    }

    public void setCuentaUnica(CuentaUnica cuentaUnica) {
        this.cuentaUnica = cuentaUnica;
    }

    public List<CuentaUnica> getCuentaUnicaItems() {
        return cuentaUnicaItems;
    }

    public CuentaUnica getCuentaUnica() {
        return cuentaUnica;
    }

    public void setSettedCuentaUnica(CuentaUnica settedCuentaUnica) {
        this.settedCuentaUnica = settedCuentaUnica;
    }

    public CuentaUnica getSettedCuentaUnica() {
        return settedCuentaUnica;
    }

    @Override
    public CuentaMayor prepareCreate(ActionEvent event) {
        this.selected = new CuentaMayor();
        this.tipoPlanCuenta = new TipoPlanCuenta();
        this.subTipo = new PlanCuentaSubTipo();
        this.cuentaUnica = new CuentaUnica();
        this.nombre = "";
        this.codigo = "";
        return this.selected;
    }

    public PlanCuentaSubTipo prepareCreateSubTipo(ActionEvent event) {
        this.tipoPlanCuentaItems = new TipoPlanCuentaDaoImpl().find();
        this.tipoPlanCuenta = new TipoPlanCuenta();
//        this.subTipoItems = new PlanCuentaSubTipoDaoImpl().find();
        this.subTipo = new PlanCuentaSubTipo();
        return subTipo;
    }

    public void saveNew(ActionEvent event) {
        if (this.selected != null) {
            this.selected.setCuentaMayorNombre(nombre);
            this.selected.setCuentaMayorSubTipoId(subTipo);
            this.selected.setCuentaMayorUnicaId(cuentaUnica);

            CuentaMayor m = new ICuentaMayorDaoImpl().create(selected);

            if (m != null) {

//                CuentaUnica cuxdesactivar = m.getCuentaMayorUnicaId();
//                cuxdesactivar.setCuentaUnicaActivo(Boolean.TRUE);
//
//                CuentaUnica mm = new CuentaUnicaDaoImpl().update(cuxdesactivar);
//
//                if (mm == null) {
//                    JsfUtil.addErrorMessage("Error al actualizar la Cuenta Única, contáctese con el administrador");
//                } else {
//                    JsfUtil.addSuccessMessage("Se actualizó el inventario de cuentas únicas");
//                }
//
//                JsfUtil.addSuccessMessage("Se ha agregado la cuenta");
                this.items = new ICuentaMayorDaoImpl().findALL();
                this.cuentaUnicaItems = new CuentaUnicaDaoImpl().find();
                this.selected = null; 
            } else {
                JsfUtil.addErrorMessage("Error al registrar los cambios");
            }
        }
    }

    public void saveNewSubTipo(ActionEvent event) {
        if (this.subTipo != null) {
            this.subTipo.setPlanCuentaSubTipoCodigo(codigo);
            this.subTipo.setPlanCuentaSubTipoNombre(nombre);
            System.err.println("NOMBRE:" + this.nombre);
            this.subTipo.setPlanCuentaSubTipoCodigoTipo(this.tipoPlanCuenta.getTipoPlanCuentaId());
            this.subTipo.setPlanCuentaSubTipoIdTipoPlan(tipoPlanCuenta);

            this.subTipo.setPlanCuentaSubTipoIdPlan(plan);

            PlanCuentaSubTipo s = new PlanCuentaSubTipoDaoImpl().create(this.subTipo);
            if (s != null) {
                this.subTipoItems.add(s);
                JsfUtil.addSuccessMessage("Se ha agregado un subtipo");
                this.subTipo = new PlanCuentaSubTipo();
                this.items = new ICuentaMayorDaoImpl().findALL();
                this.codigo = "";
                this.nombre = "";
                this.tipoPlanCuenta = null;

            } else {
                JsfUtil.addErrorMessage("Error al registrar el subtipo");
            }
        }
    }

    public void save(ActionEvent event) {
        if (this.selected != null) {
            CuentaMayor m = new ICuentaMayorDaoImpl().update(this.selected);

            if (m != null) {
                JsfUtil.addSuccessMessage("Se ha editado la cuenta");
//                CuentaUnica aux = m.getCuentaMayorUnicaId();
//
//                //si las cuentas únicas son iguales, no actualizo nada 
//                if (!this.settedCuentaUnica.equals(aux)) {
//                    //si no son iguales, confirmo si la cuenta anterior fuera distinta a NINGUNA, en caso contrario, no devuelvo nada al inventario de cuentas
//                    if (this.settedCuentaUnica.getCuentaUnicaId() != 1) {
//                        //si la cuenta era disntinta a ninguna, la devuelvo al inventario
//                        this.settedCuentaUnica.setCuentaUnicaActivo(Boolean.FALSE);
//
//                        CuentaUnica antigua = new CuentaUnicaDaoImpl().update(this.settedCuentaUnica);
//                        if (antigua != null) {
//                            JsfUtil.addSuccessMessage("Se devolvió la cuenta " + antigua.getCuentaUnicaNombre() + " al inventario de cuentas únicas");
//                        } else {
//                            JsfUtil.addErrorMessage("Error al registrar los cambios en la cuenta única anterior, contáctese con el administrador");
//                        }
//                    }
//
////                    if (aux.getCuentaUnicaId() != 1) {
////
////                        aux.setCuentaUnicaActivo(Boolean.TRUE);
////                        //ya que la cuenta anterior era ninguna, actualizo la nueva cuental del inventario
////                        CuentaUnica nueva = new CuentaUnicaDaoImpl().update(aux);
////
////                        if (nueva != null) {
////                            JsfUtil.addSuccessMessage("Se eliminó la cuenta " + nueva.getCuentaUnicaNombre() + " del inventario de Cuentas Únicas");
////                        } else {
////                            JsfUtil.addErrorMessage("Error al registrar los cambios en la Cuenta Única nueva, contáctese con el administrador");
////                        }
////                    }
//                }

                this.items = new ICuentaMayorDaoImpl().findALL();
                this.cuentaUnicaItems = new CuentaUnicaDaoImpl().find();
                this.selected = null;
            } else {
                JsfUtil.addErrorMessage("Error al registrar los cambios");
            }
        }
    }

    public void onEditCuenta() {
        if (this.selected != null) {
            this.tipoPlanCuenta = this.selected.getCuentaMayorSubTipoId().getPlanCuentaSubTipoIdTipoPlan();
            this.subTipoItems = new PlanCuentaSubTipoDaoImpl().findByTipoPlan(tipoPlanCuenta);
            this.settedCuentaUnica = this.selected.getCuentaMayorUnicaId();
            if (this.settedCuentaUnica.getCuentaUnicaId() != 1) {
                this.cuentaUnicaItems.add(0, this.settedCuentaUnica);
            }
        }
    }

    public void onRowEdit(RowEditEvent event) {

        PlanCuentaSubTipo aux = null;

        try {
            aux = (PlanCuentaSubTipo) event.getObject();
            new PlanCuentaSubTipoDaoImpl().update(aux);
            JsfUtil.addSuccessMessage("Se ha actualizado el subtipo ");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar el registro");
        }

    }

    public void delete(ActionEvent event) {
        if (this.selected != null) {
            CuentaUnica c = this.selected.getCuentaMayorUnicaId();
            boolean validate = new ICuentaMayorDaoImpl().remove(selected);
            if (validate) {
                this.items = new ICuentaMayorDaoImpl().findALL();
                if (c.getCuentaUnicaId() != 1) {
                    c.setCuentaUnicaActivo(Boolean.FALSE);
                    CuentaUnica d = new CuentaUnicaDaoImpl().update(c);
                    if (d != null) {
                        JsfUtil.addSuccessMessage("Se devolvió la cuenta " + d.getCuentaUnicaNombre() + " al inventario de Cuentas Únicas");
                    } else {
                        JsfUtil.addErrorMessage("Error al registrar los cambios en la Cuenta Única nueva, contáctese con el administrador");
                    }
                }
            }
        }
    }

    public void handleCuentaChange() {
        if (this.tipoPlanCuenta != null) {
            this.subTipoItems = new PlanCuentaSubTipoDaoImpl().findByTipoPlan(tipoPlanCuenta);
            this.subTipo.setPlanCuentaSubTipoCodigoTipo(this.tipoPlanCuenta.getTipoPlanCuentaId());

            if (!this.subTipoItems.isEmpty()) {
                String aux = this.subTipoItems.get(this.subTipoItems.size() - 1).getPlanCuentaSubTipoCodigo();

                int l = aux.length();

                int v = Integer.parseInt(aux);

                v++;

                this.codigo = ("0" + String.valueOf(v));
            } else {
                this.codigo = ("01");
            }
        }
    }

    public void handleTipoCuentaChange() {
        if (this.tipoPlanCuenta != null) {
            this.subTipoItems = new PlanCuentaSubTipoDaoImpl().findByTipoPlan(tipoPlanCuenta);
        }
    }

    public void handleSubTipoCuentaChange() {
        if (this.subTipo != null) {

            this.itemsCuentaMayor = new ICuentaMayorDaoImpl().findBySubTipo(subTipo);

            if (!this.itemsCuentaMayor.isEmpty()) {
                CuentaMayor c = this.itemsCuentaMayor.get(this.itemsCuentaMayor.size() - 1);
                String v = c.getCuentaMayorCodigo();
                int a = Integer.parseInt(v);

                a++;

                this.codigo = String.valueOf(a);
                this.selected.setCuentaMayorCodigo("00" + this.codigo);
                //JsfUtil.addWarningMessage("Valor dle Código:" + this.selected.getCuentaMayorCodigo());

            } else {
                this.codigo = "1";
                this.selected.setCuentaMayorCodigo("00" + this.codigo);
                //JsfUtil.addWarningMessage("Valor dle Código:" + this.selected.getCuentaMayorCodigo());
                JsfUtil.addWarningMessage("No se han encontrado cuentas para el subtipo: " + this.subTipo.getPlanCuentaSubTipoNombre() + "se inicia con código " + this.selected.getCuentaMayorCodigo());

            }
        }
    }

    public void setValorNombre() {
        JsfUtil.addSuccessMessage(nombre);
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
