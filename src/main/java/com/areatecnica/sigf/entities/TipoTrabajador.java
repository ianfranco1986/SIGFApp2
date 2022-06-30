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
@Table(name = "tipo_trabajador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTrabajador.findAll", query = "SELECT t FROM TipoTrabajador t")
    , @NamedQuery(name = "TipoTrabajador.findByTipoTrabajadorId", query = "SELECT t FROM TipoTrabajador t WHERE t.tipoTrabajadorId = :tipoTrabajadorId")
    , @NamedQuery(name = "TipoTrabajador.findByTipoTrabajadorNombre", query = "SELECT t FROM TipoTrabajador t WHERE t.tipoTrabajadorNombre = :tipoTrabajadorNombre")})
public class TipoTrabajador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_trabajador_id", nullable = false)
    private Integer tipoTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_trabajador_nombre", nullable = false, length = 45)
    private String tipoTrabajadorNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relacionLaboralIdTipoTrabajador", fetch = FetchType.LAZY)
    private List<RelacionLaboral> relacionLaboralList;
    @JoinColumn(name = "tipo_trabajador_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta tipoTrabajadorIdCuenta;

    public TipoTrabajador() {
    }

    public TipoTrabajador(Integer tipoTrabajadorId) {
        this.tipoTrabajadorId = tipoTrabajadorId;
    }

    public TipoTrabajador(Integer tipoTrabajadorId, String tipoTrabajadorNombre) {
        this.tipoTrabajadorId = tipoTrabajadorId;
        this.tipoTrabajadorNombre = tipoTrabajadorNombre;
    }

    public Integer getTipoTrabajadorId() {
        return tipoTrabajadorId;
    }

    public void setTipoTrabajadorId(Integer tipoTrabajadorId) {
        this.tipoTrabajadorId = tipoTrabajadorId;
    }

    public String getTipoTrabajadorNombre() {
        return tipoTrabajadorNombre;
    }

    public void setTipoTrabajadorNombre(String tipoTrabajadorNombre) {
        this.tipoTrabajadorNombre = tipoTrabajadorNombre;
    }

    @XmlTransient
    public List<RelacionLaboral> getRelacionLaboralList() {
        return relacionLaboralList;
    }

    public void setRelacionLaboralList(List<RelacionLaboral> relacionLaboralList) {
        this.relacionLaboralList = relacionLaboralList;
    }

    public Cuenta getTipoTrabajadorIdCuenta() {
        return tipoTrabajadorIdCuenta;
    }

    public void setTipoTrabajadorIdCuenta(Cuenta tipoTrabajadorIdCuenta) {
        this.tipoTrabajadorIdCuenta = tipoTrabajadorIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoTrabajadorId != null ? tipoTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTrabajador)) {
            return false;
        }
        TipoTrabajador other = (TipoTrabajador) object;
        return (this.tipoTrabajadorId != null || other.tipoTrabajadorId == null) && (this.tipoTrabajadorId == null || this.tipoTrabajadorId.equals(other.tipoTrabajadorId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoTrabajador[ tipoTrabajadorId=" + tipoTrabajadorId + " ]";
    }
    
}
