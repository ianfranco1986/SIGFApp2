/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.IEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.ILiquidacionEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.IMovimientoMesDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionMinutoDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.MovimientoMes;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.models.RecaudacionLiquidacionLoteDataModel;
import javax.inject.Named;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import org.joda.time.DateTime;

/**
 *
 * @author ianfr
 */
@Named(value = "liquidacionEmpresaLoteController")
@ViewScoped
public class LiquidacionEmpresaLoteController implements Serializable {

    private Empresa empresa;
    private List<Empresa> listEmpresa;
    private List<Bus> listBus;
    private List<RecaudacionGuia> listRecaudacion;
    private List<RecaudacionMinuto> listMinutos;
    private List<LiquidacionHelper> items;
    private List<MovimientoMes> movimientosItems;
    private LiquidacionEmpresa liquidacionEmpresa;
    private RecaudacionLiquidacionLoteDataModel model;

    private String informe = "inf-liquidacion_empresa_grupal";

    private Date desde;
    private Date hasta;
    private Date fecha;
    private DateTime dateTime;
    private int mes;
    private int anio;
    private int totalAdministracion = 0;
    private int totalCuotaExtra = 0;
    private int totalBoletos = 0;
    private int totalImposiciones = 0;
    private int totalRecaudacion = 0;
    private int totalMinutos = 0;
    private int totalAbonos = 0;
    private int totalCargos = 0;

    private boolean generarLiquidacionNoMovimiento;
//    private boolean 

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private NumberFormat nf = NumberFormat.getInstance();

    /**
     * Creates a new instance of LiquidacionEmpresaLoteController
     */
    public LiquidacionEmpresaLoteController() {
    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.dateTime = new DateTime(fecha);
        DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();

        Calendar calendar = Calendar.getInstance();

        this.anio = calendar.get(Calendar.YEAR);
        this.mes = calendar.get(Calendar.MONTH) + 1;

        this.informe = "inf-liquidacion_empresa_grupal";
        setFecha();
        System.err.println("primera fecha: " + this.fecha);
        this.desde = this.fecha;
        this.hasta = _maxDate.toDate();
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        String list = "";

        if (generarLiquidacionNoMovimiento) {
            list = String.valueOf(this.items.get(0).empresa.getEmpresaId());

            for (LiquidacionHelper l : this.items.subList(0, this.items.size())) {
                list = list + "," + l.empresa.getEmpresaId();
            }
        } else {
            List<LiquidacionHelper> copy = new ArrayList<>();

            for (LiquidacionHelper l : this.items) {
                if (l.saldo != 0) {
                    copy.add(l);
                }
            }

            list = String.valueOf(copy.get(0).empresa.getEmpresaId());

            for (LiquidacionHelper l : copy.subList(0, copy.size())) {
                list = list + "," + l.empresa.getEmpresaId();
            }
        }

        System.err.println("LISTA:" + list);

        map.put("fechaCompleta", getFechaCompleta());
        map.put("desde", desde);
        map.put("hasta", hasta);
        map.put("list", list);

        return map;
    }

    public Map<String, Object> getSingleMap() {
        Map<String, Object> map = new HashMap();

        String list = "";

        list = String.valueOf(empresa.getEmpresaId());

        map.put("fechaCompleta", getFechaCompleta());
        map.put("desde", desde);
        map.put("hasta", hasta);
        map.put("list", list);

        return map;
    }

    public void load() {
        this.items = new ArrayList<>();
        setFecha();
        this.listEmpresa = new IEmpresaDaoImpl().findByNandu();
        this.items = new ArrayList<LiquidacionHelper>();

        this.totalAdministracion = 0;
        this.totalCuotaExtra = 0;
        this.totalBoletos = 0;
        this.totalImposiciones = 0;
        this.totalRecaudacion = 0;
        this.totalMinutos = 0;
        this.totalAbonos = 0;
        this.totalCargos = 0;

        for (Empresa e : this.listEmpresa) {
            LiquidacionHelper h = new LiquidacionHelper(e, desde, hasta);

            liquidacionEmpresa = new ILiquidacionEmpresaDaoImpl().findByEmpresaBetweenDate(empresa, desde, hasta);
            h.setLiquidacionEmpresa(liquidacionEmpresa);

            this.totalAdministracion = this.totalAdministracion + h.getTotalAdministracion();
            this.totalBoletos = this.totalBoletos + h.getTotalBoletos();
            this.totalCuotaExtra = this.totalCuotaExtra + h.getTotalCuotaExtra();
            this.totalMinutos = this.totalMinutos + h.getTotalMinutos();
            this.totalImposiciones = this.totalImposiciones + h.getTotalImposiciones();
            this.totalRecaudacion = this.totalRecaudacion + h.getTotal();
            this.totalAbonos = this.totalAbonos + h.getAbonosVarios();
            this.totalCargos = this.totalCargos + h.getCargosVarios();

            items.add(h);
        }

        this.model = new RecaudacionLiquidacionLoteDataModel(items);
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

    public void save() {
        if (!this.items.isEmpty()) {
            for (LiquidacionHelper l : this.items) {
                if (l.liquidacionEmpresa == null) {
//                    AbonoLiquidacion abonoLiquidacion = new AbonoLiquidacion();
//
//                    for (AbonoBus a : l.abonoBusItems) {
//                        //abonoLiquidacion
//                    }
//
//                    LiquidacionEmpresa liquidacion = new LiquidacionEmpresa();
//
//                    liquidacion.setLiquidacionEmpresaFechaLiquidacion(desde);
//                    liquidacion.setLiquidacionEmpresaFechaPago(hasta);
//                    liquidacion.setLiquidacionEmpresaIdEmpresa(l.empresa);
//                    liquidacion.setLiquidacionEmpresaSaldo(l.saldo);
//                    liquidacion.setLiquidacionEmpresaTotalAbonos(totalAbonos);
//                    liquidacion.setLiquidacionEmpresaTotalCargos(totalCargos);
//
//                    ILiquidacionEmpresaDaoImpl daoImpl = new ILiquidacionEmpresaDaoImpl();
//                    daoImpl.create(l.liquidacionEmpresa);

                }
            }
        }
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getInforme() {
        return informe;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<Empresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public void setGenerarLiquidacionNoMovimiento(boolean generarLiquidacionNoMovimiento) {
        this.generarLiquidacionNoMovimiento = generarLiquidacionNoMovimiento;
    }

    public boolean isGenerarLiquidacionNoMovimiento() {
        return generarLiquidacionNoMovimiento;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public int getTotalAdministracion() {
        return totalAdministracion;
    }

    public void setTotalAdministracion(int totalAdministracion) {
        this.totalAdministracion = totalAdministracion;
    }

    public int getTotalCuotaExtra() {
        return totalCuotaExtra;
    }

    public void setTotalCuotaExtra(int totalCuotaExtra) {
        this.totalCuotaExtra = totalCuotaExtra;
    }

    public int getTotalBoletos() {
        return totalBoletos;
    }

    public void setTotalBoletos(int totalBoletos) {
        this.totalBoletos = totalBoletos;
    }

    public int getTotalImposiciones() {
        return totalImposiciones;
    }

    public void setTotalImposiciones(int totalImposiciones) {
        this.totalImposiciones = totalImposiciones;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getTotalMinutos() {
        return totalMinutos;
    }

    public void setTotalMinutos(int totalMinutos) {
        this.totalMinutos = totalMinutos;
    }

    public int getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalAbonos(int totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public int getTotalCargos() {
        return totalCargos;
    }

    public void setTotalCargos(int totalCargos) {
        this.totalCargos = totalCargos;
    }

    public RecaudacionLiquidacionLoteDataModel getModel() {
        return model;
    }

    public void setModel(RecaudacionLiquidacionLoteDataModel model) {
        this.model = model;
    }

    public NumberFormat getNf() {
        return nf;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

    public void setFecha() {
        try {

            this.fecha = this.sdf.parse(this.anio + "/" + this.mes + "/01");

            this.dateTime = new DateTime(fecha);
            DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();
            this.desde = this.fecha;
            this.hasta = _maxDate.toDate();

        } catch (ParseException ex) {

        }
    }

    public String getFormatValue(int val) {
        return nf.format(val);

    }

    public class LiquidacionHelper {

        private Integer id;
        private Empresa empresa;
        private List<RecaudacionGuia> recaudacionItems;
        private List<RecaudacionMinuto> recaudacionMinutoItems;
//        private List<CargoBus> cargoBusItems;
//        private List<AbonoBus> abonoBusItems;
        private List<MovimientoMes> movimientos;
        private LinkedHashMap hashMap;

        private int liquidaciones;
        private int saldo;
        private LiquidacionEmpresa liquidacionEmpresa;

        private Date from;
        private Date to;

        private int totalAdministracion = 0;
        private int administracion = 0;
        private int totalCuotaExtra = 0;
        private int cuotaExtra = 0;
        private int totalBoletos = 0;
        private int boletos = 0;
        private int totalImposiciones = 0;
        private int imposiciones = 0;
        private int totalMinutos = 0;
        private int minutos = 0;
        private int abonosVarios = 0;
        private int totalAbonos = 0;
        private int cargosVarios = 0;
        private int totalCargos = 0;
        private int total = 0;
        private int totalFinal = 0;

        private int buses;

        public LiquidacionHelper() {
        }

        public LiquidacionHelper(Empresa empresa, Date from, Date to) {
            this.id = empresa.getEmpresaId();
            this.empresa = empresa;
            this.from = from;
            this.to = to;
            System.err.println("Desde:" + from);
            System.err.println("Hasta:" + to);
            load();
        }

        private void load() {
            hashMap = new LinkedHashMap();

            LinkedHashMap hashAbonos = new LinkedHashMap();
            LinkedHashMap hashDescuentos = new LinkedHashMap();

            int i = 0;
            //corregir
//            for (Bus b : this.empresa.getBusList()) {
//                i++;
//                //System.err.println("Empresa:" + empresa.getEmpresaNombre() + " N° Bus:" + b.getBusNumero());
//                this.recaudacionItems = new IRecaudacionGuiaDaoImpl().findByBusBetweenFechaRecaudacion(b, from, to);
//                System.err.println("Tamaño de Guías:" + this.recaudacionItems.size() + " DESDE:" + from + " HASTA:" + to);
//                if (!this.recaudacionItems.isEmpty()) {
//                    System.err.println("Tamaño de Guías:" + this.recaudacionItems.size());
//                    for (RecaudacionGuia r : this.recaudacionItems) {
//
//                        switch (r.getRecaudacionGuiaIdEgreso().getEgresoId()) {
//                            case 1:
//                                this.administracion = this.administracion + r.getRecaudacionGuiaMonto();
//                                break;
//                            case 2:
//                                this.cuotaExtra = this.cuotaExtra + r.getRecaudacionGuiaMonto();
//                                break;
//                            case 3:
//                                this.imposiciones = this.imposiciones + r.getRecaudacionGuiaMonto();
//                                break;
//                            case 4:
//                                int aux = r.getRecaudacionGuiaMonto();
//
//                                int cantidad = aux / 5000;
//                                if (cantidad > 0) {
//                                    aux = aux - (cantidad * 500);
//
//                                    this.boletos = this.boletos + aux;
//                                }
//
//                                break;
//                        }
//
//                    }
//
//                }
//                
////                hashMap.put("Empresario", empresa.getEmpresaNombre());
////                hashMap.put("Admin.", administracion);
////                hashMap.put("C.Extra", cuotaExtra);
////                hashMap.put("Boletos", boletos);
////                hashMap.put("Impos.", imposiciones);
////                hashMap.put("", saldo)
//
//                this.recaudacionMinutoItems = new IRecaudacionMinutoDaoImpl().findRecibidosBusAndDate(b, from, to);
//                System.err.println("Tamaño de Minutos:" + this.recaudacionMinutoItems.size());
//                if (!this.recaudacionMinutoItems.isEmpty()) {
//                    for (RecaudacionMinuto rrm : this.recaudacionMinutoItems) {
//                        this.minutos = this.minutos + rrm.getRecaudacionMinutoMonto();
//                    }
//                }
//
//                this.totalAdministracion = this.totalAdministracion + this.administracion;
//                this.totalMinutos = this.totalMinutos + this.minutos;
//                this.totalCuotaExtra = this.totalCuotaExtra + this.cuotaExtra;
//                this.totalBoletos = this.totalBoletos + this.boletos;
//                this.totalImposiciones = this.totalImposiciones + this.imposiciones;
//                //
//
//                this.administracion = 0;
//                this.cuotaExtra = 0;
//                this.imposiciones = 0;
//                this.boletos = 0;
//                this.minutos = 0;
//
////                this.cargoBusItems = new CargoBusDaoImpl().findByBusBetweenDates(b, from, to);
////
////                for (CargoBus c : this.cargoBusItems) {
////                    this.cargosVarios = this.cargosVarios + c.getCargoBusMontoFijo();
////                }
////
////                this.abonoBusItems = new AbonoBusDaoImpl().findByBusBetweenDates(b, from, to);
////
////                for (AbonoBus c : this.abonoBusItems) {
////                    this.abonosVarios = this.abonosVarios + c.getAbonoBusMontoFijo();
////                }
//
//            }

            this.movimientos = new IMovimientoMesDaoImpl().findByEmpresaAndDates(empresa, from, to);

            if (!this.movimientos.isEmpty()) {
//                for (MovimientoMes m : this.movimientos) {
//                    if (m.getMovimientoMesMvtoId().getTipoMovimientoAbono()) {
//                        this.abonosVarios = this.abonosVarios + m.getMovimientoMesMonto();
//
//                        hashAbonos.put(m.getMovimientoMesMvtoId().getTipoMovimientoId(), m.getMovimientoMesMonto());
//
//                    }
//
//                    if (m.getMovimientoMesMvtoId().getTipoMovimientoDescuento()) {
//                        this.cargosVarios = this.cargosVarios + m.getMovimientoMesMonto();
//
//                        hashDescuentos.put(m.getMovimientoMesMvtoId().getTipoMovimientoId(), m.getMovimientoMesMonto());
//
//                    }
//
//                }

            }

            hashMap.putAll(hashAbonos);
            hashMap.putAll(hashDescuentos);

            this.buses = i;
//                
            this.totalCargos = totalCargos + cargosVarios;
            this.totalAbonos = totalAbonos + abonosVarios;

            this.total = this.totalAdministracion + this.totalBoletos + this.totalCuotaExtra + this.totalImposiciones + this.totalMinutos;
            this.saldo = (this.total + this.totalAbonos) - this.totalCargos;
        }

//        public List<CargoBus> getCargoBusItems() {
//            return cargoBusItems;
//        }
//
//        public void setCargoBusItems(List<CargoBus> cargoBusItems) {
//            this.cargoBusItems = cargoBusItems;
//        }
//
//        public List<AbonoBus> getAbonoBusItems() {
//            return abonoBusItems;
//        }
//
//        public void setAbonoBusItems(List<AbonoBus> abonoBusItems) {
//            this.abonoBusItems = abonoBusItems;
//        }

        public LinkedHashMap getHashMap() {
            return hashMap;
        }

        public void setHashMap(LinkedHashMap hashMap) {
            this.hashMap = hashMap;
        }

        public void setEmpresa(Empresa empresa) {
            this.empresa = empresa;
        }

        public Empresa getEmpresa() {
            return empresa;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public Integer getAdministracion() {
            return administracion;
        }

        public void setAdministracion(int administracion) {
            this.administracion = administracion;
        }

        public int getCuotaExtra() {
            return cuotaExtra;
        }

        public void setCuotaExtra(int cuotaExtra) {
            this.cuotaExtra = cuotaExtra;
        }

        public int getBoletos() {
            return boletos;
        }

        public void setBoletos(int boletos) {
            this.boletos = boletos;
        }

        public int getImposiciones() {
            return imposiciones;
        }

        public void setImposiciones(int imposiciones) {
            this.imposiciones = imposiciones;
        }

        public void setAbonosVarios(int abonosVarios) {
            this.abonosVarios = abonosVarios;
        }

        public int getAbonosVarios() {
            return abonosVarios;
        }

        public void setMinutos(int minutos) {
            this.minutos = minutos;
        }

        public int getMinutos() {
            return minutos;
        }

        public void setCargosVarios(int cargosVarios) {
            this.cargosVarios = cargosVarios;
        }

        public int getCargosVarios() {
            return cargosVarios;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

        public int getTotalAdministracion() {
            return totalAdministracion;
        }

        public void setTotalAdministracion(int totalAdministracion) {
            this.totalAdministracion = totalAdministracion;
        }

        public int getTotalCuotaExtra() {
            return totalCuotaExtra;
        }

        public void setTotalCuotaExtra(int totalCuotaExtra) {
            this.totalCuotaExtra = totalCuotaExtra;
        }

        public int getTotalBoletos() {
            return totalBoletos;
        }

        public void setTotalBoletos(int totalBoletos) {
            this.totalBoletos = totalBoletos;
        }

        public int getTotalImposiciones() {
            return totalImposiciones;
        }

        public void setTotalImposiciones(int totalImposiciones) {
            this.totalImposiciones = totalImposiciones;
        }

        public int getTotalMinutos() {
            return totalMinutos;
        }

        public void setTotalMinutos(int totalMinutos) {
            this.totalMinutos = totalMinutos;
        }

        public void setBuses(int buses) {
            this.buses = buses;
        }

        public int getBuses() {
            return buses;
        }

        public LiquidacionEmpresa getLiquidacionEmpresa() {
            return liquidacionEmpresa;
        }

        public void setLiquidacionEmpresa(LiquidacionEmpresa liquidacionEmpresa) {
            this.liquidacionEmpresa = liquidacionEmpresa;
        }

        public int getLiquidaciones() {
            return liquidaciones;
        }

        public void setLiquidaciones(int liquidaciones) {
            this.liquidaciones = liquidaciones;
        }

    }
}
