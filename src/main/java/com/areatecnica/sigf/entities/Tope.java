/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tope")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tope.findAll", query = "SELECT t FROM Tope t")
    , @NamedQuery(name = "Tope.findByTopeId", query = "SELECT t FROM Tope t WHERE t.topeId = :topeId")
    , @NamedQuery(name = "Tope.findByTopeNombre", query = "SELECT t FROM Tope t WHERE t.topeNombre = :topeNombre")
    , @NamedQuery(name = "Tope.findByTopeMontoUf", query = "SELECT t FROM Tope t WHERE t.topeMontoUf = :topeMontoUf")})
public class Tope implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tope_id", nullable = false)
    private Integer topeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tope_nombre", nullable = false, length = 255)
    private String topeNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tope_monto_uf", nullable = false)
    private float topeMontoUf;

    public Tope() {
    }

    public Tope(Integer topeId) {
        this.topeId = topeId;
    }

    public Tope(Integer topeId, String topeNombre, float topeMontoUf) {
        this.topeId = topeId;
        this.topeNombre = topeNombre;
        this.topeMontoUf = topeMontoUf;
    }

    public Integer getTopeId() {
        return topeId;
    }

    public void setTopeId(Integer topeId) {
        this.topeId = topeId;
    }

    public String getTopeNombre() {
        return topeNombre;
    }

    public void setTopeNombre(String topeNombre) {
        this.topeNombre = topeNombre;
    }

    public float getTopeMontoUf() {
        return topeMontoUf;
    }

    public void setTopeMontoUf(float topeMontoUf) {
        this.topeMontoUf = topeMontoUf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (topeId != null ? topeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tope)) {
            return false;
        }
        Tope other = (Tope) object;
        return (this.topeId != null || other.topeId == null) && (this.topeId == null || this.topeId.equals(other.topeId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Tope[ topeId=" + topeId + " ]";
    }
    
}
