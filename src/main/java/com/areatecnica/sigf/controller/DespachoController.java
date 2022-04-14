package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Despacho;
import com.areatecnica.sigf.facade.DespachoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "despachoController")
@ViewScoped
public class DespachoController extends AbstractController<Despacho> {

    @Inject
    private BusController despachoIdBusController;
    @Inject
    private TrabajadorController despachoIdTrabajadorController;
    @Inject
    private UsuarioController despachoIdInspectorController;
    @Inject
    private ServicioController despachoIdServicioController;

    public DespachoController() {
        // Inform the Abstract parent controller of the concrete Despacho Entity
        super(Despacho.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        despachoIdBusController.setSelected(null);
        despachoIdTrabajadorController.setSelected(null);
        despachoIdInspectorController.setSelected(null);
        despachoIdServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoIdBus(ActionEvent event) {
        Despacho selected = this.getSelected();
        if (selected != null && despachoIdBusController.getSelected() == null) {
            despachoIdBusController.setSelected(selected.getDespachoIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoIdTrabajador(ActionEvent event) {
        Despacho selected = this.getSelected();
        if (selected != null && despachoIdTrabajadorController.getSelected() == null) {
            despachoIdTrabajadorController.setSelected(selected.getDespachoIdTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoIdInspector(ActionEvent event) {
        Despacho selected = this.getSelected();
        if (selected != null && despachoIdInspectorController.getSelected() == null) {
            despachoIdInspectorController.setSelected(selected.getDespachoIdInspector());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoIdServicio(ActionEvent event) {
        Despacho selected = this.getSelected();
        if (selected != null && despachoIdServicioController.getSelected() == null) {
            despachoIdServicioController.setSelected(selected.getDespachoIdServicio());
        }
    }

}
