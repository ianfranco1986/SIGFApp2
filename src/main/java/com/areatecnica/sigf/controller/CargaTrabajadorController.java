package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CargaRetroactiva;
import com.areatecnica.sigf.entities.CargaTrabajador;
import com.areatecnica.sigf.facade.CargaTrabajadorFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "cargaTrabajadorController")
@ViewScoped
public class CargaTrabajadorController extends AbstractController<CargaTrabajador> {

    @Inject
    private ParentescoCargaController cargaTrabajadorIdParentescoController;
    @Inject
    private TipoCargaFamiliarController cargaTrabajadorIdTipoController;
    @Inject
    private TrabajadorController cargaTrabajadorIdTrabajadorController;

    // Flags to indicate if child collections are empty
    private boolean isCargaRetroactivaListEmpty;

    public CargaTrabajadorController() {
        // Inform the Abstract parent controller of the concrete CargaTrabajador Entity
        super(CargaTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cargaTrabajadorIdParentescoController.setSelected(null);
        cargaTrabajadorIdTipoController.setSelected(null);
        cargaTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCargaRetroactivaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the ParentescoCarga controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaTrabajadorIdParentesco(ActionEvent event) {
        CargaTrabajador selected = this.getSelected();
        if (selected != null && cargaTrabajadorIdParentescoController.getSelected() == null) {
            cargaTrabajadorIdParentescoController.setSelected(selected.getCargaTrabajadorIdParentesco());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCargaFamiliar controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaTrabajadorIdTipo(ActionEvent event) {
        CargaTrabajador selected = this.getSelected();
        if (selected != null && cargaTrabajadorIdTipoController.getSelected() == null) {
            cargaTrabajadorIdTipoController.setSelected(selected.getCargaTrabajadorIdTipo());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaTrabajadorIdTrabajador(ActionEvent event) {
        CargaTrabajador selected = this.getSelected();
        if (selected != null && cargaTrabajadorIdTrabajadorController.getSelected() == null) {
            cargaTrabajadorIdTrabajadorController.setSelected(selected.getCargaTrabajadorIdTrabajador());
        }
    }

    public boolean getIsCargaRetroactivaListEmpty() {
        return this.isCargaRetroactivaListEmpty;
    }

    private void setIsCargaRetroactivaListEmpty() {
        CargaTrabajador selected = this.getSelected();
        if (selected != null) {
            CargaTrabajadorFacade ejbFacade = (CargaTrabajadorFacade) this.getFacade();
            this.isCargaRetroactivaListEmpty = ejbFacade.isCargaRetroactivaListEmpty(selected);
        } else {
            this.isCargaRetroactivaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargaRetroactiva entities
     * that are retrieved from CargaTrabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CargaRetroactiva page
     */
    public String navigateCargaRetroactivaList() {
        CargaTrabajador selected = this.getSelected();
        if (selected != null) {
            CargaTrabajadorFacade ejbFacade = (CargaTrabajadorFacade) this.getFacade();
            List<CargaRetroactiva> selectedCargaRetroactivaList = ejbFacade.findCargaRetroactivaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaRetroactiva_items", selectedCargaRetroactivaList);
        }
        return "/app/cargaRetroactiva/index";
    }

}
