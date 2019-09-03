package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.IAbonoBusDao;
import com.areatecnica.sigf.dao.IUnidadNegocioDao;
import com.areatecnica.sigf.dao.impl.AbonoBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IUnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;
import com.areatecnica.sigf.facade.AbonoBusFacade;
import com.areatecnica.sigf.models.AbonoBusDataModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.joda.time.DateTime;

@Named(value = "abonoBusController")
@ViewScoped
public class AbonoBusController extends AbstractController<AbonoBus> {

    @Inject
    private BusController abonoBusIdBusController;
    @Inject
    private TipoAbonoController abonoBusIdTipoController;

    // Flags to indicate if child collections are empty
    private boolean isCargoLiquidacionListEmpty;

    private AbonoBusDataModel model;
    private AbonoBus rowSelected;

    private List<AbonoBus> items;
    private List<UnidadNegocio> unidadItems;
    private List<Bus> busItems;
    private UnidadNegocio unidadNegocio;
    private Date fecha;
    private Boolean cuotas;

    private IAbonoBusDao dao;
    private IBusDao busDao;
    private IUnidadNegocioDao unidadNegocioDao;

    public AbonoBusController() {
        // Inform the Abstract parent controller of the concrete AbonoBus Entity
        super(AbonoBus.class);
        this.setSelected(prepareCreate(null));
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.
        this.fecha = new Date();
        this.unidadNegocioDao = new IUnidadNegocioDaoImpl();
        this.dao = new AbonoBusDaoImpl();
        this.items = this.dao.findLast();
        this.model = new AbonoBusDataModel(items);
        this.unidadItems = this.unidadNegocioDao.findNandu();
        this.getSelected().setAbonoBusCuotaActual(0);
        this.getSelected().setAbonoBusTotalCuotas(0);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        abonoBusIdBusController.setSelected(null);
        abonoBusIdTipoController.setSelected(null);
    }

    @Override
    public void saveNew(ActionEvent event) {
        //super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        if (this.getSelected() != null) {

            this.getSelected().setAbonoBusFechaInicio(fecha);
            this.getSelected().setAbonoBusActivo(true);

            if (this.getSelected().getAbonoBusTotalCuotas() == 0) {

                this.getSelected().setAbonoBusCuotaActual(0);
                this.getSelected().setAbonoBusTotalCuotas(0);
                this.getSelected().setAbonoBusFechaTermino(fecha);

                dao.update(this.getSelected());
                this.items.add(0, this.getSelected());

            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);

                int cuotasRestantes = (this.getSelected().getAbonoBusTotalCuotas() - this.getSelected().getAbonoBusCuotaActual()) + 1;;

                if (cuotasRestantes > 0) {
                    DateTime dateTime = new DateTime(fecha);

                    calendar.add(calendar.get(Calendar.MONTH), cuotasRestantes);
                    this.getSelected().setAbonoBusFechaTermino(dateTime.plusMonths(cuotasRestantes).toDate());

                    int i = this.getSelected().getAbonoBusCuotaActual();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

                    for (; i <= cuotasRestantes; i++) {

                        AbonoBus abonoBus = new AbonoBus();
                        abonoBus.setAbonoBusCuotaActual(i);
                        abonoBus.setAbonoBusDescripcion(this.getSelected().getAbonoBusDescripcion());
                        abonoBus.setAbonoBusActivo(Boolean.TRUE);
                        abonoBus.setAbonoBusFechaInicio(dateTime.toDate());
                        abonoBus.setAbonoBusFechaTermino(this.getSelected().getAbonoBusFechaTermino());
                        abonoBus.setAbonoBusIdBus(this.getSelected().getAbonoBusIdBus());
                        abonoBus.setAbonoBusIdTipoAbono(this.getSelected().getAbonoBusIdTipoAbono());
                        abonoBus.setAbonoBusMontoFijo(this.getSelected().getAbonoBusMontoFijo());
                        abonoBus.setAbonoBusTotalCuotas(cuotasRestantes);

                        dateTime = dateTime.plusMonths(1);

                        dao.update(abonoBus);
                        this.items.add(0, abonoBus);

                        JsfUtil.addSuccessMessage("Se ha generado un abono con fecha: " + sdf.format(dateTime.toDate()) + " al Bus N°: " + abonoBus.getAbonoBusIdBus().getBusNumero());
                    }
                }
//                } else {
//                    this.getSelected().setAbonoBusCuotaActual(0);
//                    this.getSelected().setAbonoBusTotalCuotas(0);
//                    this.getSelected().setAbonoBusFechaTermino(fecha);
//                    super.saveNew(event);
//                    this.items.add(0, this.getSelected());
//                }

            }

            JsfUtil.addSuccessMessage("Se ha registrado un nuevo Abono");

            this.getSelected().setAbonoBusFechaInicio(this.getSelected().getAbonoBusFechaInicio());
            this.getSelected().setAbonoBusIdTipoAbono(this.getSelected().getAbonoBusIdTipoAbono());
            this.getSelected().setAbonoBusMontoFijo(this.getSelected().getAbonoBusMontoFijo());
            this.getSelected().setAbonoBusCuotaActual(this.getSelected().getAbonoBusCuotaActual());
            this.getSelected().setAbonoBusTotalCuotas(this.getSelected().getAbonoBusTotalCuotas());
            this.setSelected(prepareCreate(event));
        } else {
            JsfUtil.addErrorMessage("Error al validar el Abono");
        }
    }

    @Override
    public void save(ActionEvent event) {
        if (rowSelected != null) {
            this.dao.update(this.rowSelected);

            JsfUtil.addSuccessMessage("Se ha actualizado el abono");
            this.setSelected(prepareCreate(event));
            this.getSelected().setAbonoBusFechaInicio(this.rowSelected.getAbonoBusFechaInicio());
            this.getSelected().setAbonoBusIdTipoAbono(this.rowSelected.getAbonoBusIdTipoAbono());
            this.getSelected().setAbonoBusMontoFijo(this.rowSelected.getAbonoBusMontoFijo());
            this.getSelected().setAbonoBusCuotaActual(this.rowSelected.getAbonoBusCuotaActual());
            this.getSelected().setAbonoBusTotalCuotas(this.rowSelected.getAbonoBusTotalCuotas());
            this.rowSelected = null;
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un abono");
        }
    }

    @Override
    public void delete(ActionEvent event) {
        if (rowSelected != null) {
            this.dao.delete(this.rowSelected);
            this.items.remove(this.rowSelected);

            JsfUtil.addSuccessMessage("Se ha eliminado el abono");
            this.setSelected(prepareCreate(event));
            this.getSelected().setAbonoBusFechaInicio(this.rowSelected.getAbonoBusFechaInicio());
            this.getSelected().setAbonoBusIdTipoAbono(this.rowSelected.getAbonoBusIdTipoAbono());
            this.getSelected().setAbonoBusMontoFijo(this.rowSelected.getAbonoBusMontoFijo());
            this.getSelected().setAbonoBusCuotaActual(this.rowSelected.getAbonoBusCuotaActual());
            this.getSelected().setAbonoBusTotalCuotas(this.rowSelected.getAbonoBusTotalCuotas());
            this.rowSelected = null;
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un abono");
        }
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCargoLiquidacionListEmpty();
    }

    public boolean getIsCargoLiquidacionListEmpty() {
        return this.isCargoLiquidacionListEmpty;
    }

    public void setModel(AbonoBusDataModel model) {
        this.model = model;
    }

    public AbonoBusDataModel getModel() {
        return model;
    }

    public AbonoBus getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(AbonoBus rowSelected) {
        this.rowSelected = rowSelected;
    }

    public void setCuotas(Boolean cuotas) {
        this.cuotas = cuotas;
    }

    public Boolean getCuotas() {
        return cuotas;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public UnidadNegocio getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public List<UnidadNegocio> getUnidadItems() {
        return unidadItems;
    }

    public void setUnidadItems(List<UnidadNegocio> unidadItems) {
        this.unidadItems = unidadItems;
    }

    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    public List<Bus> getBusItems() {
        return busItems;
    }

    public List<AbonoBus> getItems() {
        return items;
    }

    public void setItems(List<AbonoBus> items) {
        this.items = items;
    }

    private void setIsCargoLiquidacionListEmpty() {
        AbonoBus selected = this.getSelected();
        if (selected != null) {
            AbonoBusFacade ejbFacade = (AbonoBusFacade) this.getFacade();
            this.isCargoLiquidacionListEmpty = ejbFacade.isAbonoLiquidacionListEmpty(selected);
        } else {
            this.isCargoLiquidacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargoLiquidacion entities
     * that are retrieved from AbonoBus and returns the navigation outcome.
     *
     * @return navigation outcome for CargoLiquidacion page
     */
    public String navigateCargoLiquidacionList() {
        AbonoBus selected = this.getSelected();
        if (selected != null) {
            AbonoBusFacade ejbFacade = (AbonoBusFacade) this.getFacade();
            List<AbonoLiquidacion> selectedCargoLiquidacionList = ejbFacade.findAbonoLiquidacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargoLiquidacion_items", selectedCargoLiquidacionList);
        }
        return "/app/cargoLiquidacion/index";
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAbonoBusIdBus(ActionEvent event) {
        AbonoBus selected = this.getSelected();
        if (selected != null && abonoBusIdBusController.getSelected() == null) {
            abonoBusIdBusController.setSelected(selected.getAbonoBusIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCargo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAbonoBusIdTipo(ActionEvent event) {
        AbonoBus selected = this.getSelected();
        if (selected != null && abonoBusIdTipoController.getSelected() == null) {
            abonoBusIdTipoController.setSelected(selected.getAbonoBusIdTipoAbono());
        }
    }

    public void handleUnidadChange() {
        if (this.unidadNegocio != null) {
            this.busDao = new IBusDaoImpl();
            this.busItems = this.busDao.findByUnidad(unidadNegocio);
        }
    }

    public void habilitaCuotas() {
        if (cuotas) {

        }
    }

    public void setMontoXDefecto() {
        if (this.getSelected().getAbonoBusIdTipoAbono() != null) {
            this.getSelected().setAbonoBusMontoFijo(this.getSelected().getAbonoBusIdTipoAbono().getTipoAbonoMontoDefecto());
        }
    }

}
