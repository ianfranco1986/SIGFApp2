/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.audit;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "audit_field")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditField.findAll", query = "SELECT a FROM AuditField a")
    , @NamedQuery(name = "AuditField.findById", query = "SELECT a FROM AuditField a WHERE a.id = :id")
    , @NamedQuery(name = "AuditField.findByFieldName", query = "SELECT a FROM AuditField a WHERE a.fieldName = :fieldName")
    , @NamedQuery(name = "AuditField.findByFieldValue", query = "SELECT a FROM AuditField a WHERE a.fieldValue = :fieldValue")})
public class AuditField implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "field_name", length = 255)
    private String fieldName;
    @Size(max = 255)
    @Column(name = "field_oldValue", length = 255)
    private String fieldOldValue;
    @Size(max = 255)
    @Column(name = "field_value", length = 255)
    private String fieldValue;
    @JoinColumn(name = "audit_entry_id", referencedColumnName = "id")
    @ManyToOne
    private AuditEntry auditEntryId;

    public AuditField() {
        this.fieldOldValue = "";
    }

    public AuditField(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public void setFieldOldValue(String fieldOldValue) {
        this.fieldOldValue = fieldOldValue;
    }

    public String getFieldOldValue() {
        return fieldOldValue;
    }

    public AuditEntry getAuditEntryId() {
        return auditEntryId;
    }

    public void setAuditEntryId(AuditEntry auditEntryId) {
        this.auditEntryId = auditEntryId;
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
        if (!(object instanceof AuditField)) {
            return false;
        }
        AuditField other = (AuditField) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AuditField[ id=" + id + " ]";
    }
    
}
