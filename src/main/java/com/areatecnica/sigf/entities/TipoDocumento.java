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
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findByTipoDocumentoId", query = "SELECT t FROM TipoDocumento t WHERE t.tipoDocumentoId = :tipoDocumentoId")
    , @NamedQuery(name = "TipoDocumento.findByTipoDocumentoSigla", query = "SELECT t FROM TipoDocumento t WHERE t.tipoDocumentoSigla = :tipoDocumentoSigla")
    , @NamedQuery(name = "TipoDocumento.findByTipoDocumentoNombre", query = "SELECT t FROM TipoDocumento t WHERE t.tipoDocumentoNombre = :tipoDocumentoNombre")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_documento_id")
    private Integer tipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipo_documento_sigla")
    private String tipoDocumentoSigla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_documento_nombre")
    private String tipoDocumentoNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraTipoDocumentoId")
    private List<Compra> compraList;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public TipoDocumento(Integer tipoDocumentoId, String tipoDocumentoSigla, String tipoDocumentoNombre) {
        this.tipoDocumentoId = tipoDocumentoId;
        this.tipoDocumentoSigla = tipoDocumentoSigla;
        this.tipoDocumentoNombre = tipoDocumentoNombre;
    }

    public Integer getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(Integer tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public String getTipoDocumentoSigla() {
        return tipoDocumentoSigla;
    }

    public void setTipoDocumentoSigla(String tipoDocumentoSigla) {
        this.tipoDocumentoSigla = tipoDocumentoSigla;
    }

    public String getTipoDocumentoNombre() {
        return tipoDocumentoNombre;
    }

    public void setTipoDocumentoNombre(String tipoDocumentoNombre) {
        this.tipoDocumentoNombre = tipoDocumentoNombre;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDocumentoId != null ? tipoDocumentoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        return (this.tipoDocumentoId != null || other.tipoDocumentoId == null) && (this.tipoDocumentoId == null || this.tipoDocumentoId.equals(other.tipoDocumentoId));
    }

    @Override
    public String toString() {
        return tipoDocumentoNombre;
    }
    
}
