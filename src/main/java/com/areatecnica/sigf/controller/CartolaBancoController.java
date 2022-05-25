package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ICartolaBancoDao;
import com.areatecnica.sigf.dao.impl.ICartolaBancoDaoImpl;
import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.DetalleCartola;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.facade.CartolaBancoFacade;
import com.areatecnica.sigf.models.CartolaBancoDataModel;
import com.areatecnica.sigf.models.DetalleCartolaDataModel;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named(value = "cartolaBancoController")
@ViewScoped
public class CartolaBancoController extends AbstractController<CartolaBanco> {

    @Inject
    private CuentaBancariaController cartolaBancoIdCuentaBancariaController;

    private List<CartolaBanco> items;
    private List<DetalleCartola> detalleItems;

    private CuentaBancaria cuentaBancaria;
    private Empresa empresa;
    private DetalleCartola detalleCartola;
    private Date fecha;
    private LocalDate dateTime;
    private ICartolaBancoDao dao;
    private int saldo;
    private int saldoInicial;

    private CartolaBancoDataModel model;
    private DetalleCartolaDataModel detalleModel;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    // Flags to indicate if child collections are empty
    private boolean isDetalleCartolaListEmpty;

    public CartolaBancoController() {
        // Inform the Abstract parent controller of the concrete CartolaBanco Entity
        super(CartolaBanco.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.
        Calendar calendar = Calendar.getInstance();

        this.model = new CartolaBancoDataModel(new ArrayList<>());
        this.dao = new ICartolaBancoDaoImpl();
    }

    @Override
    public CartolaBanco prepareCreate(ActionEvent event) {
        if (cuentaBancaria != null) {
            super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
//            this.dateTime = new DateTime(fecha);
//            DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();
            CartolaBanco _aux = null;//this.dao.fingByCuentaBancoFecha(cuentaBancaria, fecha, _maxDate.toDate());

            if (_aux != null) {
                this.getSelected().setCartolaBancoSaldoInicial(_aux.getCartolaBancoSaldoFinal());
                this.getSelected().setCartolaBancoSaldoFinal(_aux.getCartolaBancoSaldoFinal());
                this.getSelected().setCartolaBancoNumero(_aux.getCartolaBancoNumero() + 1);
                this.getSelected().setCartolaBancoFechaInicial(_aux.getCartolaBancoFechaFinal());
                this.getSelected().setCartolaBancoFechaFinal(this.getSelected().getCartolaBancoFechaInicial());
            }

            this.getSelected().setCartolaBancoIdCuentaBancaria(cuentaBancaria);
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la cuenta bancaria");
        }
        return this.getSelected();
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setDetalleCartola(DetalleCartola detalleCartola) {
        this.detalleCartola = detalleCartola;
    }

    public DetalleCartola getDetalleCartola() {
        return detalleCartola;
    }

    public int getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(int saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    @Override
    public List<CartolaBanco> getItems() {
        return items;
    }

    public void setItems(List<CartolaBanco> items) {
        this.items = items;
    }

    public void load() {
//        setDate();
//
//        this.dateTime = new DateTime(fecha);
//        DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();
//
//        this.items = this.dao.findByCuentaBancariaBetweenDates(cuentaBancaria, fecha, _maxDate.toDate());
//
//        if (this.items.isEmpty()) {
//            JsfUtil.addErrorMessage("No se han encontrado cartolas");
//            this.model = new CartolaBancoDataModel(new ArrayList<>());
//        } else {
//            this.model = new CartolaBancoDataModel(this.items);
//        }
    }

    public void loadDetalle() {
        System.err.println("LLEGAL AL DETALLE");
        if (this.getSelected() != null) {
            System.err.println("SELECTED NO IS NULL");

            this.detalleItems = this.getSelected().getDetalleCartolaList();

            if (!this.detalleItems.isEmpty()) {
                this.detalleModel = new DetalleCartolaDataModel(detalleItems);

                int saldoInicial = this.getSelected().getCartolaBancoSaldoInicial();
                for (DetalleCartola d : this.detalleItems) {
                    saldoInicial = saldoInicial + d.getDetalleCartolaAbono() - d.getDetalleCartolaCargo();
                    d.setSaldo(saldoInicial);
                }

                this.getSelected().setCartolaBancoSaldoFinal(saldoInicial);

            } else {
                this.detalleModel = new DetalleCartolaDataModel();
            }

        } else {
            System.err.println("SELECTED IS NULL");
            JsfUtil.addErrorMessage("Debe seleccionar una cartola");
        }
    }

    public void setModel(CartolaBancoDataModel model) {
        this.model = model;
    }

    public CartolaBancoDataModel getModel() {
        return model;
    }

    public void setDetalleModel(DetalleCartolaDataModel detalleModel) {
        this.detalleModel = detalleModel;
    }

    public DetalleCartolaDataModel getDetalleModel() {
        return detalleModel;
    }

    public void setDetalleItems(List<DetalleCartola> detalleItems) {
        this.detalleItems = detalleItems;
    }

    public List<DetalleCartola> getDetalleItems() {
        return detalleItems;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.

        this.items.add(this.getSelected());
    }

    @Override
    public void delete(ActionEvent event) {

        this.items.remove(this.getSelected());
        super.delete(event); //To change body of generated methods, choose Tools | Templates.
        this.setSelected(null);
    }

    public int saldo(int saldo, int abono, int cargo) {

        saldo = saldo + abono - cargo;

        return saldo;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cartolaBancoIdCuentaBancariaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDetalleCartolaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the CuentaBancaria controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCartolaBancoIdCuentaBancaria(ActionEvent event) {
        CartolaBanco selected = this.getSelected();
        if (selected != null && cartolaBancoIdCuentaBancariaController.getSelected() == null) {
            cartolaBancoIdCuentaBancariaController.setSelected(selected.getCartolaBancoIdCuentaBancaria());
        }
    }

    public boolean getIsDetalleCartolaListEmpty() {
        return this.isDetalleCartolaListEmpty;
    }

    private void setIsDetalleCartolaListEmpty() {
        CartolaBanco selected = this.getSelected();
        if (selected != null) {
            CartolaBancoFacade ejbFacade = (CartolaBancoFacade) this.getFacade();
            this.isDetalleCartolaListEmpty = ejbFacade.isDetalleCartolaListEmpty(selected);
        } else {
            this.isDetalleCartolaListEmpty = true;
        }
    }

}
