package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICargoBusDao;
import com.areatecnica.sigf.dao.IUnidadNegocioDao;
import com.areatecnica.sigf.dao.impl.CargoBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IUnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;
import com.areatecnica.sigf.models.CargoBusDataModel;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.joda.time.DateTime;

@Named(value = "cargoBusController")
@ViewScoped
public class CargoBusController implements Serializable {

    @Inject
    private BusController cargoBusIdBusController;
    @Inject
    private TipoCargoController cargoBusIdTipoController;

    // Flags to indicate if child collections are empty
    private boolean isCargoLiquidacionListEmpty;

    private CargoBusDataModel model;
    private CargoBus rowSelected;
    private CargoBus selected;

    private List<CargoBus> items;
    private List<UnidadNegocio> unidadItems;
    private List<Bus> busItems;
    private UnidadNegocio unidadNegocio;
    private Date fecha;
    private Boolean cuotas;

    private ICargoBusDao dao;
    private IBusDao busDao;
    private IUnidadNegocioDao unidadNegocioDao;

    public CargoBusController() {
        // Inform the Abstract parent controller of the concrete CargoBus Entity

    }

    @PostConstruct
    public void initParams() {
        this.fecha = new Date();
        this.unidadNegocioDao = new IUnidadNegocioDaoImpl();
        this.dao = new CargoBusDaoImpl();
        this.items = this.dao.findLast();
        this.model = new CargoBusDataModel(items);
        this.unidadItems = this.unidadNegocioDao.findByCuenta(cargoBusIdBusController.getUserCount());
        this.selected = prepareCreate();
        this.selected.setCargoBusCuotaActual(0);
        this.selected.setCargoBusTotalCuotas(0);
    }

    public void setSelected(CargoBus selected) {
        this.selected = selected;
    }

    public CargoBus getSelected() {
        return selected;
    }

    public CargoBus prepareCreate() {
        CargoBus newCargoBus;
        newCargoBus = new CargoBus();
        newCargoBus.setCargoBusMontoFijo(0);
        newCargoBus.setCargoBusTotalCuotas(0);
        return newCargoBus;
    }

    public void saveNew(ActionEvent event) {
        //super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        if (this.getSelected() != null) {

            this.getSelected().setCargoBusFechaInicio(fecha);
            this.getSelected().setCargoBusActivo(true);

            if (this.getSelected().getCargoBusTotalCuotas() == 0) {
                this.getSelected().setCargoBusFechaTermino(fecha);
                this.getSelected().setCargoBusCuotaActual(0);
                this.getSelected().setCargoBusTotalCuotas(0);
                this.getSelected().setCargoBusFechaTermino(fecha);
                //this.getSelected().set
                dao.update(this.getSelected());
                this.items.add(0, this.getSelected());
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);

                int cuotasRestantes = (this.getSelected().getCargoBusTotalCuotas() - this.getSelected().getCargoBusCuotaActual()) + 1;

                if (cuotasRestantes > 0) {

                    DateTime dateTime = new DateTime(fecha);

                    calendar.add(calendar.get(Calendar.MONTH), cuotasRestantes);
                    this.getSelected().setCargoBusFechaTermino(dateTime.plusMonths(cuotasRestantes).toDate());

                    int i = this.getSelected().getCargoBusCuotaActual();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

                    for (; i <= cuotasRestantes; i++) {

                        CargoBus cargoBus = new CargoBus();
                        cargoBus.setCargoBusCuotaActual(i);
                        cargoBus.setCargoBusDescripcion(this.getSelected().getCargoBusDescripcion());
                        cargoBus.setCargoBusActivo(Boolean.TRUE);
                        cargoBus.setCargoBusFechaInicio(dateTime.toDate());
                        cargoBus.setCargoBusFechaTermino(this.getSelected().getCargoBusFechaTermino());
                        cargoBus.setCargoBusIdBus(this.getSelected().getCargoBusIdBus());
                        cargoBus.setCargoBusIdTipo(this.getSelected().getCargoBusIdTipo());
                        cargoBus.setCargoBusMontoFijo(this.getSelected().getCargoBusMontoFijo());
                        cargoBus.setCargoBusTotalCuotas(cuotasRestantes);

                        dateTime = dateTime.plusMonths(1);

                        dao.update(cargoBus);
                        this.items.add(0, cargoBus);

                        JsfUtil.addSuccessMessage("Se ha generado un cargo con fecha: " + sdf.format(dateTime.toDate()) + " al Bus N°: " + cargoBus.getCargoBusIdBus().getBusNumero());
                    }

                }

            }

            JsfUtil.addSuccessMessage("Se ha registrado un nuevo cargo");

            this.getSelected().setCargoBusFechaInicio(this.getSelected().getCargoBusFechaInicio());
            this.getSelected().setCargoBusIdTipo(this.getSelected().getCargoBusIdTipo());
            this.getSelected().setCargoBusMontoFijo(this.getSelected().getCargoBusMontoFijo());
            this.getSelected().setCargoBusCuotaActual(this.getSelected().getCargoBusCuotaActual());
            this.getSelected().setCargoBusTotalCuotas(this.getSelected().getCargoBusTotalCuotas());
            this.setSelected(prepareCreate());
        } else {
            JsfUtil.addErrorMessage("Error al validar el cargo");
        }
    }

    public void save(ActionEvent event) {
        if (rowSelected != null) {
            this.dao.update(this.rowSelected);
            this.rowSelected = null;
            JsfUtil.addSuccessMessage("Se ha actualizado el cargo");
            this.setSelected(prepareCreate());
            this.getSelected().setCargoBusFechaInicio(this.rowSelected.getCargoBusFechaInicio());
            this.getSelected().setCargoBusIdTipo(this.rowSelected.getCargoBusIdTipo());
            this.getSelected().setCargoBusMontoFijo(this.rowSelected.getCargoBusMontoFijo());
            this.getSelected().setCargoBusCuotaActual(this.rowSelected.getCargoBusCuotaActual());
            this.getSelected().setCargoBusTotalCuotas(this.rowSelected.getCargoBusTotalCuotas());
            this.rowSelected = null;
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un cargo");
        }
    }

    public void delete(ActionEvent event) {
        if (rowSelected != null) {
            this.dao.delete(this.rowSelected);
            this.items.remove(this.rowSelected);

            JsfUtil.addSuccessMessage("Se ha eliminado el cargo");
            this.setSelected(prepareCreate());
            this.getSelected().setCargoBusFechaInicio(this.rowSelected.getCargoBusFechaInicio());
            this.getSelected().setCargoBusIdTipo(this.rowSelected.getCargoBusIdTipo());
            this.getSelected().setCargoBusMontoFijo(this.rowSelected.getCargoBusMontoFijo());
            this.getSelected().setCargoBusCuotaActual(this.rowSelected.getCargoBusCuotaActual());
            this.getSelected().setCargoBusTotalCuotas(this.rowSelected.getCargoBusTotalCuotas());
            this.rowSelected = null;
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un cargo");
        }
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
//    @Override
//    protected void setChildrenEmptyFlags() {
//        this.setIsCargoLiquidacionListEmpty();
//    }
    public boolean getIsCargoLiquidacionListEmpty() {
        return this.isCargoLiquidacionListEmpty;
    }

    public void setModel(CargoBusDataModel model) {
        this.model = model;
    }

    public CargoBusDataModel getModel() {
        return model;
    }

    public CargoBus getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(CargoBus rowSelected) {
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

    public List<CargoBus> getItems() {
        return items;
    }

    public void setItems(List<CargoBus> items) {
        this.items = items;
    }

//    private void setIsCargoLiquidacionListEmpty() {
//        CargoBus selected = this.getSelected();
//        if (selected != null) {
//            CargoBusFacade ejbFacade = (CargoBusFacade) this.getFacade();
//            this.isCargoLiquidacionListEmpty = ejbFacade.isCargoLiquidacionListEmpty(selected);
//        } else {
//            this.isCargoLiquidacionListEmpty = true;
//        }
//    }
    /**
     * Sets the "items" attribute with a collection of CargoLiquidacion entities
     * that are retrieved from CargoBus and returns the navigation outcome.
     *
     * @return navigation outcome for CargoLiquidacion page
     */
//    public String navigateCargoLiquidacionList() {
//        CargoBus selected = this.getSelected();
//        if (selected != null) {
//            CargoBusFacade ejbFacade = (CargoBusFacade) this.getFacade();
//            List<CargoLiquidacion> selectedCargoLiquidacionList = ejbFacade.findCargoLiquidacionList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargoLiquidacion_items", selectedCargoLiquidacionList);
//        }
//        return "/app/cargoLiquidacion/index";
//    }
    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargoBusIdBus(ActionEvent event) {
        CargoBus selected = this.getSelected();
        if (selected != null && cargoBusIdBusController.getSelected() == null) {
            cargoBusIdBusController.setSelected(selected.getCargoBusIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCargo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargoBusIdTipo(ActionEvent event) {
        CargoBus selected = this.getSelected();
        if (selected != null && cargoBusIdTipoController.getSelected() == null) {
            cargoBusIdTipoController.setSelected(selected.getCargoBusIdTipo());
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
        if (this.getSelected().getCargoBusIdTipo() != null) {
            this.getSelected().setCargoBusMontoFijo(this.getSelected().getCargoBusIdTipo().getTipoCargoMontoDefecto());
        }
    }

}
