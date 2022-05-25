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
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "egreso")
@EntityListeners(AuditListener.class)
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Egreso.findAll", query = "SELECT e FROM Egreso e"),
    @NamedQuery(name = "Egreso.findByEgresoId", query = "SELECT e FROM Egreso e WHERE e.egresoId = :egresoId"),
    @NamedQuery(name = "Egreso.findByCuenta", query = "SELECT e FROM Egreso e WHERE e.egresoIdCuenta = :idCuenta"),
    @NamedQuery(name = "Egreso.findAllActived", query = "SELECT e FROM Egreso e WHERE e.egresoActivo = true ORDER BY e.egresoNumeroOrden"),
    @NamedQuery(name = "Egreso.findByEgresoNombre", query = "SELECT e FROM Egreso e WHERE e.egresoNombre = :egresoNombre"),
    @NamedQuery(name = "Egreso.findByEgresoValorDefecto", query = "SELECT e FROM Egreso e WHERE e.egresoValorDefecto = :egresoValorDefecto"),
    @NamedQuery(name = "Egreso.findByEgresoPorcentaje", query = "SELECT e FROM Egreso e WHERE e.egresoPorcentaje = :egresoPorcentaje"),
    @NamedQuery(name = "Egreso.findByEgresoNumeroOrden", query = "SELECT e FROM Egreso e WHERE e.egresoNumeroOrden = :egresoNumeroOrden"),
    @NamedQuery(name = "Egreso.findByEgresoObligatorio", query = "SELECT e FROM Egreso e WHERE e.egresoObligatorio = :egresoObligatorio"),
    @NamedQuery(name = "Egreso.findByEgresoActivo", query = "SELECT e FROM Egreso e WHERE e.egresoActivo = :egresoActivo")})
public class Egreso extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_id", nullable = false)
    private Integer egresoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "egreso_nombre", nullable = false, length = 45)
    private String egresoNombre;
    @Column(name = "egreso_valor_defecto")
    private Integer egresoValorDefecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "egreso_porcentaje", precision = 10, scale = 2)
    private BigDecimal egresoPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_numero_orden", nullable = false)
    private int egresoNumeroOrden;
    @Column(name = "egreso_obligatorio")
    private Boolean egresoObligatorio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_activo", nullable = false)
    private boolean egresoActivo;
    @JoinColumn(name = "egreso_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta egresoIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoCajaRecaudacionIdEgreso")
    private List<EgresoCajaRecaudacion> egresoCajaRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoProcesoRecaudacionIdEgreso")
    private List<EgresoProcesoRecaudacion> egresoProcesoRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionGuiaIdEgreso")
    private List<RecaudacionGuia> recaudacionGuiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoBusIdEgreso")
    private List<EgresoBus> egresoBusList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoFlotaIdEgreso")
    private List<EgresoFlota> egresoFlotaList;

    public Egreso() {
    }

    public Egreso(Integer egresoId) {
        this.egresoId = egresoId;
    }

    public Egreso(Integer egresoId, String egresoNombre, int egresoNumeroOrden, boolean egresoActivo) {
        this.egresoId = egresoId;
        this.egresoNombre = egresoNombre;
        this.egresoNumeroOrden = egresoNumeroOrden;
        this.egresoActivo = egresoActivo;
    }

    public Integer getEgresoId() {
        return egresoId;
    }

    public void setEgresoId(Integer egresoId) {
        this.egresoId = egresoId;
    }

    public String getEgresoNombre() {
        return egresoNombre;
    }

    public void setEgresoNombre(String egresoNombre) {
        this.egresoNombre = egresoNombre;
    }

    public Integer getEgresoValorDefecto() {
        return egresoValorDefecto;
    }

    public void setEgresoValorDefecto(Integer egresoValorDefecto) {
        this.egresoValorDefecto = egresoValorDefecto;
    }

    public BigDecimal getEgresoPorcentaje() {
        return egresoPorcentaje;
    }

    public void setEgresoPorcentaje(BigDecimal egresoPorcentaje) {
        this.egresoPorcentaje = egresoPorcentaje;
    }

    public int getEgresoNumeroOrden() {
        return egresoNumeroOrden;
    }

    public void setEgresoNumeroOrden(int egresoNumeroOrden) {
        this.egresoNumeroOrden = egresoNumeroOrden;
    }

    public Boolean getEgresoObligatorio() {
        return egresoObligatorio;
    }

    public void setEgresoObligatorio(Boolean egresoObligatorio) {
        this.egresoObligatorio = egresoObligatorio;
    }

    public boolean getEgresoActivo() {
        return egresoActivo;
    }

    public void setEgresoActivo(boolean egresoActivo) {
        this.egresoActivo = egresoActivo;
    }

    public Cuenta getEgresoIdCuenta() {
        return egresoIdCuenta;
    }

    public void setEgresoIdCuenta(Cuenta egresoIdCuenta) {
        this.egresoIdCuenta = egresoIdCuenta;
    }

    @XmlTransient
    public List<EgresoCajaRecaudacion> getEgresoCajaRecaudacionList() {
        return egresoCajaRecaudacionList;
    }

    public void setEgresoCajaRecaudacionList(List<EgresoCajaRecaudacion> egresoCajaRecaudacionList) {
        this.egresoCajaRecaudacionList = egresoCajaRecaudacionList;
    }

    @XmlTransient
    public List<EgresoProcesoRecaudacion> getEgresoProcesoRecaudacionList() {
        return egresoProcesoRecaudacionList;
    }

    public void setEgresoProcesoRecaudacionList(List<EgresoProcesoRecaudacion> egresoProcesoRecaudacionList) {
        this.egresoProcesoRecaudacionList = egresoProcesoRecaudacionList;
    }

    @XmlTransient
    public List<RecaudacionGuia> getRecaudacionGuiaList() {
        return recaudacionGuiaList;
    }

    public void setRecaudacionGuiaList(List<RecaudacionGuia> recaudacionGuiaList) {
        this.recaudacionGuiaList = recaudacionGuiaList;
    }

    @XmlTransient
    public List<EgresoBus> getEgresoBusList() {
        return egresoBusList;
    }

    public void setEgresoBusList(List<EgresoBus> egresoBusList) {
        this.egresoBusList = egresoBusList;
    }

    @XmlTransient
    public List<EgresoFlota> getEgresoFlotaList() {
        return egresoFlotaList;
    }

    public void setEgresoFlotaList(List<EgresoFlota> egresoFlotaList) {
        this.egresoFlotaList = egresoFlotaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoId != null ? egresoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egreso)) {
            return false;
        }
        Egreso other = (Egreso) object;
        return (this.egresoId != null || other.egresoId == null) && (this.egresoId == null || this.egresoId.equals(other.egresoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Egreso[ egresoId=" + egresoId + " ]";
    }

}
