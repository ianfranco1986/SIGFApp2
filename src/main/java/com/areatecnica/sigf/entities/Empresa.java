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
@Table(name = "empresa", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"empresa_rut"})})
@EntityListeners(AuditListener.class)
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e ORDER BY e.empresaNombre"),
    @NamedQuery(name = "Empresa.findAllByCuenta", query = "SELECT e FROM Empresa e WHERE e.empresaIdCuenta = :idCuenta ORDER BY e.empresaNombre ASC"),
    @NamedQuery(name = "Empresa.findByNandu", query = "SELECT e FROM Empresa e WHERE e.empresaIdCuenta.cuentaId = 1 AND e.empresaId <> 1 AND e.empresaActiva = true ORDER BY e.empresaNombre ASC"),
    @NamedQuery(name = "Empresa.findByEmpresaId", query = "SELECT e FROM Empresa e WHERE e.empresaId = :empresaId"),
    @NamedQuery(name = "Empresa.findByEmpresaRut", query = "SELECT e FROM Empresa e WHERE e.empresaRut = :empresaRut"),
    @NamedQuery(name = "Empresa.findByEmpresaNombre", query = "SELECT e FROM Empresa e WHERE e.empresaNombre = :empresaNombre"),
    @NamedQuery(name = "Empresa.findByEmpresaGiro", query = "SELECT e FROM Empresa e WHERE e.empresaGiro = :empresaGiro"),
    @NamedQuery(name = "Empresa.findByEmpresaDireccion", query = "SELECT e FROM Empresa e WHERE e.empresaDireccion = :empresaDireccion"),
    @NamedQuery(name = "Empresa.findByEmpresaTelefono", query = "SELECT e FROM Empresa e WHERE e.empresaTelefono = :empresaTelefono"),
    @NamedQuery(name = "Empresa.findByEmpresaCelular", query = "SELECT e FROM Empresa e WHERE e.empresaCelular = :empresaCelular"),
    @NamedQuery(name = "Empresa.findByEmpresaEmail", query = "SELECT e FROM Empresa e WHERE e.empresaEmail = :empresaEmail"),
    @NamedQuery(name = "Empresa.findByEmpresaPorcentajeMutual", query = "SELECT e FROM Empresa e WHERE e.empresaPorcentajeMutual = :empresaPorcentajeMutual")})
public class Empresa extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empresa_id", nullable = false)
    private Integer empresaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "empresa_rut", nullable = false, length = 9)
    private String empresaRut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "empresa_nombre", nullable = false, length = 255)
    private String empresaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "empresa_giro", nullable = false, length = 255)
    private String empresaGiro;
    @Size(max = 255)
    @Column(name = "empresa_direccion", length = 255)
    private String empresaDireccion;
    @Size(max = 255)
    @Column(name = "empresa_telefono", length = 255)
    private String empresaTelefono;
    @Size(max = 100)
    @Column(name = "empresa_celular", length = 100)
    private String empresaCelular;
    @Size(max = 100)
    @Column(name = "empresa_email", length = 100)
    private String empresaEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "empresa_porcentaje_mutual", nullable = false)
    private float empresaPorcentajeMutual;
    @Column(name = "empresa_activa")
    private Boolean empresaActiva;
    @JoinColumn(name = "empresa_id_caja_compensacion", referencedColumnName = "caja_compensacion_id", nullable = false)
    @ManyToOne(optional = false)
    private CajaCompensacion empresaIdCajaCompensacion;
    @JoinColumn(name = "empresa_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta empresaIdCuenta;
    @JoinColumn(name = "empresa_id_mutual", referencedColumnName = "mutual_id", nullable = false)
    @ManyToOne(optional = false)
    private Mutual empresaIdMutual;

    public Empresa() {
    }

    public Empresa(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Empresa(Integer empresaId, String empresaRut, String empresaNombre, String empresaGiro, float empresaPorcentajeMutual) {
        this.empresaId = empresaId;
        this.empresaRut = empresaRut;
        this.empresaNombre = empresaNombre;
        this.empresaGiro = empresaGiro;
        this.empresaPorcentajeMutual = empresaPorcentajeMutual;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaRut() {
        return empresaRut;
    }

    public void setEmpresaRut(String empresaRut) {
        this.empresaRut = empresaRut;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public String getEmpresaGiro() {
        return empresaGiro;
    }

    public void setEmpresaGiro(String empresaGiro) {
        this.empresaGiro = empresaGiro;
    }

    public String getEmpresaDireccion() {
        return empresaDireccion;
    }

    public void setEmpresaDireccion(String empresaDireccion) {
        this.empresaDireccion = empresaDireccion;
    }

    public String getEmpresaTelefono() {
        return empresaTelefono;
    }

    public void setEmpresaTelefono(String empresaTelefono) {
        this.empresaTelefono = empresaTelefono;
    }

    public String getEmpresaCelular() {
        return empresaCelular;
    }

    public void setEmpresaCelular(String empresaCelular) {
        this.empresaCelular = empresaCelular;
    }

    public String getEmpresaEmail() {
        return empresaEmail;
    }

    public void setEmpresaEmail(String empresaEmail) {
        this.empresaEmail = empresaEmail;
    }

    public float getEmpresaPorcentajeMutual() {
        return empresaPorcentajeMutual;
    }

    public void setEmpresaPorcentajeMutual(float empresaPorcentajeMutual) {
        this.empresaPorcentajeMutual = empresaPorcentajeMutual;
    }

    public Boolean getEmpresaActiva() {
        return empresaActiva;
    }

    public void setEmpresaActiva(Boolean empresaActiva) {
        this.empresaActiva = empresaActiva;
    }

    public CajaCompensacion getEmpresaIdCajaCompensacion() {
        return empresaIdCajaCompensacion;
    }

    public void setEmpresaIdCajaCompensacion(CajaCompensacion empresaIdCajaCompensacion) {
        this.empresaIdCajaCompensacion = empresaIdCajaCompensacion;
    }

    public Cuenta getEmpresaIdCuenta() {
        return empresaIdCuenta;
    }

    public void setEmpresaIdCuenta(Cuenta empresaIdCuenta) {
        this.empresaIdCuenta = empresaIdCuenta;
    }

    public Mutual getEmpresaIdMutual() {
        return empresaIdMutual;
    }

    public void setEmpresaIdMutual(Mutual empresaIdMutual) {
        this.empresaIdMutual = empresaIdMutual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresaId != null ? empresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        return (this.empresaId != null || other.empresaId == null) && (this.empresaId == null || this.empresaId.equals(other.empresaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Empresa[ empresaId=" + empresaId + " ]";
    }

}
