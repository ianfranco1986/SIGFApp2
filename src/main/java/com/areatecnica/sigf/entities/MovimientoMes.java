/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "movimiento_mes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoMes.findAll", query = "SELECT m FROM MovimientoMes m"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesId", query = "SELECT m FROM MovimientoMes m WHERE m.movimientoMesId = :movimientoMesId"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvto", query = "SELECT m FROM MovimientoMes m WHERE m.movimientoMesFechaMvto = :movimientoMesFechaMvto"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaLiquidacion", query = "SELECT m FROM MovimientoMes m WHERE m.movimientoMesFechaLiquidacion = :movimientoMesFechaLiquidacion"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesCuenta", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesCuentaBancoId = :movimientoMesCuentaId ORDER BY d.movimientoMesId ASC"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesIngresos", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesTipoMvtoId.tipoMovimientoAbono = true ORDER BY d.movimientoMesId ASC"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesEgresos", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesTipoMvtoId.tipoMovimientoDescuento = true ORDER BY d.movimientoMesId ASC"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesEmpresa", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesEmpresaId = :movimientoMesEmpresaId ORDER BY d.movimientoMesId ASC"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesTipo", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesTipoMvtoId = :movimientoMesMvtoId ORDER BY d.movimientoMesId ASC"),
    @NamedQuery(name = "MovimientoMes.findLastByCuenta", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesCuentaBancoId = :movimientoMesCuentaId ORDER BY d.movimientoMesNumeroDocumento DESC"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesMonto", query = "SELECT m FROM MovimientoMes m WHERE m.movimientoMesMonto = :movimientoMesMonto"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesDetalle", query = "SELECT m FROM MovimientoMes m WHERE m.movimientoMesDetalle = :movimientoMesDetalle"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesTipoDocumento", query = "SELECT m FROM MovimientoMes m WHERE m.movimientoMesTipoDocumento = :movimientoMesTipoDocumento"),
    @NamedQuery(name = "MovimientoMes.findByMovimientoMesNumeroDocumento", query = "SELECT m FROM MovimientoMes m WHERE m.movimientoMesNumeroDocumento = :movimientoMesNumeroDocumento")})
public class MovimientoMes extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movimiento_mes_id")
    private Integer movimientoMesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "movimiento_mes_fecha_mvto")
    @Temporal(TemporalType.DATE)
    private Date movimientoMesFechaMvto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "movimiento_mes_fecha_liquidacion")
    @Temporal(TemporalType.DATE)
    private Date movimientoMesFechaLiquidacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "movimiento_mes_monto")
    private int movimientoMesMonto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "movimiento_mes_detalle")
    private String movimientoMesDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "movimiento_mes_tipo_documento")
    private int movimientoMesTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "movimiento_mes_numero_documento")
    private int movimientoMesNumeroDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraMovimientoId")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anticipoMovimientoId")
    private List<Anticipo> anticipoList;
    @JoinColumn(name = "movimiento_mes_cuenta_banco_id", referencedColumnName = "cuenta_bancaria_id")
    @ManyToOne(optional = false)
    private CuentaBancaria movimientoMesCuentaBancoId;
    @JoinColumn(name = "movimiento_mes_empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne(optional = false)
    private Empresa movimientoMesEmpresaId;
    @JoinColumn(name = "movimiento_mes_tipo_movimiento_id", referencedColumnName = "tipo_movimiento_id")
    @ManyToOne(optional = false)
    private TipoMovimiento movimientoMesTipoMvtoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaMovimientoId")
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "honorarioMovimientoId")
    private List<Honorario> honorarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraPetroleoMovtId")
    private List<CompraPetroleo> compraPetroleoList;

    public MovimientoMes() {
    }

    public MovimientoMes(Integer movimientoMesId) {
        this.movimientoMesId = movimientoMesId;
    }

    public MovimientoMes(Integer movimientoMesId, Date movimientoMesFechaMvto, Date movimientoMesFechaLiquidacion, int movimientoMesMonto, String movimientoMesDetalle, int movimientoMesTipoDocumento, int movimientoMesNumeroDocumento) {
        this.movimientoMesId = movimientoMesId;
        this.movimientoMesFechaMvto = movimientoMesFechaMvto;
        this.movimientoMesFechaLiquidacion = movimientoMesFechaLiquidacion;
        this.movimientoMesMonto = movimientoMesMonto;
        this.movimientoMesDetalle = movimientoMesDetalle;
        this.movimientoMesTipoDocumento = movimientoMesTipoDocumento;
        this.movimientoMesNumeroDocumento = movimientoMesNumeroDocumento;
    }

    public Integer getMovimientoMesId() {
        return movimientoMesId;
    }

    public void setMovimientoMesId(Integer movimientoMesId) {
        this.movimientoMesId = movimientoMesId;
    }

    public TipoMovimiento getMovimientoMesMvtoId() {
        return movimientoMesTipoMvtoId;
    }

    public void setMovimientoMesMvtoId(TipoMovimiento movimientoMesTipoMvtoId) {
        this.movimientoMesTipoMvtoId = movimientoMesTipoMvtoId;
    }

    public Date getMovimientoMesFechaMvto() {
        return movimientoMesFechaMvto;
    }

    public void setMovimientoMesFechaMvto(Date movimientoMesFechaMvto) {
        this.movimientoMesFechaMvto = movimientoMesFechaMvto;
    }

    public Date getMovimientoMesFechaLiquidacion() {
        return movimientoMesFechaLiquidacion;
    }

    public void setMovimientoMesFechaLiquidacion(Date movimientoMesFechaLiquidacion) {
        this.movimientoMesFechaLiquidacion = movimientoMesFechaLiquidacion;
    }

    public int getMovimientoMesMonto() {
        return movimientoMesMonto;
    }

    public void setMovimientoMesMonto(int movimientoMesMonto) {
        this.movimientoMesMonto = movimientoMesMonto;
    }

    public String getMovimientoMesDetalle() {
        return movimientoMesDetalle;
    }

    public void setMovimientoMesDetalle(String movimientoMesDetalle) {
        this.movimientoMesDetalle = movimientoMesDetalle;
    }

    public int getMovimientoMesTipoDocumento() {
        return movimientoMesTipoDocumento;
    }

    public void setMovimientoMesTipoDocumento(int movimientoMesTipoDocumento) {
        this.movimientoMesTipoDocumento = movimientoMesTipoDocumento;
    }

    public int getMovimientoMesNumeroDocumento() {
        return movimientoMesNumeroDocumento;
    }

    public void setMovimientoMesNumeroDocumento(int movimientoMesNumeroDocumento) {
        this.movimientoMesNumeroDocumento = movimientoMesNumeroDocumento;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @XmlTransient
    public List<Anticipo> getAnticipoList() {
        return anticipoList;
    }

    public void setAnticipoList(List<Anticipo> anticipoList) {
        this.anticipoList = anticipoList;
    }

    public CuentaBancaria getMovimientoMesCuentaBancoId() {
        return movimientoMesCuentaBancoId;
    }

    public void setMovimientoMesCuentaBancoId(CuentaBancaria movimientoMesCuentaBancoId) {
        this.movimientoMesCuentaBancoId = movimientoMesCuentaBancoId;
    }

    public Empresa getMovimientoMesEmpresaId() {
        return movimientoMesEmpresaId;
    }

    public void setMovimientoMesEmpresaId(Empresa movimientoMesEmpresaId) {
        this.movimientoMesEmpresaId = movimientoMesEmpresaId;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<Honorario> getHonorarioList() {
        return honorarioList;
    }

    public void setHonorarioList(List<Honorario> honorarioList) {
        this.honorarioList = honorarioList;
    }

    @XmlTransient
    public List<CompraPetroleo> getCompraPetroleoList() {
        return compraPetroleoList;
    }

    public void setCompraPetroleoList(List<CompraPetroleo> compraPetroleoList) {
        this.compraPetroleoList = compraPetroleoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimientoMesId != null ? movimientoMesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoMes)) {
            return false;
        }
        MovimientoMes other = (MovimientoMes) object;
        return (this.movimientoMesId != null || other.movimientoMesId == null) && (this.movimientoMesId == null || this.movimientoMesId.equals(other.movimientoMesId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.MovimientoMes[ movimientoMesId=" + movimientoMesId + " ]";
    }

}
