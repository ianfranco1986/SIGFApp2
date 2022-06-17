/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.CompraBoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.InventarioInternoDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import com.areatecnica.sigf.entities.InventarioInterno;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "boletosAdquiridos")
@ViewScoped
public class BoletosAdquiridos implements Serializable {

    private CompraBoleto compraBoletos;
    private DetalleCompraBoleto detalle;
    private BoletosAdquiridosHelper selected;
    private List<BoletosAdquiridosHelper> items;
    private List<BoletosAdquiridosHelper> selectedItems;
    private List<InventarioInterno> listToSave;
    private int totalCompra;
    private String totalFormatted;

    private Date fecha;
    private static final String pattern = "###,###.###";
    private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    /**
     * Creates a new instance of BoletosAdquiridos
     */
    public BoletosAdquiridos() {
    }

    @PostConstruct
    public void init() {
        this.compraBoletos = new CompraBoleto();
        this.compraBoletos.setDetalleCompraBoletoList(new ArrayList<>());
        this.items = new ArrayList<>();
        this.fecha = new Date();
        this.detalle = new DetalleCompraBoleto();

    }

    public CompraBoleto getCompraBoletos() {
        return compraBoletos;
    }

    public void setCompraBoletos(CompraBoleto compraBoletos) {
        this.compraBoletos = compraBoletos;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public BoletosAdquiridosHelper getSelected() {
        return selected;
    }

    public void setSelected(BoletosAdquiridosHelper selected) {
        this.selected = selected;
    }

    public List<BoletosAdquiridosHelper> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<BoletosAdquiridosHelper> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void setItems(List<BoletosAdquiridosHelper> items) {
        this.items = items;
    }

    public List<BoletosAdquiridosHelper> getItems() {
        return items;
    }

    public int getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(int totalCompra) {
        this.totalCompra = totalCompra;
    }

    public void setDetalle(DetalleCompraBoleto detalle) {
        this.detalle = detalle;
    }

    public DetalleCompraBoleto getDetalle() {
        return detalle;
    }

    public String getTotalFormatted() {
        return this.decimalFormat.format(this.totalCompra);
    }

    public void setTotalFormatted(String totalFormatted) {
        this.totalFormatted = totalFormatted;
    }

    public void delete() {
        if (this.selected != null) {
            this.items.remove(this.selected);
            this.totalCompra +=this.selected.boletoTotal;
            this.selected = null;
        }
    }

    public void deleteSelectedGuias() {
        if (hasSelectedGuias()) {
            this.items.removeAll(this.selectedItems);
            this.totalCompra = 0; 
            this.selectedItems = new ArrayList<>();
        }
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedGuias()) {
            int size = this.selectedItems.size();
            return size > 1 ? size + " filas seleccionadas" : "1 fila seleccionada";
        }

        return "Eliminar";
    }

    public boolean hasSelectedGuias() {
        return this.selectedItems != null && !this.selectedItems.isEmpty();
    }

    public void addDetalle(ActionEvent event) {
        if (this.detalle != null) {
            if (this.detalle.getDetalleCompraBoletoIdBoleto() != null) {
                if (!this.detalle.getDetalleCompraBoletoIdentificador().equals("")) {
                    if (!this.detalle.getDetalleCompraBoletoSerie().equals("")) {
                        if (this.detalle.getDetalleCompraBoletoCantidadRollos() > 0 || this.detalle.getDetalleCompraBoletoTotal() > 0) {

                            BoletosAdquiridosHelper bb = new BoletosAdquiridosHelper();
                            bb.setId(new Random().nextInt());
                            bb.setBoletoCantidadRollos(this.detalle.getDetalleCompraBoletoCantidadRollos());
                            bb.setBoletoIdentificador(this.detalle.getDetalleCompraBoletoIdentificador());
                            bb.setBoletoSerie(this.detalle.getDetalleCompraBoletoSerie());
                            bb.setBoleto(this.detalle.getDetalleCompraBoletoIdBoleto());
                            bb.setBoletoTotal(this.detalle.getDetalleCompraBoletoCantidadRollos() * this.detalle.getDetalleCompraBoletoTotal());
                            this.items.add(bb);
                            
                            this.totalCompra +=bb.boletoTotal;
                            
                        } else {
                            JsfUtil.addErrorMessage("Debe ingresar un valor Mayor que cero");
                        }
                    } else {
                        JsfUtil.addErrorMessage("Debe ingresar la Serie");
                    }
                } else {
                    JsfUtil.addErrorMessage("Debe ingresar el Identificador");
                }
            } else {
                JsfUtil.addErrorMessage("Debe Seleccionar el Boleto");
            }

            this.detalle.setDetalleCompraBoletoIdBoleto(null);
            this.detalle.setDetalleCompraBoletoSerie("0");
            this.detalle.setDetalleCompraBoletoIdentificador("");

        } else {
            JsfUtil.addErrorMessage("Error al procesar el formulario");
        }

    }

    public void saveNew(ActionEvent event) {
        this.listToSave = new ArrayList<>();
        //
        int totalCompra = 0;
        this.compraBoletos.setDetalleCompraBoletoList(new ArrayList<>());
        List<InventarioInterno> inventarioInterno = new ArrayList<>();
        for (BoletosAdquiridosHelper d : this.items) {

            int serieInicial = Integer.parseInt(d.getBoletoSerie());
            totalCompra = totalCompra + d.getBoletoTotal();
            for (int i = 0; i < d.getBoletoCantidadRollos(); i++) {

                InventarioInterno ii = new InventarioInterno();
                ii.setInventarioInternoEstado(Boolean.FALSE);

                ii.setInventarioInternoIdBoleto(d.getBoleto());
                ii.setInventarioInternoIdentificador(d.getBoletoIdentificador());
                ii.setInventarioInternoSerie(serieInicial);
                serieInicial += 1000;

                //new IInventarioInternoDaoImpl().update(ii);
                System.err.println("Llega al update");
                listToSave.add(ii);
            }
            DetalleCompraBoleto dcb = new DetalleCompraBoleto();
            dcb.setDetalleCompraBoletoCantidadRollos(d.boletoCantidadRollos);
            dcb.setDetalleCompraBoletoIdBoleto(d.boleto);
            dcb.setDetalleCompraBoletoIdCompraBoleto(compraBoletos);
            dcb.setDetalleCompraBoletoIdentificador(d.boletoIdentificador);
            dcb.setDetalleCompraBoletoSerie(d.boletoSerie);
            dcb.setDetalleCompraBoletoTotal(d.boletoCantidadRollos * d.boletoTotal);

            this.compraBoletos.getDetalleCompraBoletoList().add(dcb);

        }
        
        this.compraBoletos.setCompraBoletoIdCuenta(JsfUtil.getCurrentUser().getUsuarioIdCuenta());
        this.compraBoletos.setCompraBoletoFecha(fecha);
        this.compraBoletos.setCompraBoletoTotal(totalCompra);

        CompraBoleto c = new CompraBoletoDaoImpl().create(this.compraBoletos);

        if (c != null) {
            for (InventarioInterno i : this.listToSave) {
                new InventarioInternoDaoImpl().create(i);
            }

        }

        c = null;
        this.listToSave = new ArrayList<>();
        this.items = new ArrayList<>();
        this.selected = null;
        this.selectedItems = new ArrayList<>();
        this.compraBoletos = new CompraBoleto();
        this.compraBoletos.setCompraBoletoFecha(new Date());
        this.totalCompra = 0; 

        JsfUtil.addSuccessMessage("Se ha agregado una nueva Compra de Boletos");
        JsfUtil.addSuccessMessage("Se ha actualizado el Inventario Interno");
    }

    public static class BoletosAdquiridosHelper implements Serializable {

        private static final long serialVersionUID = 1L;
        private Integer id;
        private String boletoIdentificador;
        private String boletoSerie;
        private int boletoCantidadRollos;
        private int boletoTotal;
        private Boleto boleto;

        public BoletosAdquiridosHelper() {
        }

        public BoletosAdquiridosHelper(String boletoIdentificador, String boletoSerie, int boletoCantidadRollos, int boletoTotal, Boleto boleto) {
            this.boletoIdentificador = boletoIdentificador;
            this.boletoSerie = boletoSerie;
            this.boletoCantidadRollos = boletoCantidadRollos;
            this.boletoTotal = boletoTotal;
            this.boleto = boleto;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public String getBoletoIdentificador() {
            return boletoIdentificador;
        }

        public void setBoletoIdentificador(String boletoIdentificador) {
            this.boletoIdentificador = boletoIdentificador;
        }

        public String getBoletoSerie() {
            return boletoSerie;
        }

        public void setBoletoSerie(String boletoSerie) {
            this.boletoSerie = boletoSerie;
        }

        public int getBoletoCantidadRollos() {
            return boletoCantidadRollos;
        }

        public void setBoletoCantidadRollos(int boletoCantidadRollos) {
            this.boletoCantidadRollos = boletoCantidadRollos;
        }

        public int getBoletoTotal() {
            return boletoTotal;
        }

        public void setBoletoTotal(int boletoTotal) {
            this.boletoTotal = boletoTotal;
        }

        public Boleto getBoleto() {
            return boleto;
        }

        public void setBoleto(Boleto IdBoleto) {
            this.boleto = IdBoleto;
        }

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

    }

}
