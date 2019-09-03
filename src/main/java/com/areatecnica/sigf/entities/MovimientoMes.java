/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Cacheable(false)
@Table(name = "movimiento_mes", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoMes.findAll", query = "SELECT d FROM MovimientoMes d")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesId", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesId = :movimientoMesId")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvto", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto = :movimientoMesFechaMvto")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDates", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to ORDER BY d.movimientoMesId ASC")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesIngresos", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesMvtoId.tipoMovimientoAbono = 1 ORDER BY d.movimientoMesId ASC")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesEgresos", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesMvtoId.tipoMovimientoDescuento = 1 ORDER BY d.movimientoMesId ASC")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesEmpresa", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesEmpresaId = :movimientoMesEmpresaId ORDER BY d.movimientoMesId ASC")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesTipo", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesMvtoId = :movimientoMesMvtoId ORDER BY d.movimientoMesId ASC")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesFechaMvtoDatesCuenta", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesFechaMvto BETWEEN :from AND :to AND d.movimientoMesCuentaId = :movimientoMesCuentaId ORDER BY d.movimientoMesId ASC")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesDocumento", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesDocumento = :movimientoMesDocumento")
    , @NamedQuery(name = "MovimientoMes.findLastByCuenta", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesCuentaId = :movimientoMesCuentaId ORDER BY d.movimientoMesDocumento DESC")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesDetalle", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesDetalle = :movimientoMesDetalle")
    , @NamedQuery(name = "MovimientoMes.findByMovimientoMesMonto", query = "SELECT d FROM MovimientoMes d WHERE d.movimientoMesMonto = :movimientoMesMonto")})
public class MovimientoMes implements Serializable {

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
    @Column(name = "movimiento_mes_documento")
    private int movimientoMesDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "movimiento_mes_detalle")
    private String movimientoMesDetalle;
    @JoinColumn(name = "movimiento_mes_empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne(optional = false)
    private Empresa movimientoMesEmpresaId;
    @JoinColumn(name = "movimiento_mes_mvto_id", referencedColumnName = "tipo_movimiento_id")
    @ManyToOne(optional = false)
    private TipoMovimiento movimientoMesMvtoId;
    @JoinColumn(name = "movimiento_mes_cuenta_id", referencedColumnName = "cuenta_bancaria_id")
    @ManyToOne(optional = false)
    private CuentaBancaria movimientoMesCuentaId;
    @Transient
    private int saldo;
    @Transient
    private String liquidacion;

    public MovimientoMes() {
    }

    public MovimientoMes(Integer movimientoMesId) {
        this.movimientoMesId = movimientoMesId;
    }

    public MovimientoMes(Integer movimientoMesId, Date movimientoMesFechaMvto, Date movimientoMesFechaLiquidacion, int movimientoMesDocumento, String movimientoMesDetalle, int movimientoMesMonto) {
        this.movimientoMesId = movimientoMesId;
        this.movimientoMesFechaMvto = movimientoMesFechaMvto;
        this.movimientoMesFechaLiquidacion = movimientoMesFechaLiquidacion;
        this.movimientoMesDocumento = movimientoMesDocumento;
        this.movimientoMesDetalle = movimientoMesDetalle;
        this.movimientoMesMonto = movimientoMesMonto;

    }

    public Integer getMovimientoMesId() {
        return movimientoMesId;
    }

    public void setMovimientoMesId(Integer movimientoMesId) {
        this.movimientoMesId = movimientoMesId;
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

    public int getMovimientoMesDocumento() {
        return movimientoMesDocumento;
    }

    public void setMovimientoMesDocumento(int movimientoMesDocumento) {
        this.movimientoMesDocumento = movimientoMesDocumento;
    }

    public String getMovimientoMesDetalle() {
        return movimientoMesDetalle;
    }

    public void setMovimientoMesDetalle(String movimientoMesDetalle) {
        this.movimientoMesDetalle = movimientoMesDetalle;
    }

    public int getMovimientoMesMonto() {
        return movimientoMesMonto;
    }

    public void setMovimientoMesMonto(int movimientoMesMonto) {
        this.movimientoMesMonto = movimientoMesMonto;
    }

    public Empresa getMovimientoMesEmpresaId() {
        return movimientoMesEmpresaId;
    }

    public void setMovimientoMesEmpresaId(Empresa movimientoMesEmpresaId) {
        this.movimientoMesEmpresaId = movimientoMesEmpresaId;
    }

    public TipoMovimiento getMovimientoMesMvtoId() {
        return movimientoMesMvtoId;
    }

    public void setMovimientoMesMvtoId(TipoMovimiento movimientoMesMvtoId) {
        this.movimientoMesMvtoId = movimientoMesMvtoId;
    }

    public CuentaBancaria getMovimientoMesCuentaId() {
        return movimientoMesCuentaId;
    }

    public void setMovimientoMesCuentaId(CuentaBancaria movimientoMesCuentaId) {
        this.movimientoMesCuentaId = movimientoMesCuentaId;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setLiquidacion(String liquidacion) {
        this.liquidacion = liquidacion;
    }

    public String getLiquidacion() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.movimientoMesFechaLiquidacion);

        int mes = cal.get(Calendar.MONTH) + 1;
        int anio = cal.get(Calendar.YEAR);
        switch (mes) {
            case 1:
                this.liquidacion = "ENE";
                break;
            case 2:
                this.liquidacion = "FEB";
                break;
            case 3:
                this.liquidacion = "MAR";
                break;
            case 4:
                this.liquidacion = "ABR";
                break;
            case 5:
                this.liquidacion = "MAY";
                break;
            case 6:
                this.liquidacion = "JUN";
                break;
            case 7:
                this.liquidacion = "JUL";
                break;
            case 8:
                this.liquidacion = "AGO";
                break;
            case 9:
                this.liquidacion = "SEP";
                break;
            case 10:
                this.liquidacion = "OCT";
                break;
            case 11:
                this.liquidacion = "NOV";
                break;
            case 12:
                this.liquidacion = "DIC";
                break;

        }
        liquidacion = liquidacion + " " + anio;

        return liquidacion;
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
        if ((this.movimientoMesId == null && other.movimientoMesId != null) || (this.movimientoMesId != null && !this.movimientoMesId.equals(other.movimientoMesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.MovimientoMes[ movimientoMesId=" + movimientoMesId + " ]";
    }

}
