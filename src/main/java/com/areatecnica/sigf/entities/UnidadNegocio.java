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
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "unidad_negocio")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadNegocio.findAll", query = "SELECT u FROM UnidadNegocio u")
    , @NamedQuery(name = "UnidadNegocio.findAllByCuenta", query = "SELECT u FROM UnidadNegocio u WHERE u.unidadNegocioIdCuenta = :idCuenta ")
    , @NamedQuery(name = "UnidadNegocio.findNandu", query = "SELECT u FROM UnidadNegocio u WHERE u.unidadNegocioId <> 1")
    , @NamedQuery(name = "UnidadNegocio.findByUnidadNegocioId", query = "SELECT u FROM UnidadNegocio u WHERE u.unidadNegocioId = :unidadNegocioId")
    , @NamedQuery(name = "UnidadNegocio.findByUnidadNegocioNumero", query = "SELECT u FROM UnidadNegocio u WHERE u.unidadNegocioNumero = :unidadNegocioNumero")
    , @NamedQuery(name = "UnidadNegocio.findByUnidadNegocioFolio", query = "SELECT u FROM UnidadNegocio u WHERE u.unidadNegocioFolio = :unidadNegocioFolio")})
public class UnidadNegocio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unidad_negocio_id", nullable = false)
    private Integer unidadNegocioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidad_negocio_numero", nullable = false)
    private int unidadNegocioNumero;
    @Size(max = 45)
    @Column(name = "unidad_negocio_folio", length = 45)
    private String unidadNegocioFolio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoUnidadIdUnidadNegocio", fetch = FetchType.LAZY)
    private List<ContratoUnidad> contratoUnidadList;
    @OrderBy("busNumero")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busIdUnidadNegocio", fetch = FetchType.LAZY)
    private List<Bus> busList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "valorRolloUnidadIdUnidad", fetch = FetchType.LAZY)
    private List<ValorRolloUnidad> valorRolloUnidadList;
    @JoinColumn(name = "unidad_negocio_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta unidadNegocioIdCuenta;
    @JoinColumn(name = "unidad_negocio_id_operador_transporte", referencedColumnName = "operador_transporte_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OperadorTransporte unidadNegocioIdOperadorTransporte;
    @JoinColumn(name = "unidad_negocio_id_region", referencedColumnName = "region_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Region unidadNegocioIdRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioIdUnidad", fetch = FetchType.LAZY)
    private List<Servicio> servicioList;

    public UnidadNegocio() {
    }

    public UnidadNegocio(Integer unidadNegocioId) {
        this.unidadNegocioId = unidadNegocioId;
    }

    public UnidadNegocio(Integer unidadNegocioId, int unidadNegocioNumero) {
        this.unidadNegocioId = unidadNegocioId;
        this.unidadNegocioNumero = unidadNegocioNumero;
    }

    public Integer getUnidadNegocioId() {
        return unidadNegocioId;
    }

    public void setUnidadNegocioId(Integer unidadNegocioId) {
        this.unidadNegocioId = unidadNegocioId;
    }

    public int getUnidadNegocioNumero() {
        return unidadNegocioNumero;
    }

    public void setUnidadNegocioNumero(int unidadNegocioNumero) {
        this.unidadNegocioNumero = unidadNegocioNumero;
    }

    public String getUnidadNegocioFolio() {
        return unidadNegocioFolio;
    }

    public void setUnidadNegocioFolio(String unidadNegocioFolio) {
        this.unidadNegocioFolio = unidadNegocioFolio;
    }

    @XmlTransient
    public List<ContratoUnidad> getContratoUnidadList() {
        return contratoUnidadList;
    }

    public void setContratoUnidadList(List<ContratoUnidad> contratoUnidadList) {
        this.contratoUnidadList = contratoUnidadList;
    }

    @XmlTransient
    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    @XmlTransient
    public List<ValorRolloUnidad> getValorRolloUnidadList() {
        return valorRolloUnidadList;
    }

    public void setValorRolloUnidadList(List<ValorRolloUnidad> valorRolloUnidadList) {
        this.valorRolloUnidadList = valorRolloUnidadList;
    }

    public Cuenta getUnidadNegocioIdCuenta() {
        return unidadNegocioIdCuenta;
    }

    public void setUnidadNegocioIdCuenta(Cuenta unidadNegocioIdCuenta) {
        this.unidadNegocioIdCuenta = unidadNegocioIdCuenta;
    }

    public OperadorTransporte getUnidadNegocioIdOperadorTransporte() {
        return unidadNegocioIdOperadorTransporte;
    }

    public void setUnidadNegocioIdOperadorTransporte(OperadorTransporte unidadNegocioIdOperadorTransporte) {
        this.unidadNegocioIdOperadorTransporte = unidadNegocioIdOperadorTransporte;
    }

    public Region getUnidadNegocioIdRegion() {
        return unidadNegocioIdRegion;
    }

    public void setUnidadNegocioIdRegion(Region unidadNegocioIdRegion) {
        this.unidadNegocioIdRegion = unidadNegocioIdRegion;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidadNegocioId != null ? unidadNegocioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadNegocio)) {
            return false;
        }
        UnidadNegocio other = (UnidadNegocio) object;
        return (this.unidadNegocioId != null || other.unidadNegocioId == null) && (this.unidadNegocioId == null || this.unidadNegocioId.equals(other.unidadNegocioId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.UnidadNegocio[ unidadNegocioId=" + unidadNegocioId + " ]";
    }

}
