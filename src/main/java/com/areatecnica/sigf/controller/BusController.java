package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Despacho;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.DescuentoExtraBus;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.entities.EgresoBus;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;
import com.areatecnica.sigf.facade.BusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "busController")
@ViewScoped
public class BusController extends AbstractController<Bus> {

    @Inject
    private EmpresaController busIdEmpresaController;
    @Inject
    private EstadoBusController busIdEstadoBusController;
    @Inject
    private FlotaController busIdFlotaController;
    @Inject
    private GrupoServicioController busIdGrupoServicioController;
    @Inject
    private ModeloMarcaBusController busIdModeloController;
    @Inject
    private ProcesoRecaudacionController busIdProcesoRecaudacionController;
    @Inject
    private TerminalController busIdTerminalController;
    @Inject
    private UnidadNegocioController busIdUnidadNegocioController;

    // Flags to indicate if child collections are empty
    private boolean isAbonoBusListEmpty;
    private boolean isCargoBusListEmpty;
    private boolean isDespachoListEmpty;
    private boolean isVentaBoletoListEmpty;
    private boolean isDescuentoExtraBusListEmpty;
    private boolean isVentaCombustibleListEmpty;
    private boolean isEgresoBusListEmpty;
    private boolean isGuiaListEmpty;
    private boolean isRegistroMinutoListEmpty;
    private boolean isRegistroMinutoList1Empty;

    public BusController() {
        // Inform the Abstract parent controller of the concrete Bus Entity
        super(Bus.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Bus.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        busIdEmpresaController.setSelected(null);
        busIdEstadoBusController.setSelected(null);
        busIdFlotaController.setSelected(null);
        busIdGrupoServicioController.setSelected(null);
        busIdModeloController.setSelected(null);
        busIdProcesoRecaudacionController.setSelected(null);
        busIdTerminalController.setSelected(null);
        busIdUnidadNegocioController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsAbonoBusListEmpty();
        this.setIsCargoBusListEmpty();
        this.setIsDespachoListEmpty();
        this.setIsVentaBoletoListEmpty();
        this.setIsDescuentoExtraBusListEmpty();
        this.setIsVentaCombustibleListEmpty();
        this.setIsEgresoBusListEmpty();
        this.setIsGuiaListEmpty();
        this.setIsRegistroMinutoListEmpty();
        this.setIsRegistroMinutoList1Empty();
    }

    public boolean getIsAbonoBusListEmpty() {
        return this.isAbonoBusListEmpty;
    }

    private void setIsAbonoBusListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isAbonoBusListEmpty = ejbFacade.isAbonoBusListEmpty(selected);
        } else {
            this.isAbonoBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of AbonoBus entities that
     * are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for AbonoBus page
     */
    public String navigateAbonoBusList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
//            List<AbonoBus> selectedAbonoBusList = ejbFacade.findAbonoBusList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AbonoBus_items", selectedAbonoBusList);
        }
        return "/app/abonoBus/index";
    }

    public boolean getIsCargoBusListEmpty() {
        return this.isCargoBusListEmpty;
    }

    private void setIsCargoBusListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isCargoBusListEmpty = ejbFacade.isCargoBusListEmpty(selected);
        } else {
            this.isCargoBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargoBus entities that
     * are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for CargoBus page
     */
    public String navigateCargoBusList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
//            List<CargoBus> selectedCargoBusList = ejbFacade.findCargoBusList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargoBus_items", selectedCargoBusList);
        }
        return "/app/cargoBus/index";
    }

    public boolean getIsDespachoListEmpty() {
        return this.isDespachoListEmpty;
    }

    private void setIsDespachoListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isDespachoListEmpty = ejbFacade.isDespachoListEmpty(selected);
        } else {
            this.isDespachoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Despacho entities that
     * are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for Despacho page
     */
    public String navigateDespachoList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            List<Despacho> selectedDespachoList = ejbFacade.findDespachoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Despacho_items", selectedDespachoList);
        }
        return "/app/despacho/index";
    }

    public boolean getIsVentaBoletoListEmpty() {
        return this.isVentaBoletoListEmpty;
    }

    private void setIsVentaBoletoListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isVentaBoletoListEmpty = ejbFacade.isVentaBoletoListEmpty(selected);
        } else {
            this.isVentaBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of VentaBoleto entities that
     * are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for VentaBoleto page
     */
    public String navigateVentaBoletoList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            List<VentaBoleto> selectedVentaBoletoList = ejbFacade.findVentaBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VentaBoleto_items", selectedVentaBoletoList);
        }
        return "/app/ventaBoleto/index";
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdEmpresa(ActionEvent event) {
        Bus selected = this.getSelected();
        if (selected != null && busIdEmpresaController.getSelected() == null) {
            busIdEmpresaController.setSelected(selected.getBusIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the EstadoBus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdEstadoBus(ActionEvent event) {
        Bus selected = this.getSelected();
        if (selected != null && busIdEstadoBusController.getSelected() == null) {
            busIdEstadoBusController.setSelected(selected.getBusIdEstadoBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Flota controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdFlota(ActionEvent event) {
        Bus selected = this.getSelected();
        if (selected != null && busIdFlotaController.getSelected() == null) {
            busIdFlotaController.setSelected(selected.getBusIdFlota());
        }
    }

    /**
     * Sets the "selected" attribute of the GrupoServicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdGrupoServicio(ActionEvent event) {
        Bus selected = this.getSelected();
        if (selected != null && busIdGrupoServicioController.getSelected() == null) {
            busIdGrupoServicioController.setSelected(selected.getBusIdGrupoServicio());
        }
    }

    /**
     * Sets the "selected" attribute of the ModeloMarcaBus controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdModelo(ActionEvent event) {
        Bus selected = this.getSelected();
        if (selected != null && busIdModeloController.getSelected() == null) {
            busIdModeloController.setSelected(selected.getBusIdModelo());
        }
    }

    /**
     * Sets the "selected" attribute of the ProcesoRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdProcesoRecaudacion(ActionEvent event) {
        Bus selected = this.getSelected();
        if (selected != null && busIdProcesoRecaudacionController.getSelected() == null) {
            busIdProcesoRecaudacionController.setSelected(selected.getBusIdProcesoRecaudacion());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdTerminal(ActionEvent event) {
        Bus selected = this.getSelected();
        if (selected != null && busIdTerminalController.getSelected() == null) {
            busIdTerminalController.setSelected(selected.getBusIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the UnidadNegocio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdUnidadNegocio(ActionEvent event) {
        Bus selected = this.getSelected();
        if (selected != null && busIdUnidadNegocioController.getSelected() == null) {
            busIdUnidadNegocioController.setSelected(selected.getBusIdUnidadNegocio());
        }
    }

    public boolean getIsDescuentoExtraBusListEmpty() {
        return this.isDescuentoExtraBusListEmpty;
    }

    private void setIsDescuentoExtraBusListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isDescuentoExtraBusListEmpty = ejbFacade.isDescuentoExtraBusListEmpty(selected);
        } else {
            this.isDescuentoExtraBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoExtraBus
     * entities that are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for DescuentoExtraBus page
     */
    public String navigateDescuentoExtraBusList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            List<DescuentoExtraBus> selectedDescuentoExtraBusList = ejbFacade.findDescuentoExtraBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoExtraBus_items", selectedDescuentoExtraBusList);
        }
        return "/app/descuentoExtraBus/index";
    }

    public boolean getIsVentaCombustibleListEmpty() {
        return this.isVentaCombustibleListEmpty;
    }

    private void setIsVentaCombustibleListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isVentaCombustibleListEmpty = ejbFacade.isVentaCombustibleListEmpty(selected);
        } else {
            this.isVentaCombustibleListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of VentaCombustible entities
     * that are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for VentaCombustible page
     */
    public String navigateVentaCombustibleList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            List<VentaCombustible> selectedVentaCombustibleList = ejbFacade.findVentaCombustibleList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VentaCombustible_items", selectedVentaCombustibleList);
        }
        return "/app/ventaCombustible/index";
    }

    public boolean getIsEgresoBusListEmpty() {
        return this.isEgresoBusListEmpty;
    }

    private void setIsEgresoBusListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isEgresoBusListEmpty = ejbFacade.isEgresoBusListEmpty(selected);
        } else {
            this.isEgresoBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoBus entities that
     * are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoBus page
     */
    public String navigateEgresoBusList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            List<EgresoBus> selectedEgresoBusList = ejbFacade.findEgresoBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoBus_items", selectedEgresoBusList);
        }
        return "/app/egresoBus/index";
    }

    public boolean getIsGuiaListEmpty() {
        return this.isGuiaListEmpty;
    }

    private void setIsGuiaListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isGuiaListEmpty = ejbFacade.isGuiaListEmpty(selected);
        } else {
            this.isGuiaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Guia entities that are
     * retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for Guia page
     */
    public String navigateGuiaList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            List<Guia> selectedGuiaList = ejbFacade.findGuiaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Guia_items", selectedGuiaList);
        }
        return "/app/guia/index";
    }

    public boolean getIsRegistroMinutoListEmpty() {
        return this.isRegistroMinutoListEmpty;
    }

    private void setIsRegistroMinutoListEmpty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isRegistroMinutoListEmpty = ejbFacade.isRegistroMinutoListEmpty(selected);
        } else {
            this.isRegistroMinutoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RegistroMinuto entities
     * that are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for RegistroMinuto page
     */
    public String navigateRegistroMinutoList() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            List<RegistroMinuto> selectedRegistroMinutoList = ejbFacade.findRegistroMinutoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroMinuto_items", selectedRegistroMinutoList);
        }
        return "/app/registroMinuto/index";
    }

    public boolean getIsRegistroMinutoList1Empty() {
        return this.isRegistroMinutoList1Empty;
    }

    private void setIsRegistroMinutoList1Empty() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            this.isRegistroMinutoList1Empty = ejbFacade.isRegistroMinutoList1Empty(selected);
        } else {
            this.isRegistroMinutoList1Empty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RegistroMinuto entities
     * that are retrieved from Bus and returns the navigation outcome.
     *
     * @return navigation outcome for RegistroMinuto page
     */
    public String navigateRegistroMinutoList1() {
        Bus selected = this.getSelected();
        if (selected != null) {
            BusFacade ejbFacade = (BusFacade) this.getFacade();
            List<RegistroMinuto> selectedRegistroMinutoList1 = ejbFacade.findRegistroMinutoList1(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroMinuto_items", selectedRegistroMinutoList1);
        }
        return "/app/registroMinuto/index";
    }

    public void findMaxByUnidad() {
        UnidadNegocio unidad = this.getSelected().getBusIdUnidadNegocio();
        this.getSelected().setBusNumero(new IBusDaoImpl().findMaxNumeroUnidad(unidad));
    }

}
