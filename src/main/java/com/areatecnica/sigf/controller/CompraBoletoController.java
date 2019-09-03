package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ICompraBoletoDao;
import com.areatecnica.sigf.dao.impl.ICompraBoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.IInventarioInternoDaoImpl;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import com.areatecnica.sigf.entities.InventarioInterno;
import java.util.List;
import com.areatecnica.sigf.facade.CompraBoletoFacade;
import com.areatecnica.sigf.facade.InventarioInternoFacade;
import com.areatecnica.sigf.models.DetalleCompraBoletosDataModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "compraBoletoController")
@ViewScoped
public class CompraBoletoController extends AbstractController<CompraBoleto> {

    @Inject
    private CuentaController compraBoletoIdCuentaController;

    @Inject
    private InventarioInternoFacade inventarioInternoFacade;

    private DetalleCompraBoleto detalleCompraBoleto;
    private DetalleCompraBoleto selectedDetalleCompraBoleto;
    private List<DetalleCompraBoleto> itemsDetalleCompraBoleto;
    private ICompraBoletoDao compraBoletoDao;
    private DetalleCompraBoletosDataModel model;
    private int totalCompra;
    private String totalFormatted;

    private static final String pattern = "###,###.###";
    private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    // Flags to indicate if child collections are empty
    private boolean isDetalleCompraBoletoListEmpty;

    public CompraBoletoController() {
        // Inform the Abstract parent controller of the concrete CompraBoleto Entity
        super(CompraBoleto.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.
        if (this.getSelected() == null) {
            this.setSelected(prepareCreate(null));
            this.getSelected().setCompraBoletoFecha(new Date());
            this.getSelected().setCompraBoletoTotal(0);

            this.detalleCompraBoleto = new DetalleCompraBoleto();
            this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.getSelected());

            this.itemsDetalleCompraBoleto = new ArrayList<DetalleCompraBoleto>();
            this.model = new DetalleCompraBoletosDataModel(itemsDetalleCompraBoleto);
            this.totalCompra = 0;
        }

    }

    @Override
    public CompraBoleto prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setCompraBoletoIdCuenta(this.getUserCount());

        return this.getSelected();
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void saveNew(ActionEvent event) {
        //super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setDetalleCompraBoletoList(itemsDetalleCompraBoleto);
        new ICompraBoletoDaoImpl().update(this.getSelected());
//                            this.getSelected().setCompraBoletoTotal(this.getSelected().getCompraBoletoTotal() + total);
        int totalCompra = 0;
        System.err.println("Guardando....");

        List<InventarioInterno> inventarioInterno = new ArrayList<>();
        for (DetalleCompraBoleto d : this.getSelected().getDetalleCompraBoletoList()) {
            System.err.println("Tamaño de Detalle de Boletos: ...." + this.getSelected().getDetalleCompraBoletoList().size());

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

        this.getSelected().setDetalleCompraBoletoList(new ArrayList());
        this.setSelected(null);

        this.setSelected(super.prepareCreate(null));
        this.getSelected().setCompraBoletoIdCuenta(this.getUserCount());
        this.detalleCompraBoleto = new DetalleCompraBoleto();
        this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.getSelected());
        this.getSelected().setCompraBoletoFecha(new Date());
        this.getSelected().setCompraBoletoTotal(0);
        this.getSelected().setDetalleCompraBoletoList(new ArrayList<DetalleCompraBoleto>());

        this.model = new DetalleCompraBoletosDataModel(this.getSelected().getDetalleCompraBoletoList());
        JsfUtil.addSuccessMessage("Se ha agregado una nueva Compra de Boletos");
        JsfUtil.addSuccessMessage("Se ha actualizado el Inventario Interno");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        compraBoletoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDetalleCompraBoletoListEmpty();
    }

    public boolean getIsDetalleCompraBoletoListEmpty() {
        return this.isDetalleCompraBoletoListEmpty;
    }

    private void setIsDetalleCompraBoletoListEmpty() {
        CompraBoleto selected = this.getSelected();
        if (selected != null) {
            CompraBoletoFacade ejbFacade = (CompraBoletoFacade) this.getFacade();
            this.isDetalleCompraBoletoListEmpty = ejbFacade.isDetalleCompraBoletoListEmpty(selected);
        } else {
            this.isDetalleCompraBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleCompraBoleto
     * entities that are retrieved from CompraBoleto and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleCompraBoleto page
     */
    public String navigateDetalleCompraBoletoList() {
        CompraBoleto selected = this.getSelected();
        if (selected != null) {
            CompraBoletoFacade ejbFacade = (CompraBoletoFacade) this.getFacade();
            List<DetalleCompraBoleto> selectedDetalleCompraBoletoList = ejbFacade.findDetalleCompraBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleCompraBoleto_items", selectedDetalleCompraBoletoList);
        }
        return "/app/detalleCompraBoleto/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCompraBoletoIdCuenta(ActionEvent event) {
        CompraBoleto selected = this.getSelected();
        if (selected != null && compraBoletoIdCuentaController.getSelected() == null) {
            compraBoletoIdCuentaController.setSelected(selected.getCompraBoletoIdCuenta());
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

    /**
     * @return the itemsDetalleCompraBoleto
     */
    public List<DetalleCompraBoleto> getItemsDetalleCompraBoleto() {
        return itemsDetalleCompraBoleto;
    }

    /**
     * @param itemsDetalleCompraBoleto the itemsDetalleCompraBoleto to set
     */
    public void setItemsDetalleCompraBoleto(List<DetalleCompraBoleto> itemsDetalleCompraBoleto) {
        this.itemsDetalleCompraBoleto = itemsDetalleCompraBoleto;
    }

    public void addDetalleCompra(ActionEvent event) {
        if (this.detalleCompraBoleto != null) {
            int valorRollo = 0;
            int cantidad = 0;
            if (this.detalleCompraBoleto.getDetalleCompraBoletoIdBoleto() != null) {
                if (!this.detalleCompraBoleto.getDetalleCompraBoletoIdentificador().equals("")) {
                    if (!this.detalleCompraBoleto.getDetalleCompraBoletoSerie().equals("")) {
                        if (this.detalleCompraBoleto.getDetalleCompraBoletoCantidadRollos() > 0 || this.detalleCompraBoleto.getDetalleCompraBoletoTotal() > 0) {
                            valorRollo = this.detalleCompraBoleto.getDetalleCompraBoletoTotal();
                            cantidad = this.detalleCompraBoleto.getDetalleCompraBoletoCantidadRollos();
                            int total = valorRollo * cantidad;

                            this.detalleCompraBoleto.setDetalleCompraBoletoTotal(total);
                            this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.getSelected());

                            this.itemsDetalleCompraBoleto.add(detalleCompraBoleto);
                            this.totalCompra = this.totalCompra + total;
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
        if (this.selectedDetalleCompraBoleto != null) {

            this.totalCompra = this.totalCompra - this.selectedDetalleCompraBoleto.getDetalleCompraBoletoTotal();
            this.itemsDetalleCompraBoleto.remove(this.selectedDetalleCompraBoleto);
            this.selectedDetalleCompraBoleto = null;
            JsfUtil.addSuccessMessage("Se ha eliminado la fila");
        }
    }

    public void findFolio() {
        this.compraBoletoDao = new ICompraBoletoDaoImpl();
        CompraBoleto compraBoleto = this.compraBoletoDao.findByFolioFactura(this.getSelected().getCompraBoletoFolioFactura());
        if (compraBoleto != null) {
            JsfUtil.addErrorMessage("La factura N° " + this.getSelected().getCompraBoletoFolioFactura() + " ya se encuentra ingresada");
        }
    }

    public void load() {
        JsfUtil.addErrorMessage("Boleto:" + this.selectedDetalleCompraBoleto.getDetalleCompraBoletoSerie());
    }

    public void setModel(DetalleCompraBoletosDataModel model) {
        this.model = model;
    }

    public DetalleCompraBoletosDataModel getModel() {
        return model;
    }

    public int getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(int totalCompra) {
        this.totalCompra = totalCompra;
    }

    public void setSelectedDetalleCompraBoleto(DetalleCompraBoleto selectedDetalleCompraBoleto) {
        this.selectedDetalleCompraBoleto = selectedDetalleCompraBoleto;
    }

    public DetalleCompraBoleto getSelectedDetalleCompraBoleto() {
        return selectedDetalleCompraBoleto;
    }

    public void setTotalFormatted(String totalFormatted) {
        this.totalFormatted = totalFormatted;
    }

    public String getTotalFormatted() {
        this.totalFormatted = "$ " + decimalFormat.format(this.totalCompra);
        return totalFormatted;
    }

}
