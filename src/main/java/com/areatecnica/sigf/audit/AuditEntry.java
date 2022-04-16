/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.audit;

import com.areatecnica.sigf.entities.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Noncacheable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "audit_entry", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditEntry.findAll", query = "SELECT a FROM AuditEntry a"),
    @NamedQuery(name = "AuditEntry.findById", query = "SELECT a FROM AuditEntry a WHERE a.id = :id"),
    @NamedQuery(name = "AuditEntry.findByAuditEntry", query = "SELECT a FROM AuditEntry a WHERE a.auditEntry = :auditEntry"),
    @NamedQuery(name = "AuditEntry.findByEventId", query = "SELECT a FROM AuditEntry a WHERE a.eventId = :eventId"),
    @NamedQuery(name = "AuditEntry.findByOperation", query = "SELECT a FROM AuditEntry a WHERE a.operation = :operation"),
    @NamedQuery(name = "AuditEntry.findByOperationTime", query = "SELECT a FROM AuditEntry a WHERE a.operationTime = :operationTime"),
    @NamedQuery(name = "AuditEntry.findByTableName", query = "SELECT a FROM AuditEntry a WHERE a.tableName = :tableName"),
    @NamedQuery(name = "AuditEntry.findByAuditUser", query = "SELECT a FROM AuditEntry a WHERE a.auditUser = :auditUser")})
public class AuditEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "auditUser", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario auditUser;
    @Column(name = "eventId")
    private Long eventId;
    @Size(max = 45)
    @Column(name = "tableName")
    private String tableName;
    @Enumerated(EnumType.STRING)
    @Column(name = "operation")
    private AuditOperation operation;
    @Column(name = "operationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationTime;
    @Column(name = "auditEntry")
    private Integer auditEntry;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditEntryId",fetch = FetchType.EAGER)
    @Noncacheable
    private Collection<AuditField> field;

    public AuditEntry() {
    }

    public AuditEntry(Integer id) {
        this.id = id;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(Usuario auditUser) {
        this.auditUser = auditUser;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public AuditOperation getOperation() {
        return operation;
    }

    public void setOperation(AuditOperation operation) {
        this.operation = operation;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getAuditEntry() {
        return auditEntry;
    }

    public void setAuditEntry(Integer auditEntry) {
        this.auditEntry = auditEntry;
    }

    public Collection<AuditField> getField() {
        return field;
    }

    public void setField(Collection<AuditField> field) {
        this.field = field;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditEntry)) {
            return false;
        }
        AuditEntry other = (AuditEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AuditEntry[ id=" + id + " ]";
    }

}
