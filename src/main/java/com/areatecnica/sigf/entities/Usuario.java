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
@Table(name = "usuario")
@XmlRootElement
@EntityListeners(AuditListener.class)
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId")
    , @NamedQuery(name = "Usuario.findByUsuarioRutAndPass", query = "SELECT u FROM Usuario u WHERE u.usuarioRut = :usuarioRut AND u.usuarioPass = :usuarioPass")
    , @NamedQuery(name = "Usuario.findByUsuarioRut", query = "SELECT u FROM Usuario u WHERE u.usuarioRut = :usuarioRut")
    , @NamedQuery(name = "Usuario.findByUsuarioPass", query = "SELECT u FROM Usuario u WHERE u.usuarioPass = :usuarioPass")
    , @NamedQuery(name = "Usuario.findByUsuarioNombres", query = "SELECT u FROM Usuario u WHERE u.usuarioNombres = :usuarioNombres")
    , @NamedQuery(name = "Usuario.findByUsuarioApellidoPaterno", query = "SELECT u FROM Usuario u WHERE u.usuarioApellidoPaterno = :usuarioApellidoPaterno")
    , @NamedQuery(name = "Usuario.findByUsuarioApellidoMaterno", query = "SELECT u FROM Usuario u WHERE u.usuarioApellidoMaterno = :usuarioApellidoMaterno")
    , @NamedQuery(name = "Usuario.findByUsuarioEmail", query = "SELECT u FROM Usuario u WHERE u.usuarioEmail = :usuarioEmail")
    , @NamedQuery(name = "Usuario.findByUsuarioActivo", query = "SELECT u FROM Usuario u WHERE u.usuarioActivo = :usuarioActivo")})
public class Usuario extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "usuario_rut", nullable = false, length = 9)
    private String usuarioRut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario_pass", nullable = false, length = 45)
    private String usuarioPass;
    @Size(max = 255)
    @Column(name = "usuario_nombres", length = 255)
    private String usuarioNombres;
    @Size(max = 255)
    @Column(name = "usuario_apellido_paterno", length = 255)
    private String usuarioApellidoPaterno;
    @Size(max = 255)
    @Column(name = "usuario_apellido_materno", length = 255)
    private String usuarioApellidoMaterno;
    @Size(max = 255)
    @Column(name = "usuario_email", length = 255)
    private String usuarioEmail;
    @Column(name = "usuario_activo")
    private Boolean usuarioActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioSessionIdUsuario", fetch = FetchType.LAZY)
    private List<UsuarioSession> usuarioSessionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "despachoIdInspector", fetch = FetchType.LAZY)
    private List<Despacho> despachoList;
    @JoinColumn(name = "usuario_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta usuarioIdCuenta;
    @JoinColumn(name = "usuario_id_rol", referencedColumnName = "rol_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol usuarioIdRol;
    @JoinColumn(name = "usuario_id_terminal", referencedColumnName = "terminal_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Terminal usuarioIdTerminal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaRecaudacionIdUsuario", fetch = FetchType.LAZY)
    private List<CajaRecaudacion> cajaRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administradorIdUsuario", fetch = FetchType.LAZY)
    private List<Administrador> administradorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketIdUsuario", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;

    public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario(Integer usuarioId, String usuarioRut, String usuarioPass) {
        this.usuarioId = usuarioId;
        this.usuarioRut = usuarioRut;
        this.usuarioPass = usuarioPass;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioRut() {
        return usuarioRut;
    }

    public void setUsuarioRut(String usuarioRut) {
        this.usuarioRut = usuarioRut;
    }

    public String getUsuarioPass() {
        return usuarioPass;
    }

    public void setUsuarioPass(String usuarioPass) {
        this.usuarioPass = usuarioPass;
    }

    public String getUsuarioNombres() {
        return usuarioNombres;
    }

    public void setUsuarioNombres(String usuarioNombres) {
        this.usuarioNombres = usuarioNombres;
    }

    public String getUsuarioApellidoPaterno() {
        return usuarioApellidoPaterno;
    }

    public void setUsuarioApellidoPaterno(String usuarioApellidoPaterno) {
        this.usuarioApellidoPaterno = usuarioApellidoPaterno;
    }

    public String getUsuarioApellidoMaterno() {
        return usuarioApellidoMaterno;
    }

    public void setUsuarioApellidoMaterno(String usuarioApellidoMaterno) {
        this.usuarioApellidoMaterno = usuarioApellidoMaterno;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public Boolean getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Boolean usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    @XmlTransient
    public List<UsuarioSession> getUsuarioSessionList() {
        return usuarioSessionList;
    }

    public void setUsuarioSessionList(List<UsuarioSession> usuarioSessionList) {
        this.usuarioSessionList = usuarioSessionList;
    }

    @XmlTransient
    public List<Despacho> getDespachoList() {
        return despachoList;
    }

    public void setDespachoList(List<Despacho> despachoList) {
        this.despachoList = despachoList;
    }

    public Cuenta getUsuarioIdCuenta() {
        return usuarioIdCuenta;
    }

    public void setUsuarioIdCuenta(Cuenta usuarioIdCuenta) {
        this.usuarioIdCuenta = usuarioIdCuenta;
    }

    public Rol getUsuarioIdRol() {
        return usuarioIdRol;
    }

    public void setUsuarioIdRol(Rol usuarioIdRol) {
        this.usuarioIdRol = usuarioIdRol;
    }

    public Terminal getUsuarioIdTerminal() {
        return usuarioIdTerminal;
    }

    public void setUsuarioIdTerminal(Terminal usuarioIdTerminal) {
        this.usuarioIdTerminal = usuarioIdTerminal;
    }


    @XmlTransient
    public List<CajaRecaudacion> getCajaRecaudacionList() {
        return cajaRecaudacionList;
    }

    public void setCajaRecaudacionList(List<CajaRecaudacion> cajaRecaudacionList) {
        this.cajaRecaudacionList = cajaRecaudacionList;
    }

    @XmlTransient
    public List<Administrador> getAdministradorList() {
        return administradorList;
    }

    public void setAdministradorList(List<Administrador> administradorList) {
        this.administradorList = administradorList;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return (this.usuarioId != null || other.usuarioId == null) && (this.usuarioId == null || this.usuarioId.equals(other.usuarioId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Usuario[ usuarioId=" + usuarioId + " ]";
    }

}
