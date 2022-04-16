/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "resumen_recaudacion", catalog = "sigfdb", schema = "")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResumenRecaudacion.findAll", query = "SELECT r FROM ResumenRecaudacion r"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionId", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionId = :resumenRecaudacionId"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionFecha", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionFecha = :resumenRecaudacionFecha"),
    @NamedQuery(name = "ResumenRecaudacion.findByCajaProcesoDate", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionIdCaja = :resumenRecaudacionIdCaja AND r.resumenRecaudacionIdProceso = :resumenRecaudacionIdProceso AND r.resumenRecaudacionFecha = :resumenRecaudacionFecha"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionTotal", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionTotal = :resumenRecaudacionTotal"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionTieneTransporte", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionTieneTransporte = :resumenRecaudacionTieneTransporte"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionCerrado", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionCerrado = :resumenRecaudacionCerrado"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion20000", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion20000 = :resumenRecaudacion20000"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion10000", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion10000 = :resumenRecaudacion10000"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion5000", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion5000 = :resumenRecaudacion5000"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion2000", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion2000 = :resumenRecaudacion2000"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion1000", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion1000 = :resumenRecaudacion1000"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion500", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion500 = :resumenRecaudacion500"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion100", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion100 = :resumenRecaudacion100"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion50", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion50 = :resumenRecaudacion50"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacion10", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacion10 = :resumenRecaudacion10"),
    @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionCheques", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionCheques = :resumenRecaudacionCheques")})
public class ResumenRecaudacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "resumen_recaudacion_id")
    private Integer resumenRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_fecha")
    @Temporal(TemporalType.DATE)
    private Date resumenRecaudacionFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_total")
    private int resumenRecaudacionTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_tiene_transporte")
    private int resumenRecaudacionTieneTransporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_cerrado")
    private boolean resumenRecaudacionCerrado;
    @Column(name = "resumen_recaudacion_20000")
    private Integer resumenRecaudacion20000;
    @Column(name = "resumen_recaudacion_10000")
    private Integer resumenRecaudacion10000;
    @Column(name = "resumen_recaudacion_5000")
    private Integer resumenRecaudacion5000;
    @Column(name = "resumen_recaudacion_2000")
    private Integer resumenRecaudacion2000;
    @Column(name = "resumen_recaudacion_1000")
    private Integer resumenRecaudacion1000;
    @Column(name = "resumen_recaudacion_500")
    private Integer resumenRecaudacion500;
    @Column(name = "resumen_recaudacion_100")
    private Integer resumenRecaudacion100;
    @Column(name = "resumen_recaudacion_50")
    private Integer resumenRecaudacion50;
    @Column(name = "resumen_recaudacion_10")
    private Integer resumenRecaudacion10;
    @Column(name = "resumen_recaudacion_cheques")
    private Integer resumenRecaudacionCheques;
    @JoinColumn(name = "resumen_recaudacion_id_caja", referencedColumnName = "caja_recaudacion_id")
    @ManyToOne(optional = false)
    private CajaRecaudacion resumenRecaudacionIdCaja;
    @JoinColumn(name = "resumen_recaudacion_id_proceso", referencedColumnName = "proceso_recaudacion_id")
    @ManyToOne(optional = false)
    private ProcesoRecaudacion resumenRecaudacionIdProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleDepositoRecaudacionIdResumen")
    private List<DetalleDepositoRecaudacion> detalleDepositoRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ctvResumenIdResumenRecaudacion")
    private List<CtvResumen> ctvResumenList;

    public ResumenRecaudacion() {
    }

    public ResumenRecaudacion(Integer resumenRecaudacionId) {
        this.resumenRecaudacionId = resumenRecaudacionId;
    }

    public ResumenRecaudacion(Integer resumenRecaudacionId, Date resumenRecaudacionFecha, int resumenRecaudacionTotal, int resumenRecaudacionTieneTransporte, boolean resumenRecaudacionCerrado) {
        this.resumenRecaudacionId = resumenRecaudacionId;
        this.resumenRecaudacionFecha = resumenRecaudacionFecha;
        this.resumenRecaudacionTotal = resumenRecaudacionTotal;
        this.resumenRecaudacionTieneTransporte = resumenRecaudacionTieneTransporte;
        this.resumenRecaudacionCerrado = resumenRecaudacionCerrado;
    }

    public Integer getResumenRecaudacionId() {
        return resumenRecaudacionId;
    }

    public void setResumenRecaudacionId(Integer resumenRecaudacionId) {
        this.resumenRecaudacionId = resumenRecaudacionId;
    }

    public Date getResumenRecaudacionFecha() {
        return resumenRecaudacionFecha;
    }

    public void setResumenRecaudacionFecha(Date resumenRecaudacionFecha) {
        this.resumenRecaudacionFecha = resumenRecaudacionFecha;
    }

    public int getResumenRecaudacionTotal() {
        return resumenRecaudacionTotal;
    }

    public void setResumenRecaudacionTotal(int resumenRecaudacionTotal) {
        this.resumenRecaudacionTotal = resumenRecaudacionTotal;
    }

    public int getResumenRecaudacionTieneTransporte() {
        return resumenRecaudacionTieneTransporte;
    }

    public void setResumenRecaudacionTieneTransporte(int resumenRecaudacionTieneTransporte) {
        this.resumenRecaudacionTieneTransporte = resumenRecaudacionTieneTransporte;
    }

    public boolean getResumenRecaudacionCerrado() {
        return resumenRecaudacionCerrado;
    }

    public void setResumenRecaudacionCerrado(boolean resumenRecaudacionCerrado) {
        this.resumenRecaudacionCerrado = resumenRecaudacionCerrado;
    }

    public Integer getResumenRecaudacion20000() {
        return resumenRecaudacion20000;
    }

    public void setResumenRecaudacion20000(Integer resumenRecaudacion20000) {
        this.resumenRecaudacion20000 = resumenRecaudacion20000;
    }

    public Integer getResumenRecaudacion10000() {
        return resumenRecaudacion10000;
    }

    public void setResumenRecaudacion10000(Integer resumenRecaudacion10000) {
        this.resumenRecaudacion10000 = resumenRecaudacion10000;
    }

    public Integer getResumenRecaudacion5000() {
        return resumenRecaudacion5000;
    }

    public void setResumenRecaudacion5000(Integer resumenRecaudacion5000) {
        this.resumenRecaudacion5000 = resumenRecaudacion5000;
    }

    public Integer getResumenRecaudacion2000() {
        return resumenRecaudacion2000;
    }

    public void setResumenRecaudacion2000(Integer resumenRecaudacion2000) {
        this.resumenRecaudacion2000 = resumenRecaudacion2000;
    }

    public Integer getResumenRecaudacion1000() {
        return resumenRecaudacion1000;
    }

    public void setResumenRecaudacion1000(Integer resumenRecaudacion1000) {
        this.resumenRecaudacion1000 = resumenRecaudacion1000;
    }

    public Integer getResumenRecaudacion500() {
        return resumenRecaudacion500;
    }

    public void setResumenRecaudacion500(Integer resumenRecaudacion500) {
        this.resumenRecaudacion500 = resumenRecaudacion500;
    }

    public Integer getResumenRecaudacion100() {
        return resumenRecaudacion100;
    }

    public void setResumenRecaudacion100(Integer resumenRecaudacion100) {
        this.resumenRecaudacion100 = resumenRecaudacion100;
    }

    public Integer getResumenRecaudacion50() {
        return resumenRecaudacion50;
    }

    public void setResumenRecaudacion50(Integer resumenRecaudacion50) {
        this.resumenRecaudacion50 = resumenRecaudacion50;
    }

    public Integer getResumenRecaudacion10() {
        return resumenRecaudacion10;
    }

    public void setResumenRecaudacion10(Integer resumenRecaudacion10) {
        this.resumenRecaudacion10 = resumenRecaudacion10;
    }

    public Integer getResumenRecaudacionCheques() {
        return resumenRecaudacionCheques;
    }

    public void setResumenRecaudacionCheques(Integer resumenRecaudacionCheques) {
        this.resumenRecaudacionCheques = resumenRecaudacionCheques;
    }

    public CajaRecaudacion getResumenRecaudacionIdCaja() {
        return resumenRecaudacionIdCaja;
    }

    public void setResumenRecaudacionIdCaja(CajaRecaudacion resumenRecaudacionIdCaja) {
        this.resumenRecaudacionIdCaja = resumenRecaudacionIdCaja;
    }

    public void setResumenRecaudacionIdProceso(ProcesoRecaudacion resumenRecaudacionIdProceso) {
        this.resumenRecaudacionIdProceso = resumenRecaudacionIdProceso;
    }

    public ProcesoRecaudacion getResumenRecaudacionIdProceso() {
        return resumenRecaudacionIdProceso;
    }

    @XmlTransient
    public List<DetalleDepositoRecaudacion> getDetalleDepositoRecaudacionList() {
        return detalleDepositoRecaudacionList;
    }

    public void setDetalleDepositoRecaudacionList(List<DetalleDepositoRecaudacion> detalleDepositoRecaudacionList) {
        this.detalleDepositoRecaudacionList = detalleDepositoRecaudacionList;
    }

    @XmlTransient
    public List<CtvResumen> getCtvResumenList() {
        return ctvResumenList;
    }

    public void setCtvResumenList(List<CtvResumen> ctvResumenList) {
        this.ctvResumenList = ctvResumenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resumenRecaudacionId != null ? resumenRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenRecaudacion)) {
            return false;
        }
        ResumenRecaudacion other = (ResumenRecaudacion) object;
        if ((this.resumenRecaudacionId == null && other.resumenRecaudacionId != null) || (this.resumenRecaudacionId != null && !this.resumenRecaudacionId.equals(other.resumenRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ResumenRecaudacion[ resumenRecaudacionId=" + resumenRecaudacionId + " ]";
    }

}
