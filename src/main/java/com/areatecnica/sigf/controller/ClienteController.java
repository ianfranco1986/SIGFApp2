package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Cliente;
import com.areatecnica.sigf.entities.Factura;
import com.areatecnica.sigf.facade.ClienteFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "clienteController")
@ViewScoped
public class ClienteController extends AbstractController<Cliente> {

    @Inject
    private ComunaController clienteComunaIdController;

    // Flags to indicate if child collections are empty
    private boolean isFacturaListEmpty;

    public ClienteController() {
        // Inform the Abstract parent controller of the concrete Cliente Entity
        super(Cliente.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        clienteComunaIdController.setSelected(null);
    }


    /**
     * Sets the "selected" attribute of the Comuna controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareClienteComunaId(ActionEvent event) {
        Cliente selected = this.getSelected();
        if (selected != null && clienteComunaIdController.getSelected() == null) {
            clienteComunaIdController.setSelected(selected.getClienteComunaId());
        }
    }

    public boolean getIsFacturaListEmpty() {
        return this.isFacturaListEmpty;
    }


    /**
     * Sets the "items" attribute with a collection of Factura entities that are
     * retrieved from Cliente and returns the navigation outcome.
     *
     * @return navigation outcome for Factura page
     */
    public String navigateFacturaList() {
        Cliente selected = this.getSelected();
        if (selected != null) {
            ClienteFacade ejbFacade = (ClienteFacade) this.getFacade();
            List<Factura> selectedFacturaList = ejbFacade.findFacturaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Factura_items", selectedFacturaList);
        }
        return "/app/factura/index";
    }

}
