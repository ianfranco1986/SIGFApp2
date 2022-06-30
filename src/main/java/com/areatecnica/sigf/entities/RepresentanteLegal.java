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
@Table(name = "representante_legal")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepresentanteLegal.findAll", query = "SELECT r FROM RepresentanteLegal r")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalId", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalId = :representanteLegalId")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalNombre", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalNombre = :representanteLegalNombre")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalRut", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalRut = :representanteLegalRut")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalDireccion", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalDireccion = :representanteLegalDireccion")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalTelefono", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalTelefono = :representanteLegalTelefono")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalEmail", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalEmail = :representanteLegalEmail")})
public class RepresentanteLegal extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "representante_legal_id", nullable = false)
    private Integer representanteLegalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "representante_legal_nombre", nullable = false, length = 45)
    private String representanteLegalNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "representante_legal_rut", nullable = false, length = 45)
    private String representanteLegalRut;
    @Size(max = 45)
    @Column(name = "representante_legal_direccion", length = 45)
    private String representanteLegalDireccion;
    @Size(max = 45)
    @Column(name = "representante_legal_telefono", length = 45)
    private String representanteLegalTelefono;
    @Size(max = 45)
    @Column(name = "representante_legal_email", length = 45)
    private String representanteLegalEmail;
    @JoinColumn(name = "representante_legal_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta representanteLegalIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "representanteEmpresaIdRepresentanteLegal", fetch = FetchType.LAZY)
    private List<RepresentanteEmpresa> representanteEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operadorTransporteIdRepresentante", fetch = FetchType.LAZY)
    private List<OperadorTransporte> operadorTransporteList;

    public RepresentanteLegal() {
    }

    public RepresentanteLegal(Integer representanteLegalId) {
        this.representanteLegalId = representanteLegalId;
    }

    public RepresentanteLegal(Integer representanteLegalId, String representanteLegalNombre, String representanteLegalRut) {
        this.representanteLegalId = representanteLegalId;
        this.representanteLegalNombre = representanteLegalNombre;
        this.representanteLegalRut = representanteLegalRut;
    }

    public Integer getRepresentanteLegalId() {
        return representanteLegalId;
    }

    public void setRepresentanteLegalId(Integer representanteLegalId) {
        this.representanteLegalId = representanteLegalId;
    }

    public String getRepresentanteLegalNombre() {
        return representanteLegalNombre;
    }

    public void setRepresentanteLegalNombre(String representanteLegalNombre) {
        this.representanteLegalNombre = representanteLegalNombre;
    }

    public String getRepresentanteLegalRut() {
        return representanteLegalRut;
    }

    public void setRepresentanteLegalRut(String representanteLegalRut) {
        this.representanteLegalRut = representanteLegalRut;
    }

    public String getRepresentanteLegalDireccion() {
        return representanteLegalDireccion;
    }

    public void setRepresentanteLegalDireccion(String representanteLegalDireccion) {
        this.representanteLegalDireccion = representanteLegalDireccion;
    }

    public String getRepresentanteLegalTelefono() {
        return representanteLegalTelefono;
    }

    public void setRepresentanteLegalTelefono(String representanteLegalTelefono) {
        this.representanteLegalTelefono = representanteLegalTelefono;
    }

    public String getRepresentanteLegalEmail() {
        return representanteLegalEmail;
    }

    public void setRepresentanteLegalEmail(String representanteLegalEmail) {
        this.representanteLegalEmail = representanteLegalEmail;
    }

    public Cuenta getRepresentanteLegalIdCuenta() {
        return representanteLegalIdCuenta;
    }

    public void setRepresentanteLegalIdCuenta(Cuenta representanteLegalIdCuenta) {
        this.representanteLegalIdCuenta = representanteLegalIdCuenta;
    }

    @XmlTransient
    public List<RepresentanteEmpresa> getRepresentanteEmpresaList() {
        return representanteEmpresaList;
    }

    public void setRepresentanteEmpresaList(List<RepresentanteEmpresa> representanteEmpresaList) {
        this.representanteEmpresaList = representanteEmpresaList;
    }

    @XmlTransient
    public List<OperadorTransporte> getOperadorTransporteList() {
        return operadorTransporteList;
    }

    public void setOperadorTransporteList(List<OperadorTransporte> operadorTransporteList) {
        this.operadorTransporteList = operadorTransporteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (representanteLegalId != null ? representanteLegalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepresentanteLegal)) {
            return false;
        }
        RepresentanteLegal other = (RepresentanteLegal) object;
        return (this.representanteLegalId != null || other.representanteLegalId == null) && (this.representanteLegalId == null || this.representanteLegalId.equals(other.representanteLegalId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RepresentanteLegal[ representanteLegalId=" + representanteLegalId + " ]";
    }
    
}
