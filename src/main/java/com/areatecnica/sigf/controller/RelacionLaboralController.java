package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.FiniquitoRelacionLaboral;
import java.util.List;
import com.areatecnica.sigf.facade.RelacionLaboralFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "relacionLaboralController")
@ViewScoped
public class RelacionLaboralController extends AbstractController<RelacionLaboral> {

    @Inject
    private TipoContratoController relacionLaboralIdTipoContratoController;
    @Inject
    private EmpresaController relacionLaboralIdEmpresaController;
    @Inject
    private OperadorTransporteController relacionLaboralIdOperadorController;
    @Inject
    private TerminalController relacionLaboralIdTerminalController;
    @Inject
    private TrabajadorController relacionLaboralIdTrabajadorController;
    @Inject
    private TipoTrabajadorController relacionLaboralIdTipoTrabajadorController;

    // Flags to indicate if child collections are empty
    private boolean isFiniquitoRelacionLaboralListEmpty;

    public RelacionLaboralController() {
        // Inform the Abstract parent controller of the concrete RelacionLaboral Entity
        super(RelacionLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        relacionLaboralIdTipoContratoController.setSelected(null);
        relacionLaboralIdEmpresaController.setSelected(null);
        relacionLaboralIdOperadorController.setSelected(null);
        relacionLaboralIdTerminalController.setSelected(null);
        relacionLaboralIdTrabajadorController.setSelected(null);
        relacionLaboralIdTipoTrabajadorController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsFiniquitoRelacionLaboralListEmpty();
    }

    /**
     * Sets the "selected" attribute of the TipoContrato controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdTipoContrato(ActionEvent event) {
        RelacionLaboral selected = this.getSelected();
        if (selected != null && relacionLaboralIdTipoContratoController.getSelected() == null) {
            relacionLaboralIdTipoContratoController.setSelected(selected.getRelacionLaboralIdTipoContrato());
        }
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdEmpresa(ActionEvent event) {
        RelacionLaboral selected = this.getSelected();
        if (selected != null && relacionLaboralIdEmpresaController.getSelected() == null) {
            relacionLaboralIdEmpresaController.setSelected(selected.getRelacionLaboralIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the OperadorTransporte controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdOperador(ActionEvent event) {
        RelacionLaboral selected = this.getSelected();
        if (selected != null && relacionLaboralIdOperadorController.getSelected() == null) {
            relacionLaboralIdOperadorController.setSelected(selected.getRelacionLaboralIdOperador());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdTerminal(ActionEvent event) {
        RelacionLaboral selected = this.getSelected();
        if (selected != null && relacionLaboralIdTerminalController.getSelected() == null) {
            relacionLaboralIdTerminalController.setSelected(selected.getRelacionLaboralIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdTrabajador(ActionEvent event) {
        RelacionLaboral selected = this.getSelected();
        if (selected != null && relacionLaboralIdTrabajadorController.getSelected() == null) {
            relacionLaboralIdTrabajadorController.setSelected(selected.getRelacionLaboralIdTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoTrabajador controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdTipoTrabajador(ActionEvent event) {
        RelacionLaboral selected = this.getSelected();
        if (selected != null && relacionLaboralIdTipoTrabajadorController.getSelected() == null) {
            relacionLaboralIdTipoTrabajadorController.setSelected(selected.getRelacionLaboralIdTipoTrabajador());
        }
    }

    public boolean getIsFiniquitoRelacionLaboralListEmpty() {
        return this.isFiniquitoRelacionLaboralListEmpty;
    }

    private void setIsFiniquitoRelacionLaboralListEmpty() {
        RelacionLaboral selected = this.getSelected();
        if (selected != null) {
            RelacionLaboralFacade ejbFacade = (RelacionLaboralFacade) this.getFacade();
            this.isFiniquitoRelacionLaboralListEmpty = ejbFacade.isFiniquitoRelacionLaboralListEmpty(selected);
        } else {
            this.isFiniquitoRelacionLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of FiniquitoRelacionLaboral
     * entities that are retrieved from RelacionLaboral and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FiniquitoRelacionLaboral page
     */
    public String navigateFiniquitoRelacionLaboralList() {
        RelacionLaboral selected = this.getSelected();
        if (selected != null) {
            RelacionLaboralFacade ejbFacade = (RelacionLaboralFacade) this.getFacade();
            List<FiniquitoRelacionLaboral> selectedFiniquitoRelacionLaboralList = ejbFacade.findFiniquitoRelacionLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FiniquitoRelacionLaboral_items", selectedFiniquitoRelacionLaboralList);
        }
        return "/app/finiquitoRelacionLaboral/index";
    }

}
