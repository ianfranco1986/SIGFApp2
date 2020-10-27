package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICuentaBancariaDaoImpl;
import com.areatecnica.sigf.dao.impl.IEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.IMovimientoMesDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.ITipoMovimientoDaoImpl;
import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.MovimientoMes;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.TipoMovimiento;
import com.areatecnica.sigf.models.MovimientoMesDataModel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@Named(value = "movimientoMesEmpresaController")
@ViewScoped
public class MovimientoMesEmpresaController extends AbstractController<MovimientoMes> {

    private List<Empresa> empresaItems;
    private List<Empresa> selectedEmpresa;
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
    private int saldo = 0;
    private int tipo;
    private int numeroCuotas = 0;

    private int administracion = 0;
    private int cuotaExtra = 0;
    private int boletos = 0;
    private int minutos = 0;
    private int imposiciones = 0;

    private int totalIngresos = 0;

    private Date fecha;
    private Date desde;
    private Date hasta;
    private Date fechaMovimiento;
    private Date fechaLiquidacion;
    private DateTime dateTime;

    private String informe = "inf-liquidacion_empresa_grupal";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private NumberFormat nf = NumberFormat.getInstance();

    public MovimientoMesEmpresaController() {
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

            this.administracion = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 1);
            this.cuotaExtra = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 2);
            this.imposiciones = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 3);
            this.boletos = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 4);

            int auxMinutos = new IRecaudacionMinutoDaoImpl().findMinutosRecibidos(this.empresa, this.desde, this.hasta);

            this.minutos = (auxMinutos > 0 ? (auxMinutos - (auxMinutos / 5000 * 550)) : 0);

            this.totalIngresos = this.administracion + this.cuotaExtra + this.imposiciones + this.boletos + this.minutos;

            this.items = new IMovimientoMesDaoImpl().findByEmpresaAndDates(this.empresa, fecha, this.dateTime.dayOfMonth().withMaximumValue().toDate());

            this.model = new MovimientoMesDataModel(items);
            if (this.items.isEmpty()) {
                JsfUtil.addWarningMessage("No se han encontrado movimientos ");
            } else {
                JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");
            }

            getTotals();

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

        this.saldo = (this.totalIngresos + this.totalAbonos) - this.totalDescuentos;

    }

    public void next() {
        if (this.empresa != null) {
            int idx = this.empresaItems.indexOf(this.empresa);

            if ((idx + 1) != this.empresaItems.size()) {
                this.empresa = this.empresaItems.get(idx + 1);

                setFecha();
                if (this.fecha != null) {

                    this.administracion = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 1);
                    this.cuotaExtra = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 2);
                    this.imposiciones = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 3);
                    this.boletos = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 4);

                    int auxMinutos = new IRecaudacionMinutoDaoImpl().findMinutosRecibidos(this.empresa, this.desde, this.hasta);

                    this.minutos = (auxMinutos > 0 ? (auxMinutos - (auxMinutos / 5000 * 550)) : 0);

                    this.totalIngresos = this.administracion + this.cuotaExtra + this.imposiciones + this.boletos + this.minutos;

                    this.items = new IMovimientoMesDaoImpl().findByEmpresaAndDates(this.empresa, fecha, this.dateTime.dayOfMonth().withMaximumValue().toDate());

                    this.model = new MovimientoMesDataModel(items);
                    if (this.items.isEmpty()) {
                        JsfUtil.addWarningMessage("No se han encontrado movimientos ");
                    } else {
                        JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");
                    }

                    getTotals();

                }
            } else {
                JsfUtil.addErrorMessage("No existen más empresas a la derecha");
            }
        } else {
            JsfUtil.addErrorMessage("No ha seleccionado ninguna empresa");
        }
    }

    public void previous() {
        if (this.empresa != null) {
            int idx = this.empresaItems.indexOf(this.empresa);

            if ((idx) != 0) {
                this.empresa = this.empresaItems.get(idx - 1);

                setFecha();
                if (this.fecha != null) {

                    this.administracion = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 1);
                    this.cuotaExtra = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 2);
                    this.imposiciones = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 3);
                    this.boletos = new IRecaudacionGuiaDaoImpl().findByEgreso(this.desde, this.hasta, this.empresa, 4);

                    int auxMinutos = new IRecaudacionMinutoDaoImpl().findMinutosRecibidos(this.empresa, this.desde, this.hasta);

                    this.minutos = (auxMinutos > 0 ? (auxMinutos - (auxMinutos / 5000 * 550)) : 0);

                    this.totalIngresos = this.administracion + this.cuotaExtra + this.imposiciones + this.boletos + this.minutos;

                    this.items = new IMovimientoMesDaoImpl().findByEmpresaAndDates(this.empresa, fecha, this.dateTime.dayOfMonth().withMaximumValue().toDate());

                    this.model = new MovimientoMesDataModel(items);
                    if (this.items.isEmpty()) {
                        JsfUtil.addWarningMessage("No se han encontrado movimientos ");
                    } else {
                        JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");
                    }

                    getTotals();

                }
            } else {
                JsfUtil.addErrorMessage("No existen más empresas a la izquierda");
            }

        } else {
            JsfUtil.addErrorMessage("No ha seleccionado ninguna empresa");
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
            if (this.numeroCuotas != 0) {
                if (!this.selectedEmpresa.isEmpty()) {
                    for (Empresa e : this.selectedEmpresa) {

                        DateTime fechaL = new DateTime(this.fechaLiquidacion);
                        DateTime fechaM = new DateTime(this.fechaMovimiento);

                        MovimientoMes movEmpresa = new MovimientoMes();

                        for (int i = 0; i < this.numeroCuotas; i++) {

                            movEmpresa.setMovimientoMesMvtoId(tipoMovimiento);
                            movEmpresa.setMovimientoMesFechaLiquidacion(fechaL.plusMonths(i).toDate());
                            movEmpresa.setMovimientoMesFechaMvto(fechaM.plusMonths(i).toDate());
                            movEmpresa.setMovimientoMesCuentaBancoId(cuentaBancaria);
                            movEmpresa.setMovimientoMesNumeroDocumento(documento);
                            movEmpresa.setMovimientoMesMonto(this.getSelected().getMovimientoMesMonto());
                            movEmpresa.setMovimientoMesDetalle(tipoMovimiento.getTipoMovimientoNombre() + " (cuota " + (i + 1) + " de " + this.numeroCuotas + ")");
                            movEmpresa.setMovimientoMesEmpresaId(e);

                            new IMovimientoMesDaoImpl().create(movEmpresa);

                            this.items.add(movEmpresa);

                            movEmpresa = new MovimientoMes();

                            //this.setSelected(prepareCreate(event));
                            //this.getSelected().setMovimientoMesNumeroDocumento(documento + 1);
                            //this.documento = this.documento + 1;
                            //JsfUtil.addSuccessMessage("Se ha registrado un movimiento");
                        }
                    }

                    JsfUtil.addSuccessMessage("Se han ingresado los registros");
                    this.setSelected(prepareCreate(null));
                    this.setSelectedEmpresa(new ArrayList<>());
                    this.tipoMovimiento = null;
                    this.numeroCuotas = 0;

                } else {
                    JsfUtil.addErrorMessage("Debe seleccionar al menos una empresa");
                }

            } else {
                JsfUtil.addErrorMessage("Debe ingresar un número de cuotas mayor a 0");
            }

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

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        if (this.empresa != null) {
            String list = "";

            list = String.valueOf(empresa.getEmpresaId());

            map.put("fechaCompleta", getFechaCompleta());
            map.put("desde", desde);
            map.put("hasta", hasta);
            map.put("list", list);
        }

        return map;
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

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setSelectedEmpresa(List<Empresa> selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }

    public List<Empresa> getSelectedEmpresa() {
        return selectedEmpresa;
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

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setBoletos(int boletos) {
        this.boletos = boletos;
    }

    public void setImposiciones(int imposiciones) {
        this.imposiciones = imposiciones;
    }

    public void setCuotaExtra(int cuotaExtra) {
        this.cuotaExtra = cuotaExtra;
    }

    public void setAdministracion(int administracion) {
        this.administracion = administracion;
    }

    public void setTotalIngresos(int totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public int getTotalIngresos() {
        return totalIngresos;
    }

    public int getAdministracion() {
        return administracion;
    }

    public int getCuotaExtra() {
        return cuotaExtra;
    }

    public int getBoletos() {
        return boletos;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getImposiciones() {
        return imposiciones;
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
