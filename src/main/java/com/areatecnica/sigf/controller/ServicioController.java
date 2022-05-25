package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.facade.ServicioFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "servicioController")
@ViewScoped
public class ServicioController extends AbstractController<Servicio> {

    @Inject
    private CuentaController servicioIdCuentaController;
    @Inject
    private GrupoServicioController servicioIdGrupoServicioController;
    @Inject
    private TerminalController servicioIdTerminalController;
    @Inject
    private UnidadNegocioController servicioIdUnidadController;

    // Flags to indicate if child collections are empty
    private boolean isCalleServicioListEmpty;
    private boolean isDespachoListEmpty;
    private boolean isFrecuenciaServicioListEmpty;
    private boolean isControlServicioListEmpty;
    private boolean isHorarioServicioListEmpty;
    private boolean isRegistroBoletoListEmpty;

    public ServicioController() {
        // Inform the Abstract parent controller of the concrete Servicio Entity
        super(Servicio.class);
//        this.setLimitedByCuenta(Boolean.TRUE);
//        this.setNamedQuery("Servicio.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        servicioIdCuentaController.setSelected(null);
        servicioIdGrupoServicioController.setSelected(null);
        servicioIdTerminalController.setSelected(null);
        servicioIdUnidadController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCalleServicioListEmpty();
        this.setIsDespachoListEmpty();
        this.setIsFrecuenciaServicioListEmpty();
        this.setIsControlServicioListEmpty();
        this.setIsHorarioServicioListEmpty();

    }

    public boolean getIsCalleServicioListEmpty() {
        return this.isCalleServicioListEmpty;
    }

    private void setIsCalleServicioListEmpty() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            this.isCalleServicioListEmpty = ejbFacade.isCalleServicioListEmpty(selected);
        } else {
            this.isCalleServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CalleServicio entities
     * that are retrieved from Servicio and returns the navigation outcome.
     *
     * @return navigation outcome for CalleServicio page
     */
    public String navigateCalleServicioList() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            List<CalleServicio> selectedCalleServicioList = ejbFacade.findCalleServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CalleServicio_items", selectedCalleServicioList);
        }
        return "/app/calleServicio/index";
    }

    public boolean getIsDespachoListEmpty() {
        return this.isDespachoListEmpty;
    }

    private void setIsDespachoListEmpty() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            this.isDespachoListEmpty = ejbFacade.isDespachoListEmpty(selected);
        } else {
            this.isDespachoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Despacho entities that
     * are retrieved from Servicio and returns the navigation outcome.
     *
     * @return navigation outcome for Despacho page
     */
    public String navigateDespachoList() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            List<Despacho> selectedDespachoList = ejbFacade.findDespachoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Despacho_items", selectedDespachoList);
        }
        return "/app/despacho/index";
    }

    public boolean getIsFrecuenciaServicioListEmpty() {
        return this.isFrecuenciaServicioListEmpty;
    }

    private void setIsFrecuenciaServicioListEmpty() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            this.isFrecuenciaServicioListEmpty = ejbFacade.isFrecuenciaServicioListEmpty(selected);
        } else {
            this.isFrecuenciaServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of FrecuenciaServicio
     * entities that are retrieved from Servicio and returns the navigation
     * outcome.
     *
     * @return navigation outcome for FrecuenciaServicio page
     */
    public String navigateFrecuenciaServicioList() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            List<FrecuenciaServicio> selectedFrecuenciaServicioList = ejbFacade.findFrecuenciaServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FrecuenciaServicio_items", selectedFrecuenciaServicioList);
        }
        return "/app/frecuenciaServicio/index";
    }

    public boolean getIsControlServicioListEmpty() {
        return this.isControlServicioListEmpty;
    }

    private void setIsControlServicioListEmpty() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            this.isControlServicioListEmpty = ejbFacade.isControlServicioListEmpty(selected);
        } else {
            this.isControlServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ControlServicio entities
     * that are retrieved from Servicio and returns the navigation outcome.
     *
     * @return navigation outcome for ControlServicio page
     */
    public String navigateControlServicioList() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            List<ControlServicio> selectedControlServicioList = ejbFacade.findControlServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlServicio_items", selectedControlServicioList);
        }
        return "/app/controlServicio/index";
    }

    public boolean getIsHorarioServicioListEmpty() {
        return this.isHorarioServicioListEmpty;
    }

    private void setIsHorarioServicioListEmpty() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            this.isHorarioServicioListEmpty = ejbFacade.isHorarioServicioListEmpty(selected);
        } else {
            this.isHorarioServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HorarioServicio entities
     * that are retrieved from Servicio and returns the navigation outcome.
     *
     * @return navigation outcome for HorarioServicio page
     */
    public String navigateHorarioServicioList() {
        Servicio selected = this.getSelected();
        if (selected != null) {
            ServicioFacade ejbFacade = (ServicioFacade) this.getFacade();
            List<HorarioServicio> selectedHorarioServicioList = ejbFacade.findHorarioServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HorarioServicio_items", selectedHorarioServicioList);
        }
        return "/app/horarioServicio/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioIdCuenta(ActionEvent event) {
        Servicio selected = this.getSelected();
        if (selected != null && servicioIdCuentaController.getSelected() == null) {
            servicioIdCuentaController.setSelected(selected.getServicioIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the GrupoServicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioIdGrupoServicio(ActionEvent event) {
        Servicio selected = this.getSelected();
        if (selected != null && servicioIdGrupoServicioController.getSelected() == null) {
            servicioIdGrupoServicioController.setSelected(selected.getServicioIdGrupoServicio());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioIdTerminal(ActionEvent event) {
        Servicio selected = this.getSelected();
        if (selected != null && servicioIdTerminalController.getSelected() == null) {
            servicioIdTerminalController.setSelected(selected.getServicioIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the UnidadNegocio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioIdUnidad(ActionEvent event) {
        Servicio selected = this.getSelected();
        if (selected != null && servicioIdUnidadController.getSelected() == null) {
            servicioIdUnidadController.setSelected(selected.getServicioIdUnidad());
        }
    }

    public boolean getIsRegistroBoletoListEmpty() {
        return this.isRegistroBoletoListEmpty;
    }

    @Override
    public Servicio prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setServicioIdCuenta(this.getUserCount());
        return getSelected();
    }

}
