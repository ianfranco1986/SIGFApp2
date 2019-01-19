package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.FiniquitoRelacionLaboral;
import com.areatecnica.sigf.facade.FiniquitoRelacionLaboralFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "finiquitoRelacionLaboralController")
@ViewScoped
public class FiniquitoRelacionLaboralController extends AbstractController<FiniquitoRelacionLaboral> {

    @Inject
    private CausalFiniquitoController finiquitoRelacionLaboralIdCausalFiniquitoController;
    @Inject
    private RelacionLaboralController finiquitoRelacionLaboralIdRelacionLaboralController;

    public FiniquitoRelacionLaboralController() {
        // Inform the Abstract parent controller of the concrete FiniquitoRelacionLaboral Entity
        super(FiniquitoRelacionLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        finiquitoRelacionLaboralIdCausalFiniquitoController.setSelected(null);
        finiquitoRelacionLaboralIdRelacionLaboralController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CausalFiniquito controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFiniquitoRelacionLaboralIdCausalFiniquito(ActionEvent event) {
        FiniquitoRelacionLaboral selected = this.getSelected();
        if (selected != null && finiquitoRelacionLaboralIdCausalFiniquitoController.getSelected() == null) {
            finiquitoRelacionLaboralIdCausalFiniquitoController.setSelected(selected.getFiniquitoRelacionLaboralIdCausalFiniquito());
        }
    }

    /**
     * Sets the "selected" attribute of the RelacionLaboral controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFiniquitoRelacionLaboralIdRelacionLaboral(ActionEvent event) {
        FiniquitoRelacionLaboral selected = this.getSelected();
        if (selected != null && finiquitoRelacionLaboralIdRelacionLaboralController.getSelected() == null) {
            finiquitoRelacionLaboralIdRelacionLaboralController.setSelected(selected.getFiniquitoRelacionLaboralIdRelacionLaboral());
        }
    }

}
