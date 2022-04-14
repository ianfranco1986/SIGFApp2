package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.HorarioServicio;
import com.areatecnica.sigf.entities.ControlHorarioServicio;
import java.util.List;
import com.areatecnica.sigf.facade.HorarioServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "horarioServicioController")
@ViewScoped
public class HorarioServicioController extends AbstractController<HorarioServicio> {

    @Inject
    private TipoEstacionalidadController horarioServicioIdTipoEstacionalidadController;
    @Inject
    private ServicioController horarioServicioIdServicioController;

    // Flags to indicate if child collections are empty
    private boolean isControlHorarioServicioListEmpty;

    public HorarioServicioController() {
        // Inform the Abstract parent controller of the concrete HorarioServicio Entity
        super(HorarioServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        horarioServicioIdTipoEstacionalidadController.setSelected(null);
        horarioServicioIdServicioController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsControlHorarioServicioListEmpty();
    }

    public boolean getIsControlHorarioServicioListEmpty() {
        return this.isControlHorarioServicioListEmpty;
    }

    private void setIsControlHorarioServicioListEmpty() {
        HorarioServicio selected = this.getSelected();
        if (selected != null) {
            HorarioServicioFacade ejbFacade = (HorarioServicioFacade) this.getFacade();
            this.isControlHorarioServicioListEmpty = ejbFacade.isControlHorarioServicioListEmpty(selected);
        } else {
            this.isControlHorarioServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ControlHorarioServicio
     * entities that are retrieved from HorarioServicio and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ControlHorarioServicio page
     */
    public String navigateControlHorarioServicioList() {
        HorarioServicio selected = this.getSelected();
        if (selected != null) {
            HorarioServicioFacade ejbFacade = (HorarioServicioFacade) this.getFacade();
            List<ControlHorarioServicio> selectedControlHorarioServicioList = ejbFacade.findControlHorarioServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlHorarioServicio_items", selectedControlHorarioServicioList);
        }
        return "/app/controlHorarioServicio/index";
    }

    /**
     * Sets the "selected" attribute of the TipoEstacionalidad controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHorarioServicioIdTipoEstacionalidad(ActionEvent event) {
        HorarioServicio selected = this.getSelected();
        if (selected != null && horarioServicioIdTipoEstacionalidadController.getSelected() == null) {
            horarioServicioIdTipoEstacionalidadController.setSelected(selected.getHorarioServicioIdTipoEstacionalidad());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHorarioServicioIdServicio(ActionEvent event) {
        HorarioServicio selected = this.getSelected();
        if (selected != null && horarioServicioIdServicioController.getSelected() == null) {
            horarioServicioIdServicioController.setSelected(selected.getHorarioServicioIdServicio());
        }
    }

}
