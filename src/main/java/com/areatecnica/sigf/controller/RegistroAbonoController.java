/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.AbonoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.BusDaoImpl;
import com.areatecnica.sigf.dao.impl.CargoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.EmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.LiquidacionEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.ProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoAbonoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoCargoDaoImpl;
import com.areatecnica.sigf.dao.impl.UnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.util.LocalDateConverter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    private int totalItems = 0;

    private List<String> resultsHeader;

    private boolean movimientoxBus = Boolean.FALSE;

    //Filtros
    private List<UnidadNegocio> unidadItemsFilter;
    private List<ProcesoRecaudacion> procesoRecaudacionItemsFilter;
    private List<Empresa> empresaItemsFilter;
    private List<Bus> busItemsFilter;
    private UnidadNegocio selectedUnidadItems;
    private List<ProcesoRecaudacion> selectedProcesoRecaudacionItems;
    private List<Empresa> selectedEmpresaItems;
    private List<Bus> selectedBusItems;
    private Map<Empresa, Map<ProcesoRecaudacion, Integer>> busesFilter;
    private int[] b;

    private String tipoMovimiento;
    private NumberFormat nf = NumberFormat.getInstance();

    /**
     * Creates a new instance of RegistroAbonoController
     */
    public RegistroAbonoController() {

    }

    @PostConstruct
    public void init() {
        this.setDate(LocalDate.now());
        this.busesFilter = new HashMap<>();
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

        resetFilters();

        this.resultsHeader = new ArrayList<>();

        for (ProcesoRecaudacion p : this.procesoRecaudacionItemsFilter) {
            this.resultsHeader.add(p.getProcesoRecaudacionNombre());
        }
    }

    public void resetFilters() {
        this.empresaItems = new EmpresaDaoImpl().findByNandu();
        this.unidadItemsFilter = new UnidadNegocioDaoImpl().findNandu();
        this.empresaItemsFilter = new ArrayList<>(empresaItems);
        this.procesoRecaudacionItemsFilter = new ProcesoRecaudacionDaoImpl().findByNandu();
        this.busItemsFilter = new BusDaoImpl().findAll();
        this.selectedUnidadItems = null;
        this.selectedBusItems = new ArrayList<>();
        this.selectedProcesoRecaudacionItems = new ArrayList<>();
        this.selectedEmpresaItems = new ArrayList<>();
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
        if (this.tipoMovimiento.equals("0")) {
            this.montoAbono = this.tipoMovimientoHelper.getTipoCargo().getTipoCargoMontoDefecto();
        } else {
            this.montoAbono = this.tipoMovimientoHelper.getTipoAbono().getTipoAbonoMontoDefecto();
        }
    }

    public void load() {
        if (this.tipoMovimientoHelper != null) {
            this.totalItems = 0;
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

                    MovimientosHelper mv = new MovimientosHelper(al);
                    mv.setBusProcesos(getBusesProcesos(e));

                    if (this.movimientoxBus) {
                        mv.monto = this.montoAbono * mv.totalBuses;
                    } else {
                        mv.monto = this.montoAbono;
                    }
                    totalItems += mv.monto;
                    this.items.add(mv);
                }

//                this.montoAbono = this.tipoMovimientoHelper.getTipoCargo().getTipoCargoMontoDefecto();
            } else {
                this.items = new ArrayList<>();
                for (Empresa e : this.empresaItems) {
                    AbonoLiquidacion al = new AbonoLiquidacionDaoImpl().findByEmpresaTipoBetweenDates(e, tipoMovimientoHelper.tipoAbono, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

                    if (al == null) {

                        LiquidacionEmpresa l = new LiquidacionEmpresaDaoImpl().findByEmpresaFechaLiquidacion(e, this.dc.getFirstDateOfMonth());

                        if (l == null) {
                            l = new LiquidacionEmpresa();
                            l.setLiquidacionEmpresaFechaLiquidacion(this.dc.getFirstDateOfMonth());
                            l.setLiquidacionEmpresaFechaPago(this.dc.getLastDayOfMonth());
                            l.setLiquidacionEmpresaIdEmpresa(e);
                        }

                        al = new AbonoLiquidacion(l);
                        al.setAbonoLiquidacionTipoId(tipoMovimientoHelper.tipoAbono);
                        al.setAbonoLiquidacionMonto(tipoMovimientoHelper.tipoAbono.getTipoAbonoMontoDefecto());
                    }

                    MovimientosHelper mv = new MovimientosHelper(al);
                    mv.setBusProcesos(getBusesProcesos(e));

                    if (this.movimientoxBus) {
                        mv.monto = this.montoAbono * mv.totalBuses;
                    } else {
                        mv.monto = this.montoAbono;
                    }

                    totalItems += mv.monto;
                    this.items.add(mv);
                }

//                this.montoAbono = this.tipoMovimientoHelper.getTipoAbono().getTipoAbonoMontoDefecto();
            }
        }
    }

    public LinkedHashMap getBusesProcesos(Empresa empresa) {
        LinkedHashMap<ProcesoRecaudacion, Long> l = new LinkedHashMap();
        for (ProcesoRecaudacion p : this.procesoRecaudacionItemsFilter) {
            l.put(p, new BusDaoImpl().getBusesByEmpresaProceso(empresa, p));
        }
        return l;
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

            System.err.println("inicio TOTAL: " + this.totalItems);

            this.totalItems = this.totalItems - (int) oldValue;
            System.err.println("actualizado TOTAL: " + this.totalItems);

            this.totalItems = this.totalItems + (int) newValue;

            System.err.println("NUEVO TOTAL: " + this.totalItems);
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
//                this.montoAbono = 0;
//                this.descripcionAbono = "";
//                this.tipoMovimientoHelper = new TipoMovimientosHelper();
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
//                this.montoAbono = 0;
//                this.descripcionAbono = "";
//                this.tipoMovimientoHelper = new TipoMovimientosHelper();
                JsfUtil.addSuccessMessage("Se han registrado los cambios");

            }
        }
        
        this.totalItems = 0; 
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

    public void movimientoxBus() {
        if (!this.items.isEmpty()) {
            if (this.movimientoxBus) {
                for (MovimientosHelper m : this.items) {
                    m.setMonto(m.totalBuses * this.montoAbono);
                }
            } else {
                for (MovimientosHelper m : this.items) {
                    m.setMonto(this.montoAbono);
                }
            }
        }
    }

    public void loadFiltroUnidad() {
        if (this.selectedUnidadItems != null) {
            this.selectedEmpresaItems = new ArrayList<>();
            this.selectedProcesoRecaudacionItems = new ArrayList<>();
            this.selectedBusItems = new ArrayList<>();
            this.busItemsFilter = getBuses(selectedUnidadItems);
            this.procesoRecaudacionItemsFilter = getProcesos(busItemsFilter);
            this.empresaItemsFilter = getEmpresas(busItemsFilter);
            this.empresaItems = this.empresaItemsFilter;
        } else {
            resetFilters();
        }

    }

    public void loadFiltroProceso() {
        if (!this.selectedProcesoRecaudacionItems.isEmpty()) {
            this.selectedEmpresaItems = new ArrayList<>();
            this.selectedBusItems = new ArrayList<>();
            this.busItemsFilter = new ArrayList<>();
            for (ProcesoRecaudacion p : this.selectedProcesoRecaudacionItems) {

                this.busItemsFilter.addAll(new BusDaoImpl().findByProceso(p));
            }
            this.empresaItemsFilter = getEmpresas(busItemsFilter);
            this.empresaItems = this.empresaItemsFilter;

        } else {
            loadFiltroUnidad();
        }
    }

    public void loadFiltroEmpresas() {
        if (!this.selectedEmpresaItems.isEmpty()) {
            this.selectedBusItems = new ArrayList<>();
            this.busItemsFilter = new ArrayList<>();
            this.empresaItems = this.selectedEmpresaItems;
            for (Empresa e : this.selectedEmpresaItems) {
                if (this.selectedProcesoRecaudacionItems.isEmpty()) {
                    this.busItemsFilter.addAll(new BusDaoImpl().findByEmpresa(e));
                } else {
                    for (ProcesoRecaudacion p : this.selectedProcesoRecaudacionItems) {
                        this.busItemsFilter.addAll(new BusDaoImpl().findByEmpresaProceso(e, p));
                    }
                }
            }
        } else {
            if (!this.selectedProcesoRecaudacionItems.isEmpty()) {
                loadFiltroProceso();
            } else {
                loadFiltroUnidad();
            }
        }
    }

    public List<Bus> getBuses(UnidadNegocio unidad) {
        return new BusDaoImpl().findByUnidad(unidad);
    }

    public List<Bus> getBuses(ProcesoRecaudacion proceso) {
        return new BusDaoImpl().findByProceso(proceso);
    }

    public List<ProcesoRecaudacion> getProcesos(List<Bus> buses) {
        Map<Integer, ProcesoRecaudacion> p = new HashMap();
        for (Bus b : buses) {
            if (b.getBusIdEmpresa().getEmpresaId() > 1) {
                p.put(b.getBusIdProcesoRecaudacion().getProcesoRecaudacionId(), b.getBusIdProcesoRecaudacion());
            }
        }
        return new ArrayList<>(p.values());
    }

    public List<Empresa> getEmpresas(List<Bus> buses) {
        Map<Integer, Empresa> e = new HashMap();
        for (Bus b : buses) {
            e.put(b.getBusIdEmpresa().getEmpresaId(), b.getBusIdEmpresa());
        }

        e.remove(1);

        List<Empresa> aux = new ArrayList<>(e.values());
        aux.sort(Comparator.comparing(Empresa::getEmpresaNombre));
        return aux;
    }

//    public void loadFiltroUnidad() {
//        if (this.selectedUnidadItems != null) {
//
//            this.items = new ArrayList<>();
//            this.empresaItems = new ArrayList<>();
//
//            Map empresas = new HashMap();
//
//            this.busItemsFilter = new BusDaoImpl().findByUnidad(selectedUnidadItems);
//
//            for (Bus b : this.busItemsFilter) {
//                if (b.getBusIdEmpresa().getEmpresaActiva() && b.getBusActivo()) {
//                    empresas.put(b.getBusIdEmpresa().getEmpresaId(), b.getBusIdEmpresa());
//                }
//            }
//
//            this.empresaItems = new ArrayList<>(empresas.values());
//
//            empresaItems.sort(Comparator.comparing(Empresa::getEmpresaNombre));
//            this.empresaItemsFilter = empresaItems;
//            load();
//
//        } else {
//            JsfUtil.addErrorMessage("Sin filtro de Unidad");
//            init();
//        }
//    }
//    public void loadFiltroProceso() {
//        if (!this.selectedProcesoRecaudacionItems.isEmpty()) {
//            List<Bus> aux = new ArrayList<>();
//            for (ProcesoRecaudacion p : this.selectedProcesoRecaudacionItems) {
//                JsfUtil.addErrorMessage("Seleccionado algún proceso: " + this.selectedProcesoRecaudacionItems);
//                aux.addAll(p.getBusList());
//            }
//
//            Map empresas = new HashMap();
//
//            this.busItemsFilter = aux;
//
//            for (Bus b : this.busItemsFilter) {
//                if (b.getBusIdEmpresa().getEmpresaActiva() && b.getBusActivo()) {
//                    empresas.put(b.getBusIdEmpresa().getEmpresaId(), b.getBusIdEmpresa());
//                }
//            }
//
//            this.empresaItems = new ArrayList<>(empresas.values());
//
//            empresaItems.sort(Comparator.comparing(Empresa::getEmpresaNombre));
//            this.empresaItemsFilter = empresaItems;
//            load();
//        } else {
//            init();
//        }
//    }
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

    public List<String> getResultsHeader() {
        return resultsHeader;
    }

    public void setResultsHeader(List<String> resultsHeader) {
        this.resultsHeader = resultsHeader;
    }

    public boolean isMovimientoxBus() {
        return movimientoxBus;
    }

    public void setMovimientoxBus(boolean movimientoxBus) {
        this.movimientoxBus = movimientoxBus;
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

    public List<TipoAbono> getTipoAbonoItems() {
        return tipoAbonoItems;
    }

    public void setTipoAbonoItems(List<TipoAbono> tipoAbonoItems) {
        this.tipoAbonoItems = tipoAbonoItems;
    }

    public List<TipoCargo> getTipoCargoItems() {
        return tipoCargoItems;
    }

    public void setTipoCargoItems(List<TipoCargo> tipoCargoItems) {
        this.tipoCargoItems = tipoCargoItems;
    }

    public List<UnidadNegocio> getUnidadItemsFilter() {
        return unidadItemsFilter;
    }

    public void setUnidadItemsFilter(List<UnidadNegocio> unidadItemsFilter) {
        this.unidadItemsFilter = unidadItemsFilter;
    }

    public List<ProcesoRecaudacion> getProcesoRecaudacionItemsFilter() {
        return procesoRecaudacionItemsFilter;
    }

    public void setProcesoRecaudacionItemsFilter(List<ProcesoRecaudacion> procesoRecaudacionItemsFilter) {
        this.procesoRecaudacionItemsFilter = procesoRecaudacionItemsFilter;
    }

    public List<Empresa> getEmpresaItemsFilter() {
        return empresaItemsFilter;
    }

    public void setEmpresaItemsFilter(List<Empresa> empresaItemsFilter) {
        this.empresaItemsFilter = empresaItemsFilter;
    }

    public List<Bus> getBusItemsFilter() {
        return busItemsFilter;
    }

    public void setBusItemsFilter(List<Bus> busItemsFilter) {
        this.busItemsFilter = busItemsFilter;
    }

    public UnidadNegocio getSelectedUnidadItems() {
        return selectedUnidadItems;
    }

    public void setSelectedUnidadItems(UnidadNegocio selectedUnidadItems) {
        this.selectedUnidadItems = selectedUnidadItems;
    }

    public List<ProcesoRecaudacion> getSelectedProcesoRecaudacionItems() {
        return selectedProcesoRecaudacionItems;
    }

    public void setSelectedProcesoRecaudacionItems(List<ProcesoRecaudacion> selectedProcesoRecaudacionItems) {
        this.selectedProcesoRecaudacionItems = selectedProcesoRecaudacionItems;
    }

    public List<Empresa> getSelectedEmpresaItems() {
        return selectedEmpresaItems;
    }

    public void setSelectedEmpresaItems(List<Empresa> selectedEmpresaItems) {
        this.selectedEmpresaItems = selectedEmpresaItems;
    }

    public List<Bus> getSelectedBusItems() {
        return selectedBusItems;
    }

    public void setSelectedBusItems(List<Bus> selectedBusItems) {
        this.selectedBusItems = selectedBusItems;
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

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
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
        private boolean solymar;
        private boolean fenur;
        private LinkedHashMap<ProcesoRecaudacion, Long> busProcesos;
        private int totalBuses = 0;

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

        public void load() {
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

        public void setTotalBuses(int totalBuses) {
            this.totalBuses = totalBuses;
        }

        public int getTotalBuses() {
            return totalBuses;
        }

        public void setBusProcesos(LinkedHashMap<ProcesoRecaudacion, Long> busProcesos) {
            this.busProcesos = busProcesos;

            for (Long i : busProcesos.values()) {
                this.totalBuses += i;
            }
        }

        public LinkedHashMap getBusProcesos() {
            return busProcesos;
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
