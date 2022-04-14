package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.ResumenRecaudacion;
import com.areatecnica.sigf.entities.InventarioCaja;
import java.util.List;
import com.areatecnica.sigf.facade.CajaRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cajaRecaudacionController")
@ViewScoped
public class CajaRecaudacionController extends AbstractController<CajaRecaudacion> {

    @Inject
    private CuentaController cajaRecaudacionIdCuentaController;
    @Inject
    private TerminalController cajaRecaudacionIdTerminalController;
    @Inject
    private UsuarioController cajaRecaudacionIdUsuarioController;

    // Flags to indicate if child collections are empty
    private boolean isEgresoCajaRecaudacionListEmpty;
    private boolean isRecaudacionListEmpty;
    private boolean isCajaProcesoListEmpty;
    private boolean isResumenRecaudacionListEmpty;
    private boolean isInventarioCajaListEmpty;

    public CajaRecaudacionController() {
        // Inform the Abstract parent controller of the concrete CajaRecaudacion Entity
        super(CajaRecaudacion.class);
//        this.setLimitedByCuenta(Boolean.TRUE);
//        this.setNamedQuery("CajaRecaudacion.findAllByCuenta");
    }

    @Override
    public CajaRecaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setCajaRecaudacionIdCuenta(this.getUserCount());
        return this.getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cajaRecaudacionIdCuentaController.setSelected(null);
        cajaRecaudacionIdTerminalController.setSelected(null);
        cajaRecaudacionIdUsuarioController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEgresoCajaRecaudacionListEmpty();
        this.setIsRecaudacionListEmpty();
        this.setIsCajaProcesoListEmpty();
        this.setIsResumenRecaudacionListEmpty();
        this.setIsInventarioCajaListEmpty();
    }

    public boolean getIsEgresoCajaRecaudacionListEmpty() {
        return this.isEgresoCajaRecaudacionListEmpty;
    }

    private void setIsEgresoCajaRecaudacionListEmpty() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            this.isEgresoCajaRecaudacionListEmpty = ejbFacade.isEgresoCajaRecaudacionListEmpty(selected);
        } else {
            this.isEgresoCajaRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoCajaRecaudacion
     * entities that are retrieved from CajaRecaudacion and returns the
     * navigation outcome.
     *
     * @return navigation outcome for EgresoCajaRecaudacion page
     */
    public String navigateEgresoCajaRecaudacionList() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            List<EgresoCajaRecaudacion> selectedEgresoCajaRecaudacionList = ejbFacade.findEgresoCajaRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoCajaRecaudacion_items", selectedEgresoCajaRecaudacionList);
        }
        return "/app/egresoCajaRecaudacion/index";
    }

    public boolean getIsRecaudacionListEmpty() {
        return this.isRecaudacionListEmpty;
    }

    private void setIsRecaudacionListEmpty() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            this.isRecaudacionListEmpty = ejbFacade.isRecaudacionListEmpty(selected);
        } else {
            this.isRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Recaudacion entities that
     * are retrieved from CajaRecaudacion and returns the navigation outcome.
     *
     * @return navigation outcome for Recaudacion page
     */
    public String navigateRecaudacionList() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            List<Recaudacion> selectedRecaudacionList = ejbFacade.findRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Recaudacion_items", selectedRecaudacionList);
        }
        return "/app/recaudacion/index";
    }

    public boolean getIsCajaProcesoListEmpty() {
        return this.isCajaProcesoListEmpty;
    }

    private void setIsCajaProcesoListEmpty() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            this.isCajaProcesoListEmpty = ejbFacade.isCajaProcesoListEmpty(selected);
        } else {
            this.isCajaProcesoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CajaProceso entities that
     * are retrieved from CajaRecaudacion and returns the navigation outcome.
     *
     * @return navigation outcome for CajaProceso page
     */
    public String navigateCajaProcesoList() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            List<CajaProceso> selectedCajaProcesoList = ejbFacade.findCajaProcesoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaProceso_items", selectedCajaProcesoList);
        }
        return "/app/cajaProceso/index";
    }

    public boolean getIsResumenRecaudacionListEmpty() {
        return this.isResumenRecaudacionListEmpty;
    }

    private void setIsResumenRecaudacionListEmpty() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            this.isResumenRecaudacionListEmpty = ejbFacade.isResumenRecaudacionListEmpty(selected);
        } else {
            this.isResumenRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ResumenRecaudacion
     * entities that are retrieved from CajaRecaudacion and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ResumenRecaudacion page
     */
    public String navigateResumenRecaudacionList() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            List<ResumenRecaudacion> selectedResumenRecaudacionList = ejbFacade.findResumenRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ResumenRecaudacion_items", selectedResumenRecaudacionList);
        }
        return "/app/resumenRecaudacion/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCajaRecaudacionIdCuenta(ActionEvent event) {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null && cajaRecaudacionIdCuentaController.getSelected() == null) {
            cajaRecaudacionIdCuentaController.setSelected(selected.getCajaRecaudacionIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCajaRecaudacionIdTerminal(ActionEvent event) {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null && cajaRecaudacionIdTerminalController.getSelected() == null) {
            cajaRecaudacionIdTerminalController.setSelected(selected.getCajaRecaudacionIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCajaRecaudacionIdUsuario(ActionEvent event) {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null && cajaRecaudacionIdUsuarioController.getSelected() == null) {
            cajaRecaudacionIdUsuarioController.setSelected(selected.getCajaRecaudacionIdUsuario());
        }
    }

    public boolean getIsInventarioCajaListEmpty() {
        return this.isInventarioCajaListEmpty;
    }

    private void setIsInventarioCajaListEmpty() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            this.isInventarioCajaListEmpty = ejbFacade.isInventarioCajaListEmpty(selected);
        } else {
            this.isInventarioCajaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of InventarioCaja entities
     * that are retrieved from CajaRecaudacion and returns the navigation
     * outcome.
     *
     * @return navigation outcome for InventarioCaja page
     */
    public String navigateInventarioCajaList() {
        CajaRecaudacion selected = this.getSelected();
        if (selected != null) {
            CajaRecaudacionFacade ejbFacade = (CajaRecaudacionFacade) this.getFacade();
            List<InventarioCaja> selectedInventarioCajaList = ejbFacade.findInventarioCajaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InventarioCaja_items", selectedInventarioCajaList);
        }
        return "/app/inventarioCaja/index";
    }

}
