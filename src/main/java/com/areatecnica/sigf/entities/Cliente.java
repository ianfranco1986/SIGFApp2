/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "cliente")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByClienteId", query = "SELECT c FROM Cliente c WHERE c.clienteId = :clienteId"),
    @NamedQuery(name = "Cliente.findByClienteRut", query = "SELECT c FROM Cliente c WHERE c.clienteRut = :clienteRut"),
    @NamedQuery(name = "Cliente.findByClienteNombre", query = "SELECT c FROM Cliente c WHERE c.clienteNombre = :clienteNombre"),
    @NamedQuery(name = "Cliente.findByClienteDireccion", query = "SELECT c FROM Cliente c WHERE c.clienteDireccion = :clienteDireccion"),
    @NamedQuery(name = "Cliente.findByClienteTelefono", query = "SELECT c FROM Cliente c WHERE c.clienteTelefono = :clienteTelefono"),
    @NamedQuery(name = "Cliente.findByClienteEmail", query = "SELECT c FROM Cliente c WHERE c.clienteEmail = :clienteEmail"),
    @NamedQuery(name = "Cliente.findByClienteGiro", query = "SELECT c FROM Cliente c WHERE c.clienteGiro = :clienteGiro")})
public class Cliente extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cliente_id")
    private Integer clienteId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cliente_rut")
    private String clienteRut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cliente_nombre")
    private String clienteNombre;
    @Size(max = 45)
    @Column(name = "cliente_direccion")
    private String clienteDireccion;
    @Size(max = 45)
    @Column(name = "cliente_telefono")
    private String clienteTelefono;
    @Size(max = 45)
    @Column(name = "cliente_email")
    private String clienteEmail;
    @Size(max = 45)
    @Column(name = "cliente_giro")
    private String clienteGiro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaClienteId")
    private List<Factura> facturaList;
    @JoinColumn(name = "cliente_comuna_id", referencedColumnName = "comuna_id")
    @ManyToOne(optional = false)
    private Comuna clienteComunaId;

    public Cliente() {
    }

    public Cliente(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Cliente(Integer clienteId, String clienteRut, String clienteNombre) {
        this.clienteId = clienteId;
        this.clienteRut = clienteRut;
        this.clienteNombre = clienteNombre;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteRut() {
        return clienteRut;
    }

    public void setClienteRut(String clienteRut) {
        this.clienteRut = clienteRut;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteGiro() {
        return clienteGiro;
    }

    public void setClienteGiro(String clienteGiro) {
        this.clienteGiro = clienteGiro;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public Comuna getClienteComunaId() {
        return clienteComunaId;
    }

    public void setClienteComunaId(Comuna clienteComunaId) {
        this.clienteComunaId = clienteComunaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteId != null ? clienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.clienteId == null && other.clienteId != null) || (this.clienteId != null && !this.clienteId.equals(other.clienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Cliente[ clienteId=" + clienteId + " ]";
    }
    
}
