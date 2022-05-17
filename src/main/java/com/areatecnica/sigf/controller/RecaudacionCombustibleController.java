package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IPrivilegioDao;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IPrivilegioDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionCombustibleDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.entities.Privilegio;
import com.areatecnica.sigf.models.RecaudacionCombustibleDataModel;
import com.areatecnica.sigf.models.VentaCombustibleModel;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;

@Named(value = "recaudacionCombustibleController")
@ViewScoped
public class RecaudacionCombustibleController extends AbstractController<RecaudacionCombustible> {

    private List<CajaRecaudacion> cajasRecaudacionItems;
    private List<RecaudacionCombustible> itemsRecaudacion;
    private List<RecaudacionCombustible> selectedItems;
    private RecaudacionCombustibleDataModel recaudacionCombustibleDataModel;
    private RecaudacionCombustible selectedItem;
    private VentaCombustibleModel deudasModel;
    private VentaCombustible ventaCombustible;
    private CajaRecaudacion cajaRecaudacion;
    private Date fecha;
    private int totalRecaudacion = 0;
    private int guiasAnuladas = 0;

    private boolean print;
    private Privilegio privilegio;

    private NumberFormat nf = NumberFormat.getInstance();
    LocalDate f;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM", new Locale("es", "PE"));

    public RecaudacionCombustibleController() {
        // Inform the Abstract parent controller of the concrete RecaudacionCombustible Entity
        super(RecaudacionCombustible.class);

    }

    @PostConstruct
    private void init() {
        this.fecha = new Date();
        this.cajasRecaudacionItems = new ICajaRecaudacionDaoImpl().findAllActive();
        this.privilegio = new IPrivilegioDaoImpl().findById(89);
    }

    public void load() {
        if (this.cajaRecaudacion != null) {
            this.itemsRecaudacion = new IRecaudacionCombustibleDaoImpl().findByCajaDate(cajaRecaudacion, fecha);

            if (!this.itemsRecaudacion.isEmpty()) {
                this.recaudacionCombustibleDataModel = new RecaudacionCombustibleDataModel(itemsRecaudacion);
                this.totalRecaudacion = 0;
                for (RecaudacionCombustible r : this.itemsRecaudacion) {
                    this.totalRecaudacion = this.totalRecaudacion + r.getRecaudacionCombustibleMonto();
                }
            } else {
                JsfUtil.addErrorMessage("No se han encontrado recaudaciones");
            }

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la caja de recaudación");
        }
    }

    public void delete(ActionEvent event) {
        if (this.selectedItem != null) {
            try {
                this.ventaCombustible = this.selectedItem.getRecaudacionCombustibleIdVentaCombustible();

                this.ventaCombustible.setVentaCombustibleRecaudado(Boolean.FALSE);

                new IVentaCombustibleDaoImpl().update(ventaCombustible);

                this.setSelected(selectedItem);
                super.delete(event);
                this.setSelected(null);
                JsfUtil.addSuccessMessage("Se ha eliminado la recaudación");
                this.itemsRecaudacion.remove(this.selectedItem);

            } catch (NullPointerException e) {
                JsfUtil.addErrorMessage("Se ha producido un error: " + e.getLocalizedMessage());
                this.setSelected(null);
            }
            load();
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar una recaudación");
            this.setSelected(null);
        }

    }

    public String getDeleteButtonMessage() {
        if (hasSelectedGuias()) {
            int size = this.selectedItems.size();
            return size > 1 ? size + " recaudaciones seleccionadas" : "1 recaudación seleccionada";
        }

        return "Eliminar";
    }

    public boolean hasSelectedGuias() {
        return this.selectedItems != null && !this.selectedItems.isEmpty();
    }

    public void deleteSelectedGuias() {
        if (hasSelectedGuias()) {
            for (RecaudacionCombustible r : this.selectedItems) {

                try {
                    
                    r.getRecaudacionCombustibleIdVentaCombustible().setVentaCombustibleRecaudado(Boolean.FALSE);
                    r.getRecaudacionCombustibleIdRecaudacion().setRecaudacionTotal(0);
                    
                    RecaudacionCombustible aux = new IRecaudacionCombustibleDaoImpl().update(r);
                    
                    
                    JsfUtil.addSuccessMessage("Se ha eliminado la recaudación de combustible #"+aux.getRecaudacionCombustibleIdRecaudacion().getRecaudacionId());
                    
                    new IRecaudacionCombustibleDaoImpl().delete(aux);

                } catch (NullPointerException e) {
                    JsfUtil.addErrorMessage("Se ha producido un error: " + e.getLocalizedMessage());
                    this.setSelected(null);
                }

            }
            this.itemsRecaudacion.removeAll(this.selectedItems);
            this.selectedItems = new ArrayList<>();
            load();
        }
    }

    public String getFechaCompleta() {
        LocalDate date = LocalDate.from(this.fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return date.format(formatter);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionCombustibleIdRecaudacion(ActionEvent event) {

    }

    /**
     * Sets the "selected" attribute of the VentaCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionCombustibleIdVentaCombustible(ActionEvent event) {
    }

    public void setVentaCombustible(VentaCombustible ventaCombustible) {
        this.ventaCombustible = ventaCombustible;
    }

    public VentaCombustible getVentaCombustible() {
        return ventaCombustible;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public List<CajaRecaudacion> getCajaRecaudacionItems() {
        return cajasRecaudacionItems;
    }

    public void setCajaRecaudacionItems(List<CajaRecaudacion> cajasItems) {
        this.cajasRecaudacionItems = cajasItems;
    }

    public List<RecaudacionCombustible> getItemsRecaudacion() {
        return itemsRecaudacion;
    }

    public void setItemsRecaudacion(List<RecaudacionCombustible> itemsRecaudacion) {
        this.itemsRecaudacion = itemsRecaudacion;
    }

    public RecaudacionCombustibleDataModel getRecaudacionCombustibleDataModel() {
        return recaudacionCombustibleDataModel;
    }

    public void setRecaudacionCombustibleDataModel(RecaudacionCombustibleDataModel recaudacionCombustibleDataModel) {
        this.recaudacionCombustibleDataModel = recaudacionCombustibleDataModel;
    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public VentaCombustibleModel getDeudasModel() {
        return deudasModel;
    }

    public void setDeudasModel(VentaCombustibleModel deudasModel) {
        this.deudasModel = deudasModel;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean isPrint() {
        return print;
    }

    public int getGuiasAnuladas() {
        return guiasAnuladas;
    }

    public void setGuiasAnuladas(int guiasAnuladas) {
        this.guiasAnuladas = guiasAnuladas;
    }

    public RecaudacionCombustible getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(RecaudacionCombustible selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<RecaudacionCombustible> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<RecaudacionCombustible> selectedItems) {
        this.selectedItems = selectedItems;
    }

}
