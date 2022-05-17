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
import javax.persistence.Cacheable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Cacheable(false)
@Table(name = "vuelta_guia")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VueltaGuia.findAll", query = "SELECT v FROM VueltaGuia v")
    , @NamedQuery(name = "VueltaGuia.findByVueltaGuiaId", query = "SELECT v FROM VueltaGuia v WHERE v.vueltaGuiaId = :vueltaGuiaId")
    , @NamedQuery(name = "VueltaGuia.findByVueltaGuiaTotal", query = "SELECT v FROM VueltaGuia v WHERE v.vueltaGuiaTotal = :vueltaGuiaTotal")
    , @NamedQuery(name = "VueltaGuia.findByVueltaGuiaNumero", query = "SELECT v FROM VueltaGuia v WHERE v.vueltaGuiaNumero = :vueltaGuiaNumero")})
public class VueltaGuia extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vuelta_guia_id", nullable = false)
    private Integer vueltaGuiaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vuelta_guia_total", nullable = false)
    private int vueltaGuiaTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vuelta_guia_numero", nullable = false)
    private int vueltaGuiaNumero;
    @JoinColumn(name = "vuelta_guia_id_guia", referencedColumnName = "guia_id", nullable = false)
    @ManyToOne(optional = false)
    private Guia vueltaGuiaIdGuia;
    @JoinColumn(name = "vuelta_guia_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio vueltaGuiaIdServicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroBoletoIdVueltaGuia")
    private List<RegistroBoleto> registroBoletoList;

    public VueltaGuia() {
    }

    public VueltaGuia(Integer vueltaGuiaId) {
        this.vueltaGuiaId = vueltaGuiaId;
    }

    public VueltaGuia(Integer vueltaGuiaId, int vueltaGuiaTotal, int vueltaGuiaNumero) {
        this.vueltaGuiaId = vueltaGuiaId;
        this.vueltaGuiaTotal = vueltaGuiaTotal;
        this.vueltaGuiaNumero = vueltaGuiaNumero;
    }

    public Integer getVueltaGuiaId() {
        return vueltaGuiaId;
    }

    public void setVueltaGuiaId(Integer vueltaGuiaId) {
        this.vueltaGuiaId = vueltaGuiaId;
    }

    public int getVueltaGuiaTotal() {
        return vueltaGuiaTotal;
    }

    public void setVueltaGuiaTotal(int vueltaGuiaTotal) {
        this.vueltaGuiaTotal = vueltaGuiaTotal;
    }

    public int getVueltaGuiaNumero() {
        return vueltaGuiaNumero;
    }

    public void setVueltaGuiaNumero(int vueltaGuiaNumero) {
        this.vueltaGuiaNumero = vueltaGuiaNumero;
    }

    public Guia getVueltaGuiaIdGuia() {
        return vueltaGuiaIdGuia;
    }

    public void setVueltaGuiaIdGuia(Guia vueltaGuiaIdGuia) {
        this.vueltaGuiaIdGuia = vueltaGuiaIdGuia;
    }

    public Servicio getVueltaGuiaIdServicio() {
        return vueltaGuiaIdServicio;
    }

    public void setVueltaGuiaIdServicio(Servicio vueltaGuiaIdServicio) {
        this.vueltaGuiaIdServicio = vueltaGuiaIdServicio;
    }

    @XmlTransient
    public List<RegistroBoleto> getRegistroBoletoList() {
        return registroBoletoList;
    }

    public void setRegistroBoletoList(List<RegistroBoleto> registroBoletoList) {
        this.registroBoletoList = registroBoletoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vueltaGuiaId != null ? vueltaGuiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VueltaGuia)) {
            return false;
        }
        VueltaGuia other = (VueltaGuia) object;
        if ((this.vueltaGuiaId == null && other.vueltaGuiaId != null) || (this.vueltaGuiaId != null && !this.vueltaGuiaId.equals(other.vueltaGuiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.VueltaGuia[ vueltaGuiaId=" + vueltaGuiaId + " ]";
    }
    
}
