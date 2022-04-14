package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoDescuentoTrabajador;
import com.areatecnica.sigf.entities.DescuentoTrabajador;
import java.util.List;
import com.areatecnica.sigf.facade.TipoDescuentoTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoDescuentoTrabajadorController")
@ViewScoped
public class TipoDescuentoTrabajadorController extends AbstractController<TipoDescuentoTrabajador> {

    @Inject
    private CuentaController tipoDescuentoTrabajadorIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isDescuentoTrabajadorListEmpty;

    public TipoDescuentoTrabajadorController() {
        // Inform the Abstract parent controller of the concrete TipoDescuentoTrabajador Entity
        super(TipoDescuentoTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoDescuentoTrabajadorIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDescuentoTrabajadorListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoDescuentoTrabajadorIdCuenta(ActionEvent event) {
        TipoDescuentoTrabajador selected = this.getSelected();
        if (selected != null && tipoDescuentoTrabajadorIdCuentaController.getSelected() == null) {
            tipoDescuentoTrabajadorIdCuentaController.setSelected(selected.getTipoDescuentoTrabajadorIdCuenta());
        }
    }

    public boolean getIsDescuentoTrabajadorListEmpty() {
        return this.isDescuentoTrabajadorListEmpty;
    }

    private void setIsDescuentoTrabajadorListEmpty() {
        TipoDescuentoTrabajador selected = this.getSelected();
        if (selected != null) {
            TipoDescuentoTrabajadorFacade ejbFacade = (TipoDescuentoTrabajadorFacade) this.getFacade();
            this.isDescuentoTrabajadorListEmpty = ejbFacade.isDescuentoTrabajadorListEmpty(selected);
        } else {
            this.isDescuentoTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoTrabajador
     * entities that are retrieved from TipoDescuentoTrabajador and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DescuentoTrabajador page
     */
    public String navigateDescuentoTrabajadorList() {
        TipoDescuentoTrabajador selected = this.getSelected();
        if (selected != null) {
            TipoDescuentoTrabajadorFacade ejbFacade = (TipoDescuentoTrabajadorFacade) this.getFacade();
            List<DescuentoTrabajador> selectedDescuentoTrabajadorList = ejbFacade.findDescuentoTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoTrabajador_items", selectedDescuentoTrabajadorList);
        }
        return "/app/descuentoTrabajador/index";
    }

}
