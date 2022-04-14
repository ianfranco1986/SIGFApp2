package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ICompraBoletoDao;
import com.areatecnica.sigf.dao.impl.ICompraBoletoDaoImpl;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import java.util.List;
import com.areatecnica.sigf.facade.CompraBoletoFacade;
import com.areatecnica.sigf.facade.InventarioInternoFacade;
import com.areatecnica.sigf.models.DetalleCompraBoletosDataModel;
import java.text.DecimalFormat;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "compraBoletoDetalleController")
@ViewScoped
public class CompraBoletoDetalleController extends AbstractController<CompraBoleto> {

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
    private List<CompraBoleto> items2;

    private static final String pattern = "###,###.###";
    private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    // Flags to indicate if child collections are empty
    private boolean isDetalleCompraBoletoListEmpty;

    public CompraBoletoDetalleController() {
        // Inform the Abstract parent controller of the concrete CompraBoleto Entity
        super(CompraBoleto.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.
//        if (this.getSelected() == null) {
//            this.setSelected(prepareCreate(null));
//            this.getSelected().setCompraBoletoFecha(new Date());
//            this.getSelected().setCompraBoletoTotal(0);
//
//            this.detalleCompraBoleto = new DetalleCompraBoleto();
//            this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.getSelected());
//
//            this.itemsDetalleCompraBoleto = new ArrayList<DetalleCompraBoleto>();
//            this.model = new DetalleCompraBoletosDataModel(itemsDetalleCompraBoleto);
//            this.totalCompra = 0;
//        }
        this.items2 = new ICompraBoletoDaoImpl().findAll();
    }

    public void setItems2(List<CompraBoleto> items2) {
        this.items2 = items2;
    }

    public List<CompraBoleto> getItems2() {
        return items2;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        compraBoletoIdCuentaController.setSelected(null);
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
