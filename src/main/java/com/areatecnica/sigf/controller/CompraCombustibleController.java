package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.IDetalleCartolaDao;
import com.areatecnica.sigf.dao.impl.ICartolaBancoDaoImpl;
import com.areatecnica.sigf.dao.impl.ICuentaBancariaDaoImpl;
import com.areatecnica.sigf.dao.impl.IDetalleCartolaDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoCombustibleDaoImpl;
import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.CompraCombustible;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.DetalleCartola;
import com.areatecnica.sigf.entities.TipoCombustible;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "compraCombustibleController")
@ViewScoped
public class CompraCombustibleController extends AbstractController<CompraCombustible> {

    @Inject
    private CuentaController compraCombustibleIdCuentaController;
    @Inject
    private TipoCombustibleController compraCombustibleIdTipoController;
    private TipoCombustible tipoCombustible;
    private CuentaBancaria cuenta;
    private List<CuentaBancaria> cuentaBancariaItems;

    public CompraCombustibleController() {
        // Inform the Abstract parent controller of the concrete CompraCombustible Entity
        super(CompraCombustible.class);
        this.tipoCombustible = new TipoCombustibleDaoImpl().findTipoCombustibleDefecto();
        this.cuentaBancariaItems = new ICuentaBancariaDaoImpl().findAll();
    }

    @Override
    public CompraCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setCompraCombustibleIdCuenta(this.getUserCount());
        this.getSelected().setCompraCombustibleIdTipo(tipoCombustible);
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
        CompraCombustible selected = this.getSelected();
        if (selected != null && compraCombustibleIdCuentaController.getSelected() == null) {
            compraCombustibleIdCuentaController.setSelected(selected.getCompraCombustibleIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCompraCombustibleIdTipo(ActionEvent event) {
        CompraCombustible selected = this.getSelected();
        if (selected != null && compraCombustibleIdTipoController.getSelected() == null) {
            compraCombustibleIdTipoController.setSelected(selected.getCompraCombustibleIdTipo());
        }
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.

        CartolaBanco cartola = cuenta.getCartolaBancoList().get(cuenta.getCartolaBancoList().size() - 1);

        DetalleCartola detalle = new DetalleCartola();
        detalle.setDetalleCartolaAbono(0);
        detalle.setDetalleCartolaCargo(this.getSelected().getCompraCombustibleTotalCompra());
        detalle.setDetalleCartolaFechaMovimiento(this.getSelected().getCompraCombustibleFechaPago());
        detalle.setDetalleCartolaDescripcion("Pago Factura N° " + this.getSelected().getCompraCombustibleFolio() + " Petroleo");
        detalle.setDetalleCartolaNumeroDocumento(String.valueOf(this.getSelected().getCompraCombustibleFolio()));
        detalle.setDetalleCartolaCartolaBancoId(cartola);

        new IDetalleCartolaDaoImpl().create(detalle);

        cartola.setCartolaBancoSaldoFinal(cartola.getCartolaBancoSaldoFinal() - detalle.getDetalleCartolaCargo());
        new ICartolaBancoDaoImpl().update(cartola);
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
