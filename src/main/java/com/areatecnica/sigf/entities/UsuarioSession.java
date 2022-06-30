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
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "usuario_session")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "UsuarioSession.findAll", query = "SELECT u FROM UsuarioSession u")
    , @NamedQuery(name = "UsuarioSession.findByUsuarioSessionId", query = "SELECT u FROM UsuarioSession u WHERE u.usuarioSessionId = :usuarioSessionId")
    , @NamedQuery(name = "UsuarioSession.findByUsuarioSessionFechaIngreso", query = "SELECT u FROM UsuarioSession u WHERE u.usuarioSessionFechaIngreso = :usuarioSessionFechaIngreso")
    , @NamedQuery(name = "UsuarioSession.findByUsuarioSessionIpAddress", query = "SELECT u FROM UsuarioSession u WHERE u.usuarioSessionIpAddress = :usuarioSessionIpAddress")})
public class UsuarioSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_session_id", nullable = false)
    private Integer usuarioSessionId;
    @Column(name = "usuario_session_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioSessionFechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario_session_ip_address", nullable = false, length = 50)
    private String usuarioSessionIpAddress;
    @JoinColumn(name = "usuario_session_id_usuario", referencedColumnName = "usuario_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioSessionIdUsuario;

    public UsuarioSession() {
    }

    public UsuarioSession(Integer usuarioSessionId) {
        this.usuarioSessionId = usuarioSessionId;
    }

    public UsuarioSession(Integer usuarioSessionId, String usuarioSessionIpAddress) {
        this.usuarioSessionId = usuarioSessionId;
        this.usuarioSessionIpAddress = usuarioSessionIpAddress;
    }

    public Integer getUsuarioSessionId() {
        return usuarioSessionId;
    }

    public void setUsuarioSessionId(Integer usuarioSessionId) {
        this.usuarioSessionId = usuarioSessionId;
    }

    public Date getUsuarioSessionFechaIngreso() {
        return usuarioSessionFechaIngreso;
    }

    public void setUsuarioSessionFechaIngreso(Date usuarioSessionFechaIngreso) {
        this.usuarioSessionFechaIngreso = usuarioSessionFechaIngreso;
    }

    public String getUsuarioSessionIpAddress() {
        return usuarioSessionIpAddress;
    }

    public void setUsuarioSessionIpAddress(String usuarioSessionIpAddress) {
        this.usuarioSessionIpAddress = usuarioSessionIpAddress;
    }

    public Usuario getUsuarioSessionIdUsuario() {
        return usuarioSessionIdUsuario;
    }

    public void setUsuarioSessionIdUsuario(Usuario usuarioSessionIdUsuario) {
        this.usuarioSessionIdUsuario = usuarioSessionIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioSessionId != null ? usuarioSessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioSession)) {
            return false;
        }
        UsuarioSession other = (UsuarioSession) object;
        return (this.usuarioSessionId != null || other.usuarioSessionId == null) && (this.usuarioSessionId == null || this.usuarioSessionId.equals(other.usuarioSessionId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.UsuarioSession[ usuarioSessionId=" + usuarioSessionId + " ]";
    }
    
}
