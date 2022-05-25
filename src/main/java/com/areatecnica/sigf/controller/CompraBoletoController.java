package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ICompraBoletoDao;
import com.areatecnica.sigf.dao.impl.ICompraBoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.IInventarioInternoDaoImpl;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import com.areatecnica.sigf.entities.InventarioInterno;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Named(value = "compraBoletoController")
@ViewScoped
public class CompraBoletoController extends AbstractController<CompraBoleto> {

//    @Inject
//    private CuentaController compraBoletoIdCuentaController;
//
//    @Inject
//    private InventarioInternoFacade inventarioInternoFacade;
    private List<CompraBoleto> items;
    private CompraBoleto selected;
    private DetalleCompraBoleto detalleCompraBoleto;
    private DetalleCompraBoleto selectedItem;
    private ICompraBoletoDao compraBoletoDao;
    private int totalCompra;
    private String totalFormatted;

    private static final String pattern = "###,###.###";
    private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    public CompraBoletoController() {
        // Inform the Abstract parent controller of the concrete CompraBoleto Entity
        super(CompraBoleto.class);
    }

    @PostConstruct
    @Override
    public void initParams() {

        if (this.selected == null) {
            this.selected = prepareCreate(null);
            this.selected.setCompraBoletoFecha(new Date());
            this.selected.setCompraBoletoTotal(0);
            this.selected.setDetalleCompraBoletoList(new ArrayList<DetalleCompraBoleto>());

            this.detalleCompraBoleto = new DetalleCompraBoleto();
            this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.selected);

            this.selectedItem = new DetalleCompraBoleto();
            this.totalCompra = 0;
        }

    }

    public void addDetalleCompra(ActionEvent event) {
        if (this.detalleCompraBoleto != null) {
            int valorRollo = 0;
            int cantidad = 0;
            if (this.detalleCompraBoleto.getDetalleCompraBoletoIdBoleto() != null) {
                if (!this.detalleCompraBoleto.getDetalleCompraBoletoIdentificador().equals("")) {
                    if (!this.detalleCompraBoleto.getDetalleCompraBoletoSerie().equals("")) {
                        if (this.detalleCompraBoleto.getDetalleCompraBoletoCantidadRollos() > 0 || this.detalleCompraBoleto.getDetalleCompraBoletoTotal() > 0) {

                            DetalleCompraBoleto b = new DetalleCompraBoleto();
                            b.setDetalleCompraBoletoId(new Random().nextInt());
                            b.setDetalleCompraBoletoIdBoleto(this.detalleCompraBoleto.getDetalleCompraBoletoIdBoleto());
                            b.setDetalleCompraBoletoCantidadRollos(this.detalleCompraBoleto.getDetalleCompraBoletoCantidadRollos());
                            b.setDetalleCompraBoletoIdCompraBoleto(this.detalleCompraBoleto.getDetalleCompraBoletoIdCompraBoleto());
                            b.setDetalleCompraBoletoIdentificador(this.detalleCompraBoleto.getDetalleCompraBoletoIdentificador());
                            b.setDetalleCompraBoletoSerie(this.detalleCompraBoleto.getDetalleCompraBoletoSerie());
                            b.setDetalleCompraBoletoTotal(this.detalleCompraBoleto.getDetalleCompraBoletoTotal() * this.detalleCompraBoleto.getDetalleCompraBoletoCantidadRollos());
                            b.setDetalleCompraBoletoIdCompraBoleto(this.selected);

                            this.selected.getDetalleCompraBoletoList().add(b);
                            this.totalCompra = this.totalCompra + b.getDetalleCompraBoletoTotal();
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

            this.detalleCompraBoleto = null;
            this.detalleCompraBoleto = new DetalleCompraBoleto();
            this.detalleCompraBoleto.setDetalleCompraBoletoIdBoleto(null);
            this.detalleCompraBoleto.setDetalleCompraBoletoSerie("0");
            this.detalleCompraBoleto.setDetalleCompraBoletoTotal(valorRollo);
            this.detalleCompraBoleto.setDetalleCompraBoletoCantidadRollos(cantidad);
        } else {
        }
    }

    public void deleteDetalle() {
        if (this.selectedItem != null) {

            this.totalCompra = this.totalCompra - this.selectedItem.getDetalleCompraBoletoTotal();
            this.selected.getDetalleCompraBoletoList().remove(this.selectedItem);
            this.selectedItem = null;
            JsfUtil.addSuccessMessage("Se ha eliminado la fila");
            this.selectedItem = new DetalleCompraBoleto();
        } else {
            JsfUtil.addErrorMessage("Error al eliminar el boleto");
        }
    }

    public void findFolio() {
        this.compraBoletoDao = new ICompraBoletoDaoImpl();
        CompraBoleto compraBoleto = this.compraBoletoDao.findByFolioFactura(this.selected.getCompraBoletoFolioFactura());
        if (compraBoleto != null) {
            JsfUtil.addErrorMessage("La factura N° " + this.selected.getCompraBoletoFolioFactura() + " ya se encuentra ingresada");
        }
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void saveNew(ActionEvent event) {
        //super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        new ICompraBoletoDaoImpl().update(this.selected);
//                            this.getSelected().setCompraBoletoTotal(this.getSelected().getCompraBoletoTotal() + total);
        int totalCompra = 0;
        System.err.println("Guardando....");

        List<InventarioInterno> inventarioInterno = new ArrayList<>();
        for (DetalleCompraBoleto d : this.selected.getDetalleCompraBoletoList()) {
            System.err.println("Tamaño de Detalle de Boletos: ...." + this.selected.getDetalleCompraBoletoList().size());

            int serieInicial = Integer.parseInt(d.getDetalleCompraBoletoSerie());
            totalCompra = totalCompra + d.getDetalleCompraBoletoTotal();
            for (int i = 0; i < d.getDetalleCompraBoletoCantidadRollos(); i++) {

                InventarioInterno ii = new InventarioInterno();
                ii.setInventarioInternoEstado(Boolean.FALSE);

                ii.setInventarioInternoIdBoleto(d.getDetalleCompraBoletoIdBoleto());
                ii.setInventarioInternoIdentificador(d.getDetalleCompraBoletoIdentificador());
                ii.setInventarioInternoSerie(serieInicial);
                serieInicial += 1000;

                new IInventarioInternoDaoImpl().update(ii);
                System.err.println("Llega al update");
                inventarioInterno.add(ii);
            }

        }

        this.selected = null;
        this.selected = new CompraBoleto();

        this.selected.setDetalleCompraBoletoList(new ArrayList());
        this.selected.setCompraBoletoIdCuenta(this.getUserCount());
        this.selected.setCompraBoletoFecha(new Date());
        this.selected.setCompraBoletoTotal(0);

        this.detalleCompraBoleto = new DetalleCompraBoleto();
        this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.selected);

        JsfUtil.addSuccessMessage("Se ha agregado una nueva Compra de Boletos");
        JsfUtil.addSuccessMessage("Se ha actualizado el Inventario Interno");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedGuias()) {
            int size = this.items.size();
            return size > 1 ? size + " filas seleccionadas" : "1 fila seleccionada";
        }

        return "Eliminar";
    }

    public boolean hasSelectedGuias() {
        return this.items != null && !this.items.isEmpty();
    }

    public void deleteSelectedGuias() {
        if (hasSelectedGuias()) {
            JsfUtil.addSuccessMessage("Acá se va a implementar el borrado ...");
        }
    }

    /**
     * @return the detalleCompraBoleto
     */
    public DetalleCompraBoleto getDetalleCompraBoleto() {
        return detalleCompraBoleto;
    }

    /**
     * @param detalleCompraBoleto the detalleCompraBoleto to set
     */
    public void setDetalleCompraBoleto(DetalleCompraBoleto detalleCompraBoleto) {
        this.detalleCompraBoleto = detalleCompraBoleto;
    }

    public void load() {
        JsfUtil.addErrorMessage("Boleto:" + this.detalleCompraBoleto.getDetalleCompraBoletoSerie());
    }

    public DetalleCompraBoleto getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(DetalleCompraBoleto selectedItem) {
        this.selectedItem = selectedItem;
    }

    public CompraBoleto getSelected() {
        return this.selected;
    }

    public void setSelected(CompraBoleto selected) {
        this.selected = selected;
    }

    public int getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(int totalCompra) {
        this.totalCompra = totalCompra;
    }

    public List<CompraBoleto> getItems() {
        return items;
    }

    public void setItems(List<CompraBoleto> items) {
        this.items = items;
    }
    
    public void setTotalFormatted(String totalFormatted) {
        this.totalFormatted = totalFormatted;
    }

    public String getTotalFormatted() {
        this.totalFormatted = "$ " + decimalFormat.format(this.totalCompra);
        return totalFormatted;
    }
}
