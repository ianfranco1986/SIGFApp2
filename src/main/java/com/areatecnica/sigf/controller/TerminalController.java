package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Surtidor;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Servicio;
import java.util.List;
import com.areatecnica.sigf.facade.TerminalFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "terminalController")
@ViewScoped
public class TerminalController extends AbstractController<Terminal> {

    @Inject
    private ComunaController terminalIdComunaController;
    @Inject
    private CuentaController terminalIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isSurtidorListEmpty;
    private boolean isUsuarioListEmpty;
    private boolean isLiquidacionSueldoListEmpty;
    private boolean isTrabajadorListEmpty;
    private boolean isBusListEmpty;
    private boolean isRelacionLaboralListEmpty;
    private boolean isGrupoServicioListEmpty;
    private boolean isCajaRecaudacionListEmpty;
    private boolean isServicioListEmpty;

    public TerminalController() {
        // Inform the Abstract parent controller of the concrete Terminal Entity
        super(Terminal.class);
//        this.setLimitedByCuenta(Boolean.TRUE);
//        this.setNamedQuery("Terminal.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        terminalIdComunaController.setSelected(null);
        terminalIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSurtidorListEmpty();
        this.setIsUsuarioListEmpty();
        this.setIsLiquidacionSueldoListEmpty();
        this.setIsTrabajadorListEmpty();
        this.setIsBusListEmpty();
        this.setIsRelacionLaboralListEmpty();
        this.setIsGrupoServicioListEmpty();
        this.setIsCajaRecaudacionListEmpty();
        this.setIsServicioListEmpty();
    }

    public boolean getIsSurtidorListEmpty() {
        return this.isSurtidorListEmpty;
    }

    private void setIsSurtidorListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isSurtidorListEmpty = ejbFacade.isSurtidorListEmpty(selected);
        } else {
            this.isSurtidorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Surtidor entities that
     * are retrieved from Terminal and returns the navigation outcome.
     *
     * @return navigation outcome for Surtidor page
     */
    public String navigateSurtidorList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<Surtidor> selectedSurtidorList = ejbFacade.findSurtidorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Surtidor_items", selectedSurtidorList);
        }
        return "/app/surtidor/index";
    }

    public boolean getIsUsuarioListEmpty() {
        return this.isUsuarioListEmpty;
    }

    private void setIsUsuarioListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isUsuarioListEmpty = ejbFacade.isUsuarioListEmpty(selected);
        } else {
            this.isUsuarioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Terminal and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<Usuario> selectedUsuarioList = ejbFacade.findUsuarioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioList);
        }
        return "/app/usuario/index";
    }

    public boolean getIsLiquidacionSueldoListEmpty() {
        return this.isLiquidacionSueldoListEmpty;
    }

    private void setIsLiquidacionSueldoListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isLiquidacionSueldoListEmpty = ejbFacade.isLiquidacionSueldoListEmpty(selected);
        } else {
            this.isLiquidacionSueldoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from Terminal and returns the navigation
     * outcome.
     *
     * @return navigation outcome for LiquidacionSueldo page
     */
    public String navigateLiquidacionSueldoList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<LiquidacionSueldo> selectedLiquidacionSueldoList = ejbFacade.findLiquidacionSueldoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionSueldo_items", selectedLiquidacionSueldoList);
        }
        return "/app/liquidacionSueldo/index";
    }

    public boolean getIsTrabajadorListEmpty() {
        return this.isTrabajadorListEmpty;
    }

    private void setIsTrabajadorListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from Terminal and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

    public boolean getIsBusListEmpty() {
        return this.isBusListEmpty;
    }

    private void setIsBusListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isBusListEmpty = ejbFacade.isBusListEmpty(selected);
        } else {
            this.isBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from Terminal and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<Bus> selectedBusList = ejbFacade.findBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", selectedBusList);
        }
        return "/app/bus/index";
    }

    public boolean getIsRelacionLaboralListEmpty() {
        return this.isRelacionLaboralListEmpty;
    }

    private void setIsRelacionLaboralListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isRelacionLaboralListEmpty = ejbFacade.isRelacionLaboralListEmpty(selected);
        } else {
            this.isRelacionLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from Terminal and returns the navigation outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<RelacionLaboral> selectedRelacionLaboralList = ejbFacade.findRelacionLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", selectedRelacionLaboralList);
        }
        return "/app/relacionLaboral/index";
    }

    public boolean getIsGrupoServicioListEmpty() {
        return this.isGrupoServicioListEmpty;
    }

    private void setIsGrupoServicioListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isGrupoServicioListEmpty = ejbFacade.isGrupoServicioListEmpty(selected);
        } else {
            this.isGrupoServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of GrupoServicio entities
     * that are retrieved from Terminal and returns the navigation outcome.
     *
     * @return navigation outcome for GrupoServicio page
     */
    public String navigateGrupoServicioList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<GrupoServicio> selectedGrupoServicioList = ejbFacade.findGrupoServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("GrupoServicio_items", selectedGrupoServicioList);
        }
        return "/app/grupoServicio/index";
    }

    public boolean getIsCajaRecaudacionListEmpty() {
        return this.isCajaRecaudacionListEmpty;
    }

    private void setIsCajaRecaudacionListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isCajaRecaudacionListEmpty = ejbFacade.isCajaRecaudacionListEmpty(selected);
        } else {
            this.isCajaRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CajaRecaudacion entities
     * that are retrieved from Terminal and returns the navigation outcome.
     *
     * @return navigation outcome for CajaRecaudacion page
     */
    public String navigateCajaRecaudacionList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<CajaRecaudacion> selectedCajaRecaudacionList = ejbFacade.findCajaRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaRecaudacion_items", selectedCajaRecaudacionList);
        }
        return "/app/cajaRecaudacion/index";
    }

    public boolean getIsServicioListEmpty() {
        return this.isServicioListEmpty;
    }

    private void setIsServicioListEmpty() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            this.isServicioListEmpty = ejbFacade.isServicioListEmpty(selected);
        } else {
            this.isServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from Terminal and returns the navigation outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        Terminal selected = this.getSelected();
        if (selected != null) {
            TerminalFacade ejbFacade = (TerminalFacade) this.getFacade();
            List<Servicio> selectedServicioList = ejbFacade.findServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", selectedServicioList);
        }
        return "/app/servicio/index";
    }

    /**
     * Sets the "selected" attribute of the Comuna controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTerminalIdComuna(ActionEvent event) {
        Terminal selected = this.getSelected();
        if (selected != null && terminalIdComunaController.getSelected() == null) {
            terminalIdComunaController.setSelected(selected.getTerminalIdComuna());
        }
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTerminalIdCuenta(ActionEvent event) {
        Terminal selected = this.getSelected();
        if (selected != null && terminalIdCuentaController.getSelected() == null) {
            terminalIdCuentaController.setSelected(selected.getTerminalIdCuenta());
        }
    }

    @Override
    public Terminal prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTerminalIdCuenta(this.getUserCount());
        return getSelected();
    }

}
