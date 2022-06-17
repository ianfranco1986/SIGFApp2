/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "compra", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByCompraId", query = "SELECT c FROM Compra c WHERE c.compraId = :compraId"),
    @NamedQuery(name = "Compra.findByFolioProveedorTipo", query = "SELECT c FROM Compra c WHERE c.compraFolio = :compraFolio AND c.compraProveedorId = :compraProveedorId AND c.compraTipoDocumentoId =:compraTipoDocumentoId"),
    @NamedQuery(name = "Compra.findByCompraBetweenDates", query = "SELECT c FROM Compra c WHERE c.compraFechaDocumento BETWEEN :from AND :to ORDER BY c.compraFechaDocumento"),
    @NamedQuery(name = "Compra.findByCompraFechaDocumento", query = "SELECT c FROM Compra c WHERE c.compraFechaDocumento = :compraFechaDocumento"),
    @NamedQuery(name = "Compra.findByCompraFechaAcuse", query = "SELECT c FROM Compra c WHERE c.compraFechaAcuse = :compraFechaAcuse"),
    @NamedQuery(name = "Compra.findByCompraFechaRecepcion", query = "SELECT c FROM Compra c WHERE c.compraFechaRecepcion = :compraFechaRecepcion"),
    @NamedQuery(name = "Compra.findByCompraFolio", query = "SELECT c FROM Compra c WHERE c.compraFolio = :compraFolio"),
    @NamedQuery(name = "Compra.findByCompraDescripcion", query = "SELECT c FROM Compra c WHERE c.compraDescripcion = :compraDescripcion"),
    @NamedQuery(name = "Compra.findByCompraNeto", query = "SELECT c FROM Compra c WHERE c.compraNeto = :compraNeto"),
    @NamedQuery(name = "Compra.findByCompraExento", query = "SELECT c FROM Compra c WHERE c.compraExento = :compraExento"),
    @NamedQuery(name = "Compra.findByCompraIvaRecuperable", query = "SELECT c FROM Compra c WHERE c.compraIvaRecuperable = :compraIvaRecuperable"),
    @NamedQuery(name = "Compra.findByCompraIvaNoRecuperable", query = "SELECT c FROM Compra c WHERE c.compraIvaNoRecuperable = :compraIvaNoRecuperable"),
    @NamedQuery(name = "Compra.findByCompraOtrosImpuestos", query = "SELECT c FROM Compra c WHERE c.compraOtrosImpuestos = :compraOtrosImpuestos"),
    @NamedQuery(name = "Compra.findByCompraTotal", query = "SELECT c FROM Compra c WHERE c.compraTotal = :compraTotal"),
    @NamedQuery(name = "Compra.findByCompraDg", query = "SELECT c FROM Compra c WHERE c.compraDg = :compraDg"),
    @NamedQuery(name = "Compra.findByCompraSm", query = "SELECT c FROM Compra c WHERE c.compraSm = :compraSm"),
    @NamedQuery(name = "Compra.findByCompraBr", query = "SELECT c FROM Compra c WHERE c.compraBr = :compraBr"),
    @NamedQuery(name = "Compra.findByCompraAf", query = "SELECT c FROM Compra c WHERE c.compraAf = :compraAf"),
    @NamedQuery(name = "Compra.findByCompraIuc", query = "SELECT c FROM Compra c WHERE c.compraIuc = :compraIuc"),
    @NamedQuery(name = "Compra.findByCompraInr", query = "SELECT c FROM Compra c WHERE c.compraInr = :compraInr")})
public class Compra extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compra_id")
    private Integer compraId;
    @Column(name = "compra_fecha_documento")
    @Temporal(TemporalType.DATE)
    private Date compraFechaDocumento;
    @Column(name = "compra_fecha_acuse")
    @Temporal(TemporalType.DATE)
    private Date compraFechaAcuse;
    @Column(name = "compra_fecha_recepcion")
    @Temporal(TemporalType.DATE)
    private Date compraFechaRecepcion;
    @Column(name = "compra_folio")
    private Integer compraFolio;
    @Column(name = "compra_descripcion")
    private String compraDescripcion;
    @Column(name = "compra_neto")
    private Integer compraNeto;
    @Column(name = "compra_exento")
    private Integer compraExento;
    @Column(name = "compra_iva_recuperable")
    private Integer compraIvaRecuperable;
    @Column(name = "compra_iva_no_recuperable")
    private Integer compraIvaNoRecuperable;
    @Column(name = "compra_otros_impuestos")
    private Integer compraOtrosImpuestos;
    @Column(name = "compra_total")
    private Integer compraTotal;
    @Column(name = "compra_dg")
    private boolean compraDg;  //Del giro
    @Column(name = "compra_sm")
    private boolean compraSm;  //Supermercado
    @Column(name = "compra_br")
    private boolean compraBr;  //Bien raiz
    @Column(name = "compra_af")
    private boolean compraAf;  //Activo Fijo
    @Column(name = "compra_iuc")
    private boolean compraIuc; //IVA uso común
    @Column(name = "compra_inr")
    private boolean compraInr; //IVA no recuperable
    @JoinColumn(name = "compra_codigo_iva_nr", referencedColumnName = "iva_no_recuperable_id")
    @ManyToOne(optional = false)
    private IvaNoRecuperable compraCodigoIvaNr;
    @JoinColumn(name = "compra_otros_imp_id", referencedColumnName = "otros_impuestos_id")
    @ManyToOne(optional = false)
    private OtrosImpuestos compraOtrosImpId;
    @JoinColumn(name = "compra_proveedor_id", referencedColumnName = "proveedor_id")
    @ManyToOne(optional = false)
    private Proveedor compraProveedorId;
    @JoinColumn(name = "compra_tipo_documento_id", referencedColumnName = "tipo_documento_id")
    @ManyToOne(optional = false)
    private TipoDocumento compraTipoDocumentoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auxiliarComprasCompraId")
    private List<AuxiliarCompras> auxiliarComprasList;

    public Compra() {
    }

    public Compra(Integer compraId) {
        this.compraId = compraId;
        this.compraDg = true;
    }

    public Compra(Date compraFechaDocumento, Date compraFechaAcuse, Date compraFechaRecepcion, Integer compraFolio, String compraDescripcion, Integer compraNeto, Integer compraExento, Integer compraIva, Integer compraOtrosImpuestos, Integer compraTotal) {
        this.compraFechaDocumento = compraFechaDocumento;
        this.compraFechaAcuse = compraFechaAcuse;
        this.compraFechaRecepcion = compraFechaRecepcion;
        this.compraFolio = compraFolio;
        this.compraDescripcion = compraDescripcion;
        this.compraNeto = compraNeto;
        this.compraExento = compraExento;
        this.compraIvaRecuperable = compraIva;
        this.compraOtrosImpuestos = compraOtrosImpuestos;
        this.compraTotal = compraTotal;
        this.compraDg = true;
    }

    public Integer getCompraId() {
        return compraId;
    }

    public void setCompraId(Integer compraId) {
        this.compraId = compraId;
    }

    public Date getCompraFechaDocumento() {
        return compraFechaDocumento;
    }

    public void setCompraFechaDocumento(Date compraFechaDocumento) {
        this.compraFechaDocumento = compraFechaDocumento;
    }

    public Date getCompraFechaAcuse() {
        return compraFechaAcuse;
    }

    public void setCompraFechaAcuse(Date compraFechaAcuse) {
        this.compraFechaAcuse = compraFechaAcuse;
    }

    public Date getCompraFechaRecepcion() {
        return compraFechaRecepcion;
    }

    public void setCompraFechaRecepcion(Date compraFechaRecepcion) {
        this.compraFechaRecepcion = compraFechaRecepcion;
    }

    public Integer getCompraFolio() {
        return compraFolio;
    }

    public void setCompraFolio(Integer compraFolio) {
        this.compraFolio = compraFolio;
    }

    public String getCompraDescripcion() {
        return compraDescripcion;
    }

    public void setCompraDescripcion(String compraDescripcion) {
        this.compraDescripcion = compraDescripcion;
    }

    public Integer getCompraNeto() {
        return compraNeto;
    }

    public void setCompraNeto(Integer compraNeto) {
        this.compraNeto = compraNeto;
    }

    public Integer getCompraExento() {
        return compraExento;
    }

    public void setCompraExento(Integer compraExento) {
        this.compraExento = compraExento;
    }

    public Integer getCompraIvaRecuperable() {
        return compraIvaRecuperable;
    }

    public void setCompraIvaRecuperable(Integer compraIvaRecuperable) {
        this.compraIvaRecuperable = compraIvaRecuperable;
    }

    public Integer getCompraIvaNoRecuperable() {
        return compraIvaNoRecuperable;
    }

    public void setCompraIvaNoRecuperable(Integer compraIvaNoRecuperable) {
        this.compraIvaNoRecuperable = compraIvaNoRecuperable;
    }

    public Integer getCompraOtrosImpuestos() {
        return compraOtrosImpuestos;
    }

    public void setCompraOtrosImpuestos(Integer compraOtrosImpuestos) {
        this.compraOtrosImpuestos = compraOtrosImpuestos;
    }

    public Integer getCompraTotal() {
        return compraTotal;
    }

    public void setCompraTotal(Integer compraTotal) {
        this.compraTotal = compraTotal;
    }

    public boolean getCompraDg() {
        return compraDg;
    }

    public void setCompraDg(boolean compraDg) {
        this.compraDg = compraDg;
    }

    public boolean getCompraSm() {
        return compraSm;
    }

    public void setCompraSm(boolean compraSm) {
        this.compraSm = compraSm;
    }

    public boolean getCompraBr() {
        return compraBr;
    }

    public void setCompraBr(boolean compraBr) {
        this.compraBr = compraBr;
    }

    public boolean getCompraAf() {
        return compraAf;
    }

    public void setCompraAf(boolean compraAf) {
        this.compraAf = compraAf;
    }

    public boolean getCompraIuc() {
        return compraIuc;
    }

    public void setCompraIuc(boolean compraIuc) {
        this.compraIuc = compraIuc;
    }

    public boolean getCompraInr() {
        return compraInr;
    }

    public void setCompraInr(boolean compraInr) {
        this.compraInr = compraInr;
    }

    public IvaNoRecuperable getCompraCodigoIvaNr() {
        return compraCodigoIvaNr;
    }

    public void setCompraCodigoIvaNr(IvaNoRecuperable compraCodigoIvaNr) {
        this.compraCodigoIvaNr = compraCodigoIvaNr;
    }

    public OtrosImpuestos getCompraOtrosImpId() {
        return compraOtrosImpId;
    }

    public void setCompraOtrosImpId(OtrosImpuestos compraOtrosImpId) {
        this.compraOtrosImpId = compraOtrosImpId;
    }

    public Proveedor getCompraProveedorId() {
        return compraProveedorId;
    }

    public void setCompraProveedorId(Proveedor compraProveedorId) {
        this.compraProveedorId = compraProveedorId;
    }

    public TipoDocumento getCompraTipoDocumentoId() {
        return compraTipoDocumentoId;
    }

    public void setCompraTipoDocumentoId(TipoDocumento compraTipoDocumentoId) {
        this.compraTipoDocumentoId = compraTipoDocumentoId;
    }

    public List<AuxiliarCompras> getAuxiliarComprasList() {
        return auxiliarComprasList;
    }

    public void setAuxiliarComprasList(List<AuxiliarCompras> auxiliarComprasList) {
        this.auxiliarComprasList = auxiliarComprasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraId != null ? compraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.compraId == null && other.compraId != null) || (this.compraId != null && !this.compraId.equals(other.compraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Compra[ compraId=" + compraId + " ]";
    }

}
