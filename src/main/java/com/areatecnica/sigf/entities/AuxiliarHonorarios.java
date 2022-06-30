/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "auxiliar_honorarios", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "AuxiliarHonorarios.findAll", query = "SELECT a FROM AuxiliarHonorarios a"),
    @NamedQuery(name = "AuxiliarHonorarios.findByAuxiliarHonorarioId", query = "SELECT a FROM AuxiliarHonorarios a WHERE a.auxiliarHonorarioId = :auxiliarHonorarioId")})
public class AuxiliarHonorarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auxiliar_honorario_id")
    private Integer auxiliarHonorarioId;
    @JoinColumn(name = "auxiliar_honorario_honorario_id", referencedColumnName = "honorario_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Honorario auxiliarHonorarioHonorarioId;
    @JoinColumn(name = "auxiliar_honorario_v_mov_id", referencedColumnName = "voucher_movimiento_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VoucherMovimiento auxiliarHonorarioVMovId;

    public AuxiliarHonorarios() {
    }

    public AuxiliarHonorarios(Integer auxiliarHonorarioId) {
        this.auxiliarHonorarioId = auxiliarHonorarioId;
    }

    public Integer getAuxiliarHonorarioId() {
        return auxiliarHonorarioId;
    }

    public void setAuxiliarHonorarioId(Integer auxiliarHonorarioId) {
        this.auxiliarHonorarioId = auxiliarHonorarioId;
    }

    public Honorario getAuxiliarHonorarioHonorarioId() {
        return auxiliarHonorarioHonorarioId;
    }

    public void setAuxiliarHonorarioHonorarioId(Honorario auxiliarHonorarioHonorarioId) {
        this.auxiliarHonorarioHonorarioId = auxiliarHonorarioHonorarioId;
    }

    public VoucherMovimiento getAuxiliarHonorarioVMovId() {
        return auxiliarHonorarioVMovId;
    }

    public void setAuxiliarHonorarioVMovId(VoucherMovimiento auxiliarHonorarioVMovId) {
        this.auxiliarHonorarioVMovId = auxiliarHonorarioVMovId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auxiliarHonorarioId != null ? auxiliarHonorarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuxiliarHonorarios)) {
            return false;
        }
        AuxiliarHonorarios other = (AuxiliarHonorarios) object;
        if ((this.auxiliarHonorarioId == null && other.auxiliarHonorarioId != null) || (this.auxiliarHonorarioId != null && !this.auxiliarHonorarioId.equals(other.auxiliarHonorarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AuxiliarHonorarios[ auxiliarHonorarioId=" + auxiliarHonorarioId + " ]";
    }
    
}
