package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoDocumento;
import com.areatecnica.sigf.entities.Compra;
import java.util.List;
import com.areatecnica.sigf.facade.TipoDocumentoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoDocumentoController")
@ViewScoped
public class TipoDocumentoController extends AbstractController<TipoDocumento> {

    // Flags to indicate if child collections are empty
    private boolean isCompraListEmpty;

    public TipoDocumentoController() {
        // Inform the Abstract parent controller of the concrete TipoDocumento Entity
        super(TipoDocumento.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCompraListEmpty();
    }

    public boolean getIsCompraListEmpty() {
        return this.isCompraListEmpty;
    }

    private void setIsCompraListEmpty() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            this.isCompraListEmpty = ejbFacade.isCompraListEmpty(selected);
        } else {
            this.isCompraListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Compra entities that are
     * retrieved from TipoDocumento and returns the navigation outcome.
     *
     * @return navigation outcome for Compra page
     */
    public String navigateCompraList() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            List<Compra> selectedCompraList = ejbFacade.findCompraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Compra_items", selectedCompraList);
        }
        return "/app/compra/index";
    }

}
