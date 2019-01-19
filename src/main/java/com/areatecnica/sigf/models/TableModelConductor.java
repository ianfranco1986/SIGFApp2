/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
public class TableModelConductor {

    private Recaudacion recaudacion;
    private Trabajador trabajador;
    private int administracion;
    private int cuotaextra;
    private int imposicion;
    private int boletos;

    public TableModelConductor(Recaudacion recaudacion) {
        this.recaudacion = recaudacion;
    }

    private void load() {
        for (RecaudacionGuia r : this.recaudacion.getRecaudacionGuiaList()) {

            switch (r.getRecaudacionGuiaIdEgreso().getEgresoId()) {
                case 1:
                    this.administracion = r.getRecaudacionGuiaMonto();
                    break;
                case 2:
                    this.cuotaextra = r.getRecaudacionGuiaMonto();
                    break;
                case 3:
                    this.boletos = r.getRecaudacionGuiaMonto();
                    break;
                case 4:
                    this.imposicion = r.getRecaudacionGuiaMonto();
                    break;
            }

        }
    }

    public void setRecaudacion(Recaudacion recaudacion) {
        this.recaudacion = recaudacion;
    }

    public Recaudacion getRecaudacion() {
        return recaudacion;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public int getAdministracion() {
        return administracion;
    }

    public int getBoletos() {
        return boletos;
    }

    public int getCuotaextra() {
        return cuotaextra;
    }

    public int getImposicion() {
        return imposicion;
    }

    public void setAdministracion(int administracion) {
        this.administracion = administracion;
    }

    public void setBoletos(int boletos) {
        this.boletos = boletos;
    }

    public void setCuotaextra(int cuotaextra) {
        this.cuotaextra = cuotaextra;
    }

    public void setImposicion(int imposicion) {
        this.imposicion = imposicion;
    }

}
