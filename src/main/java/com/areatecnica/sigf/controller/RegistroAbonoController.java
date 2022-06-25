/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.AbonoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.CargoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.EmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.LiquidacionEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoAbonoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoCargoDaoImpl;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.util.LocalDateConverter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "registroAbonoController")
@ViewScoped
public class RegistroAbonoController implements Serializable {

    private LiquidacionEmpresa liquidacion;
    private AbonoLiquidacion abonoLiquidacion;

    private List<MovimientosHelper> items;
    private MovimientosHelper selected;
    private List<TipoMovimientosHelper> tiposItems;
    private List<Empresa> empresaItems;
    private List<TipoAbono> tipoAbonoItems;
    private List<TipoCargo> tipoCargoItems;
    private TipoMovimientosHelper tipoMovimientoHelper;
    private String descripcionAbono;
    private int montoAbono;
    private LocalDate date;
    private LocalDateConverter dc;
    private int numeroCuota;
    private int totalCuotas;
    private boolean cuotas;

    private String tipoMovimiento;

    /**
     * Creates a new instance of RegistroAbonoController
     */
    public RegistroAbonoController() {

    }

    @PostConstruct
    public void init() {
        this.setDate(LocalDate.now());
        this.empresaItems = new EmpresaDaoImpl().findByNandu();

        this.items = new ArrayList<>();
        this.tiposItems = new ArrayList<>();

        this.tipoCargoItems = new TipoCargoDaoImpl().findAll();
        for (TipoCargo c : this.tipoCargoItems) {
            TipoMovimientosHelper t = new TipoMovimientosHelper(c);
            this.tiposItems.add(t);
        }

        this.tipoMovimiento = "0";

        this.descripcionAbono = "";
        this.montoAbono = 0;
        this.cuotas = Boolean.FALSE;
    }

    public void loadMovimientos() {
        this.items = new ArrayList<>();
        this.tiposItems = new ArrayList<>();

        if (this.tipoMovimiento.equals("0")) {
            this.tipoCargoItems = new TipoCargoDaoImpl().findAll();
            for (TipoCargo c : this.tipoCargoItems) {
                TipoMovimientosHelper t = new TipoMovimientosHelper(c);
                this.tiposItems.add(t);
            }
        } else {
            this.tipoAbonoItems = new TipoAbonoDaoImpl().findAll();
            for (TipoAbono a : this.tipoAbonoItems) {
                TipoMovimientosHelper t = new TipoMovimientosHelper(a);
                this.tiposItems.add(t);
            }
        }

    }

    public void load() {

        if (this.tipoMovimientoHelper != null) {
            if (this.tipoMovimiento.equals("0")) {
                this.items = new ArrayList<>();
                for (Empresa e : this.empresaItems) {
                    CargoLiquidacion al = new CargoLiquidacionDaoImpl().findByEmpresaTipoBetweenDates(e, tipoMovimientoHelper.tipoCargo, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

                    if (al == null) {
                        LiquidacionEmpresa l = new LiquidacionEmpresaDaoImpl().findByEmpresaFechaLiquidacion(e, this.dc.getFirstDateOfMonth());

                        if (l == null) {
                            l = new LiquidacionEmpresa();
                            l.setLiquidacionEmpresaFechaLiquidacion(this.dc.getFirstDateOfMonth());
                            l.setLiquidacionEmpresaFechaPago(this.dc.getLastDayOfMonth());
                            l.setLiquidacionEmpresaIdEmpresa(e);
                        }

                        al = new CargoLiquidacion(l);
                        al.setCargoLiquidacionCargoId(tipoMovimientoHelper.tipoCargo);
                        al.setCargoLiquidacionMonto(tipoMovimientoHelper.tipoCargo.getTipoCargoMontoDefecto());
                    }

                    this.items.add(new MovimientosHelper(al));
                }

                this.montoAbono = this.tipoMovimientoHelper.getTipoCargo().getTipoCargoMontoDefecto();

            } else {
                this.items = new ArrayList<>();
                for (Empresa e : this.empresaItems) {
                    AbonoLiquidacion al = new AbonoLiquidacionDaoImpl().findByEmpresaTipoBetweenDates(e, tipoMovimientoHelper.tipoAbono, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

                    if (al == null) {
                        LiquidacionEmpresa l = new LiquidacionEmpresa();
                        l.setLiquidacionEmpresaFechaLiquidacion(this.dc.getFirstDateOfMonth());
                        l.setLiquidacionEmpresaFechaPago(this.dc.getLastDayOfMonth());
                        l.setLiquidacionEmpresaIdEmpresa(e);

                        al = new AbonoLiquidacion(l);
                        al.setAbonoLiquidacionTipoId(tipoMovimientoHelper.tipoAbono);
                        al.setAbonoLiquidacionMonto(tipoMovimientoHelper.tipoAbono.getTipoAbonoMontoDefecto());
                    }

                    this.items.add(new MovimientosHelper(al));
                }

                this.montoAbono = this.tipoMovimientoHelper.getTipoAbono().getTipoAbonoMontoDefecto();
            }
        }

    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            int aux = (Integer) newValue;
            if (this.tipoMovimiento.equals("0")) {
                this.items.get(event.getRowIndex()).getCargo().setCargoLiquidacionMonto(aux);
            } else {
                this.items.get(event.getRowIndex()).getAbono().setAbonoLiquidacionMonto(aux);
            }

//            this.abonoLiquidacion = this.items.get(event.getRowIndex());
//            AbonoLiquidacion c = new AbonoLiquidacionDaoImpl().update(this.selectedAbono);
//
//            if (c != null) {
//                JsfUtil.addSuccessMessage("Se editó el valor del abono #" + c.getAbonoLiquidacionId());
//                this.totalAbonos = this.totalAbonos - (Integer) oldValue;
//                this.totalAbonos += c.getAbonoLiquidacionMonto();
//                setSaldo();
//            }
        }
    }

    public void save() {
        if (!this.items.isEmpty()) {
            if (this.tipoMovimiento.equals("0")) {//cargos

                for (MovimientosHelper h : this.items) {

                    if (h.monto > 0) {
                        CargoLiquidacion c = new CargoLiquidacionDaoImpl().update(h.getCargo());
                        if (c == null) {
                            JsfUtil.addWarningMessage("Ha ocurrido un error al registrar los cambios");
                            return;
                        }
                    } else {
                        new CargoLiquidacionDaoImpl().delete(h.getCargo());
                    }
                }

                this.items = new ArrayList<>();
                this.montoAbono = 0;
                this.descripcionAbono = "";
                this.tipoMovimientoHelper = new TipoMovimientosHelper();
                JsfUtil.addSuccessMessage("Se han registrado los cambios");

            } else {//abonos
                for (MovimientosHelper h : this.items) {

                    if (h.monto > 0) {
                        AbonoLiquidacion a = new AbonoLiquidacionDaoImpl().update(h.getAbono());
                        if (a == null) {
                            JsfUtil.addWarningMessage("Ha ocurrido un error al registrar los cambios");
                            return;
                        }
                    } else {
                        new AbonoLiquidacionDaoImpl().delete(h.getAbono());
                    }
                }

                this.items = new ArrayList<>();
                this.montoAbono = 0;
                this.descripcionAbono = "";
                this.tipoMovimientoHelper = new TipoMovimientosHelper();
                JsfUtil.addSuccessMessage("Se han registrado los cambios");

            }
        }
    }

    public void emptyDescripcion() {
        if (!this.items.isEmpty()) {
            if (this.tipoMovimiento.equals("0")) {
                for (MovimientosHelper a : this.items) {
                    a.getCargo().setCargoLiquidacionDescripcion("");
                }
            } else {
                for (MovimientosHelper a : this.items) {
                    a.getAbono().setAbonoLiquidacionDescripcion("");
                }
            }
        }
    }

    public void setValorDescripcion() {
        if (!this.items.isEmpty()) {
            if (this.tipoMovimiento.equals("0")) {
                for (MovimientosHelper a : this.items) {
                    a.setDescripcion(descripcionAbono);
                    a.getCargo().setCargoLiquidacionDescripcion(this.descripcionAbono);
                }
            } else {
                for (MovimientosHelper a : this.items) {
                    a.setDescripcion(descripcionAbono);
                    a.getAbono().setAbonoLiquidacionDescripcion(this.descripcionAbono);
                }
            }
        }
    }

    public void setValorMonto() {
        if (!this.items.isEmpty()) {
            if (this.tipoMovimiento.equals("0")) {
                for (MovimientosHelper a : this.items) {
                    a.setMonto(montoAbono);
                    a.getCargo().setCargoLiquidacionMonto(this.montoAbono);
                }
            } else {
                for (MovimientosHelper a : this.items) {
                    a.setMonto(montoAbono);
                    a.getAbono().setAbonoLiquidacionMonto(this.montoAbono);
                }
            }
        }
    }

    public MovimientosHelper getSelected() {
        return selected;
    }

    public void setSelected(MovimientosHelper selected) {
        this.selected = selected;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public LiquidacionEmpresa getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(LiquidacionEmpresa liquidacion) {
        this.liquidacion = liquidacion;
    }

    public AbonoLiquidacion getAbonoLiquidacion() {
        return abonoLiquidacion;
    }

    public void setAbonoLiquidacion(AbonoLiquidacion abonoLiquidacion) {
        this.abonoLiquidacion = abonoLiquidacion;
    }

    public List<TipoMovimientosHelper> getTiposItems() {
        return tiposItems;
    }

    public void setTiposItems(List<TipoMovimientosHelper> tiposItems) {
        this.tiposItems = tiposItems;
    }

    public TipoMovimientosHelper getTipoMovimientoHelper() {
        return tipoMovimientoHelper;
    }

    public void setTipoMovimientoHelper(TipoMovimientosHelper tipoMovimientoHelper) {
        this.tipoMovimientoHelper = tipoMovimientoHelper;
    }

    public List<MovimientosHelper> getItems() {
        return items;
    }

    public void setItems(List<MovimientosHelper> items) {
        this.items = items;
    }

    public List<Empresa> getEmpresaItems() {
        return empresaItems;
    }

    public void setEmpresaItems(List<Empresa> empresaItems) {
        this.empresaItems = empresaItems;
    }

    public String getDescripcionAbono() {
        return descripcionAbono;
    }

    public void setDescripcionAbono(String descripcionAbono) {
        this.descripcionAbono = descripcionAbono;
    }

    public int getMontoAbono() {
        return montoAbono;
    }

    public void setMontoAbono(int montoAbono) {
        this.montoAbono = montoAbono;
    }

    public LocalDateConverter getDc() {
        return dc;
    }

    public void setDc(LocalDateConverter dc) {
        this.dc = dc;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setTotalCuotas(int totalCuotas) {
        this.totalCuotas = totalCuotas;
    }

    public int getTotalCuotas() {
        return totalCuotas;
    }

    public boolean getCuotas() {
        return this.cuotas;
    }

    public void setCuotas(boolean cuotas) {
        this.cuotas = cuotas;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public class MovimientosHelper {

        private Integer id;
        private Empresa empresa;
        private AbonoLiquidacion abono;
        private CargoLiquidacion cargo;
        private int monto;
        private boolean tipo;
        private String nombre;
        private String descripcion;

        public MovimientosHelper() {
        }

        public MovimientosHelper(int id, AbonoLiquidacion abono, CargoLiquidacion cargo) {
            this.id = id;
            this.abono = abono;
            this.cargo = cargo;
        }

        public MovimientosHelper(AbonoLiquidacion abono) {
            this.id = abono.getAbonoLiquidacionId();
            this.abono = abono;
            this.empresa = abono.getAbonoLiquidacionLiquidacionEmpresaId().getLiquidacionEmpresaIdEmpresa();
            this.tipo = Boolean.TRUE;
            this.nombre = abono.getAbonoLiquidacionTipoId().getTipoAbonoNombre();
            this.descripcion = abono.getAbonoLiquidacionDescripcion();
            this.monto = abono.getAbonoLiquidacionMonto();
        }

        public MovimientosHelper(CargoLiquidacion cargo) {
            this.id = cargo.getCargoLiquidacionId();
            this.cargo = cargo;
            this.empresa = cargo.getCargoLiquidacionLiquidacionEmpresaId().getLiquidacionEmpresaIdEmpresa();
            this.tipo = Boolean.FALSE;
            this.nombre = cargo.getCargoLiquidacionCargoId().getTipoCargoNombre();
            this.descripcion = cargo.getCargoLiquidacionDescripcion();
            this.monto = cargo.getCargoLiquidacionMonto();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public AbonoLiquidacion getAbono() {
            return abono;
        }

        public void setAbono(AbonoLiquidacion abono) {
            this.abono = abono;
        }

        public CargoLiquidacion getCargo() {
            return cargo;
        }

        public void setCargo(CargoLiquidacion cargo) {
            this.cargo = cargo;
        }

        public void setMonto(int monto) {
            this.monto = monto;
        }

        public int getMonto() {
            return monto;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        public boolean getTipo() {
            return this.tipo;
        }

        public void setTipo(boolean tipo) {
            this.tipo = tipo;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setEmpresa(Empresa empresa) {
            this.empresa = empresa;
        }

        public Empresa getEmpresa() {
            return empresa;
        }

    }

    public class TipoMovimientosHelper {

        private int id;
        private TipoAbono tipoAbono;
        private TipoCargo tipoCargo;
        private String tipoNombre;
        private boolean tipo;

        public TipoMovimientosHelper(int id, TipoAbono tipoAbono, TipoCargo tipoCargo, String tipoNombre, boolean tipo) {
            this.id = id;
            this.tipoAbono = tipoAbono;
            this.tipoCargo = tipoCargo;
            this.tipoNombre = tipoNombre;
            this.tipo = tipo;
        }

        public TipoMovimientosHelper(TipoAbono tipoAbono) {
            this.tipoAbono = tipoAbono;
            this.tipoNombre = tipoAbono.getTipoAbonoNombre();
            this.tipo = Boolean.TRUE;
        }

        public TipoMovimientosHelper(TipoCargo tipoCargo) {
            this.tipoCargo = tipoCargo;
            this.tipoNombre = tipoCargo.getTipoCargoNombre();
            this.tipo = Boolean.FALSE;
        }

        public TipoMovimientosHelper() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public TipoAbono getTipoAbono() {
            return tipoAbono;
        }

        public void setTipoAbono(TipoAbono tipoAbono) {
            this.tipoAbono = tipoAbono;
        }

        public TipoCargo getTipoCargo() {
            return tipoCargo;
        }

        public void setTipoCargo(TipoCargo tipoCargo) {
            this.tipoCargo = tipoCargo;
        }

        public String getTipoNombre() {
            return tipoNombre;
        }

        public void setTipoNombre(String tipoNombre) {
            this.tipoNombre = tipoNombre;
        }

        public boolean isTipo() {
            return tipo;
        }

        public void setTipo(boolean tipo) {
            this.tipo = tipo;
        }

    }

}
