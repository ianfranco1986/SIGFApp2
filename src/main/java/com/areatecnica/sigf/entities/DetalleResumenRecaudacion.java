/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "detalle_resumen_recaudacion", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleResumenRecaudacion.findAll", query = "SELECT d FROM DetalleResumenRecaudacion d")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacionId", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacionId = :detalleResumenRecaudacionId")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion20000", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion20000 = :detalleResumenRecaudacion20000")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion10000", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion10000 = :detalleResumenRecaudacion10000")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion5000", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion5000 = :detalleResumenRecaudacion5000")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion2000", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion2000 = :detalleResumenRecaudacion2000")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion1000", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion1000 = :detalleResumenRecaudacion1000")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion500", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion500 = :detalleResumenRecaudacion500")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion100", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion100 = :detalleResumenRecaudacion100")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion50", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion50 = :detalleResumenRecaudacion50")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacion10", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacion10 = :detalleResumenRecaudacion10")})
public class DetalleResumenRecaudacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_resumen_recaudacion_id", nullable = false)
    private Integer detalleResumenRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_20000", nullable = false)
    private int detalleResumenRecaudacion20000;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_10000", nullable = false)
    private int detalleResumenRecaudacion10000;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_5000", nullable = false)
    private int detalleResumenRecaudacion5000;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_2000", nullable = false)
    private int detalleResumenRecaudacion2000;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_1000", nullable = false)
    private int detalleResumenRecaudacion1000;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_500", nullable = false)
    private int detalleResumenRecaudacion500;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_100", nullable = false)
    private int detalleResumenRecaudacion100;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_50", nullable = false)
    private int detalleResumenRecaudacion50;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_10", nullable = false)
    private int detalleResumenRecaudacion10;
    @JoinColumn(name = "detalle_resumen_recaudacion_id_resumen", referencedColumnName = "resumen_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private ResumenRecaudacion detalleResumenRecaudacionIdResumen;

    public DetalleResumenRecaudacion() {
    }

    public DetalleResumenRecaudacion(Integer detalleResumenRecaudacionId) {
        this.detalleResumenRecaudacionId = detalleResumenRecaudacionId;
    }

    public DetalleResumenRecaudacion(Integer detalleResumenRecaudacionId, int detalleResumenRecaudacion20000, int detalleResumenRecaudacion10000, int detalleResumenRecaudacion5000, int detalleResumenRecaudacion2000, int detalleResumenRecaudacion1000, int detalleResumenRecaudacion500, int detalleResumenRecaudacion100, int detalleResumenRecaudacion50, int detalleResumenRecaudacion10) {
        this.detalleResumenRecaudacionId = detalleResumenRecaudacionId;
        this.detalleResumenRecaudacion20000 = detalleResumenRecaudacion20000;
        this.detalleResumenRecaudacion10000 = detalleResumenRecaudacion10000;
        this.detalleResumenRecaudacion5000 = detalleResumenRecaudacion5000;
        this.detalleResumenRecaudacion2000 = detalleResumenRecaudacion2000;
        this.detalleResumenRecaudacion1000 = detalleResumenRecaudacion1000;
        this.detalleResumenRecaudacion500 = detalleResumenRecaudacion500;
        this.detalleResumenRecaudacion100 = detalleResumenRecaudacion100;
        this.detalleResumenRecaudacion50 = detalleResumenRecaudacion50;
        this.detalleResumenRecaudacion10 = detalleResumenRecaudacion10;
    }

    public Integer getDetalleResumenRecaudacionId() {
        return detalleResumenRecaudacionId;
    }

    public void setDetalleResumenRecaudacionId(Integer detalleResumenRecaudacionId) {
        this.detalleResumenRecaudacionId = detalleResumenRecaudacionId;
    }

    public int getDetalleResumenRecaudacion20000() {
        return detalleResumenRecaudacion20000;
    }

    public void setDetalleResumenRecaudacion20000(int detalleResumenRecaudacion20000) {
        this.detalleResumenRecaudacion20000 = detalleResumenRecaudacion20000;
    }

    public int getDetalleResumenRecaudacion10000() {
        return detalleResumenRecaudacion10000;
    }

    public void setDetalleResumenRecaudacion10000(int detalleResumenRecaudacion10000) {
        this.detalleResumenRecaudacion10000 = detalleResumenRecaudacion10000;
    }

    public int getDetalleResumenRecaudacion5000() {
        return detalleResumenRecaudacion5000;
    }

    public void setDetalleResumenRecaudacion5000(int detalleResumenRecaudacion5000) {
        this.detalleResumenRecaudacion5000 = detalleResumenRecaudacion5000;
    }

    public int getDetalleResumenRecaudacion2000() {
        return detalleResumenRecaudacion2000;
    }

    public void setDetalleResumenRecaudacion2000(int detalleResumenRecaudacion2000) {
        this.detalleResumenRecaudacion2000 = detalleResumenRecaudacion2000;
    }

    public int getDetalleResumenRecaudacion1000() {
        return detalleResumenRecaudacion1000;
    }

    public void setDetalleResumenRecaudacion1000(int detalleResumenRecaudacion1000) {
        this.detalleResumenRecaudacion1000 = detalleResumenRecaudacion1000;
    }

    public int getDetalleResumenRecaudacion500() {
        return detalleResumenRecaudacion500;
    }

    public void setDetalleResumenRecaudacion500(int detalleResumenRecaudacion500) {
        this.detalleResumenRecaudacion500 = detalleResumenRecaudacion500;
    }

    public int getDetalleResumenRecaudacion100() {
        return detalleResumenRecaudacion100;
    }

    public void setDetalleResumenRecaudacion100(int detalleResumenRecaudacion100) {
        this.detalleResumenRecaudacion100 = detalleResumenRecaudacion100;
    }

    public int getDetalleResumenRecaudacion50() {
        return detalleResumenRecaudacion50;
    }

    public void setDetalleResumenRecaudacion50(int detalleResumenRecaudacion50) {
        this.detalleResumenRecaudacion50 = detalleResumenRecaudacion50;
    }

    public int getDetalleResumenRecaudacion10() {
        return detalleResumenRecaudacion10;
    }

    public void setDetalleResumenRecaudacion10(int detalleResumenRecaudacion10) {
        this.detalleResumenRecaudacion10 = detalleResumenRecaudacion10;
    }

    public ResumenRecaudacion getDetalleResumenRecaudacionIdResumen() {
        return detalleResumenRecaudacionIdResumen;
    }

    public void setDetalleResumenRecaudacionIdResumen(ResumenRecaudacion detalleResumenRecaudacionIdResumen) {
        this.detalleResumenRecaudacionIdResumen = detalleResumenRecaudacionIdResumen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleResumenRecaudacionId != null ? detalleResumenRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleResumenRecaudacion)) {
            return false;
        }
        DetalleResumenRecaudacion other = (DetalleResumenRecaudacion) object;
        if ((this.detalleResumenRecaudacionId == null && other.detalleResumenRecaudacionId != null) || (this.detalleResumenRecaudacionId != null && !this.detalleResumenRecaudacionId.equals(other.detalleResumenRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleResumenRecaudacion[ detalleResumenRecaudacionId=" + detalleResumenRecaudacionId + " ]";
    }
    
}
