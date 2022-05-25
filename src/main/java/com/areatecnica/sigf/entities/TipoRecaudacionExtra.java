/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tipo_recaudacion_extra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRecaudacionExtra.findAll", query = "SELECT t FROM TipoRecaudacionExtra t")
    , @NamedQuery(name = "TipoRecaudacionExtra.findByTipoRecaudacionExtraId", query = "SELECT t FROM TipoRecaudacionExtra t WHERE t.tipoRecaudacionExtraId = :tipoRecaudacionExtraId")
    , @NamedQuery(name = "TipoRecaudacionExtra.findByTipoRecaudacionExtraNombre", query = "SELECT t FROM TipoRecaudacionExtra t WHERE t.tipoRecaudacionExtraNombre = :tipoRecaudacionExtraNombre")})
public class TipoRecaudacionExtra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_recaudacion_extra_id")
    private Integer tipoRecaudacionExtraId;
    @Basic(optional = false)
    @Column(name = "tipo_recaudacion_extra_nombre")
    private String tipoRecaudacionExtraNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionExtraIdTipo")
    private List<RecaudacionExtra> recaudacionExtraList;

    public TipoRecaudacionExtra() {
    }

    public TipoRecaudacionExtra(Integer tipoRecaudacionExtraId) {
        this.tipoRecaudacionExtraId = tipoRecaudacionExtraId;
    }

    public TipoRecaudacionExtra(Integer tipoRecaudacionExtraId, String tipoRecaudacionExtraNombre) {
        this.tipoRecaudacionExtraId = tipoRecaudacionExtraId;
        this.tipoRecaudacionExtraNombre = tipoRecaudacionExtraNombre;
    }

    public Integer getTipoRecaudacionExtraId() {
        return tipoRecaudacionExtraId;
    }

    public void setTipoRecaudacionExtraId(Integer tipoRecaudacionExtraId) {
        this.tipoRecaudacionExtraId = tipoRecaudacionExtraId;
    }

    public String getTipoRecaudacionExtraNombre() {
        return tipoRecaudacionExtraNombre;
    }

    public void setTipoRecaudacionExtraNombre(String tipoRecaudacionExtraNombre) {
        this.tipoRecaudacionExtraNombre = tipoRecaudacionExtraNombre;
    }

    @XmlTransient
    public List<RecaudacionExtra> getRecaudacionExtraList() {
        return recaudacionExtraList;
    }

    public void setRecaudacionExtraList(List<RecaudacionExtra> recaudacionExtraList) {
        this.recaudacionExtraList = recaudacionExtraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoRecaudacionExtraId != null ? tipoRecaudacionExtraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRecaudacionExtra)) {
            return false;
        }
        TipoRecaudacionExtra other = (TipoRecaudacionExtra) object;
        return (this.tipoRecaudacionExtraId != null || other.tipoRecaudacionExtraId == null) && (this.tipoRecaudacionExtraId == null || this.tipoRecaudacionExtraId.equals(other.tipoRecaudacionExtraId));
    }

    @Override
    public String toString() {
        return tipoRecaudacionExtraNombre;
    }
    
}
