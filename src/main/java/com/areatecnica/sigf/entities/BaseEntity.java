/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author ianfrancoconcha
 */
@MappedSuperclass
public class BaseEntity {


    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;


    @Column(name = "ultima_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;
    
    public Date getLastUpdate() {
        return ultimaModificacion;
    }

    public void setLastUpdate(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @PrePersist
    public void onPersist() {
        fechaCreacion = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        ultimaModificacion = new Date();
    }
}
