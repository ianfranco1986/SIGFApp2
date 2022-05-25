package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.*;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.models.CompraDataModel;
import com.areatecnica.sigf.reports.ReportController;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Named(value = "compraController")
@ViewScoped
public class CompraController extends AbstractController<Compra> {

    @Inject
    private ReportController reportController;
    @Inject
    private CuentaMayorController compraCuentaIdController;
    @Inject
    private ProveedorController compraProveedorIdController;
    @Inject
    private TipoDocumentoController compraTipoDocumentoIdController;

    private CompraDataModel model;

    private TipoDocumento tipoDocumento;
    private TipoMovimiento tipoMoviento;
    private CuentaMayor cuentaMayor;
    private MovimientoMes movimiento;
    private CuentaBancaria cuentaBancaria;
    private List<TipoDocumento> tipoDocumentoItems;
    private List<CuentaMayor> cuentaMayorItems;
    private List<Compra> items;
    private List<Proveedor> proveedorItems;
    private List<CuentaBancaria> cuentaItems;
    private Proveedor proveedor;
    private Empresa empresaNandu;

    private final String informe = "inf-comprobante_egreso";
    private final String defaultTitle = "Registro de Compras";
    private String title = defaultTitle;

    private int folio;
    private int mes;
    private int anio;
    private int tipo;
    private int documento;
    private int finalID;
    private Date fecha;
    private Date desde;
    private Date hasta;
    private Date fechaMovimiento;
    private Date fechaLiquidacion;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private NumberFormat nf = NumberFormat.getInstance();

    public CompraController() {
        // Inform the Abstract parent controller of the concrete Compra Entity
        super(Compra.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        //super.initParams(); //To change body of generated methods, choose Tools | Templates.

        Calendar cal = Calendar.getInstance();
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.anio = cal.get(Calendar.YEAR);
        this.fechaMovimiento = new Date();
        this.fechaLiquidacion = new Date();
        this.proveedorItems = new IProveedorDaoImpl().findAll();
        this.tipoMoviento = new ITipoMovimientoDaoImpl().findById(1);

        this.fecha = new Date();
        setFecha();
        this.desde = this.fecha;
        this.hasta = new Date();

        this.prepareCreate(null);
        this.proveedor = new Proveedor();

        this.cuentaItems = new ICuentaBancariaDaoImpl().findAll();
        this.empresaNandu = new IEmpresaDaoImpl().findById(7);
        //load();
    }

    public void load() {
        setTitle(defaultTitle);
        setFecha();
        System.err.println("FECHA DE BUSQUEDA DE FACTURAS:" + desde + " - " + hasta);
        this.items = new ICompraDaoImpl().findCompraBetweenDates(desde, hasta);

        this.cuentaMayorItems = new ICuentaMayorDaoImpl().findALL();
        
        this.tipoDocumentoItems = new ITipoDocumentoDaoImpl().findAll();

        if (this.items.isEmpty()) {
            JsfUtil.addWarningMessage("No se han encontrado registros");
            this.model = new CompraDataModel(items);
        } else {
            JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");
            this.model = new CompraDataModel(items);

        }
        prepareCreate(null);
    }

    public void handleCuentaChange() {
        if (this.cuentaBancaria != null) {
            MovimientoMes movimientoDocumento = new IMovimientoMesDaoImpl().findLastByCuenta(this.cuentaBancaria);
            if (movimientoDocumento == null) {
                movimientoDocumento = new MovimientoMes();
                movimientoDocumento.setMovimientoMesNumeroDocumento(1);
            }
            this.documento = movimientoDocumento.getMovimientoMesNumeroDocumento() + 1;
        }
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null) {

            this.getSelected().setCompraProveedorId(proveedor);
            this.getSelected().setCompraCuentaMayorId(cuentaMayor);
            this.getSelected().setCompraTipoDocumentoId(tipoDocumento);

            MovimientoMes mov = new MovimientoMes();

            mov.setMovimientoMesMvtoId(tipoMoviento);
            mov.setMovimientoMesEmpresaId(empresaNandu);
            mov.setMovimientoMesCuentaBancoId(cuentaBancaria);
            mov.setMovimientoMesFechaMvto(this.getSelected().getCompraFechaDocumento());
            mov.setMovimientoMesFechaLiquidacion(this.desde);
            mov.setMovimientoMesMonto(this.getSelected().getCompraTotal());

            String descripcion = this.getSelected().getCompraTipoDocumentoId().getTipoDocumentoSigla() + ": " + this.proveedor.getProveedorNombre() + " - " + this.getSelected().getCompraDescripcion();

            mov.setMovimientoMesDetalle(descripcion);
            mov.setMovimientoMesTipoDocumento(documento);
            mov.setMovimientoMesNumeroDocumento(this.documento);

            this.getSelected().setCompraMovimientoId(mov);

            Compra t = new ICompraDaoImpl().create(this.getSelected());

            if (t != null) {
                this.finalID = t.getCompraId();
                this.items.add(this.getSelected());
                this.model = new CompraDataModel(items);
                this.setSelected(null);
                this.setSelected(prepareCreate(null));
                resetParents();
                JsfUtil.addSuccessMessage("Se ha regristrado una Compra");
                reportController.downloadFile(this.informe, this.getMap());
            } else {
                JsfUtil.addErrorMessage("Ha ocurrido un error durante la persistencia ");
            }
        }
    }

    @Override
    public Compra prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.getSelected().setCompraFechaDocumento(new Date());
        this.getSelected().setCompraFechaAcuse(new Date());
        this.getSelected().setCompraFechaRecepcion(new Date());
        this.getSelected().setCompraIva(0);
        this.getSelected().setCompraNeto(0);
        this.getSelected().setCompraExento(0);
        this.getSelected().setCompraOtrosImpuestos(0);
        this.getSelected().setCompraTotal(0);

        return this.getSelected();
    }

    public void prepareUpdate(ActionEvent event) {
        if (this.getSelected() != null) {
            this.setTitle("Actualizando Compra N°: " + this.getSelected().getCompraFolio());
            this.setCuentaBancaria(this.getSelected().getCompraMovimientoId().getMovimientoMesCuentaBancoId());
            this.setProveedor(this.getSelected().getCompraProveedorId());
            this.setCuentaMayor(this.getSelected().getCompraCuentaMayorId());
            this.setTipoDocumento(this.getSelected().getCompraTipoDocumentoId());
            
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la compra");
        }
    }

    public void onRowEdit(RowEditEvent event) {
        Compra temp = (Compra) event.getObject();
        
        try {

            new ICompraDaoImpl().update(temp);
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
            new ICompraDaoImpl().delete(this.getSelected());
            this.prepareCreate(event);
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un movimiento");
        }
    }

    public Proveedor prepareCreateProveedor(ActionEvent event) {
        this.proveedor = new Proveedor();

        return this.proveedor;
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

    public void setModel(CompraDataModel model) {
        this.model = model;
    }

    public CompraDataModel getModel() {
        return model;
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

    public void calculaIva() {
        if (this.getSelected().getCompraNeto() > 0) {
            Double d = this.getSelected().getCompraNeto() * 0.19;
            this.getSelected().setCompraIva(d.intValue());
            this.getSelected().setCompraTotal(this.getSelected().getCompraIva() + this.getSelected().getCompraNeto());
        }
    }

    public void calculaTotal() {
        if (this.getSelected().getCompraNeto() > 0) {
            this.getSelected().setCompraTotal(this.getSelected().getCompraIva() + this.getSelected().getCompraNeto() + this.getSelected().getCompraOtrosImpuestos() + this.getSelected().getCompraExento());
        }
    }

    public void addProveedor() {
        this.proveedor = new Proveedor();
    }

    public void saveNewProveedor() {
        if (this.proveedor != null) {
            new IProveedorDaoImpl().create(proveedor);
            this.proveedorItems.add(proveedor);

            this.getSelected().setCompraProveedorId(proveedor);
        }
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getDocumento() {
        return documento;
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

    public List<CuentaBancaria> getCuentaItems() {
        return cuentaItems;
    }

    public void setCuentaItems(List<CuentaBancaria> cuentaItems) {
        this.cuentaItems = cuentaItems;
    }

    public void setProveedorItems(List<Proveedor> proveedorItems) {
        this.proveedorItems = proveedorItems;
    }

    public List<Proveedor> getProveedorItems() {
        return proveedorItems;
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

    public List<Compra> getItems() {
        return items;
    }

    public void setItems(List<Compra> items) {
        this.items = items;
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

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
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

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        if (this.getSelected().getCompraId() != null) {
            this.finalID = this.getSelected().getCompraId();
        }
        map.put("compra_id", finalID);
        return map;
    }

    public String getInforme() {
        return informe;
    }

    public String getDefaultTitle() {
        return defaultTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
