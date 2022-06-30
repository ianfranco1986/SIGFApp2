/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.AbonoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.CargoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.EmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.LiquidacionEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.RecaudacionGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.RecaudacionMinutoDaoImpl;
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
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "crostabLiquidaciones")
@ViewScoped
public class CrostabLiquidaciones implements Serializable {

    private List<Empresa> empresaItems;
    private List<TipoAbono> tipoAbonoItems;
    private List<TipoCargo> tipoCargoItems;
    private Map<Integer, Long> mapsAbonos;
    private Map<Integer, Long> mapsCargos;
    private List<TipoAbono> selectedTipoAbonoItems;
    private List<TipoCargo> selectedTipoCargoItems;
    private List<LinkedHashMap> selectedItems;
    private List<LinkedHashMap> listOfMaps;
    private List<String> resultsHeader;// = service.getResultsValues(...);
    private List<Integer> resultsTotals;
    private LinkedHashMap<String, Integer> totales;
    private LinkedHashMap<String, Integer> totales2;
    private LinkedHashMap<String, Integer> finales;

    private Boolean flagAbonos = Boolean.TRUE;
    private Boolean flagCargos = Boolean.TRUE;

    private LinkedHashMap defaultTotal;
    private LinkedHashMap selectedHashMap;

    private LocalDate date;
    private LocalDateConverter dc;
    private NumberFormat nf = NumberFormat.getInstance();

    int totalAdministracion = 0;
    int totalCuotaExtra = 0;
    int totalImposiciones = 0;
    int totalBoletos = 0;
    int totalMinutos = 0;
    int totalRecaudacion = 0;
    int totalAbonos = 0;
    int totalCargos = 0;
    int saldo = 0;

    /**
     * Creates a new instance of CrostabLiquidaciones
     */
    public CrostabLiquidaciones() {
        this.setDate(LocalDate.now());

        this.tipoAbonoItems = new TipoAbonoDaoImpl().findAll();
        this.tipoCargoItems = new TipoCargoDaoImpl().findAll();
        this.selectedTipoAbonoItems = new ArrayList<>();
        this.selectedTipoCargoItems = new ArrayList<>();
        this.mapsAbonos = new HashMap<>();
        this.mapsCargos = new HashMap<>();
        loadMovimientos();
    }

    @PostConstruct
    public void init() {
        this.empresaItems = new EmpresaDaoImpl().findByNandu();
    }

    public void loadMovimientos() {
        this.selectedTipoAbonoItems = new ArrayList<>();
        this.selectedTipoCargoItems = new ArrayList<>();
        if (this.date != null) {
            for (TipoAbono a : this.tipoAbonoItems) {
                Long aux = new AbonoLiquidacionDaoImpl().countByTipoAbono(a, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());
                this.mapsAbonos.put(a.getTipoAbonoId(), aux);
                if (aux > 0) {
                    this.selectedTipoAbonoItems.add(a);
                }
            }

            for (TipoCargo c : this.tipoCargoItems) {
                Long aux = new CargoLiquidacionDaoImpl().countByTipoCargo(c, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());
                this.mapsCargos.put(c.getTipoCargoId(), aux);
                if (aux > 0) {
                    this.selectedTipoCargoItems.add(c);
                }
            }
        }
    }

    public void load() {
        if (this.date != null) {
            createDynamicsColumns();
            createDynamicsRow();
        }
    }

    public void createDynamicsColumns() {
        resultsHeader = new ArrayList<>();

        defaultTotal = new LinkedHashMap();

        resultsHeader.add("Empresa");//0
        defaultTotal.put("Empresa", 0);

        resultsHeader.add("Admin.");//1
        defaultTotal.put("Admin.", 0);

        resultsHeader.add("C.Extra");//2
        defaultTotal.put("C.Extra", 0);

        resultsHeader.add("Impos.");//3
        defaultTotal.put("Impos.", 0);

        resultsHeader.add("Dev. Boletos");//4
        defaultTotal.put("Dev. Boletos", 0);

        resultsHeader.add("Min. Recibidos");//5
        defaultTotal.put("Min. Recibidos", 0);

        resultsHeader.add("Total Rec.");//6
        defaultTotal.put("Total Rec.", 0);

        for (TipoAbono a : (this.selectedTipoAbonoItems.isEmpty() ? this.tipoAbonoItems : this.selectedTipoAbonoItems)) {
            resultsHeader.add(a.getTipoAbonoNombre());
            defaultTotal.put(a.getTipoAbonoNombre(), 0);
        }

        resultsHeader.add("Total Abonos");//6
        defaultTotal.put("Total Abonos", 0);

        //minutos + diferencia admin. + saldos aportes 
        //    7              8                9
//
        for (TipoCargo c : (this.selectedTipoCargoItems.isEmpty() ? this.tipoCargoItems : this.selectedTipoCargoItems)) {
            resultsHeader.add(c.getTipoCargoNombre());
            defaultTotal.put(c.getTipoCargoNombre(), 0);
        }

        resultsHeader.add("Total Cargos");//6
        defaultTotal.put("Total Cargos", 0);

    }

    public void setResultTotals() {
        this.resultsTotals = new ArrayList<>();
        resultsTotals.add(0);
        resultsTotals.add(0);
        resultsTotals.add(0);
        resultsTotals.add(0);
        resultsTotals.add(0);
        resultsTotals.add(0);
        resultsTotals.add(0);
        resultsTotals.add(0);
    }

    public void createDynamicsRow() {
        //Inicio de array de listas ordenadas que contendrá c/u de las filas 
        this.listOfMaps = new ArrayList<LinkedHashMap>();
        HashMap header = new HashMap();
        this.totales = new LinkedHashMap();
        this.totales2 = new LinkedHashMap();
        setResultTotals();

        for (Empresa e : this.empresaItems) {

            LinkedHashMap hashMap = new LinkedHashMap(defaultTotal);

            int administracion = new RecaudacionGuiaDaoImpl().findByEgreso(e, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth(), 1);
            int cuotaExtra = new RecaudacionGuiaDaoImpl().findByEgreso(e, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth(), 2);
            int boletos = new RecaudacionGuiaDaoImpl().findByEgreso(e, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth(), 4);
            int imposiciones = new RecaudacionGuiaDaoImpl().findByEgreso(e, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth(), 3);
            int minutos = new RecaudacionMinutoDaoImpl().findMinutosRecibidos(e, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

            //Cálculo ahorro boletos
            if (boletos > 0) {
                int cantidad = boletos / 5000;
                int costoBoleto = cantidad * 800;
                boletos = boletos - costoBoleto;
            }

            int recaudacion = administracion + cuotaExtra + imposiciones + boletos + minutos;

            hashMap.put("Empresa", e.getEmpresaNombre());
            hashMap.put("Admin.", administracion);
            hashMap.put("C.Extra", cuotaExtra);
            hashMap.put("Impos.", imposiciones);
            hashMap.put("Dev. Boletos", boletos);
            hashMap.put("Min. Recibidos", minutos);
            hashMap.put("Total Rec.", recaudacion);

            this.totalAdministracion += administracion;
            this.totalCuotaExtra += cuotaExtra;
            this.totalImposiciones += imposiciones;
            this.totalBoletos += boletos;
            this.totalMinutos += minutos;
            this.totalRecaudacion += recaudacion;

            LiquidacionEmpresa le = new LiquidacionEmpresaDaoImpl().findByEmpresaFechaLiquidacion(e, this.dc.getFirstDateOfMonth());

            int totalColumna = 0;

            if (le != null) {
                if (!le.getAbonoLiquidacionList().isEmpty()) {
                    for (AbonoLiquidacion l : le.getAbonoLiquidacionList()) {
                        String name = l.getAbonoLiquidacionTipoId().getTipoAbonoNombre();
                        int key = l.getAbonoLiquidacionTipoId().getTipoAbonoId();

                        if (!this.selectedTipoAbonoItems.isEmpty()) {
                            if (this.selectedTipoAbonoItems.contains(l.getAbonoLiquidacionTipoId())) {

                                hashMap.put(name, l.getAbonoLiquidacionMonto());

                                if (totales.containsKey(name)) {
                                    int aux = totales.get(name);
                                    aux += l.getAbonoLiquidacionMonto();
                                    totales.put(name, aux);
                                } else {
                                    totales.put(name, l.getAbonoLiquidacionMonto());
                                }

                                totalColumna += l.getAbonoLiquidacionMonto();
                                hashMap.put("Total Abonos", totalColumna);
                                this.totalAbonos += totalColumna;
                            }
                        } else {
                            hashMap.put(name, l.getAbonoLiquidacionMonto());

                            if (totales.containsKey(name)) {
                                int aux = totales.get(name);
                                aux += l.getAbonoLiquidacionMonto();
                                totales.put(name, aux);
                            } else {
                                totales.put(name, l.getAbonoLiquidacionMonto());
                            }

                            totalColumna += l.getAbonoLiquidacionMonto();
                            hashMap.put("Total Abonos", totalColumna);
                            this.totalAbonos += totalColumna;
                        }
                    }
                } else {
                    for (TipoAbono a : (this.selectedTipoAbonoItems.isEmpty() ? this.tipoAbonoItems : this.selectedTipoAbonoItems)) {
                        hashMap.put(a.getTipoAbonoNombre(), 0);
                        totales.put(a.getTipoAbonoNombre(), 0);
                    }
                    hashMap.put("Total Abonos", 0);
                }

                totalColumna = 0;

                if (!le.getCargoLiquidacionList().isEmpty()) {
                    for (CargoLiquidacion l : le.getCargoLiquidacionList()) {
                        String name = l.getCargoLiquidacionCargoId().getTipoCargoNombre();
                        int key = l.getCargoLiquidacionCargoId().getTipoCargoId();

                        if (!this.selectedTipoCargoItems.isEmpty()) {
                            if (this.selectedTipoCargoItems.contains(l.getCargoLiquidacionCargoId())) {

                                hashMap.put(name, l.getCargoLiquidacionMonto());

                                if (totales2.containsKey(name)) {
                                    int aux = totales2.get(name);
                                    aux += l.getCargoLiquidacionMonto();
                                    totales2.put(name, aux);
                                } else {
                                    totales2.put(name, l.getCargoLiquidacionMonto());
                                }

                                totalColumna += l.getCargoLiquidacionMonto();
                                hashMap.put("Total Cargos", totalColumna);
                                this.totalCargos += totalColumna;
                            }
                        } else {
                            hashMap.put(name, l.getCargoLiquidacionMonto());

                            if (totales2.containsKey(name)) {
                                int aux = totales2.get(name);
                                aux += l.getCargoLiquidacionMonto();
                                totales2.put(name, aux);
                            } else {
                                totales2.put(name, l.getCargoLiquidacionMonto());
                            }

                            totalColumna += l.getCargoLiquidacionMonto();
                            hashMap.put("Total Cargos", totalColumna);
                            this.totalCargos += totalColumna;

                        }

                    }
                } else {
                    for (TipoCargo a : (this.selectedTipoCargoItems.isEmpty() ? this.tipoCargoItems : this.selectedTipoCargoItems)) {
                        hashMap.put(a.getTipoCargoNombre(), 0);
                        totales2.put(a.getTipoCargoNombre(), 0);
                    }
                    hashMap.put("Total Cargos", 0);
                }
            }

            listOfMaps.add(hashMap);
        }

        this.finales = new LinkedHashMap<>();
        this.finales.put("Admin.", totalAdministracion);
        this.finales.put("C.Extra", totalCuotaExtra);
        this.finales.put("Impos.", totalImposiciones);
        this.finales.put("Dev. Boletos", totalBoletos);
        this.finales.put("Min. Recibidos", totalMinutos);
        this.finales.put("Total Rec.", totalRecaudacion);
        this.finales.put("Total Abonos", totalAbonos);
        this.finales.put("Total Cargos", totalCargos);
        this.finales.put("Saldo", saldo);

        this.finales.putAll(totales);
        this.finales.putAll(totales2);

        for (Object s : defaultTotal.keySet()) {
            String key = (String) s;
            if (finales.containsKey(key)) {
                this.defaultTotal.put(key, this.finales.get(key));
            } else {
                this.defaultTotal.put(key, "0");
            }

        }

        this.resultsTotals = new ArrayList<>(defaultTotal.values());

    }

    public List<Empresa> getEmpresaItems() {
        return empresaItems;
    }

    public void setEmpresaItems(List<Empresa> empresaItems) {
        this.empresaItems = empresaItems;
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

    public List<TipoAbono> getSelectedTipoAbonoItems() {
        return selectedTipoAbonoItems;
    }

    public void setSelectedTipoAbonoItems(List<TipoAbono> selectedTipoAbonoItems) {
        this.selectedTipoAbonoItems = selectedTipoAbonoItems;
    }

    public List<TipoCargo> getSelectedTipoCargoItems() {
        return selectedTipoCargoItems;
    }

    public void setSelectedTipoCargoItems(List<TipoCargo> selectedTipoCargoItems) {
        this.selectedTipoCargoItems = selectedTipoCargoItems;
    }

    public Map<Integer, Long> getMapsAbonos() {
        return mapsAbonos;
    }

    public void setMapsAbonos(Map<Integer, Long> mapsAbonos) {
        this.mapsAbonos = mapsAbonos;
    }

    public Map<Integer, Long> getMapsCargos() {
        return mapsCargos;
    }

    public void setMapsCargos(Map<Integer, Long> mapsCargos) {
        this.mapsCargos = mapsCargos;
    }

    public List<LinkedHashMap> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<LinkedHashMap> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<LinkedHashMap> getListOfMaps() {
        return listOfMaps;
    }

    public void setListOfMaps(List<LinkedHashMap> listOfMaps) {
        this.listOfMaps = listOfMaps;
    }

    public List<String> getResultsHeader() {
        return resultsHeader;
    }

    public void setResultsHeader(List<String> resultsHeader) {
        this.resultsHeader = resultsHeader;
    }

    public List<Integer> getResultsTotals() {
        return resultsTotals;
    }

    public void setResultsTotals(List<Integer> resultsTotals) {
        this.resultsTotals = resultsTotals;
    }

    public LinkedHashMap<String, Integer> getTotales() {
        return totales;
    }

    public void setTotales(LinkedHashMap<String, Integer> totales) {
        this.totales = totales;
    }

    public LinkedHashMap getDefaultTotal() {
        return defaultTotal;
    }

    public void setDefaultTotal(LinkedHashMap defaultTotal) {
        this.defaultTotal = defaultTotal;
    }

    public LinkedHashMap getSelectedHashMap() {
        return selectedHashMap;
    }

    public void setSelectedHashMap(LinkedHashMap selectedHashMap) {
        this.selectedHashMap = selectedHashMap;
    }

    public void setFlagCargos(Boolean flagCargos) {
        this.flagCargos = flagCargos;
    }

    public void setFlagAbonos(Boolean flagAbonos) {
        this.flagAbonos = flagAbonos;
    }

    public Boolean getFlagCargos() {
        return flagCargos;
    }

    public Boolean getFlagAbonos() {
        return flagAbonos;
    }

    public LocalDateConverter getDc() {
        return dc;
    }

    public void setDc(LocalDateConverter dc) {
        this.dc = dc;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public NumberFormat getNf() {
        return nf;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

}
