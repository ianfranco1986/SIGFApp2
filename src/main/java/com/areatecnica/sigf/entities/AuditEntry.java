/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "audit_entry", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditEntry.findAll", query = "SELECT a FROM AuditEntry a")
    , @NamedQuery(name = "AuditEntry.findById", query = "SELECT a FROM AuditEntry a WHERE a.id = :id")
    , @NamedQuery(name = "AuditEntry.findByAuditEntry", query = "SELECT a FROM AuditEntry a WHERE a.auditEntry = :auditEntry")
    , @NamedQuery(name = "AuditEntry.findByEventId", query = "SELECT a FROM AuditEntry a WHERE a.eventId = :eventId")
    , @NamedQuery(name = "AuditEntry.findByOperation", query = "SELECT a FROM AuditEntry a WHERE a.operation = :operation")
    , @NamedQuery(name = "AuditEntry.findByOperationTime", query = "SELECT a FROM AuditEntry a WHERE a.operationTime = :operationTime")
    , @NamedQuery(name = "AuditEntry.findByTableName", query = "SELECT a FROM AuditEntry a WHERE a.tableName = :tableName")
    , @NamedQuery(name = "AuditEntry.findByAuditUser", query = "SELECT a FROM AuditEntry a WHERE a.auditUser = :auditUser")})
public class AuditEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "auditEntry")
    private Integer auditEntry;
    @Column(name = "eventId")
    private BigInteger eventId;
    @Size(max = 255)
    @Column(name = "operation", length = 255)
    private String operation;
    @Column(name = "operationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationTime;
    @Size(max = 255)
    @Column(name = "tableName", length = 255)
    private String tableName;
    @Column(name = "auditUser")
    private Integer auditUser;
    @OneToMany(mappedBy = "auditEntryId")
    private List<AuditField> auditFieldList;

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

    public Integer getAuditEntry() {
        return auditEntry;
    }

    public void setAuditEntry(Integer auditEntry) {
        this.auditEntry = auditEntry;
    }

    public BigInteger getEventId() {
        return eventId;
    }

    public void setEventId(BigInteger eventId) {
        this.eventId = eventId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(Integer auditUser) {
        this.auditUser = auditUser;
    }

    @XmlTransient
    public List<AuditField> getAuditFieldList() {
        return auditFieldList;
    }

    public void setAuditFieldList(List<AuditField> auditFieldList) {
        this.auditFieldList = auditFieldList;
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
