package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.OperadorTransporte;
import com.areatecnica.sigf.entities.RepresentanteEmpresa;
import com.areatecnica.sigf.entities.RepresentanteLegal;
import com.areatecnica.sigf.facade.RepresentanteLegalFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "representanteLegalController")
@ViewScoped
public class RepresentanteLegalController extends AbstractController<RepresentanteLegal> {

    @Inject
    private CuentaController representanteLegalIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isRepresentanteEmpresaListEmpty;
    private boolean isOperadorTransporteListEmpty;

    public RepresentanteLegalController() {
        // Inform the Abstract parent controller of the concrete RepresentanteLegal Entity
        super(RepresentanteLegal.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        representanteLegalIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRepresentanteEmpresaListEmpty();
        this.setIsOperadorTransporteListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRepresentanteLegalIdCuenta(ActionEvent event) {
        RepresentanteLegal selected = this.getSelected();
        if (selected != null && representanteLegalIdCuentaController.getSelected() == null) {
            representanteLegalIdCuentaController.setSelected(selected.getRepresentanteLegalIdCuenta());
        }
    }

    public boolean getIsRepresentanteEmpresaListEmpty() {
        return this.isRepresentanteEmpresaListEmpty;
    }

    private void setIsRepresentanteEmpresaListEmpty() {
        RepresentanteLegal selected = this.getSelected();
        if (selected != null) {
            RepresentanteLegalFacade ejbFacade = (RepresentanteLegalFacade) this.getFacade();
            this.isRepresentanteEmpresaListEmpty = ejbFacade.isRepresentanteEmpresaListEmpty(selected);
        } else {
            this.isRepresentanteEmpresaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RepresentanteEmpresa
     * entities that are retrieved from RepresentanteLegal and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RepresentanteEmpresa page
     */
    public String navigateRepresentanteEmpresaList() {
        RepresentanteLegal selected = this.getSelected();
        if (selected != null) {
            RepresentanteLegalFacade ejbFacade = (RepresentanteLegalFacade) this.getFacade();
            List<RepresentanteEmpresa> selectedRepresentanteEmpresaList = ejbFacade.findRepresentanteEmpresaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RepresentanteEmpresa_items", selectedRepresentanteEmpresaList);
        }
        return "/app/representanteEmpresa/index";
    }

    public boolean getIsOperadorTransporteListEmpty() {
        return this.isOperadorTransporteListEmpty;
    }

    private void setIsOperadorTransporteListEmpty() {
        RepresentanteLegal selected = this.getSelected();
        if (selected != null) {
            RepresentanteLegalFacade ejbFacade = (RepresentanteLegalFacade) this.getFacade();
            this.isOperadorTransporteListEmpty = ejbFacade.isOperadorTransporteListEmpty(selected);
        } else {
            this.isOperadorTransporteListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of OperadorTransporte
     * entities that are retrieved from RepresentanteLegal and returns the
     * navigation outcome.
     *
     * @return navigation outcome for OperadorTransporte page
     */
    public String navigateOperadorTransporteList() {
        RepresentanteLegal selected = this.getSelected();
        if (selected != null) {
            RepresentanteLegalFacade ejbFacade = (RepresentanteLegalFacade) this.getFacade();
            List<OperadorTransporte> selectedOperadorTransporteList = ejbFacade.findOperadorTransporteList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("OperadorTransporte_items", selectedOperadorTransporteList);
        }
        return "/app/operadorTransporte/index";
    }

}
