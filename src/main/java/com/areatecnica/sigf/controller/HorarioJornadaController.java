package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.HorarioJornada;
import com.areatecnica.sigf.entities.JornadaLaboral;
import java.util.List;
import com.areatecnica.sigf.facade.HorarioJornadaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "horarioJornadaController")
@ViewScoped
public class HorarioJornadaController extends AbstractController<HorarioJornada> {

    @Inject
    private CuentaController horarioJornadaIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isJornadaLaboralListEmpty;

    public HorarioJornadaController() {
        // Inform the Abstract parent controller of the concrete HorarioJornada Entity
        super(HorarioJornada.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        horarioJornadaIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsJornadaLaboralListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHorarioJornadaIdCuenta(ActionEvent event) {
        HorarioJornada selected = this.getSelected();
        if (selected != null && horarioJornadaIdCuentaController.getSelected() == null) {
            horarioJornadaIdCuentaController.setSelected(selected.getHorarioJornadaIdCuenta());
        }
    }

    public boolean getIsJornadaLaboralListEmpty() {
        return this.isJornadaLaboralListEmpty;
    }

    private void setIsJornadaLaboralListEmpty() {
        HorarioJornada selected = this.getSelected();
        if (selected != null) {
            HorarioJornadaFacade ejbFacade = (HorarioJornadaFacade) this.getFacade();
            this.isJornadaLaboralListEmpty = ejbFacade.isJornadaLaboralListEmpty(selected);
        } else {
            this.isJornadaLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of JornadaLaboral entities
     * that are retrieved from HorarioJornada and returns the navigation
     * outcome.
     *
     * @return navigation outcome for JornadaLaboral page
     */
    public String navigateJornadaLaboralList() {
        HorarioJornada selected = this.getSelected();
        if (selected != null) {
            HorarioJornadaFacade ejbFacade = (HorarioJornadaFacade) this.getFacade();
            List<JornadaLaboral> selectedJornadaLaboralList = ejbFacade.findJornadaLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("JornadaLaboral_items", selectedJornadaLaboralList);
        }
        return "/app/jornadaLaboral/index";
    }

}
