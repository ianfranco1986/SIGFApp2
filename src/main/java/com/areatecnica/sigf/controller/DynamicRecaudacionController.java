/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.*;
import com.areatecnica.sigf.entities.*;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Named(value = "dynamicRecaudacionController")
@ViewScoped
public class DynamicRecaudacionController implements Serializable {

    private List<Recaudacion> items;
    private List<LinkedHashMap> selectedItems;

    private List<RecaudacionGuia> recaudacionGuias;
    private List<Egreso> egresoList;
    private List<CajaRecaudacion> cajaRecaudacionList;
    private List<Bus> busList;
    private List<Trabajador> trabajadorList;

    private Recaudacion selected;
    private CajaRecaudacion cajaRecaudacion;

    private LinkedHashMap selectedHashMap;
    private List<LinkedHashMap> listOfMaps;

    private List<String> resultsHeader;// = service.getResultsValues(...);
    private List<String> resultsTotals;
    private Map folios;
    private LinkedHashMap<Integer, Integer> totales;
    private LinkedHashMap defaultTotal;
    private Date fecha;

    private int totalRecaudacion = 0;
    private int cantidadBoletos = 0;
    private int guiasAnuladas = 0;

    LocalDate f;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM", new Locale("es", "PE"));
    private final NumberFormat nf = NumberFormat.getInstance();

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.selectedItems = new ArrayList<>();
        this.cajaRecaudacionList = new CajaRecaudacionDaoImpl().findAllActive();
        this.trabajadorList = new TrabajadorDaoImpl().findNandu();
        this.busList = new BusDaoImpl().findByProceso(new ProcesoRecaudacionDaoImpl().findById(2));
    }

    public void load() {
        this.totalRecaudacion = 0;
        this.guiasAnuladas = 0;
        if (this.cajaRecaudacion != null) {
            createDynamicsColumns();
            createDynamicsRow();
        }
    }

    public void createDynamicsColumns() {
        resultsHeader = new ArrayList<>();

        defaultTotal = new LinkedHashMap();

        resultsHeader.add("Folio");
        defaultTotal.put("Folio", 0);

        resultsHeader.add("G.SolyMar");
        defaultTotal.put("G.SolyMar", 0);

        resultsHeader.add("NºBus");
        defaultTotal.put("NºBus", 0);

        resultsHeader.add("NºConductor");
        defaultTotal.put("NºConductor", 0);

        this.egresoList = new EgresoDaoImpl().findAllActived();

        for (Egreso e : egresoList) {
            resultsHeader.add(e.getEgresoNombre());

            defaultTotal.put(e.getEgresoNombre(), 0);

        }
        resultsHeader.add("Total");
        defaultTotal.put("Total", 0);
    }

    public void setResultTotals() {
        this.resultsTotals = new ArrayList<>();
        resultsTotals.add("");
        resultsTotals.add("");
        resultsTotals.add("");
        resultsTotals.add("");
        resultsTotals.add("");
    }

    public void createDynamicsRow() {
        //Inicio de array de listas ordenadas que contendrá c/u de las recaudaciones
        this.listOfMaps = new  ArrayList<LinkedHashMap>();
        HashMap header = new HashMap();
        this.folios = new HashMap<Integer, Integer>();
        this.totales = new LinkedHashMap();
        setResultTotals();

        this.items = new RecaudacionDaoImpl().findByCajaFechaRecaudacion(cajaRecaudacion, fecha);

        if (!this.items.isEmpty()) {
            //
            for (Recaudacion g : this.items) {
                if (!g.getRecaudacionGuiaList().isEmpty()) {
                    LinkedHashMap hashMap = new LinkedHashMap(defaultTotal);

                    this.folios.put(g.getRecaudacionId(), g);

                    hashMap.put("Folio", g.getRecaudacionId());
                    hashMap.put("G.SolyMar", g.getRecaudacionGuiaList().get(0).getRecaudacionGuiaIdGuia().getGuiaFolio());
                    hashMap.put("NºBus", g.getRecaudacionGuiaList().get(0).getRecaudacionGuiaIdGuia().getGuiaIdBus().getBusNumero());
                    hashMap.put("NºConductor", g.getRecaudacionGuiaList().get(0).getRecaudacionGuiaIdGuia().getGuiaIdTrabajador().getTrabajadorCodigo());

                    int totalColumna = 0;
                    for (RecaudacionGuia rg : g.getRecaudacionGuiaList()) {

                        String key = rg.getRecaudacionGuiaIdEgreso().getEgresoNombre();
                        int order = rg.getRecaudacionGuiaIdEgreso().getEgresoNumeroOrden();
                        hashMap.put(key, rg.getRecaudacionGuiaMonto());

                        if (totales.containsKey(order)) {
                            int aux = totales.get(order);
                            aux += rg.getRecaudacionGuiaMonto();
                            totales.put(order, aux);
                        } else {
                            totales.put(order, rg.getRecaudacionGuiaMonto());
                        }

                        totalColumna += rg.getRecaudacionGuiaMonto();
                        hashMap.put("Total", totalColumna);
                    }
                    if (totalColumna == 0) {
                        this.guiasAnuladas++;
                    }

                    listOfMaps.add(hashMap);
                }
            }

            ArrayList aux = new ArrayList<>(totales.values());
            System.err.println("CONTENIDO DE RESULTTOTALS PREVIO:" + resultsTotals + " tamaño: " + this.resultsTotals.size());
            System.err.println("CONTENTIDO DE AUX:" + aux + " tamaño de aux:" + aux.size());
            System.err.println("CONTENIDO DEL KEYSET:" + totales.keySet());
            resultsTotals.addAll(aux);
            //optimizar acá

            for (Object i : totales.keySet()) {
                int totali = totales.get(i);
                resultsTotals.set((3 + (int) i), nf.format(totali));

                this.totalRecaudacion += totali;
            }

            resultsTotals.set(resultsTotals.size() - 1, nf.format(this.totalRecaudacion));
            System.err.println("TAMAÑO DE RESULTTOTALS;" + this.resultsTotals.size());
            System.err.println("TAMAÑO DE headers;" + this.resultsHeader.size());
            System.err.println("TAMAÑO DE TOTALES:" + this.totales.size());
            System.err.println("CONTENIDO DE RESULTTOTALS: " + this.resultsTotals);
        } else {
            for (Egreso e : egresoList) {
                resultsTotals.add("");
            }
            resultsTotals.add("");
            this.listOfMaps.add(defaultTotal);
        }
    }

    public List<Recaudacion> getItems() {
        return items;
    }

    public void setItems(List<Recaudacion> items) {
        this.items = items;
    }

    public List<RecaudacionGuia> getRecaudacionGuias() {
        return recaudacionGuias;
    }

    public void setRecaudacionGuias(List<RecaudacionGuia> recaudacionGuias) {
        this.recaudacionGuias = recaudacionGuias;
    }

    public List<Egreso> getEgresoList() {
        return egresoList;
    }

    public void setEgresoList(List<Egreso> egresoList) {
        this.egresoList = egresoList;
    }

    public List<CajaRecaudacion> getCajaRecaudacionList() {
        return cajaRecaudacionList;
    }

    public void setCajaRecaudacionList(List<CajaRecaudacion> cajaRecaudacionList) {
        this.cajaRecaudacionList = cajaRecaudacionList;
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    public Recaudacion getSelected() {
        return selected;
    }

    public void setSelected(Recaudacion selected) {
        this.selected = selected;
    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public LinkedHashMap getSelectedHashMap() {
        return selectedHashMap;
    }

    public void setSelectedHashMap(LinkedHashMap selectedHashMap) {
        this.selectedHashMap = selectedHashMap;
    }

    public List<LinkedHashMap> getListOfMaps() {
        return listOfMaps;
    }

    public void setListOfMaps(ArrayList<LinkedHashMap> listOfMaps) {
        this.listOfMaps = listOfMaps;
    }

    public List<String> getResultsHeader() {
        return resultsHeader;
    }

    public void setResultsHeader(List<String> resultsHeader) {
        this.resultsHeader = resultsHeader;
    }

    public List<String> getResultsTotals() {
        return resultsTotals;
    }

    public void setResultsTotals(List<String> resultsTotals) {
        this.resultsTotals = resultsTotals;
    }

    public Map getFolios() {
        return folios;
    }

    public void setFolios(Map folios) {
        this.folios = folios;
    }

    public LinkedHashMap getTotales() {
        return totales;
    }

    public void setTotales(LinkedHashMap totales) {
        this.totales = totales;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public void setCantidadBoletos(int cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }

    public int getGuiasAnuladas() {
        return guiasAnuladas;
    }

    public void setGuiasAnuladas(int guiasAnuladas) {
        this.guiasAnuladas = guiasAnuladas;
    }

    public void setSelectedItems(List<LinkedHashMap> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<LinkedHashMap> getSelectedItems() {
        return selectedItems;
    }

    public String getFechaCompleta() {
        LocalDate date = LocalDate.from(this.fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return date.format(formatter);
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void delete() {

    }

    public String getDeleteButtonMessage() {
        if (hasSelectedGuias()) {
            int size = this.selectedItems.size();
            return size > 1 ? size + " recaudaciones seleccionadas" : "1 recaudación seleccionada";
        }

        return "Eliminar";
    }

    public boolean hasSelectedGuias() {
        return this.selectedItems != null && !this.selectedItems.isEmpty();
    }

    public void deleteSelectedGuias() {
        //Eliminar
    }
    
    public void print(){
        System.err.println("cantidad de seleccionados:"+this.selectedItems.size()+" valor seleccionados:"+this.selectedItems);
        System.err.println("linkedhashmap"+this.selectedHashMap);
    }
    
    public void addRow(){
        if(this.selectedHashMap!=null){
            if(!this.getSelectedItems().contains(this.selectedHashMap)){
                this.selectedItems.add(this.selectedHashMap);
            }
        }
        System.err.println("SELECTED HASHMAP: "+this.selectedHashMap);
        System.err.println("TAMAÑO ITEMS: "+this.selectedItems.size());
        System.err.println("OBJETOS EN ITEMS: "+this.selectedItems);
    }
    
    
}
