package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICuentaBancariaDaoImpl;
import com.areatecnica.sigf.dao.impl.IEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.IMovimientoMesDaoImpl;
import com.areatecnica.sigf.dao.impl.ITipoMovimientoDaoImpl;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.models.MovimientoMesDataModel;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named(value = "movimientoMesController")
@ViewScoped
public class MovimientoMesController extends AbstractController<MovimientoMes> {

    private List<Empresa> empresaItems;
    private List<CuentaBancaria> cuentaItems;
    private List<MovimientoMes> items;
    private List<TipoMovimiento> tipoMovimientoItems;
    private Empresa empresa;
    private CuentaBancaria cuentaBancaria;
    private CartolaBanco cartolaBanco;
    private MovimientoMes movimientoDocumento;
    private TipoMovimiento tipoMovimiento;

    private MovimientoMesDataModel model;

    private int documento;
    private int mes;
    private int anio;
    private int totalAbonos = 0;
    private int totalDescuentos = 0;
    private int tipo;
    private Date fecha;
    private Date desde;
    private Date hasta;
    private Date fechaMovimiento;
    private Date fechaLiquidacion;
//    private DateTime dateTime;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private final NumberFormat nf = NumberFormat.getInstance();

    public MovimientoMesController() {
        // Inform the Abstract parent controller of the concrete MovimientoMes Entity
        super(MovimientoMes.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.

        this.empresaItems = new IEmpresaDaoImpl().findByCuenta(this.getUserCount());

        this.tipoMovimientoItems = new ITipoMovimientoDaoImpl().findALL();
        //this.cuentaItems = new ICuentaBancariaDaoImpl().findAll();
        this.cuentaBancaria = new ICuentaBancariaDaoImpl().findById(8);

        Calendar cal = Calendar.getInstance();
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.anio = cal.get(Calendar.YEAR);
        this.fechaMovimiento = new Date();
        this.fechaLiquidacion = new Date();
        this.documento = 0; 
        this.fecha = new Date();
        setFecha();
        this.desde = this.fecha;
        this.hasta = new Date();

        this.prepareCreate(null);
        this.getSelected().setMovimientoMesFechaMvto(new Date());
        this.getSelected().setMovimientoMesFechaLiquidacion(fecha);

        //load();
    }

    public void loadDocumento() {
        if (this.documento > 0) {
            this.items = new IMovimientoMesDaoImpl().findByDocumento(documento);
            this.model = new MovimientoMesDataModel(items);
            if (this.items.isEmpty()) {
                JsfUtil.addWarningMessage("No se ha encontrado un documento con el número: " + documento);
            }
        }
    }

    public void load() {
        setFecha();
        if (this.fecha != null) {
            if (this.tipo == 1) {
                this.tipoMovimientoItems = new ITipoMovimientoDaoImpl().findByEgreso();
//                this.items = new IMovimientoMesDaoImpl().findByEgresosDates(fecha, this.dateTime.dayOfMonth().withMaximumValue().toDate());
                this.model = new MovimientoMesDataModel(items);
                if (this.items.isEmpty()) {
                    JsfUtil.addWarningMessage("No se han encontrado registros ");
                } else {
                    JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

                }
            } else {
                this.tipoMovimientoItems = new ITipoMovimientoDaoImpl().findByIngreso();

//                this.items = new IMovimientoMesDaoImpl().findByIngresosDates(fecha, this.dateTime.dayOfMonth().withMaximumValue().toDate());
                this.model = new MovimientoMesDataModel(items);
                if (this.items.isEmpty()) {
                    JsfUtil.addWarningMessage("No se han encontrado registros ");
                } else {
                    JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

                }
            }
        }
    }

    public void loadTipo() {
        if (this.tipoMovimiento != null) {
            setFecha();
            if (this.desde != null && this.hasta != null) {
                this.items = new IMovimientoMesDaoImpl().findByTipoAndDates(this.tipoMovimiento, desde, hasta);
                this.model = new MovimientoMesDataModel(items);
                getTotals();
                if (this.items.isEmpty()) {
                    JsfUtil.addWarningMessage("No se han encontrado registros ");
                } else {
                    JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

                }
            }
        }
    }

    public void loadEmpresa() {
        if (this.empresa != null) {

            if (this.desde != null && this.hasta != null) {
                this.items = new IMovimientoMesDaoImpl().findByEmpresaAndDates(this.empresa, desde, hasta);
                this.model = new MovimientoMesDataModel(items);
                getTotals();
                if (this.items.isEmpty()) {
                    JsfUtil.addWarningMessage("No se han encontrado registros ");
                } else {
                    JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

                }
            }
        }
    }

    public void loadCuenta() {
        if (this.cuentaBancaria != null) {
            setFecha();
            if (this.desde != null && this.hasta != null) {
                this.items = new IMovimientoMesDaoImpl().findByCuentaAndDates(this.cuentaBancaria, desde, hasta);
                this.model = new MovimientoMesDataModel(items);
                getTotals();
                if (this.items.isEmpty()) {
                    JsfUtil.addWarningMessage("No se han encontrado registros ");
                } else {
                    JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

                }
            }
        }
    }

    public void getTotals() {
        this.totalAbonos = 0;
        this.totalDescuentos = 0;

        if (!this.items.isEmpty()) {
            for (MovimientoMes m : this.items) {
                if (m.getMovimientoMesMvtoId().getTipoMovimientoAbono()) {
                    this.totalAbonos = this.totalAbonos + m.getMovimientoMesMonto();
                }

                if (m.getMovimientoMesMvtoId().getTipoMovimientoDescuento()) {
                    this.totalDescuentos = this.totalDescuentos + m.getMovimientoMesMonto();
                }
            }
        }
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void onRowEdit(RowEditEvent event) {
        MovimientoMes temp = (MovimientoMes) event.getObject();

        try {

            new IMovimientoMesDaoImpl().update(temp);
            JsfUtil.addSuccessMessage("Se ha actualizado el registro");
            getTotals();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ha ocurrido un error al guardar los cambios");
        }
    }

    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null) {
            this.getSelected().setMovimientoMesMvtoId(tipoMovimiento);
            this.getSelected().setMovimientoMesFechaLiquidacion(fechaLiquidacion);
            this.getSelected().setMovimientoMesFechaMvto(fechaMovimiento);
            this.getSelected().setMovimientoMesCuentaBancoId(cuentaBancaria);
            this.getSelected().setMovimientoMesNumeroDocumento(documento);
            this.getSelected().setMovimientoMesEmpresaId(empresa);
            new IMovimientoMesDaoImpl().create(this.getSelected());

            this.items.add(this.getSelected());

            MovimientoMes _aux = this.getSelected();

            this.setSelected(prepareCreate(event));
            this.getSelected().setMovimientoMesNumeroDocumento(documento + 1);
            this.documento = this.documento + 1;
            JsfUtil.addSuccessMessage("Se ha registrado un movimiento");

        } else {
            JsfUtil.addErrorMessage("Ocurrió un error al guardar el registro");
        }
    }

    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {

            this.items.remove(this.getSelected());
            new IMovimientoMesDaoImpl().delete(this.getSelected());
            this.prepareCreate(event);
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un movimiento");
        }
    }

    public void handleMovimientoChange() {
        if (this.tipoMovimiento != null) {
            this.getSelected().setMovimientoMesMonto(this.tipoMovimiento.getTipoMovimientoMontoDefecto());
            this.getSelected().setMovimientoMesDetalle(this.tipoMovimiento.getTipoMovimientoDescripcion());
        }
    }

    public void handleCuentaChange() {
        if (this.cuentaBancaria != null) {
            this.movimientoDocumento = new IMovimientoMesDaoImpl().findLastByCuenta(this.cuentaBancaria);
            if (this.movimientoDocumento == null) {
                this.movimientoDocumento = new MovimientoMes();
                this.movimientoDocumento.setMovimientoMesNumeroDocumento(1);
            }

            this.documento = this.movimientoDocumento.getMovimientoMesNumeroDocumento() + 1;
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public int getTotalDescuentos() {
        return totalDescuentos;
    }

    public int getTotalAbonos() {
        return totalAbonos;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getDocumento() {
        return documento;
    }

    public void setMovimientoDocumento(MovimientoMes movimientoDocumento) {
        this.movimientoDocumento = movimientoDocumento;
    }

    public MovimientoMes getMovimientoDocumento() {
        return movimientoDocumento;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getDesde() {
        return desde;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setModel(MovimientoMesDataModel model) {
        this.model = model;
    }

    public MovimientoMesDataModel getModel() {
        return model;
    }

    public void setItems(List<MovimientoMes> items) {
        this.items = items;
    }

    public List<MovimientoMes> getItems() {
        return items;
    }

    public List<Empresa> getEmpresaItems() {
        return empresaItems;
    }

    public void setEmpresaItems(List<Empresa> empresaItems) {
        this.empresaItems = empresaItems;
    }

    public List<CuentaBancaria> getCartolaItems() {
        return cuentaItems;
    }

    public void setCartolaItems(List<CuentaBancaria> cuentaItems) {
        this.cuentaItems = cuentaItems;
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

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public String formatDate(Date fecha) {
        return sdf.format(fecha);
    }

    public void setTipoMovimientoItems(List<TipoMovimiento> tipoMovimientoItems) {
        this.tipoMovimientoItems = tipoMovimientoItems;
    }

    public List<TipoMovimiento> getTipoMovimientoItems() {
        return tipoMovimientoItems;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getMes() {
        return mes;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
//        detalleCartolaCartolaBancoIdController.setSelected(null);
    }

    @Override
    public MovimientoMes prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.getSelected().setMovimientoMesFechaLiquidacion(fecha);
        this.getSelected().setMovimientoMesFechaMvto(fechaMovimiento);

        return this.getSelected();
    }

    public void setFecha() {
//        try {
//            System.err.println("NUEVA FECHA:");
//            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
//            this.dateTime = new DateTime(this.fecha);
//        } catch (ParseException ex) {
//        }
    }

}
