package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoEstacionalidad;
import com.areatecnica.sigf.entities.HorarioServicio;
import java.util.List;
import com.areatecnica.sigf.facade.TipoEstacionalidadFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoEstacionalidadController")
@ViewScoped
public class TipoEstacionalidadController extends AbstractController<TipoEstacionalidad> {

    // Flags to indicate if child collections are empty
    private boolean isHorarioServicioListEmpty;

    public TipoEstacionalidadController() {
        // Inform the Abstract parent controller of the concrete TipoEstacionalidad Entity
        super(TipoEstacionalidad.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsHorarioServicioListEmpty();
    }

    public boolean getIsHorarioServicioListEmpty() {
        return this.isHorarioServicioListEmpty;
    }

    private void setIsHorarioServicioListEmpty() {
        TipoEstacionalidad selected = this.getSelected();
        if (selected != null) {
            TipoEstacionalidadFacade ejbFacade = (TipoEstacionalidadFacade) this.getFacade();
            this.isHorarioServicioListEmpty = ejbFacade.isHorarioServicioListEmpty(selected);
        } else {
            this.isHorarioServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HorarioServicio entities
     * that are retrieved from TipoEstacionalidad and returns the navigation
     * outcome.
     *
     * @return navigation outcome for HorarioServicio page
     */
    public String navigateHorarioServicioList() {
        TipoEstacionalidad selected = this.getSelected();
        if (selected != null) {
            TipoEstacionalidadFacade ejbFacade = (TipoEstacionalidadFacade) this.getFacade();
            List<HorarioServicio> selectedHorarioServicioList = ejbFacade.findHorarioServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HorarioServicio_items", selectedHorarioServicioList);
        }
        return "/app/horarioServicio/index";
    }

}
