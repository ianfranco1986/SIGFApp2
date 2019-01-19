package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.JornadaLaboral;
import com.areatecnica.sigf.entities.JornadaTrabajador;
import java.util.List;
import com.areatecnica.sigf.facade.JornadaLaboralFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "jornadaLaboralController")
@ViewScoped
public class JornadaLaboralController extends AbstractController<JornadaLaboral> {

    @Inject
    private HorarioJornadaController jornadaLaboralIdHorarioJornadaController;

    // Flags to indicate if child collections are empty
    private boolean isJornadaTrabajadorListEmpty;

    public JornadaLaboralController() {
        // Inform the Abstract parent controller of the concrete JornadaLaboral Entity
        super(JornadaLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        jornadaLaboralIdHorarioJornadaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsJornadaTrabajadorListEmpty();
    }

    /**
     * Sets the "selected" attribute of the HorarioJornada controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJornadaLaboralIdHorarioJornada(ActionEvent event) {
        JornadaLaboral selected = this.getSelected();
        if (selected != null && jornadaLaboralIdHorarioJornadaController.getSelected() == null) {
            jornadaLaboralIdHorarioJornadaController.setSelected(selected.getJornadaLaboralIdHorarioJornada());
        }
    }

    public boolean getIsJornadaTrabajadorListEmpty() {
        return this.isJornadaTrabajadorListEmpty;
    }

    private void setIsJornadaTrabajadorListEmpty() {
        JornadaLaboral selected = this.getSelected();
        if (selected != null) {
            JornadaLaboralFacade ejbFacade = (JornadaLaboralFacade) this.getFacade();
            this.isJornadaTrabajadorListEmpty = ejbFacade.isJornadaTrabajadorListEmpty(selected);
        } else {
            this.isJornadaTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of JornadaTrabajador
     * entities that are retrieved from JornadaLaboral and returns the
     * navigation outcome.
     *
     * @return navigation outcome for JornadaTrabajador page
     */
    public String navigateJornadaTrabajadorList() {
        JornadaLaboral selected = this.getSelected();
        if (selected != null) {
            JornadaLaboralFacade ejbFacade = (JornadaLaboralFacade) this.getFacade();
            List<JornadaTrabajador> selectedJornadaTrabajadorList = ejbFacade.findJornadaTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("JornadaTrabajador_items", selectedJornadaTrabajadorList);
        }
        return "/app/jornadaTrabajador/index";
    }

}
