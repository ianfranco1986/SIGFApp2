/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class AbstractDynamicReport {

    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.areatecnica_SIGFapp_war_1PU");
    protected EntityManager entityManager;

    private List<Object[]> items;
    private Object selected;
    private LinkedHashMap selectedHashMap;
    private ArrayList<LinkedHashMap> listOfMaps;
    private List<String> resultsHeader;// = service.getResultsValues(...);
    private List<String> resultsTotals;
    private LinkedHashMap totales;
    private Object header;
    private String sql;
    private Query query;

    public AbstractDynamicReport() {
        entityManager = emf.createEntityManager();
        this.resultsHeader = new ArrayList<>();
        this.resultsTotals = new ArrayList<>();
    }

    public List getItems() {
        return items;
    }

    public LinkedHashMap getSelectedHashMap() {
        return selectedHashMap;
    }

    public ArrayList<LinkedHashMap> getListOfMaps() {
        return listOfMaps;
    }

    public List<String> getResultsHeader() {
        return resultsHeader;
    }

    public List<String> getResultsTotals() {
        return resultsTotals;
    }

    public LinkedHashMap getTotales() {
        return totales;
    }

    public Object getHeader() {
        return header;
    }

    public String getSql() {
        return sql;
    }

    public Query getQuery() {
        return query;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public void setSelectedHashMap(LinkedHashMap selectedHashMap) {
        this.selectedHashMap = selectedHashMap;
    }

    public void setListOfMaps(ArrayList<LinkedHashMap> listOfMaps) {
        this.listOfMaps = listOfMaps;
    }

    public void setResultsHeader(List<String> resultsHeader) {
        this.resultsHeader = resultsHeader;
    }

    public void setResultsTotals(List<String> resultsTotals) {
        this.resultsTotals = resultsTotals;
    }

    public void setTotales(LinkedHashMap totales) {
        this.totales = totales;
    }

    public void setHeader(Object header) {
        this.header = header;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<Object[]> load() {

        this.query = this.entityManager.createNativeQuery(sql);
        this.items = query.getResultList();

        System.err.println("tamaño" + this.items.size());
        return items;
    }

    public Object loadSingle() {
        this.query = this.entityManager.createNativeQuery(sql);
        this.selected = query.getResultList();
        return selected;
    }

}
