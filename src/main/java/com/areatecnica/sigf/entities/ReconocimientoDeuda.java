/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

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
@Table(name = "reconocimiento_deuda")
@EntityListeners(AuditListener.class)
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "ReconocimientoDeuda.findAll", query = "SELECT r FROM ReconocimientoDeuda r")
    , @NamedQuery(name = "ReconocimientoDeuda.findByReconocimientoDeudaId", query = "SELECT r FROM ReconocimientoDeuda r WHERE r.reconocimientoDeudaId = :reconocimientoDeudaId")
    , @NamedQuery(name = "ReconocimientoDeuda.findByReconocimientoDeudaMonto", query = "SELECT r FROM ReconocimientoDeuda r WHERE r.reconocimientoDeudaMonto = :reconocimientoDeudaMonto")
    , @NamedQuery(name = "ReconocimientoDeuda.findByReconocimientoDeudaDescripcion", query = "SELECT r FROM ReconocimientoDeuda r WHERE r.reconocimientoDeudaDescripcion = :reconocimientoDeudaDescripcion")})
public class ReconocimientoDeuda extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reconocimiento_deuda_id", nullable = false)
    private Integer reconocimientoDeudaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reconocimiento_deuda_monto", nullable = false)
    private int reconocimientoDeudaMonto;
    @Size(max = 255)
    @Column(name = "reconocimiento_deuda_descripcion", length = 255)
    private String reconocimientoDeudaDescripcion;
    @JoinColumn(name = "reconocimiento_deuda_id_empresa", referencedColumnName = "empresa_id", nullable = false)
    @ManyToOne(optional = false)
    private Empresa reconocimientoDeudaIdEmpresa;
    @JoinColumn(name = "reconocimiento_deuda_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador reconocimientoDeudaIdTrabajador;

    public ReconocimientoDeuda() {
    }

    public ReconocimientoDeuda(Integer reconocimientoDeudaId) {
        this.reconocimientoDeudaId = reconocimientoDeudaId;
    }

    public ReconocimientoDeuda(Integer reconocimientoDeudaId, int reconocimientoDeudaMonto) {
        this.reconocimientoDeudaId = reconocimientoDeudaId;
        this.reconocimientoDeudaMonto = reconocimientoDeudaMonto;
    }

    public Integer getReconocimientoDeudaId() {
        return reconocimientoDeudaId;
    }

    public void setReconocimientoDeudaId(Integer reconocimientoDeudaId) {
        this.reconocimientoDeudaId = reconocimientoDeudaId;
    }

    public int getReconocimientoDeudaMonto() {
        return reconocimientoDeudaMonto;
    }

    public void setReconocimientoDeudaMonto(int reconocimientoDeudaMonto) {
        this.reconocimientoDeudaMonto = reconocimientoDeudaMonto;
    }

    public String getReconocimientoDeudaDescripcion() {
        return reconocimientoDeudaDescripcion;
    }

    public void setReconocimientoDeudaDescripcion(String reconocimientoDeudaDescripcion) {
        this.reconocimientoDeudaDescripcion = reconocimientoDeudaDescripcion;
    }

    public Empresa getReconocimientoDeudaIdEmpresa() {
        return reconocimientoDeudaIdEmpresa;
    }

    public void setReconocimientoDeudaIdEmpresa(Empresa reconocimientoDeudaIdEmpresa) {
        this.reconocimientoDeudaIdEmpresa = reconocimientoDeudaIdEmpresa;
    }

    public Trabajador getReconocimientoDeudaIdTrabajador() {
        return reconocimientoDeudaIdTrabajador;
    }

    public void setReconocimientoDeudaIdTrabajador(Trabajador reconocimientoDeudaIdTrabajador) {
        this.reconocimientoDeudaIdTrabajador = reconocimientoDeudaIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reconocimientoDeudaId != null ? reconocimientoDeudaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReconocimientoDeuda)) {
            return false;
        }
        ReconocimientoDeuda other = (ReconocimientoDeuda) object;
        return (this.reconocimientoDeudaId != null || other.reconocimientoDeudaId == null) && (this.reconocimientoDeudaId == null || this.reconocimientoDeudaId.equals(other.reconocimientoDeudaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ReconocimientoDeuda[ reconocimientoDeudaId=" + reconocimientoDeudaId + " ]";
    }
    
}
