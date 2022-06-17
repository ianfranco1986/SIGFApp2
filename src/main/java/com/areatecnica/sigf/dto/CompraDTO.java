/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.dto;

import com.areatecnica.sigf.entities.Compra;
import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.entities.DetalleCompraCuenta;
import com.areatecnica.sigf.entities.Proveedor;
import com.areatecnica.sigf.entities.TipoDocumento;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ianfrancoconcha
 */
public class CompraDTO implements Serializable {

    private int nro;
    private int tipoDoc;
    private String tipoCompra;
    private String rutProveedor;
    private String razonSocial;
    private int folio;
    private Date fechaDocto;
    private Date fechaRecepcion;
    private Date fechaAcuse;
    private int montoExento;
    private int montoNeto;
    private int montoIVARecuperable;
    private int montoIvaNoRecuperable;
    private int codigoIVANoRec;
    private int montoTotal;
    private int montoNetoActivoFijo;
    private int ivaActivoFijo;
    private int ivaUsoComun;
    private int imptoSinDerechoACredito;
    private int ivaNoRetenido;
    private int tabacosPuros;
    private int tabacosCigarrillos;
    private int tabacosElaborados;
    private int NCEoNDE;
    private int codigoOtroImpuesto;
    private int valorOtroImpuesto;
    private int tasaOtroImpuesto;
    private boolean registrada;
    private String estadoProveedor;
    private String colorProveedor;
    private Proveedor proveedor; 
    private TipoDocumento tipoDocumento; 
    private List<CuentaMayor> cuentaItems; 
    private List<DetalleCompraCuenta> detalleCompraItems; 
    
    private Compra compra;

    private Map documento = new HashMap();

    public CompraDTO() {
    }

    public CompraDTO(int nro, int tipoDoc, String tipoCompra, String rutProveedor, String razonSocial, int folio, Date fechaDocto, Date fechaRecepcion, Date fechaAcuse, int montoExento, int montoNeto, int montoIVARecuperable, int montoIvaNoRecuperable, int codigoIVANoRec, int montoTotal, int montoNetoActivoFijo, int ivaActivoFijo, int ivaUsoComun, int imptoSinDerechoACredito, int ivaNoRetenido, int tabacosPuros, int tabacosCigarrillos, int tabacosElaborados, int NCEoNDE, int codigoOtroImpuesto, int valorOtroImpuesto, int tasaOtroImpuesto) {
        this.nro = nro;                                                         //OK          
        this.tipoDoc = tipoDoc;                                                 //compraTipoDocumentoId
        this.tipoCompra = tipoCompra;                                           //OK app
        this.rutProveedor = rutProveedor;                                       //compraProveedorId
        this.razonSocial = razonSocial;                                         //compraProveedorId
        this.folio = folio;                                                     //OK
        this.fechaDocto = fechaDocto;                                           //OK
        this.fechaRecepcion = fechaRecepcion;                                   //OK
        this.fechaAcuse = fechaAcuse;                                           //OK
        this.montoExento = montoExento;                                         //OK
        this.montoNeto = montoNeto;                                             //OK
        this.montoIVARecuperable = montoIVARecuperable;                         //OK app
        this.montoIvaNoRecuperable = montoIvaNoRecuperable;                     //OK
        this.codigoIVANoRec = codigoIVANoRec;                                   //NO
        this.montoTotal = montoTotal;                                           //OK
        this.montoNetoActivoFijo = montoNetoActivoFijo;                         //NO
        this.ivaActivoFijo = ivaActivoFijo;                                     //NO
        this.ivaUsoComun = ivaUsoComun;                                         //NO
        this.imptoSinDerechoACredito = imptoSinDerechoACredito;                 //NO
        this.ivaNoRetenido = ivaNoRetenido;                                     //NO
        this.tabacosPuros = tabacosPuros;                                       //NA
        this.tabacosCigarrillos = tabacosCigarrillos;                           //NA
        this.tabacosElaborados = tabacosElaborados;                             //NA
        this.NCEoNDE = NCEoNDE;                                                 //NO
        this.codigoOtroImpuesto = codigoOtroImpuesto;                           //NO
        this.valorOtroImpuesto = valorOtroImpuesto;                             //OK
        this.tasaOtroImpuesto = tasaOtroImpuesto;                               //NO

        this.compra = new Compra(fechaDocto, fechaAcuse, fechaRecepcion, folio, "", montoNeto, montoExento, montoIVARecuperable, codigoOtroImpuesto, montoTotal);

        this.documento.put(33, "FAE");
        this.documento.put(29, "FIni");
        this.documento.put(30, "FA");
        this.documento.put(32, "FE");
        this.documento.put(34, "FEE");
        this.documento.put(60, "NCre");
        this.documento.put(55, "NDeb");
        this.documento.put(61, "NCreE");
        this.documento.put(56, "NDebE");
        this.documento.put(914, "DecIng");
        this.documento.put(45, "FC");
        this.documento.put(46, "FCE");
        this.documento.put(40, "LiqF");
        this.documento.put(43, "LiqFE");
        this.documento.put(108, "SRF");
        this.documento.put(909, "FVM");
        this.documento.put(901, "FVETP");
        this.documento.put(911, "DIngZF");
        this.documento.put(904, "FT");
        this.documento.put(910, "STZF");

    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public String getRutProveedor() {
        return rutProveedor;
    }

    public void setRutProveedor(String rutProveedor) {
        this.rutProveedor = rutProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Date getFechaDocto() {
        return fechaDocto;
    }

    public void setFechaDocto(Date fechaDocto) {
        this.fechaDocto = fechaDocto;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaAcuse() {
        return fechaAcuse;
    }

    public void setFechaAcuse(Date fechaAcuse) {
        this.fechaAcuse = fechaAcuse;
    }

    public int getMontoExento() {
        return montoExento;
    }

    public void setMontoExento(int montoExento) {
        this.montoExento = montoExento;
    }

    public int getMontoNeto() {
        return montoNeto;
    }

    public void setMontoNeto(int montoNeto) {
        this.montoNeto = montoNeto;
    }

    public int getMontoIVARecuperable() {
        return montoIVARecuperable;
    }

    public void setMontoIVARecuperable(int montoIVARecuperable) {
        this.montoIVARecuperable = montoIVARecuperable;
    }

    public int getMontoIvaNoRecuperable() {
        return montoIvaNoRecuperable;
    }

    public void setMontoIvaNoRecuperable(int montoIvaNoRecuperable) {
        this.montoIvaNoRecuperable = montoIvaNoRecuperable;
    }

    public int getCodigoIVANoRec() {
        return codigoIVANoRec;
    }

    public void setCodigoIVANoRec(int codigoIVANoRec) {
        this.codigoIVANoRec = codigoIVANoRec;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getMontoNetoActivoFijo() {
        return montoNetoActivoFijo;
    }

    public void setMontoNetoActivoFijo(int montoNetoActivoFijo) {
        this.montoNetoActivoFijo = montoNetoActivoFijo;
    }

    public int getIvaActivoFijo() {
        return ivaActivoFijo;
    }

    public void setIvaActivoFijo(int ivaActivoFijo) {
        this.ivaActivoFijo = ivaActivoFijo;
    }

    public int getIvaUsoComun() {
        return ivaUsoComun;
    }

    public void setIvaUsoComun(int ivaUsoComun) {
        this.ivaUsoComun = ivaUsoComun;
    }

    public int getImptoSinDerechoACredito() {
        return imptoSinDerechoACredito;
    }

    public void setImptoSinDerechoACredito(int imptoSinDerechoACredito) {
        this.imptoSinDerechoACredito = imptoSinDerechoACredito;
    }

    public int getIvaNoRetenido() {
        return ivaNoRetenido;
    }

    public void setIvaNoRetenido(int ivaNoRetenido) {
        this.ivaNoRetenido = ivaNoRetenido;
    }

    public int getTabacosPuros() {
        return tabacosPuros;
    }

    public void setTabacosPuros(int tabacosPuros) {
        this.tabacosPuros = tabacosPuros;
    }

    public int getTabacosCigarrillos() {
        return tabacosCigarrillos;
    }

    public void setTabacosCigarrillos(int tabacosCigarrillos) {
        this.tabacosCigarrillos = tabacosCigarrillos;
    }

    public int getTabacosElaborados() {
        return tabacosElaborados;
    }

    public void setTabacosElaborados(int tabacosElaborados) {
        this.tabacosElaborados = tabacosElaborados;
    }

    public int getNCEoNDE() {
        return NCEoNDE;
    }

    public void setNCEoNDE(int NCEoNDE) {
        this.NCEoNDE = NCEoNDE;
    }

    public int getCodigoOtroImpuesto() {
        return codigoOtroImpuesto;
    }

    public void setCodigoOtroImpuesto(int codigoOtroImpuesto) {
        this.codigoOtroImpuesto = codigoOtroImpuesto;
    }

    public int getValorOtroImpuesto() {
        return valorOtroImpuesto;
    }

    public void setValorOtroImpuesto(int valorOtroImpuesto) {
        this.valorOtroImpuesto = valorOtroImpuesto;
    }

    public int getTasaOtroImpuesto() {
        return tasaOtroImpuesto;
    }

    public void setTasaOtroImpuesto(int tasaOtroImpuesto) {
        this.tasaOtroImpuesto = tasaOtroImpuesto;
    }

    public String getNombreTipoDocumento() {
        return (String) documento.get(this.tipoDoc);
    }

    public boolean getRegistrada() {
        return registrada;
    }

    public void setRegistrada(boolean registrada) {
        this.registrada = registrada;
    }

    public String getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(String estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

    public void setColorProveedor(String colorProveedor) {
        this.colorProveedor = colorProveedor;
    }

    public String getColorProveedor() {
        return colorProveedor;
    }

    @Override
    public String toString() {
        return "CompraDTO{" + "nro=" + nro + ", tipoDoc=" + tipoDoc + ", tipoCompra=" + tipoCompra + ", rutProveedor=" + rutProveedor + ", razonSocial=" + razonSocial + ", folio=" + folio + ", fechaDocto=" + fechaDocto + ", fechaRecepcion=" + fechaRecepcion + ", fechaAcuse=" + fechaAcuse + ", montoExento=" + montoExento + ", montoNeto=" + montoNeto + ", montoIVARecuperable=" + montoIVARecuperable + ", montoIvaNoRecuperable=" + montoIvaNoRecuperable + ", codigoIVANoRec=" + codigoIVANoRec + ", montoTotal=" + montoTotal + ", montoNetoActivoFijo=" + montoNetoActivoFijo + ", ivaActivoFijo=" + ivaActivoFijo + ", ivaUsoComun=" + ivaUsoComun + ", imptoSinDerechoACredito=" + imptoSinDerechoACredito + ", ivaNoRetenido=" + ivaNoRetenido + ", tabacosPuros=" + tabacosPuros + ", tabacosCigarrillos=" + tabacosCigarrillos + ", tabacosElaborados=" + tabacosElaborados + ", NCEoNDE=" + NCEoNDE + ", codigoOtroImpuesto=" + codigoOtroImpuesto + ", valorOtroImpuesto=" + valorOtroImpuesto + ", tasaOtroImpuesto=" + tasaOtroImpuesto + '}';
    }

}
