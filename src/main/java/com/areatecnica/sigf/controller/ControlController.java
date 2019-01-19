package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Control;
import com.areatecnica.sigf.entities.ControlServicio;
import com.areatecnica.sigf.entities.ControlHorarioServicio;
import java.util.List;
import com.areatecnica.sigf.facade.ControlFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "controlController")
@ViewScoped
public class ControlController extends AbstractController<Control> {

    @Inject
    private CuentaController controlIdCuentaController;
    @Inject
    private TipoControlController controlIdTipoController;

    // Flags to indicate if child collections are empty
    private boolean isControlServicioListEmpty;
    private boolean isControlHorarioServicioListEmpty;

    public ControlController() {
        // Inform the Abstract parent controller of the concrete Control Entity
        super(Control.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        controlIdCuentaController.setSelected(null);
        controlIdTipoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsControlServicioListEmpty();
        this.setIsControlHorarioServicioListEmpty();
    }

    public boolean getIsControlServicioListEmpty() {
        return this.isControlServicioListEmpty;
    }

    private void setIsControlServicioListEmpty() {
        Control selected = this.getSelected();
        if (selected != null) {
            ControlFacade ejbFacade = (ControlFacade) this.getFacade();
            this.isControlServicioListEmpty = ejbFacade.isControlServicioListEmpty(selected);
        } else {
            this.isControlServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ControlServicio entities
     * that are retrieved from Control and returns the navigation outcome.
     *
     * @return navigation outcome for ControlServicio page
     */
    public String navigateControlServicioList() {
        Control selected = this.getSelected();
        if (selected != null) {
            ControlFacade ejbFacade = (ControlFacade) this.getFacade();
            List<ControlServicio> selectedControlServicioList = ejbFacade.findControlServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlServicio_items", selectedControlServicioList);
        }
        return "/app/controlServicio/index";
    }

    public boolean getIsControlHorarioServicioListEmpty() {
        return this.isControlHorarioServicioListEmpty;
    }

    private void setIsControlHorarioServicioListEmpty() {
        Control selected = this.getSelected();
        if (selected != null) {
            ControlFacade ejbFacade = (ControlFacade) this.getFacade();
            this.isControlHorarioServicioListEmpty = ejbFacade.isControlHorarioServicioListEmpty(selected);
        } else {
            this.isControlHorarioServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ControlHorarioServicio
     * entities that are retrieved from Control and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ControlHorarioServicio page
     */
    public String navigateControlHorarioServicioList() {
        Control selected = this.getSelected();
        if (selected != null) {
            ControlFacade ejbFacade = (ControlFacade) this.getFacade();
            List<ControlHorarioServicio> selectedControlHorarioServicioList = ejbFacade.findControlHorarioServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlHorarioServicio_items", selectedControlHorarioServicioList);
        }
        return "/app/controlHorarioServicio/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlIdCuenta(ActionEvent event) {
        Control selected = this.getSelected();
        if (selected != null && controlIdCuentaController.getSelected() == null) {
            controlIdCuentaController.setSelected(selected.getControlIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoControl controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlIdTipo(ActionEvent event) {
        Control selected = this.getSelected();
        if (selected != null && controlIdTipoController.getSelected() == null) {
            controlIdTipoController.setSelected(selected.getControlIdTipo());
        }
    }

}
