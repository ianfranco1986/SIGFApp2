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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p ORDER BY p.proveedorNombre")
    , @NamedQuery(name = "Proveedor.findByProveedorId", query = "SELECT p FROM Proveedor p WHERE p.proveedorId = :proveedorId")
    , @NamedQuery(name = "Proveedor.findByProveedorRut", query = "SELECT p FROM Proveedor p WHERE p.proveedorRut = :proveedorRut")
    , @NamedQuery(name = "Proveedor.findByProveedorNombre", query = "SELECT p FROM Proveedor p WHERE p.proveedorNombre = :proveedorNombre")
    , @NamedQuery(name = "Proveedor.findByProveedorDireccion", query = "SELECT p FROM Proveedor p WHERE p.proveedorDireccion = :proveedorDireccion")
    , @NamedQuery(name = "Proveedor.findByProveedorComunaId", query = "SELECT p FROM Proveedor p WHERE p.proveedorComunaId = :proveedorComunaId")
    , @NamedQuery(name = "Proveedor.findByProveedorTelefono", query = "SELECT p FROM Proveedor p WHERE p.proveedorTelefono = :proveedorTelefono")
    , @NamedQuery(name = "Proveedor.findByProveedorEmail", query = "SELECT p FROM Proveedor p WHERE p.proveedorEmail = :proveedorEmail")
    , @NamedQuery(name = "Proveedor.findByProveedorGiro", query = "SELECT p FROM Proveedor p WHERE p.proveedorGiro = :proveedorGiro")})
public class Proveedor extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proveedor_id")
    private Integer proveedorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "proveedor_rut")
    private String proveedorRut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "proveedor_nombre")
    private String proveedorNombre;
    @Size(max = 45)
    @Column(name = "proveedor_direccion")
    private String proveedorDireccion;
    @Size(max = 45)
    @Column(name = "proveedor_telefono")
    private String proveedorTelefono;
    @Size(max = 45)
    @Column(name = "proveedor_email")
    private String proveedorEmail;
    @Size(max = 45)
    @Column(name = "proveedor_giro")
    private String proveedorGiro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraProveedorId")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraPetroleoProveedorId", fetch = FetchType.LAZY)
    private List<CompraPetroleo> compraPetroleoList;
    @JoinColumn(name = "proveedor_comuna_id", referencedColumnName = "comuna_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Comuna proveedorComunaId;

    public Proveedor() {
    }

    public Proveedor(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Proveedor(Integer proveedorId, String proveedorRut, String proveedorNombre) {
        this.proveedorId = proveedorId;
        this.proveedorRut = proveedorRut;
        this.proveedorNombre = proveedorNombre;
    }
    
    public Proveedor(String proveedorRut, String proveedorNombre, Comuna comuna) {
        this.proveedorRut = proveedorRut;
        this.proveedorNombre = proveedorNombre;
        this.proveedorComunaId = comuna; 
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getProveedorRut() {
        return proveedorRut;
    }

    public void setProveedorRut(String proveedorRut) {
        this.proveedorRut = proveedorRut;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public String getProveedorDireccion() {
        return proveedorDireccion;
    }

    public void setProveedorDireccion(String proveedorDireccion) {
        this.proveedorDireccion = proveedorDireccion;
    }

    public Comuna getProveedorComunaId() {
        return proveedorComunaId;
    }

    public void setProveedorComunaId(Comuna proveedorComunaId) {
        this.proveedorComunaId = proveedorComunaId;
    }

    public String getProveedorTelefono() {
        return proveedorTelefono;
    }

    public void setProveedorTelefono(String proveedorTelefono) {
        this.proveedorTelefono = proveedorTelefono;
    }

    public String getProveedorEmail() {
        return proveedorEmail;
    }

    public void setProveedorEmail(String proveedorEmail) {
        this.proveedorEmail = proveedorEmail;
    }

    public String getProveedorGiro() {
        return proveedorGiro;
    }

    public void setProveedorGiro(String proveedorGiro) {
        this.proveedorGiro = proveedorGiro;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }
    
    @XmlTransient
    public List<CompraPetroleo> getCompraPetroleoList() {
        return compraPetroleoList;
    }

    public void setCompraPetroleoList(List<CompraPetroleo> compraPetroleoList) {
        this.compraPetroleoList = compraPetroleoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorId != null ? proveedorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        return (this.proveedorId != null || other.proveedorId == null) && (this.proveedorId == null || this.proveedorId.equals(other.proveedorId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Proveedor[ proveedorId=" + proveedorId + " ]";
    }
    
}
