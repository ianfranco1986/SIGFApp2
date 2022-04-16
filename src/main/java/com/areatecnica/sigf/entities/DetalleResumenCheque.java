/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "detalle_resumen_cheque", catalog = "sigfdb", schema = "")
@EntityListeners(AuditListener.class)
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "DetalleResumenCheque.findAll", query = "SELECT d FROM DetalleResumenCheque d")
    , @NamedQuery(name = "DetalleResumenCheque.findByDetalleResumenChequeId", query = "SELECT d FROM DetalleResumenCheque d WHERE d.detalleResumenChequeId = :detalleResumenChequeId")
    , @NamedQuery(name = "DetalleResumenCheque.findByDetalleResumenChequeNumeroCuenta", query = "SELECT d FROM DetalleResumenCheque d WHERE d.detalleResumenChequeNumeroCuenta = :detalleResumenChequeNumeroCuenta")
    , @NamedQuery(name = "DetalleResumenCheque.findByDetalleResumenChequeTitular", query = "SELECT d FROM DetalleResumenCheque d WHERE d.detalleResumenChequeTitular = :detalleResumenChequeTitular")
    , @NamedQuery(name = "DetalleResumenCheque.findByDetalleResumenChequeMonto", query = "SELECT d FROM DetalleResumenCheque d WHERE d.detalleResumenChequeMonto = :detalleResumenChequeMonto")})
public class DetalleResumenCheque extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_resumen_cheque_id", nullable = false)
    private Integer detalleResumenChequeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_cheque_numero_cuenta", nullable = false)
    private int detalleResumenChequeNumeroCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "detalle_resumen_cheque_titular", nullable = false, length = 45)
    private String detalleResumenChequeTitular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_cheque_monto", nullable = false)
    private int detalleResumenChequeMonto;
    @JoinColumn(name = "detalle_resumen_cheque_id_banco", referencedColumnName = "banco_id", nullable = false)
    @ManyToOne(optional = false)
    private Banco detalleResumenChequeIdBanco;
    @JoinColumn(name = "detalle_resumen_cheque_id_resumen", referencedColumnName = "resumen_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private ResumenRecaudacion detalleResumenChequeIdResumen;

    public DetalleResumenCheque() {
    }

    public DetalleResumenCheque(Integer detalleResumenChequeId) {
        this.detalleResumenChequeId = detalleResumenChequeId;
    }

    public DetalleResumenCheque(Integer detalleResumenChequeId, int detalleResumenChequeNumeroCuenta, String detalleResumenChequeTitular, int detalleResumenChequeMonto) {
        this.detalleResumenChequeId = detalleResumenChequeId;
        this.detalleResumenChequeNumeroCuenta = detalleResumenChequeNumeroCuenta;
        this.detalleResumenChequeTitular = detalleResumenChequeTitular;
        this.detalleResumenChequeMonto = detalleResumenChequeMonto;
    }

    public Integer getDetalleResumenChequeId() {
        return detalleResumenChequeId;
    }

    public void setDetalleResumenChequeId(Integer detalleResumenChequeId) {
        this.detalleResumenChequeId = detalleResumenChequeId;
    }

    public int getDetalleResumenChequeNumeroCuenta() {
        return detalleResumenChequeNumeroCuenta;
    }

    public void setDetalleResumenChequeNumeroCuenta(int detalleResumenChequeNumeroCuenta) {
        this.detalleResumenChequeNumeroCuenta = detalleResumenChequeNumeroCuenta;
    }

    public String getDetalleResumenChequeTitular() {
        return detalleResumenChequeTitular;
    }

    public void setDetalleResumenChequeTitular(String detalleResumenChequeTitular) {
        this.detalleResumenChequeTitular = detalleResumenChequeTitular;
    }

    public int getDetalleResumenChequeMonto() {
        return detalleResumenChequeMonto;
    }

    public void setDetalleResumenChequeMonto(int detalleResumenChequeMonto) {
        this.detalleResumenChequeMonto = detalleResumenChequeMonto;
    }

    public Banco getDetalleResumenChequeIdBanco() {
        return detalleResumenChequeIdBanco;
    }

    public void setDetalleResumenChequeIdBanco(Banco detalleResumenChequeIdBanco) {
        this.detalleResumenChequeIdBanco = detalleResumenChequeIdBanco;
    }

    public ResumenRecaudacion getDetalleResumenChequeIdResumen() {
        return detalleResumenChequeIdResumen;
    }

    public void setDetalleResumenChequeIdResumen(ResumenRecaudacion detalleResumenChequeIdResumen) {
        this.detalleResumenChequeIdResumen = detalleResumenChequeIdResumen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleResumenChequeId != null ? detalleResumenChequeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleResumenCheque)) {
            return false;
        }
        DetalleResumenCheque other = (DetalleResumenCheque) object;
        if ((this.detalleResumenChequeId == null && other.detalleResumenChequeId != null) || (this.detalleResumenChequeId != null && !this.detalleResumenChequeId.equals(other.detalleResumenChequeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleResumenCheque[ detalleResumenChequeId=" + detalleResumenChequeId + " ]";
    }
    
}
