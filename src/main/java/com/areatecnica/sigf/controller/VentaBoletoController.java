package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IEgresoDaoImpl;
import com.areatecnica.sigf.dao.impl.IProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.ITrabajadorDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaBoletoDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.models.VentaBoletoRecaudacionDataModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

@Named(value = "ventaBoletoController")
@ViewScoped
public class VentaBoletoController extends AbstractController<VentaBoleto> {

    @Inject
    private EgresoController recaudacionGuiaIdEgresoController;
    @Inject
    private GuiaController recaudacionGuiaIdGuiaController;
    @Inject
    private RecaudacionController recaudacionGuiaIdRecaudacionController;

    private Date fecha;
    private CajaRecaudacion cajaRecaudacion;
    private List<CajaRecaudacion> cajaRecaudacionItems;
    private int totalRecaudacion = 0;

    private List<VentaBoleto> items;
    private List<Bus> busItems;
    private List<Trabajador> trabajadorItems;
    private VentaBoleto selectedItem;
    private VentaBoletoRecaudacionDataModel model;
    private Egreso boleto;

    private NumberFormat nf = NumberFormat.getInstance();

    private int totalAdministracion = 0;
    private int totalCuotaExtra = 0;
    private int totalBoletos = 0;
    private int totalImposiciones = 0;
    private int serie = 0;

    public VentaBoletoController() {
        // Inform the Abstract parent controller of the concrete RecaudacionGuia Entity
        super(VentaBoleto.class);
    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.cajaRecaudacionItems = new ICajaRecaudacionDaoImpl().findAll();
        this.items = new ArrayList<>();
        this.boleto = new IEgresoDaoImpl().getBoleto();
    }

    public void find() {
        if (this.serie > 0) {
            System.err.println("SERIE:" + this.serie);
            this.selectedItem = new IVentaBoletoDaoImpl().findBySerie(this.serie);

            if (this.selectedItem != null) {
                this.items = new ArrayList<>();
                this.items.add(this.selectedItem);
                this.model = new VentaBoletoRecaudacionDataModel(items);
                JsfUtil.addSuccessMessage("Se ha encontrado un registro");
            } else {
                JsfUtil.addWarningMessage("No se han encontrado registros");
                this.items = new ArrayList<>();
                this.model = new VentaBoletoRecaudacionDataModel(items);
            }
        }
    }

    public void load() {
        if (this.cajaRecaudacion != null) {
            this.totalAdministracion = 0;
            this.totalBoletos = 0;
            this.totalCuotaExtra = 0;
            this.totalImposiciones = 0;
            this.totalRecaudacion = 0;

            this.busItems = new IBusDaoImpl().findByProceso(new IProcesoRecaudacionDaoImpl().findById(2));
            this.trabajadorItems = new ITrabajadorDaoImpl().findNandu();

            this.items = new IVentaBoletoDaoImpl().findByCajaDate(cajaRecaudacion, fecha);
            if (!this.items.isEmpty()) {
                this.totalRecaudacion = 0;

                this.model = new VentaBoletoRecaudacionDataModel(items);

                for (VentaBoleto v : this.items) {
                    this.totalBoletos = this.totalBoletos + v.getVentaBoletoValor();
                }

                this.totalRecaudacion = this.items.size();

            } else {
                JsfUtil.addErrorMessage("No se han encontrado ventas ");
                this.totalAdministracion = 0;
                this.totalBoletos = 0;
                this.totalCuotaExtra = 0;
                this.totalImposiciones = 0;
                this.totalRecaudacion = 0;
            }
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la caja");
        }

    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getSerie() {
        return serie;
    }

    public void setTrabajadorItems(List<Trabajador> trabajadorItems) {
        this.trabajadorItems = trabajadorItems;
    }

    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    public List<Bus> getBusItems() {
        return busItems;
    }

    public List<Trabajador> getTrabajadorItems() {
        return trabajadorItems;
    }

    public void setModel(VentaBoletoRecaudacionDataModel model) {
        this.model = model;
    }

    public VentaBoletoRecaudacionDataModel getModel() {
        return model;
    }

    public void setSelectedItem(VentaBoleto selectedItem) {
        this.selectedItem = selectedItem;
    }

    public VentaBoleto getSelectedItem() {
        return selectedItem;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public int getTotalImposiciones() {
        return totalImposiciones;
    }

    public int getTotalCuotaExtra() {
        return totalCuotaExtra;
    }

    public int getTotalBoletos() {
        return totalBoletos;
    }

    public int getTotalAdministracion() {
        return totalAdministracion;
    }

    public void delete() {
        if (this.selectedItem != null) {

            int recaudacion_id = this.selectedItem.getVentaBoletoFolioRecaudacion();

            RecaudacionGuia r = new IRecaudacionGuiaDaoImpl().findByRecaudacionEgreso(recaudacion_id, this.boleto);
            int valorBoleto = r.getRecaudacionGuiaMonto();

            if (valorBoleto > 0) {
                r.setRecaudacionGuiaMonto(valorBoleto - 5000);

                new IRecaudacionGuiaDaoImpl().update(r);
                new IVentaBoletoDaoImpl().delete(selectedItem);

                this.selectedItem = null;
                JsfUtil.addSuccessMessage("Se ha cancelado la venta de boleto");
            } else {
                JsfUtil.addErrorMessage("Ha ocurrido un error al actualizar la recaudación y la venta de boleto. Favor contactar al administrador");
            }

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la recaudación");
            this.selectedItem = null;
        }
    }

    public void onRowEdit(RowEditEvent event) {
        VentaBoleto temp = null;
        try {
            temp = (VentaBoleto) event.getObject();

            new IVentaBoletoDaoImpl().update(temp);
            JsfUtil.addSuccessMessage("Se ha actualizado la Venta de Boleto: " + temp.getVentaBoletoNumeroBoleta());

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ha ocurrido un error al registrar los cambios");
        }

    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        recaudacionGuiaIdEgresoController.setSelected(null);
        recaudacionGuiaIdGuiaController.setSelected(null);
        recaudacionGuiaIdRecaudacionController.setSelected(null);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public List<CajaRecaudacion> getCajaRecaudacionItems() {
        return cajaRecaudacionItems;
    }

    public void setCajaRecaudacionItems(List<CajaRecaudacion> cajaRecaudacionItems) {
        this.cajaRecaudacionItems = cajaRecaudacionItems;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void loadGuia() {

    }

    public void findFolio() {

    }

    public void handleBusChange() {

    }

}
