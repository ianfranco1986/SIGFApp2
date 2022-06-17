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
@Table(name = "tipo_movimiento_bancario", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoMovimientoBancario.findAll", query = "SELECT t FROM TipoMovimientoBancario t"),
    @NamedQuery(name = "TipoMovimientoBancario.findByTipoMovimientoBancarioId", query = "SELECT t FROM TipoMovimientoBancario t WHERE t.tipoMovimientoBancarioId = :tipoMovimientoBancarioId"),
    @NamedQuery(name = "TipoMovimientoBancario.findByTipoMovimientoBancarioDescripcion", query = "SELECT t FROM TipoMovimientoBancario t WHERE t.tipoMovimientoBancarioDescripcion = :tipoMovimientoBancarioDescripcion")})
public class TipoMovimientoBancario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_movimiento_bancario_id")
    private Integer tipoMovimientoBancarioId;
    @Basic(optional = false)
    @Column(name = "tipo_movimiento_bancario_descripcion")
    private String tipoMovimientoBancarioDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimientoBancarioTipoId")
    private List<MovimientoBancario> movimientoBancarioList;

    public TipoMovimientoBancario() {
    }

    public TipoMovimientoBancario(Integer tipoMovimientoBancarioId) {
        this.tipoMovimientoBancarioId = tipoMovimientoBancarioId;
    }

    public TipoMovimientoBancario(Integer tipoMovimientoBancarioId, String tipoMovimientoBancarioDescripcion) {
        this.tipoMovimientoBancarioId = tipoMovimientoBancarioId;
        this.tipoMovimientoBancarioDescripcion = tipoMovimientoBancarioDescripcion;
    }

    public Integer getTipoMovimientoBancarioId() {
        return tipoMovimientoBancarioId;
    }

    public void setTipoMovimientoBancarioId(Integer tipoMovimientoBancarioId) {
        this.tipoMovimientoBancarioId = tipoMovimientoBancarioId;
    }

    public String getTipoMovimientoBancarioDescripcion() {
        return tipoMovimientoBancarioDescripcion;
    }

    public void setTipoMovimientoBancarioDescripcion(String tipoMovimientoBancarioDescripcion) {
        this.tipoMovimientoBancarioDescripcion = tipoMovimientoBancarioDescripcion;
    }

    public List<MovimientoBancario> getMovimientoBancarioList() {
        return movimientoBancarioList;
    }

    public void setMovimientoBancarioList(List<MovimientoBancario> movimientoBancarioList) {
        this.movimientoBancarioList = movimientoBancarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoMovimientoBancarioId != null ? tipoMovimientoBancarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMovimientoBancario)) {
            return false;
        }
        TipoMovimientoBancario other = (TipoMovimientoBancario) object;
        if ((this.tipoMovimientoBancarioId == null && other.tipoMovimientoBancarioId != null) || (this.tipoMovimientoBancarioId != null && !this.tipoMovimientoBancarioId.equals(other.tipoMovimientoBancarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoMovimientoBancario[ tipoMovimientoBancarioId=" + tipoMovimientoBancarioId + " ]";
    }
    
}
