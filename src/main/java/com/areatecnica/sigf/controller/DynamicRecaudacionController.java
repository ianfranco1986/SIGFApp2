/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IEgresoDaoImpl;
import com.areatecnica.sigf.dao.impl.IProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.sigf.entities.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "dynamicRecaudacionController")
@ViewScoped
public class DynamicRecaudacionController implements Serializable {

    private List<Recaudacion> items;
    private ArrayList<LinkedHashMap> selectedItems;

    private List<RecaudacionGuia> recaudacionGuias;
    private List<Egreso> egresoList;
    private List<CajaRecaudacion> cajaRecaudacionList;
    private List<Bus> busList;
    private List<Trabajador> trabajadorList;

    private Recaudacion selected;
    private CajaRecaudacion cajaRecaudacion;

    private LinkedHashMap selectedHashMap;
    private ArrayList<LinkedHashMap> listOfMaps;

    private List<String> resultsHeader;// = service.getResultsValues(...);
    private List<String> resultsTotals;
    private Map folios;
    private LinkedHashMap totales;
    private LinkedHashMap defaultTotal;
    private Date fecha;

    private int totalRecaudacion = 0;
    private int cantidadBoletos = 0;
    private int guiasAnuladas = 0;

    LocalDate f;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM", new Locale("es", "PE"));
    private NumberFormat nf = NumberFormat.getInstance();

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.cajaRecaudacionList = new ICajaRecaudacionDaoImpl().findAll();
        this.trabajadorList = new TrabajadorDaoImpl().findNandu();
        this.busList = new IBusDaoImpl().findByProceso(new IProcesoRecaudacionDaoImpl().findById(2));
    }

    public void load() {
        if (this.cajaRecaudacion != null) {
            createDynamicsColumns();
            createDynamicsRow();
        }
    }

    public void createDynamicsColumns() {
        resultsHeader = new ArrayList<String>();
        resultsTotals = new ArrayList<>();
        defaultTotal = new LinkedHashMap();

        resultsHeader.add("Folio");
        defaultTotal.put("Folio", 0);
        resultsTotals.add("");

        resultsHeader.add("G.SolyMar");
        defaultTotal.put("G.SolyMar", 0);
        resultsTotals.add("");

        resultsHeader.add("NºBus");
        defaultTotal.put("NºBus", 0);
        resultsTotals.add("");

        resultsHeader.add("NºConductor");
        defaultTotal.put("NºConductor", 0);
        resultsTotals.add("");

        List<Egreso> egresoList = new IEgresoDaoImpl().findAllActived();

        for (Egreso e : egresoList) {
            resultsHeader.add(e.getEgresoNombre());
            resultsTotals.add("0");
            defaultTotal.put(e.getEgresoNombre(), 0);

        }

    }

    public void createDynamicsRow() {
        //Inicio de array de listas ordenadas que contendrá c/u de las recaudaciones
        this.listOfMaps = new ArrayList<LinkedHashMap>();
        HashMap header = new HashMap();
        this.folios = new HashMap<Integer, Integer>();
        this.totales = new LinkedHashMap();

        this.items = new IRecaudacionDaoImpl().findByCajaFechaRecaudacion(cajaRecaudacion, fecha);

        if (!this.items.isEmpty()) {
            for (Recaudacion g : this.items) {
                if (!g.getRecaudacionGuiaList().isEmpty()) {
                    LinkedHashMap hashMap = new LinkedHashMap(defaultTotal);

                    this.folios.put(g.getRecaudacionId(), g);

                    hashMap.put("Folio", g.getRecaudacionId());
                    hashMap.put("G.SolyMar", g.getRecaudacionGuiaList().get(0).getRecaudacionGuiaIdGuia().getGuiaFolio());
                    hashMap.put("NºBus", g.getRecaudacionGuiaList().get(0).getRecaudacionGuiaIdGuia().getGuiaIdBus().getBusNumero());
                    hashMap.put("NºConductor", g.getRecaudacionGuiaList().get(0).getRecaudacionGuiaIdGuia().getGuiaIdTrabajador().getTrabajadorCodigo());

                    for (RecaudacionGuia rg : g.getRecaudacionGuiaList()) {
                        String key = rg.getRecaudacionGuiaIdEgreso().getEgresoNombre();
                        hashMap.put(key, rg.getRecaudacionGuiaMonto());
                        if (totales.containsKey(key)) {
                            int aux = (int) totales.get(key);
                            aux += rg.getRecaudacionGuiaMonto();
                            totales.put(key, aux);
                        } else {
                            totales.put(key, rg.getRecaudacionGuiaMonto());
                        }
                    }

                    listOfMaps.add(hashMap);
                }
            }

            for (Object i : totales.values()) {
                int totali = (int) i;
                resultsTotals.add(String.valueOf(totali));
                System.err.println("TOTALES:"+resultsTotals);
            }

        } else {
            System.err.println("NO SE HAN ENCONTRADO RESULTADOS");
        }

//        for(LinkedHashMap a:this.listOfMaps){
//            //System.err.println("VALOR :"+a);
//        }
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

    public ArrayList<LinkedHashMap> getListOfMaps() {
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

    public void setSelectedItems(ArrayList<LinkedHashMap> selectedItems) {
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
}
