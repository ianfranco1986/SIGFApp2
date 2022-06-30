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
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "boleto")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b")
    , @NamedQuery(name = "Boleto.findByCuenta", query = "SELECT b FROM Boleto b WHERE b.boletoIdCuenta = :boletoIdCuenta ORDER BY b.boletoNombre")
    , @NamedQuery(name = "Boleto.findByBoletoId", query = "SELECT b FROM Boleto b WHERE b.boletoId = :boletoId")
    , @NamedQuery(name = "Boleto.findByBoletoNombre", query = "SELECT b FROM Boleto b WHERE b.boletoNombre = :boletoNombre")
    , @NamedQuery(name = "Boleto.findByBoletoOrden", query = "SELECT b FROM Boleto b WHERE b.boletoOrden = :boletoOrden")
    , @NamedQuery(name = "Boleto.findByBoletoActivo", query = "SELECT b FROM Boleto b WHERE b.boletoActivo = :boletoActivo")})
public class Boleto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "boleto_id", nullable = false)
    private Integer boletoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "boleto_nombre", nullable = false, length = 45)
    private String boletoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "boleto_sigla", nullable = false, length = 45)
    private String boletoSigla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "boleto_orden", nullable = false)
    private int boletoOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "boleto_activo", nullable = false)
    private boolean boletoActivo;
    @JoinColumn(name = "boleto_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta boletoIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioInternoIdBoleto", fetch = FetchType.LAZY)
    private List<InventarioInterno> inventarioInternoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleCompraBoletoIdBoleto", fetch = FetchType.LAZY)
    private List<DetalleCompraBoleto> detalleCompraBoletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroBoletoIdBoleto", fetch = FetchType.LAZY)
    private List<RegistroBoleto> registroBoletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarifaGrupoServicioIdBoleto", fetch = FetchType.LAZY)
    private List<TarifaGrupoServicio> tarifaGrupoServicioList;

    public Boleto() {
    }

    public Boleto(Integer boletoId) {
        this.boletoId = boletoId;
    }

    public Boleto(Integer boletoId, String boletoNombre, String boletoSigla, int boletoOrden, boolean boletoActivo) {
        this.boletoId = boletoId;
        this.boletoNombre = boletoNombre;
        this.boletoSigla = boletoSigla;
        this.boletoOrden = boletoOrden;
        this.boletoActivo = boletoActivo;
    }

    public Integer getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(Integer boletoId) {
        this.boletoId = boletoId;
    }

    public String getBoletoNombre() {
        return boletoNombre;
    }

    public void setBoletoNombre(String boletoNombre) {
        this.boletoNombre = boletoNombre;
    }

    public int getBoletoOrden() {
        return boletoOrden;
    }

    public void setBoletoOrden(int boletoOrden) {
        this.boletoOrden = boletoOrden;
    }

    public boolean getBoletoActivo() {
        return boletoActivo;
    }

    public void setBoletoActivo(boolean boletoActivo) {
        this.boletoActivo = boletoActivo;
    }

    public Cuenta getBoletoIdCuenta() {
        return boletoIdCuenta;
    }

    public void setBoletoIdCuenta(Cuenta boletoIdCuenta) {
        this.boletoIdCuenta = boletoIdCuenta;
    }

    public void setBoletoSigla(String boletoSigla) {
        this.boletoSigla = boletoSigla;
    }

    public String getBoletoSigla() {
        return boletoSigla;
    }

    @XmlTransient
    public List<InventarioInterno> getInventarioInternoList() {
        return inventarioInternoList;
    }

    public void setInventarioInternoList(List<InventarioInterno> inventarioInternoList) {
        this.inventarioInternoList = inventarioInternoList;
    }

    @XmlTransient
    public List<DetalleCompraBoleto> getDetalleCompraBoletoList() {
        return detalleCompraBoletoList;
    }

    public void setDetalleCompraBoletoList(List<DetalleCompraBoleto> detalleCompraBoletoList) {
        this.detalleCompraBoletoList = detalleCompraBoletoList;
    }

    @XmlTransient
    public List<RegistroBoleto> getRegistroBoletoList() {
        return registroBoletoList;
    }

    public void setRegistroBoletoList(List<RegistroBoleto> registroBoletoList) {
        this.registroBoletoList = registroBoletoList;
    }

    @XmlTransient
    public List<TarifaGrupoServicio> getTarifaGrupoServicioList() {
        return tarifaGrupoServicioList;
    }

    public void setTarifaGrupoServicioList(List<TarifaGrupoServicio> tarifaGrupoServicioList) {
        this.tarifaGrupoServicioList = tarifaGrupoServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boletoId != null ? boletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        return (this.boletoId != null || other.boletoId == null) && (this.boletoId == null || this.boletoId.equals(other.boletoId));
    }

    @Override
    public String toString() {
        return boletoNombre;
    }

}
