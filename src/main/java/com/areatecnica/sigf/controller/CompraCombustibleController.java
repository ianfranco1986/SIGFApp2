package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.ICuentaBancariaDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoCombustibleDaoImpl;
import com.areatecnica.sigf.entities.CompraPetroleo;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.TipoCombustible;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "compraPetroleoController")
@ViewScoped
public class CompraCombustibleController extends AbstractController<CompraPetroleo> {

    @Inject
    private CuentaController compraCombustibleIdCuentaController;
    @Inject
    private TipoCombustibleController compraCombustibleIdTipoController;
    private TipoCombustible tipoCombustible;
    private CuentaBancaria cuenta;
    private List<CuentaBancaria> cuentaBancariaItems;

    //SE DEBE CREAR MOVIMIENTO AL GUARDAR ENTIDAD
    
    public CompraCombustibleController() {
        // Inform the Abstract parent controller of the concrete CompraPetroleo Entity
        super(CompraPetroleo.class);
        this.tipoCombustible = new TipoCombustibleDaoImpl().findTipoCombustibleDefecto();
        this.cuentaBancariaItems = new ICuentaBancariaDaoImpl().findAll();
    }

    @Override
    public CompraPetroleo prepareCreate(ActionEvent event) {
        super.prepareCreate(event);
        return this.getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        compraCombustibleIdCuentaController.setSelected(null);
        compraCombustibleIdTipoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCompraCombustibleIdCuenta(ActionEvent event) {
        CompraPetroleo selected = this.getSelected();
        if (selected != null && compraCombustibleIdCuentaController.getSelected() == null) {
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCompraCombustibleIdTipo(ActionEvent event) {
        CompraPetroleo selected = this.getSelected();
        if (selected != null && compraCombustibleIdTipoController.getSelected() == null) {
        }
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
    }

    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(TipoCombustible tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    public List<CuentaBancaria> getCuentaBancariaItems() {
        return cuentaBancariaItems;
    }

    public void setCuentaBancariaItems(List<CuentaBancaria> cuentaBancariaItems) {
        this.cuentaBancariaItems = cuentaBancariaItems;
    }

}
