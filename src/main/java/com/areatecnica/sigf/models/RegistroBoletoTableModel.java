/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;


import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.RegistroBoleto;
import com.areatecnica.sigf.entities.VueltaGuia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroBoletoTableModel extends AbstractTableModel {

    private List<RegistroBoleto> registroBoletoItems;
    private List<EstructuraRegistroBoletoÑandu> list;
    private Guia guia;
    private final static String[] columnNames = {"#", "Servicio", "Directo", "Plan Viña", "Local", "Esc.Directo", "Esc. Local"};
    private int numeroVuelta;

    public RegistroBoletoTableModel(Guia guia) {
        this.guia = guia;
        init();
    }

    public RegistroBoletoTableModel() {
        this.list = new ArrayList<>();
    }

    private void init() {
        this.list = new ArrayList<>();

        for (VueltaGuia v : this.guia.getVueltaGuiaList()) {
            this.list.add(new EstructuraRegistroBoletoÑandu(v));
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        }
        return Integer.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return (rowIndex == 0) ? "Serie" : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getVueltaGuia().getVueltaGuiaNumero() : (getRowCount() < 3);
            case 1:
                return (rowIndex == 0) ? "" : list.get(rowIndex).getVueltaGuia().getVueltaGuiaIdServicio().getServicioNumeroServicio();
            case 2:
                return (rowIndex == 0) ? list.get(rowIndex).getDirecto().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getDirecto().getRegistroBoletoInicio() : list.get(rowIndex).getDirecto().getRegistroBoletoTotal();
            case 3:
                return (rowIndex == 0) ? list.get(rowIndex).getPlanVina().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getPlanVina().getRegistroBoletoInicio() : list.get(rowIndex).getPlanVina().getRegistroBoletoTotal();
            case 4:
                return (rowIndex == 0) ? list.get(rowIndex).getLocal().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getLocal().getRegistroBoletoInicio() : list.get(rowIndex).getLocal().getRegistroBoletoTotal();
            case 5:
                return (rowIndex == 0) ? list.get(rowIndex).getEscolarDirecto().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getEscolarDirecto().getRegistroBoletoInicio() : list.get(rowIndex).getEscolarDirecto().getRegistroBoletoTotal();
            case 6:
                return (rowIndex == 0) ? list.get(rowIndex).getEscolarLocal().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getEscolarLocal().getRegistroBoletoInicio() : list.get(rowIndex).getEscolarLocal().getRegistroBoletoTotal();
        }

        return null;
    }

    public void addRow(EstructuraRegistroBoletoÑandu erb) {
        erb.setNumero(numeroVuelta);
        this.numeroVuelta++;

        if (this.list.size() > 2) {
            this.list.remove(this.list.size() - 1); //Aqui estaba el problema, solucionado

        }

        this.list.add(erb);

        //this.list.add(getTotales());

        fireTableChanged(null);
    }

    public boolean removeLast() {
        if (this.list.size() > 2) { //Si tiene una vuelta ingresada
            if (this.list.remove(getUltimoRegistro())) {
                fireTableChanged(null);
                return true;
            }
        }
        return false;
    }

    public int getNumeroVuelta() {
        return numeroVuelta;
    }

    public EstructuraRegistroBoletoÑandu getPrimerRegistro() {
        return (this.list.isEmpty()) ? null : list.get(0);
    }

    public EstructuraRegistroBoletoÑandu getUltimoRegistro() {
        return (this.list.isEmpty()) ? null : list.get(numeroVuelta);
    }

    public EstructuraRegistroBoletoÑandu getTotalRegistro() {
        return (this.list.isEmpty()) ? null : list.get(getRowCount() - 1);
    }

    public EstructuraRegistroBoletoÑandu getTotales() {
        EstructuraRegistroBoletoÑandu totales = new EstructuraRegistroBoletoÑandu();

        EstructuraRegistroBoletoÑandu inicio = getPrimerRegistro();

        EstructuraRegistroBoletoÑandu ultimo = list.get(getRowCount() - 1);

        //totales.setRegistro(ultimo.getRegistro());

        totales.setDirecto(new RegistroBoleto(ultimo.getDirecto().getRegistroBoletoInicio(), inicio.getDirecto().getRegistroBoletoInicio(), ultimo.getDirecto().getRegistroBoletoValor()));
        
        totales.setPlanVina(new RegistroBoleto(ultimo.getPlanVina().getRegistroBoletoInicio(), inicio.getPlanVina().getRegistroBoletoInicio(), ultimo.getPlanVina().getRegistroBoletoValor()));
        totales.setLocal(new RegistroBoleto(ultimo.getLocal().getRegistroBoletoInicio(), inicio.getLocal().getRegistroBoletoInicio(), ultimo.getLocal().getRegistroBoletoValor()));
        totales.setEscolarDirecto(new RegistroBoleto(ultimo.getEscolarDirecto().getRegistroBoletoInicio(), inicio.getEscolarDirecto().getRegistroBoletoInicio(), ultimo.getEscolarDirecto().getRegistroBoletoValor()));
        totales.setEscolarLocal(new RegistroBoleto(ultimo.getEscolarLocal().getRegistroBoletoInicio(), inicio.getEscolarLocal().getRegistroBoletoInicio(), ultimo.getEscolarLocal().getRegistroBoletoValor()));

        return totales;
    }

    public List<EstructuraRegistroBoletoÑandu> getList() {
        return this.list;
    }

}
