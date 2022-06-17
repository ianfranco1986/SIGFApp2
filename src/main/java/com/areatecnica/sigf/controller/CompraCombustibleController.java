package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.*;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.models.CompraPetroleoDataModel;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private CompraPetroleoDataModel model;

    private TipoDocumento tipoDocumento;
    private CuentaMayor cuentaMayor;
    private MovimientoMes movimiento;
    private CuentaBancaria cuentaBancaria;
    private List<TipoDocumento> tipoDocumentoItems;
    private List<CuentaMayor> cuentaMayorItems;
    private List<CompraPetroleo> items;
    private List<Proveedor> proveedorItems;
    private List<CuentaBancaria> cuentaItems;
    private Proveedor proveedor;
    private Empresa empresaNandu;

    private final String informe = "inf-comprobante_egreso_petroleo";

    private int folio;
    private int mes;
    private int anio;
    private int tipo;
    private int documento;
    private int finalID;
    private int surtidor1;
    private int surtidor2;
    private int totalLitros;
    private Date fecha;
    private Date desde;
    private Date hasta;
    private Date fechaMovimiento;
    private Date fechaLiquidacion;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private NumberFormat nf = NumberFormat.getInstance();

    //SE DEBE CREAR MOVIMIENTO AL GUARDAR ENTIDAD
    public CompraCombustibleController() {
        // Inform the Abstract parent controller of the concrete CompraPetroleo Entity
        super(CompraPetroleo.class);
        this.tipoCombustible = new TipoCombustibleDaoImpl().findTipoCombustibleDefecto();
        this.cuentaBancariaItems = new CuentaBancariaDaoImpl().findAll();
    }

    @PostConstruct
    @Override
    public void initParams() {
        Calendar cal = Calendar.getInstance();
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.anio = cal.get(Calendar.YEAR);
        this.fechaMovimiento = new Date();
        this.fechaLiquidacion = new Date();
        this.proveedorItems = new ArrayList();

        Proveedor enel = new ProveedorDaoImpl().findByRut("920110002");
        Proveedor copec = new ProveedorDaoImpl().findByRut("995200007");

        this.proveedorItems.add(enel);
        this.proveedorItems.add(copec);

        this.cuentaItems = new ArrayList<>();
        
        CuentaBancaria itau = new CuentaBancariaDaoImpl().findById(4);
        CuentaBancaria scotiabank = new CuentaBancariaDaoImpl().findById(6);
        
        this.cuentaItems.add(itau);
        this.cuentaItems.add(scotiabank);

        this.fecha = new Date();
        setFecha();
        this.desde = this.fecha;
        this.hasta = new Date();

        this.prepareCreate(null);
        this.proveedor = new Proveedor();

        this.empresaNandu = new EmpresaDaoImpl().findById(7);
    }

    public void load() {
        setFecha();
        this.items = new CompraPetroleoDaoImpl().findCompraBetweenDates(desde, hasta);

        this.cuentaMayorItems = new CuentaMayorDaoImpl().findALL();

        if (this.items.isEmpty()) {
            JsfUtil.addWarningMessage("No se han encontrado registros");
            this.model = new CompraPetroleoDataModel(items);
        } else {
            JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");
            this.model = new CompraPetroleoDataModel(items);

        }

    }

    public void handleCuentaChange() {
        if (this.cuentaBancaria != null) {
            MovimientoMes movimientoDocumento = new MovimientoMesDaoImpl().findLastByCuenta(this.cuentaBancaria);
            if (movimientoDocumento == null) {
                movimientoDocumento = new MovimientoMes();
                movimientoDocumento.setMovimientoMesNumeroDocumento(1);
            }
            this.documento = movimientoDocumento.getMovimientoMesNumeroDocumento() + 1;
        }
    }

    @Override
    public CompraPetroleo prepareCreate(ActionEvent event) {
        super.prepareCreate(event);

        this.getSelected().setCompraPetroleoFecha(new Date());
        this.getSelected().setCompraPetroleoBaseAfecta(0);
        this.getSelected().setCompraPetroleoCantidadLitros(0);
        this.getSelected().setCompraPetroleoFolio(0);
        this.getSelected().setCompraPetroleoIef(0);
        this.getSelected().setCompraPetroleoIev(0);
        this.getSelected().setCompraPetroleoIva(0);
        this.getSelected().setCompraPetroleoSurtidorN1(0);
        this.getSelected().setCompraPetroleoSurtidorN2(0);
        this.getSelected().setCompraPetroleoTotal(0);

        return this.getSelected();
    }

    public void handleSurtidor1Change() {
        if (this.getSelected().getCompraPetroleoSurtidorN1() > 0) {
            this.getSelected().setCompraPetroleoCantidadLitros(this.getSelected().getCompraPetroleoSurtidorN1() + this.getSelected().getCompraPetroleoSurtidorN2());
        }
    }

    public void handleSurtidor2Change() {
        if (this.getSelected().getCompraPetroleoSurtidorN2() > 0) {
            this.getSelected().setCompraPetroleoCantidadLitros(this.getSelected().getCompraPetroleoSurtidorN1() + this.getSelected().getCompraPetroleoSurtidorN2());
        }
    }

    public void onRowEdit(RowEditEvent event) {
        CompraPetroleo temp = (CompraPetroleo) event.getObject();

        try {

            new CompraPetroleoDaoImpl().update(temp);
            JsfUtil.addSuccessMessage("Se ha actualizado el registro");
            //getTotals();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ha ocurrido un error al guardar los cambios");
        }
    }

    @Override
    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {

            this.items.remove(this.getSelected());
            new CompraPetroleoDaoImpl().delete(this.getSelected());
            this.prepareCreate(event);
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un movimiento");
        }
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
        if (this.getSelected() != null) {

            this.getSelected().setCompraPetroleoProveedorId(proveedor);
            this.getSelected().setCompraPetroleoCuentaMayorId(cuentaMayor);

            MovimientoMes mov = new MovimientoMes();

            mov.setMovimientoMesEmpresaId(empresaNandu);
            mov.setMovimientoMesCuentaBancoId(cuentaBancaria);
            mov.setMovimientoMesFechaMvto(this.getSelected().getCompraPetroleoFecha());
            mov.setMovimientoMesFechaLiquidacion(this.desde);
            mov.setMovimientoMesMonto(this.getSelected().getCompraPetroleoTotal());

            String descripcion = this.getSelected().getCompraPetroleoTipoDocumentoId().getTipoDocumentoSigla() + ": Petróleo" + this.proveedor.getProveedorNombre();

            mov.setMovimientoMesDetalle(descripcion);
            mov.setMovimientoMesTipoDocumento(documento);
            mov.setMovimientoMesNumeroDocumento(this.documento);

            this.getSelected().setCompraPetroleoMovtId(mov);

            CompraPetroleo t = new CompraPetroleoDaoImpl().create(this.getSelected());

            if (t != null) {
                this.finalID = t.getCompraPetroleoId();
                this.items.add(this.getSelected());
                this.model = new CompraPetroleoDataModel(items);
                this.setSelected(null);
                this.setSelected(prepareCreate(null));
                resetParents();
                JsfUtil.addSuccessMessage("Se ha regristrado una Compra");
            } else {
                JsfUtil.addErrorMessage("Ha ocurrido un error durante la persistencia ");
            }
        }
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        if (this.getSelected().getCompraPetroleoId() != null) {
            this.finalID = this.getSelected().getCompraPetroleoId();
        }
        map.put("compra_id", finalID);
        return map;
    }

    public void setSurtidor2(int surtidor2) {
        this.surtidor2 = surtidor2;
    }

    public int getSurtidor2() {
        return surtidor2;
    }

    public void setSurtidor1(int surtidor1) {
        this.surtidor1 = surtidor1;
    }

    public int getSurtidor1() {
        return surtidor1;
    }

    public String getInforme() {
        return informe;
    }

    public CompraPetroleoDataModel getModel() {
        return model;
    }

    public void setModel(CompraPetroleoDataModel model) {
        this.model = model;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public CuentaMayor getCuentaMayor() {
        return cuentaMayor;
    }

    public void setCuentaMayor(CuentaMayor cuentaMayor) {
        this.cuentaMayor = cuentaMayor;
    }

    public MovimientoMes getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoMes movimiento) {
        this.movimiento = movimiento;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public List<TipoDocumento> getTipoDocumentoItems() {
        return tipoDocumentoItems;
    }

    public void setTipoDocumentoItems(List<TipoDocumento> tipoDocumentoItems) {
        this.tipoDocumentoItems = tipoDocumentoItems;
    }

    public List<CuentaMayor> getCuentaMayorItems() {
        return cuentaMayorItems;
    }

    public void setCuentaMayorItems(List<CuentaMayor> cuentaMayorItems) {
        this.cuentaMayorItems = cuentaMayorItems;
    }

    public List<CompraPetroleo> getItems() {
        return items;
    }

    public void setItems(List<CompraPetroleo> items) {
        this.items = items;
    }

    public List<Proveedor> getProveedorItems() {
        return proveedorItems;
    }

    public void setProveedorItems(List<Proveedor> proveedorItems) {
        this.proveedorItems = proveedorItems;
    }

    public List<CuentaBancaria> getCuentaItems() {
        return cuentaItems;
    }

    public void setCuentaItems(List<CuentaBancaria> cuentaItems) {
        this.cuentaItems = cuentaItems;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Empresa getEmpresaNandu() {
        return empresaNandu;
    }

    public void setEmpresaNandu(Empresa empresaNandu) {
        this.empresaNandu = empresaNandu;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getFinalID() {
        return finalID;
    }

    public void setFinalID(int finalID) {
        this.finalID = finalID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public NumberFormat getNf() {
        return nf;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        this.proveedor = null;
        this.cuentaBancaria = null;
        this.cuentaMayor = null;
        this.documento = 0;
    }

    public void calculaIva() {
        if (this.getSelected().getCompraPetroleoBaseAfecta() > 0) {
            Double d = this.getSelected().getCompraPetroleoBaseAfecta() * 0.19;
            this.getSelected().setCompraPetroleoIva(d.intValue());
            this.getSelected().setCompraPetroleoTotal(this.getSelected().getCompraPetroleoIva() + this.getSelected().getCompraPetroleoBaseAfecta());
        }
    }

    public void calculaTotal() {
        if (this.getSelected().getCompraPetroleoBaseAfecta() > 0) {
            this.getSelected().setCompraPetroleoTotal(this.getSelected().getCompraPetroleoIva() + this.getSelected().getCompraPetroleoBaseAfecta() + this.getSelected().getCompraPetroleoIef() + this.getSelected().getCompraPetroleoIev());
        }
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

    public void setFecha() {
//        try {
//            System.err.println("NUEVA FECHA:");
//            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
//            this.dateTime = new DateTime(this.fecha);
//            this.desde = this.fecha;
//            this.hasta = this.dateTime.dayOfMonth().withMaximumValue().toDate();
//        } catch (ParseException ex) {
//        }
    }

}
