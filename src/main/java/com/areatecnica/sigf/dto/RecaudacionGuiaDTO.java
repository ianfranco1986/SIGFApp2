/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.dto;

import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import java.io.Serializable;

/**
 *
 * @author ianfrancoconcha
 */
public class RecaudacionGuiaDTO implements Serializable {

    private Integer id;
    private int administracion;
    private int covid;
    private int boletos;
    private int imposiciones;
    private int fam;
    private int varios;
    private int total;

    private Recaudacion recaudacion;

    private Guia guia;

    public RecaudacionGuiaDTO() {
    }

    public RecaudacionGuiaDTO(Recaudacion recaudacion) {
        this.id = recaudacion.getRecaudacionId();
        this.recaudacion = recaudacion;
        for (RecaudacionGuia g : recaudacion.getRecaudacionGuiaList()) {

            if (guia != g.getRecaudacionGuiaIdGuia()) {
                guia = g.getRecaudacionGuiaIdGuia();
            }

            switch (g.getRecaudacionGuiaIdEgreso().getEgresoId()) {
                case 1:
                    this.administracion = g.getRecaudacionGuiaMonto();
                    break;
                case 2:
                    this.covid = g.getRecaudacionGuiaMonto();
                    break;
                case 3:
                    this.imposiciones = g.getRecaudacionGuiaMonto();
                    break;
                case 4:
                    this.boletos = g.getRecaudacionGuiaMonto();
                    break;

                case 5:
                    this.fam = g.getRecaudacionGuiaMonto();
                    break;
                case 6:
                    this.varios = g.getRecaudacionGuiaMonto();
                    break;
            }

        }
        this.total = this.administracion + this.covid + this.imposiciones + this.boletos + this.fam + this.varios;
    }

    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getAdministracion() {
        return administracion;
    }

    public void setAdministracion(int administracion) {
        this.administracion = administracion;
    }

    public int getCovid() {
        return covid;
    }

    public void setCovid(int covid) {
        this.covid = covid;
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

    public void setFam(int fam) {
        this.fam = fam;
    }

    public int getFam() {
        return fam;
    }

    public void setVarios(int varios) {
        this.varios = varios;
    }

    public int getVarios() {
        return varios;
    }

    public Recaudacion getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(Recaudacion Recaudacion) {
        this.recaudacion = Recaudacion;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

}

