package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.*;
import com.areatecnica.sigf.dto.CompraDTO;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.models.CompraDataModel;
import com.areatecnica.sigf.util.FileUpload;
import com.areatecnica.sigf.util.LocalDateConverter;
import java.io.File;
import java.io.Serializable;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

@Named(value = "edicionComprasController")
@ViewScoped
public class EdicionComprasController implements Serializable {

    @Inject
    private CargaMasivaController cargaMasivaController;

    @Inject
    private FileUpload fileUpload;

    private CompraDataModel model;

    private TipoDocumento tipoDocumento;
    private TipoMovimiento tipoMoviento;
    private CuentaMayor cuentaMayor;
    private CuentaMayor cuentaMayorContabilidad;
    private CuentaBancaria cuentaBancaria;
    private List<TipoDocumento> tipoDocumentoItems;
    private List<CuentaMayor> cuentaMayorItems;
    private List<CuentaMayor> cuentaMayorItemsContabilidad;
    private List<CompraDTO> items;
    private List<CompraDTO> selectedItems;
    private List<Proveedor> proveedorItems;
    private List<CuentaBancaria> cuentaItems;
    private Proveedor proveedor;
    private Empresa empresaNandu;
    private File file;

    private Voucher voucher;
    private VoucherMovimiento movimiento;
    private Compra selected;

    private List<VoucherMovimiento> movimientoItems;

    private final String informe = "inf-comprobante_egreso";
    private final String defaultTitle = "Registro de Compras";
    private String title = defaultTitle;

    private boolean contabilizar;

    private CentroCosto ccDefecto = new CentroCostoDaoImpl().findById(1);
    private CuentaMayor cmDefecto;

    private int folio;

    private int tipo;
    private int documento;
    private int finalID;

    private Date fechaMovimiento;
    private Date fechaLiquidacion;

    private LocalDate date;
    private LocalDateConverter dc;

    private NumberFormat nf = NumberFormat.getInstance();

    public EdicionComprasController() {
        // Inform the Abstract parent controller of the concrete Compra Entity
    }

    @PostConstruct
    public void initParams() {
        //super.initParams(); //To change body of generated methods, choose Tools | Templates.
        this.setDate(LocalDate.now());

        this.fechaMovimiento = new Date();
        this.fechaLiquidacion = new Date();
//        this.proveedorItems = new ProveedorDaoImpl().findAll();
//        this.tipoMoviento = new ITipoMovimientoDaoImpl().findById(1);

//        this.proveedor = new Proveedor();
        this.cuentaItems = new CuentaBancariaDaoImpl().findAll();
        this.empresaNandu = new EmpresaDaoImpl().findById(7);

        this.cuentaMayorItems = new CuentaMayorDaoImpl().findByCompras();

        this.cuentaMayorItemsContabilidad = new CuentaMayorDaoImpl().findByBanco();

        this.items = cargaMasivaController.getItems();
        this.selected = this.items.get(0).getCompra();
        this.movimientoItems = new ArrayList<>();
        load();
    }

    public void load() {
        prepareCreate(null);
    }

    public void prepareCreate(ActionEvent event) {
        this.voucher = new Voucher();
        this.movimiento = new VoucherMovimiento(voucher);
        this.movimiento.setVoucherMovimientoCuentaId(new CuentaMayor());
        this.movimiento.setVoucherMovimientoCentroCostoId(ccDefecto);
        this.movimiento.setVoucherMovimientoEsDebito(Boolean.TRUE);
        this.movimiento.setVoucherMovimientoMonto(this.selected.getCompraNeto());
        this.movimientoItems.add(movimiento);
    }

    public void addMovimiento(ActionEvent event) {
        if (this.movimiento != null) {
            this.movimientoItems.add(movimiento);
        }
    }

//    public void saveNew(ActionEvent event) {
//        if (this.getSelected() != null) {
//
//            this.getSelected().setCompraProveedorId(proveedor);
//            this.getSelected().setCompraCuentaMayorId(cuentaMayor);
//            this.getSelected().setCompraTipoDocumentoId(tipoDocumento);
//
//            MovimientoMes mov = new MovimientoMes();
//
//            mov.setMovimientoMesMvtoId(tipoMoviento);
//            mov.setMovimientoMesEmpresaId(empresaNandu);
//            mov.setMovimientoMesCuentaBancoId(cuentaBancaria);
//            mov.setMovimientoMesFechaMvto(this.getSelected().getCompraFechaDocumento());
//            mov.setMovimientoMesFechaLiquidacion(this.dc.getFirstDateOfMonth());
//            mov.setMovimientoMesMonto(this.getSelected().getCompraTotal());
//
//            String descripcion = this.getSelected().getCompraTipoDocumentoId().getTipoDocumentoSigla() + ": " + this.proveedor.getProveedorNombre() + " - " + this.getSelected().getCompraDescripcion();
//
//            mov.setMovimientoMesDetalle(descripcion);
//            mov.setMovimientoMesTipoDocumento(documento);
//            mov.setMovimientoMesNumeroDocumento(this.documento);
//
//            this.getSelected().setCompraMovimientoId(mov);
//
//            Compra t = new CompraDaoImpl().create(this.getSelected());
//
//            if (t != null) {
//                this.finalID = t.getCompraId();
//                this.items.add(this.getSelected());
//                this.model = new CompraDataModel(items);
//                this.setSelected(null);
//                this.setSelected(prepareCreate(null));
//                resetParents();
//                JsfUtil.addSuccessMessage("Se ha regristrado una Compra");
//                reportController.downloadFile(this.informe, this.getMap());
//            } else {
//                JsfUtil.addErrorMessage("Ha ocurrido un error durante la persistencia ");
//            }
//        }
//    }
//    public Compra prepareCreate(ActionEvent event) {
//        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
//
//        this.getSelected().setCompraFechaDocumento(new Date());
//        this.getSelected().setCompraFechaAcuse(new Date());
//        this.getSelected().setCompraFechaRecepcion(new Date());
//        this.getSelected().setCompraIva(0);
//        this.getSelected().setCompraNeto(0);
//        this.getSelected().setCompraExento(0);
//        this.getSelected().setCompraOtrosImpuestos(0);
//        this.getSelected().setCompraTotal(0);
//
//        return this.getSelected();
//    }
//
//    public void prepareUpdate(ActionEvent event) {
//        if (this.getSelected() != null) {
//            this.setTitle("Actualizando Compra N°: " + this.getSelected().getCompraFolio());
//            this.setCuentaBancaria(this.getSelected().getCompraMovimientoId().getMovimientoMesCuentaBancoId());
//            this.setProveedor(this.getSelected().getCompraProveedorId());
//            this.setCuentaMayor(this.getSelected().getCompraCuentaMayorId());
//            this.setTipoDocumento(this.getSelected().getCompraTipoDocumentoId());
//
//        } else {
//            JsfUtil.addErrorMessage("Debe seleccionar la compra");
//        }
//    }
//    public void onRowEdit(RowEditEvent event) {
//        Compra temp = (Compra) event.getObject();
//
//        try {
//
//            new CompraDaoImpl().update(temp);
//            JsfUtil.addSuccessMessage("Se ha actualizado el registro");
//            //getTotals();
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage("Ha ocurrido un error al guardar los cambios");
//        }
//    }

//    public void delete(ActionEvent event) {
//        if (this.getSelected() != null) {
//
//            this.items.remove(this.getSelected());
//            new CompraDaoImpl().delete(this.getSelected());
//            this.prepareCreate(event);
//        } else {
//            JsfUtil.addErrorMessage("Debe seleccionar un movimiento");
//        }
//    }
    public Proveedor prepareCreateProveedor(ActionEvent event) {
        this.proveedor = new Proveedor();

        return this.proveedor;
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedGuias()) {
            int size = this.selectedItems.size();
            return size > 1 ? size + " filas seleccionadas" : "1 fila seleccionada";
        }

        return "Eliminar";
    }

    public boolean hasSelectedGuias() {
        return this.selectedItems != null && !this.selectedItems.isEmpty();
    }

    public void delete(ActionEvent event) {

    }

    public void deleteSelectedGuias() {

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

    public void setSelected(Compra selected) {
        this.selected = selected;
    }

    public Compra getSelected() {
        return selected;
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

//    public void calculaIva() {
//        if (this.getSelected().getCompraNeto() > 0) {
//            Double d = this.getSelected().getCompraNeto() * 0.19;
//            this.getSelected().setCompraIva(d.intValue());
//            this.getSelected().setCompraTotal(this.getSelected().getCompraIva() + this.getSelected().getCompraNeto());
//        }
//    }
//
//    public void calculaTotal() {
//        if (this.getSelected().getCompraNeto() > 0) {
//            this.getSelected().setCompraTotal(this.getSelected().getCompraIva() + this.getSelected().getCompraNeto() + this.getSelected().getCompraOtrosImpuestos() + this.getSelected().getCompraExento());
//        }
//    }
    public void addProveedor() {
        this.proveedor = new Proveedor();
    }

    public void saveNewProveedor() {
        if (this.proveedor != null) {
            new ProveedorDaoImpl().create(proveedor);
            this.proveedorItems.add(proveedor);

        }
    }

    public void onCellEdit(CellEditEvent event) {
//        try {
//
//            selected = actividadesimpuestosList.get(event.getRowIndex());
//            Object oldValue = event.getOldValue();
//            Object newValue = event.getNewValue();
//            if (addnew) {
//                actividadesimpuestosList.get(event.getRowIndex()).setValor(selected.getIdnivel6().getValor());
//            }
//            if (!validarValores(selected)) {
//                actividadesimpuestosList.get(event.getRowIndex()).setValor(selected.getIdnivel6().getValor());
//            }
//            addnew = false;
//
//        } catch (Exception e) {
//            JSFUtil.addErrorMessage("onCellEdit()" + e.getLocalizedMessage());
//        }

    }

    public void onItemSelect(SelectEvent<CuentaMayor> event) {
        CuentaMayor c = event.getObject();
        System.err.println("CUENTA SELECCIONADA:"+c.getCuentaMayorNombre());
        JsfUtil.addSuccessMessage("Cuenta :"+c.getCuentaMayorNombre());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta Selected", c.getCuentaMayorNombre()));
    }

    public List<CuentaMayor> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<CuentaMayor> cuentaList = new CuentaMayorDaoImpl().findALL();
        System.err.println("TAMAÑO DE CUENTA LIST: " + cuentaList.size() + " LLEGA POR AQUÍ ... ");
        return cuentaList.stream().filter(t -> t.getCuentaMayorNombre().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getDocumento() {
        return documento;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public VoucherMovimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(VoucherMovimiento movimiento) {
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

    public void setCuentaMayorContabilidad(CuentaMayor cuentaMayorContabilidad) {
        this.cuentaMayorContabilidad = cuentaMayorContabilidad;
    }

    public CuentaMayor getCuentaMayorContabilidad() {
        return cuentaMayorContabilidad;
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

    public List<CompraDTO> getItems() {
        return items;
    }

    public void setItems(List<CompraDTO> items) {
        this.items = items;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public List<CompraDTO> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<CompraDTO> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public NumberFormat getNf() {
        return nf;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

    public void setContabilizar(boolean contabilizar) {
        this.contabilizar = contabilizar;
    }

    public boolean getContabilizar() {
        return contabilizar;
    }

    public List<CuentaMayor> getCuentaMayorItemsContabilidad() {
        return cuentaMayorItemsContabilidad;
    }

    public void setCuentaMayorItemsContabilidad(List<CuentaMayor> cuentaMayorItemsContabilidad) {
        this.cuentaMayorItemsContabilidad = cuentaMayorItemsContabilidad;
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

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setMovimientoItems(List<VoucherMovimiento> movimientoItems) {
        this.movimientoItems = movimientoItems;
    }

    public List<VoucherMovimiento> getMovimientoItems() {
        return movimientoItems;
    }

}
