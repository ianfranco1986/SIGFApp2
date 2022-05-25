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
import java.util.Date;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@EntityListeners(AuditListener.class)
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")
    , @NamedQuery(name = "Ticket.findAllByUser", query = "SELECT t FROM Ticket t WHERE t.ticketIdUsuario = :ticketIdUsuario ORDER BY t.ticketEstado ASC, t.ticketFechaIngreso DESC")
    , @NamedQuery(name = "Ticket.findByTicketId", query = "SELECT t FROM Ticket t WHERE t.ticketId = :ticketId")
    , @NamedQuery(name = "Ticket.findByTicketAsunto", query = "SELECT t FROM Ticket t WHERE t.ticketAsunto = :ticketAsunto")
    , @NamedQuery(name = "Ticket.findByTicketFechaIngreso", query = "SELECT t FROM Ticket t WHERE t.ticketFechaIngreso = :ticketFechaIngreso")
    , @NamedQuery(name = "Ticket.findByTicketFechaRespuesta", query = "SELECT t FROM Ticket t WHERE t.ticketFechaRespuesta = :ticketFechaRespuesta")
    , @NamedQuery(name = "Ticket.findByTicketEstado", query = "SELECT t FROM Ticket t WHERE t.ticketEstado = :ticketEstado")})
public class Ticket extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ticket_id")
    private Integer ticketId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ticket_asunto")
    private String ticketAsunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ticket_modulo")
    private String ticketModulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "ticket_descripcion")
    private String ticketDescripcion;
    @Basic(optional = true)
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "ticket_respuesta")
    private String ticketRespuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ticket_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ticketFechaIngreso;
    @Column(name = "ticket_fecha_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ticketFechaRespuesta;
    @Column(name = "ticket_estado")
    private Short ticketEstado;
    @JoinColumn(name = "ticket_id_usuario", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario ticketIdUsuario;

    public Ticket() {
    }

    public Ticket(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Ticket(Integer ticketId, String ticketModulo, String ticketAsunto, String ticketDescripcion, String ticketRespuesta, Date ticketFechaIngreso) {
        this.ticketId = ticketId;
        this.ticketModulo = ticketModulo;
        this.ticketAsunto = ticketAsunto;
        this.ticketDescripcion = ticketDescripcion;
        this.ticketRespuesta = ticketRespuesta;
        this.ticketFechaIngreso = ticketFechaIngreso;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketModulo() {
        return ticketModulo;
    }

    public void setTicketModulo(String ticketModulo) {
        this.ticketModulo = ticketModulo;
    }

    public String getTicketAsunto() {
        return ticketAsunto;
    }

    public void setTicketAsunto(String ticketAsunto) {
        this.ticketAsunto = ticketAsunto;
    }

    public String getTicketDescripcion() {
        return ticketDescripcion;
    }

    public void setTicketDescripcion(String ticketDescripcion) {
        this.ticketDescripcion = ticketDescripcion;
    }

    public String getTicketRespuesta() {
        return ticketRespuesta;
    }

    public void setTicketRespuesta(String ticketRespuesta) {
        this.ticketRespuesta = ticketRespuesta;
    }

    public Date getTicketFechaIngreso() {
        return ticketFechaIngreso;
    }

    public void setTicketFechaIngreso(Date ticketFechaIngreso) {
        this.ticketFechaIngreso = ticketFechaIngreso;
    }

    public Date getTicketFechaRespuesta() {
        return ticketFechaRespuesta;
    }

    public void setTicketFechaRespuesta(Date ticketFechaRespuesta) {
        this.ticketFechaRespuesta = ticketFechaRespuesta;
    }

    public Short getTicketEstado() {
        return ticketEstado;
    }

    public void setTicketEstado(Short ticketEstado) {
        this.ticketEstado = ticketEstado;
    }

    public Usuario getTicketIdUsuario() {
        return ticketIdUsuario;
    }

    public void setTicketIdUsuario(Usuario ticketIdUsuario) {
        this.ticketIdUsuario = ticketIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketId != null ? ticketId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        return (this.ticketId != null || other.ticketId == null) && (this.ticketId == null || this.ticketId.equals(other.ticketId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Ticket[ ticketId=" + ticketId + " ]";
    }

}
