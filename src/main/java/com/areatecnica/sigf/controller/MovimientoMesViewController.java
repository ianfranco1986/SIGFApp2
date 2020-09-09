package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICuentaBancariaDaoImpl;
import com.areatecnica.sigf.dao.impl.IEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.IMovimientoMesDaoImpl;
import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.MovimientoMes;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.models.MovimientoMesDataModel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import org.joda.time.DateTime;
import org.primefaces.event.RowEditEvent;

@Named(value = "movimientoMesViewController")
@ViewScoped
public class MovimientoMesViewController extends AbstractController<MovimientoMes> {

    private List<Empresa> empresaItems;
    private List<CuentaBancaria> cuentaItems;
    private List<MovimientoMes> items;
    private Empresa empresa;
    private CuentaBancaria cuentaBancaria;
    private CartolaBanco cartolaBanco;
    private MovimientoMes movimientoDocumento;

    private MovimientoMesDataModel model;

    private int documento;
    private int mes;
    private int anio;
    private int totalAbonos = 0;
    private int totalDescuentos = 0;
    private Date fecha;
    private Date desde;
    private Date hasta;
    private Date fechaMovimiento;
    private Date fechaLiquidacion;
    private DateTime dateTime;

    private String informeCuenta = "inf-detalle_movimiento_cuenta";
    private String informeTipo = "inf-detalle_movimiento_tipo";
    private String informeEmpresa = "inf-detalle_movimiento_empresa";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private NumberFormat nf = NumberFormat.getInstance();

    public MovimientoMesViewController() {
        // Inform the Abstract parent controller of the concrete MovimientoMes Entity
        super(MovimientoMes.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.

        this.empresaItems = new IEmpresaDaoImpl().findByCuenta(this.getUserCount());


        this.cuentaItems = new ICuentaBancariaDaoImpl().findAll();

        Calendar cal = Calendar.getInstance();
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.anio = cal.get(Calendar.YEAR);
        this.fechaMovimiento = new Date();
        this.fechaLiquidacion = new Date();

        this.fecha = new Date();
        setFecha();
        this.desde = this.fecha;
        this.hasta = new Date();

        this.prepareCreate(null);
        this.getSelected().setMovimientoMesFechaMvto(new Date());
        this.getSelected().setMovimientoMesFechaLiquidacion(fecha);

//        load();
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
            this.items = new IMovimientoMesDaoImpl().findByDates(fecha, this.dateTime.dayOfMonth().withMaximumValue().toDate());
            this.model = new MovimientoMesDataModel(items);
            if (this.items.isEmpty()) {
                JsfUtil.addWarningMessage("No se han encontrado registros " + desde + " -- " + hasta);
            } else {
                JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

            }
        }
    }

    public void loadTipo() {
//        if (this.tipoMovimiento != null) {
//            setFecha();
//            if (this.desde != null && this.hasta != null) {
//                this.items = new IMovimientoMesDaoImpl().findByTipoAndDates(this.tipoMovimiento, desde, hasta);
//                this.model = new MovimientoMesDataModel(items);
//                getTotals();
//                if (this.items.isEmpty()) {
//                    JsfUtil.addWarningMessage("No se han encontrado registros ");
//                } else {
//                    JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");
//
//                }
//            }
//        }
    }

    public void loadEmpresa() {
        if (this.empresa != null) {
            setFecha();
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

    public Map<String, Object> getMapCuenta() {
        Map<String, Object> map = new HashMap();

        map.put("fechaLiquidacion", getFechaCompleta());
        map.put("fecha", desde);
        map.put("cuenta_id", this.cuentaBancaria.getCuentaBancariaId());
        map.put("nombreCuenta", this.cuentaBancaria.getCuentaBancariaNombreTitular() + " - " + this.cuentaBancaria.getCuentaBancariaNumero());

        return map;
    }

    public Map<String, Object> getMapTipo() {
        Map<String, Object> map = new HashMap();

        map.put("fechaLiquidacion", getFechaCompleta());
        map.put("fecha", desde);
//        map.put("tipo_id", this.tipoMovimiento.getTipoMovimientoId());
//        map.put("nombreMovimiento", this.tipoMovimiento.getTipoMovimientoNombre());

        return map;
    }

    public Map<String, Object> getMapEmpresa() {
        Map<String, Object> map = new HashMap();

        map.put("fechaLiquidacion", getFechaCompleta());
        map.put("fecha", desde);
        map.put("empresa_id", this.empresa.getEmpresaId());
        map.put("nombreEmpresa", this.empresa.getEmpresaNombre());

        return map;
    }

    public void getTotals() {
        this.totalAbonos = 0;
        this.totalDescuentos = 0;

        if (!this.items.isEmpty()) {
            for (MovimientoMes m : this.items) {
//                if (m.getMovimientoMesMvtoId().getTipoMovimientoAbono()) {
//                    this.totalAbonos = this.totalAbonos + m.getMovimientoMesMonto();
//                }
//
//                if (m.getMovimientoMesMvtoId().getTipoMovimientoDescuento()) {
//                    this.totalDescuentos = this.totalDescuentos + m.getMovimientoMesMonto();
//                }
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

    @Override
    public void saveNew(ActionEvent event) {
//        if (this.getSelected() != null) {
//            this.getSelected().setMovimientoMesMvtoId(tipoMovimiento);
//            this.getSelected().setMovimientoMesFechaLiquidacion(fechaLiquidacion);
//            this.getSelected().setMovimientoMesFechaMvto(fechaMovimiento);
//            this.getSelected().setMovimientoMesCuentaId(cuentaBancaria);
//            this.getSelected().setMovimientoMesDocumento(documento);
//
//            new IMovimientoMesDaoImpl().create(this.getSelected());
//
//            this.items.add(this.getSelected());
//
//            MovimientoMes _aux = this.getSelected();
//
//            this.setSelected(prepareCreate(event));
//            this.getSelected().setMovimientoMesDocumento(documento + 1);
//
//            JsfUtil.addSuccessMessage("Se ha registrado un movimiento");
//
//        } else {
//            JsfUtil.addErrorMessage("Ocurrió un error al guardar el registro");
//        }
    }

    public void handleMovimientoChange() {
//        if (this.tipoMovimiento != null) {
//            this.getSelected().setMovimientoMesMonto(this.tipoMovimiento.getTipoMovimientoMontoDefecto());
//            this.getSelected().setMovimientoMesDetalle(this.tipoMovimiento.getTipoMovimientoDescripcion());
//        }
    }

    public void handleCuentaChange() {
//        if (this.cuentaBancaria != null) {
//            this.movimientoDocumento = new IMovimientoMesDaoImpl().findLastByCuenta(this.cuentaBancaria);
//            if (this.movimientoDocumento == null) {
//                this.movimientoDocumento = new MovimientoMes();
//                this.movimientoDocumento.setMovimientoMesDocumento(1);
//            }
//
//            this.documento = this.movimientoDocumento.getMovimientoMesDocumento() + 1;
//        }
    }

    private String getFechaCompleta() {
        String fechaCompleta = "";
        switch (mes) {
            case 1:
                fechaCompleta = "Enero ";
                break;
            case 2:
                fechaCompleta = "Febrero ";
                break;
            case 3:
                fechaCompleta = "Marzo ";
                break;
            case 4:
                fechaCompleta = "Abril ";
                break;
            case 5:
                fechaCompleta = "Mayo ";
                break;
            case 6:
                fechaCompleta = "Junio ";
                break;
            case 7:
                fechaCompleta = "Julio ";
                break;
            case 8:
                fechaCompleta = "Agosto ";
                break;
            case 9:
                fechaCompleta = "Septiembre ";
                break;
            case 10:
                fechaCompleta = "Octubre ";
                break;
            case 11:
                fechaCompleta = "Noviembre ";
                break;
            case 12:
                fechaCompleta = "Diciembre ";
                break;

        }

        return fechaCompleta + " " + anio;
    }

    public String getInformeCuenta() {
        return informeCuenta;
    }

    public void setInformeCuenta(String informeCuenta) {
        this.informeCuenta = informeCuenta;
    }

    public String getInformeTipo() {
        return informeTipo;
    }

    public void setInformeTipo(String informeTipo) {
        this.informeTipo = informeTipo;
    }

    public String getInformeEmpresa() {
        return informeEmpresa;
    }

    public void setInformeEmpresa(String informeEmpresa) {
        this.informeEmpresa = informeEmpresa;
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

    @Override
    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {

            this.items.remove(this.getSelected());
            new IMovimientoMesDaoImpl().delete(this.getSelected());
            this.prepareCreate(event);
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un movimiento");
        }
    }

    public void setFecha() {
        try {
            System.err.println("NUEVA FECHA:");
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.desde = this.fecha;
            this.dateTime = new DateTime(this.fecha);
            this.hasta = this.dateTime.dayOfMonth().withMaximumValue().toDate();
        } catch (ParseException ex) {
        }
    }

}
