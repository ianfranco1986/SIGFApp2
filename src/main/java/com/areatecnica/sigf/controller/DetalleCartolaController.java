package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ICartolaBancoDao;
import com.areatecnica.sigf.dao.ICuentaBancariaDao;
import com.areatecnica.sigf.dao.IDetalleCartolaDao;
import com.areatecnica.sigf.dao.IEmpresaDao;
import com.areatecnica.sigf.dao.impl.ICartolaBancoDaoImpl;
import com.areatecnica.sigf.dao.impl.ICuentaBancariaDaoImpl;
import com.areatecnica.sigf.dao.impl.IDetalleCartolaDaoImpl;
import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.DetalleCartola;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.models.DetalleCartolaDataModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.joda.time.DateTime;

@Named(value = "detalleCartolaController")
@ViewScoped
public class DetalleCartolaController extends AbstractController<DetalleCartola> {

    @Inject
    private CartolaBancoController detalleCartolaCartolaBancoIdController;

    private List<Empresa> empresaItems;
    private List<CuentaBancaria> cuentaItems;
    private List<CartolaBanco> cartolaItems;
    private List<DetalleCartola> items;

    private Empresa empresa;
    private CuentaBancaria cuentaBancaria;
    private CartolaBanco cartolaBanco;

    private IEmpresaDao empresaDao;
    private ICartolaBancoDao cartolaBancoDao;
    private ICuentaBancariaDao cuentaBancariaDao;
    private IDetalleCartolaDao dao;
    private DetalleCartolaDataModel model;

    private Date fecha;
    private DateTime dateTime;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public DetalleCartolaController() {
        // Inform the Abstract parent controller of the concrete DetalleCartola Entity
        super(DetalleCartola.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.
        this.fecha = new Date();
        this.cuentaBancariaDao = new ICuentaBancariaDaoImpl();
        this.cuentaItems = this.cuentaBancariaDao.findByCuenta(this.getUserCount());

        this.empresaItems = new ArrayList<Empresa>();
        for (CuentaBancaria c : this.cuentaItems) {
            if (!this.empresaItems.contains(c.getCuentaBancariaIdEmpresa())) {
                this.empresaItems.add(c.getCuentaBancariaIdEmpresa());
            }
        }

        /*this.empresaDao = new IEmpresaDaoImpl();
        this.empresaItems = this.empresaDao.findByCuenta(this.getUserCount());*/
        this.cartolaBancoDao = new ICartolaBancoDaoImpl();
        this.dao = new IDetalleCartolaDaoImpl();
        //this.cartolaBanco = new CartolaBanco()
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null) {
            System.err.println("llega por acá ");
            this.getSelected().setDetalleCartolaCartolaBancoId(this.cartolaBanco);

            //super.save(event); //To change body of generated methods, choose Tools | Templates.
            this.dao.create(this.getSelected());

            this.getSelected().setSaldo(this.getSelected().getDetalleCartolaAbono() - this.getSelected().getDetalleCartolaCargo() + this.cartolaBanco.getCartolaBancoSaldoFinal());

            this.cartolaBanco.setCartolaBancoSaldoFinal(this.getSelected().getSaldo());

            this.cartolaBancoDao.update(this.cartolaBanco);

            this.items.add(this.getSelected());
                        
            DetalleCartola _aux = this.getSelected();
            
            this.setSelected(prepareCreate(event));
            
            this.getSelected().setDetalleCartolaFechaMovimiento(_aux.getDetalleCartolaFechaMovimiento());
            
        } else {
            JsfUtil.addErrorMessage("Ocurrió un error al guardar el registro");
        }
    }

    public void handleEmpresaChange() {
        if (this.empresa != null) {
            this.cuentaItems = this.empresa.getCuentaBancariaList();
            this.cartolaItems = new ArrayList<>();
        }
    }

    public void handleCuentaChange() {
        if (this.cuentaBancaria != null) {
            this.cartolaItems = this.cuentaBancaria.getCartolaBancoList();
        }
    }

    public void handleCartolaChange() {
        if (this.cartolaBanco != null) {
            this.fecha = this.cartolaBanco.getCartolaBancoFechaInicial();
            this.setSelected(prepareCreate(null));
            this.getSelected().setDetalleCartolaFechaMovimiento(fecha);
            this.getSelected().setDetalleCartolaAbono(0);
            this.getSelected().setDetalleCartolaCargo(0);

            this.items = this.cartolaBanco.getDetalleCartolaList();

            if (!this.items.isEmpty()) {
                this.model = new DetalleCartolaDataModel(items);

                int saldoInicial = this.cartolaBanco.getCartolaBancoSaldoInicial();
                for (DetalleCartola d : this.items) {
                    saldoInicial = saldoInicial + d.getDetalleCartolaAbono() - d.getDetalleCartolaCargo();
                    d.setSaldo(saldoInicial);
                }

                this.cartolaBanco.setCartolaBancoSaldoFinal(saldoInicial);

            } else {
                this.model = new DetalleCartolaDataModel();
            }

            System.err.println("TAMAÑO DE DETALLES" + this.items.size());
        }
    }

    public void setModel(DetalleCartolaDataModel model) {
        this.model = model;
    }

    public DetalleCartolaDataModel getModel() {
        return model;
    }

    public void setItems(List<DetalleCartola> items) {
        this.items = items;
    }

    public List<DetalleCartola> getItems() {
        return items;
    }

    public List<Empresa> getEmpresaItems() {
        return empresaItems;
    }

    public void setEmpresaItems(List<Empresa> empresaItems) {
        this.empresaItems = empresaItems;
    }

    public List<CuentaBancaria> getCuentaItems() {
        return cuentaItems;
    }

    public void setCuentaItems(List<CuentaBancaria> cuentaItems) {
        this.cuentaItems = cuentaItems;
    }

    public List<CartolaBanco> getCartolaItems() {
        return cartolaItems;
    }

    public void setCartolaItems(List<CartolaBanco> cartolaItems) {
        this.cartolaItems = cartolaItems;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public CartolaBanco getCartolaBanco() {
        return cartolaBanco;
    }

    public void setCartolaBanco(CartolaBanco cartolaBanco) {
        this.cartolaBanco = cartolaBanco;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public String formatDate(Date fecha) {
        return sdf.format(fecha);
    }

    public void setCargo() {
        if (this.getSelected().getDetalleCartolaCargo() > 0) {
            this.getSelected().setDetalleCartolaAbono(0);
        }
    }

    public void setAbono() {
        if (this.getSelected().getDetalleCartolaAbono() > 0) {
            this.getSelected().setDetalleCartolaCargo(0);
        }
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleCartolaCartolaBancoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CartolaBanco controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleCartolaCartolaBancoId(ActionEvent event) {
        DetalleCartola selected = this.getSelected();
        if (selected != null && detalleCartolaCartolaBancoIdController.getSelected() == null) {
            detalleCartolaCartolaBancoIdController.setSelected(selected.getDetalleCartolaCartolaBancoId());
        }
    }

}
