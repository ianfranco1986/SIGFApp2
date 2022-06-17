/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "otros_impuestos", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "OtrosImpuestos.findAll", query = "SELECT o FROM OtrosImpuestos o"),
    @NamedQuery(name = "OtrosImpuestos.findByOtrosImpuestosId", query = "SELECT o FROM OtrosImpuestos o WHERE o.otrosImpuestosId = :otrosImpuestosId"),
    @NamedQuery(name = "OtrosImpuestos.findByOtrosImpuestosDescripcion", query = "SELECT o FROM OtrosImpuestos o WHERE o.otrosImpuestosDescripcion = :otrosImpuestosDescripcion"),
    @NamedQuery(name = "OtrosImpuestos.findByOtrosImpuestosPorcentaje", query = "SELECT o FROM OtrosImpuestos o WHERE o.otrosImpuestosPorcentaje = :otrosImpuestosPorcentaje")})
public class OtrosImpuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "otros_impuestos_id")
    private Integer otrosImpuestosId;
    @Basic(optional = false)
    @Column(name = "otros_impuestos_descripcion")
    private String otrosImpuestosDescripcion;
    @Basic(optional = false)
    @Column(name = "otros_impuestos_porcentaje")
    private float otrosImpuestosPorcentaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraOtrosImpId")
    private List<Compra> compraList;

    public OtrosImpuestos() {
    }

    public OtrosImpuestos(Integer otrosImpuestosId) {
        this.otrosImpuestosId = otrosImpuestosId;
    }

    public OtrosImpuestos(Integer otrosImpuestosId, String otrosImpuestosDescripcion, float otrosImpuestosPorcentaje) {
        this.otrosImpuestosId = otrosImpuestosId;
        this.otrosImpuestosDescripcion = otrosImpuestosDescripcion;
        this.otrosImpuestosPorcentaje = otrosImpuestosPorcentaje;
    }

    public Integer getOtrosImpuestosId() {
        return otrosImpuestosId;
    }

    public void setOtrosImpuestosId(Integer otrosImpuestosId) {
        this.otrosImpuestosId = otrosImpuestosId;
    }

    public String getOtrosImpuestosDescripcion() {
        return otrosImpuestosDescripcion;
    }

    public void setOtrosImpuestosDescripcion(String otrosImpuestosDescripcion) {
        this.otrosImpuestosDescripcion = otrosImpuestosDescripcion;
    }

    public float getOtrosImpuestosPorcentaje() {
        return otrosImpuestosPorcentaje;
    }

    public void setOtrosImpuestosPorcentaje(float otrosImpuestosPorcentaje) {
        this.otrosImpuestosPorcentaje = otrosImpuestosPorcentaje;
    }

    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (otrosImpuestosId != null ? otrosImpuestosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtrosImpuestos)) {
            return false;
        }
        OtrosImpuestos other = (OtrosImpuestos) object;
        if ((this.otrosImpuestosId == null && other.otrosImpuestosId != null) || (this.otrosImpuestosId != null && !this.otrosImpuestosId.equals(other.otrosImpuestosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.OtrosImpuestos[ otrosImpuestosId=" + otrosImpuestosId + " ]";
    }
    
}
