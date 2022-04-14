package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.OperadorTransporte;
import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;
import com.areatecnica.sigf.facade.OperadorTransporteFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "operadorTransporteController")
@ViewScoped
public class OperadorTransporteController extends AbstractController<OperadorTransporte> {

    @Inject
    private RepresentanteLegalController operadorTransporteIdRepresentanteController;

    // Flags to indicate if child collections are empty
    private boolean isRelacionLaboralListEmpty;
    private boolean isUnidadNegocioListEmpty;

    public OperadorTransporteController() {
        // Inform the Abstract parent controller of the concrete OperadorTransporte Entity
        super(OperadorTransporte.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        operadorTransporteIdRepresentanteController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRelacionLaboralListEmpty();
        this.setIsUnidadNegocioListEmpty();
    }

    /**
     * Sets the "selected" attribute of the RepresentanteLegal controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareOperadorTransporteIdRepresentante(ActionEvent event) {
        OperadorTransporte selected = this.getSelected();
        if (selected != null && operadorTransporteIdRepresentanteController.getSelected() == null) {
            operadorTransporteIdRepresentanteController.setSelected(selected.getOperadorTransporteIdRepresentante());
        }
    }

    public boolean getIsRelacionLaboralListEmpty() {
        return this.isRelacionLaboralListEmpty;
    }

    private void setIsRelacionLaboralListEmpty() {
        OperadorTransporte selected = this.getSelected();
        if (selected != null) {
            OperadorTransporteFacade ejbFacade = (OperadorTransporteFacade) this.getFacade();
            this.isRelacionLaboralListEmpty = ejbFacade.isRelacionLaboralListEmpty(selected);
        } else {
            this.isRelacionLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from OperadorTransporte and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        OperadorTransporte selected = this.getSelected();
        if (selected != null) {
            OperadorTransporteFacade ejbFacade = (OperadorTransporteFacade) this.getFacade();
            List<RelacionLaboral> selectedRelacionLaboralList = ejbFacade.findRelacionLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", selectedRelacionLaboralList);
        }
        return "/app/relacionLaboral/index";
    }

    public boolean getIsUnidadNegocioListEmpty() {
        return this.isUnidadNegocioListEmpty;
    }

    private void setIsUnidadNegocioListEmpty() {
        OperadorTransporte selected = this.getSelected();
        if (selected != null) {
            OperadorTransporteFacade ejbFacade = (OperadorTransporteFacade) this.getFacade();
            this.isUnidadNegocioListEmpty = ejbFacade.isUnidadNegocioListEmpty(selected);
        } else {
            this.isUnidadNegocioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of UnidadNegocio entities
     * that are retrieved from OperadorTransporte and returns the navigation
     * outcome.
     *
     * @return navigation outcome for UnidadNegocio page
     */
    public String navigateUnidadNegocioList() {
        OperadorTransporte selected = this.getSelected();
        if (selected != null) {
            OperadorTransporteFacade ejbFacade = (OperadorTransporteFacade) this.getFacade();
            List<UnidadNegocio> selectedUnidadNegocioList = ejbFacade.findUnidadNegocioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("UnidadNegocio_items", selectedUnidadNegocioList);
        }
        return "/app/unidadNegocio/index";
    }

}
