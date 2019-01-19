package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.FrecuenciaServicio;
import com.areatecnica.sigf.facade.FrecuenciaServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "frecuenciaServicioController")
@ViewScoped
public class FrecuenciaServicioController extends AbstractController<FrecuenciaServicio> {

    @Inject
    private PeriodoFrecuenciaController frecuenciaServicioIdPeriodoController;
    @Inject
    private ServicioController frecuenciaServicioIdServicioController;
    @Inject
    private TipoDemandaController frecuenciaServicioIdTipoDemandaController;
    @Inject
    private TipoDiaFrecuenciaController frecuenciaServicioIdTipoDiaController;

    public FrecuenciaServicioController() {
        // Inform the Abstract parent controller of the concrete FrecuenciaServicio Entity
        super(FrecuenciaServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        frecuenciaServicioIdPeriodoController.setSelected(null);
        frecuenciaServicioIdServicioController.setSelected(null);
        frecuenciaServicioIdTipoDemandaController.setSelected(null);
        frecuenciaServicioIdTipoDiaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the PeriodoFrecuencia controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFrecuenciaServicioIdPeriodo(ActionEvent event) {
        FrecuenciaServicio selected = this.getSelected();
        if (selected != null && frecuenciaServicioIdPeriodoController.getSelected() == null) {
            frecuenciaServicioIdPeriodoController.setSelected(selected.getFrecuenciaServicioIdPeriodo());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFrecuenciaServicioIdServicio(ActionEvent event) {
        FrecuenciaServicio selected = this.getSelected();
        if (selected != null && frecuenciaServicioIdServicioController.getSelected() == null) {
            frecuenciaServicioIdServicioController.setSelected(selected.getFrecuenciaServicioIdServicio());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoDemanda controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFrecuenciaServicioIdTipoDemanda(ActionEvent event) {
        FrecuenciaServicio selected = this.getSelected();
        if (selected != null && frecuenciaServicioIdTipoDemandaController.getSelected() == null) {
            frecuenciaServicioIdTipoDemandaController.setSelected(selected.getFrecuenciaServicioIdTipoDemanda());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoDiaFrecuencia controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFrecuenciaServicioIdTipoDia(ActionEvent event) {
        FrecuenciaServicio selected = this.getSelected();
        if (selected != null && frecuenciaServicioIdTipoDiaController.getSelected() == null) {
            frecuenciaServicioIdTipoDiaController.setSelected(selected.getFrecuenciaServicioIdTipoDia());
        }
    }

}
