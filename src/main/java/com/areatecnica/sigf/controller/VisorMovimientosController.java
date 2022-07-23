/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.AbonoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.CargoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoAbonoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoCargoDaoImpl;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.util.LocalDateConverter;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "visorMovimientosController")
@ViewScoped
public class VisorMovimientosController implements Serializable {

    private boolean isCargo = Boolean.TRUE;
    private List<TipoCargo> tipoCargoItems;
    private List<TipoAbono> tipoAbonoItems;

    private List<MovimientosHelper> items;
    private List<TipoMovimientosHelper> tiposItems;
    private MovimientosHelper selected;
    private TipoMovimientosHelper tipoMovimientoHelper;

    private int totalItems = 0;
    private String tipoMovimiento;

    private NumberFormat nf = NumberFormat.getInstance();
    private LocalDate date;
    private LocalDateConverter dc;

    private String informe;

    /**
     * Creates a new instance of VisorMovimientosController
     */
    public VisorMovimientosController() {
        this.setDate(LocalDate.now());

        this.items = new ArrayList<>();
        this.tiposItems = new ArrayList<>();

        this.tipoCargoItems = new TipoCargoDaoImpl().findAll();

        this.tipoMovimiento = "0";
        loadMovimientos();

        this.informe = "inf-detalle-cargos";

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

    public void handleTipoChange() {
        this.items = new ArrayList<>();
        this.totalItems = 0;

        if (this.tipoMovimientoHelper != null) {
            if (this.tipoMovimiento.equals("0")) {
                List<CargoLiquidacion> aux = new CargoLiquidacionDaoImpl().findByTipoBetweenDates(this.tipoMovimientoHelper.tipoCargo, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

                for (CargoLiquidacion c : aux) {
                    this.totalItems += c.getCargoLiquidacionMonto();
                    this.items.add(new MovimientosHelper(c));
                }
            } else {
                List<AbonoLiquidacion> aux = new AbonoLiquidacionDaoImpl().findByTipoBetweenDates(this.tipoMovimientoHelper.tipoAbono, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

                for (AbonoLiquidacion a : aux) {
                    this.totalItems += a.getAbonoLiquidacionMonto();
                    this.items.add(new MovimientosHelper(a));
                }
            }
        }
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("desde", this.dc.getFirstDateOfMonth());
        map.put("hasta", this.dc.getLastDayOfMonth());
        map.put("fecha", this.dc.getMonthYearString());
        map.put("tipoAbono", (this.tipoMovimiento.equals("0") ? this.tipoMovimientoHelper.tipoCargo.getTipoCargoId() : this.tipoMovimientoHelper.tipoAbono.getTipoAbonoId()));

        return map;
    }

    public boolean isIsCargo() {
        return isCargo;
    }

    public void setIsCargo(boolean isCargo) {
        this.isCargo = isCargo;
    }

    public List<TipoCargo> getTipoCargoItems() {
        return tipoCargoItems;
    }

    public void setTipoCargoItems(List<TipoCargo> tipoCargoItems) {
        this.tipoCargoItems = tipoCargoItems;
    }

    public List<TipoAbono> getTipoAbonoItems() {
        return tipoAbonoItems;
    }

    public void setTipoAbonoItems(List<TipoAbono> tipoAbonoItems) {
        this.tipoAbonoItems = tipoAbonoItems;
    }

    public List<MovimientosHelper> getItems() {
        return items;
    }

    public void setItems(List<MovimientosHelper> items) {
        this.items = items;
    }

    public MovimientosHelper getSelected() {
        return selected;
    }

    public void setSelected(MovimientosHelper selected) {
        this.selected = selected;
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

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public NumberFormat getNf() {
        return nf;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDateConverter getDc() {
        return dc;
    }

    public void setDc(LocalDateConverter dc) {
        this.dc = dc;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getInforme() {
        if (this.tipoMovimiento.equals("0")) {
            this.informe = "inf-detalle_cargos";
        } else {
            this.informe = "inf-detalle_abonos";
        }
        return informe;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public class MovimientosHelper {

        private Integer id;
        private Empresa empresa;
        private AbonoLiquidacion abono;
        private CargoLiquidacion cargo;
        private String descripcion;
        private int monto;
        private boolean tipo;

        public MovimientosHelper() {
        }

        public MovimientosHelper(CargoLiquidacion c) {
            this.cargo = c;
            this.monto = c.getCargoLiquidacionMonto();
            this.descripcion = c.getCargoLiquidacionDescripcion();
            this.empresa = cargo.getCargoLiquidacionLiquidacionEmpresaId().getLiquidacionEmpresaIdEmpresa();

        }

        public MovimientosHelper(AbonoLiquidacion a) {
            this.abono = a;
            this.monto = a.getAbonoLiquidacionMonto();
            this.descripcion = a.getAbonoLiquidacionDescripcion();
            this.empresa = a.getAbonoLiquidacionLiquidacionEmpresaId().getLiquidacionEmpresaIdEmpresa();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Empresa getEmpresa() {
            return empresa;
        }

        public void setEmpresa(Empresa empresa) {
            this.empresa = empresa;
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

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setCargo(CargoLiquidacion cargo) {
            this.cargo = cargo;
        }

        public int getMonto() {
            return monto;
        }

        public void setMonto(int monto) {
            this.monto = monto;
        }

        public boolean isTipo() {
            return tipo;
        }

        public void setTipo(boolean tipo) {
            this.tipo = tipo;
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
