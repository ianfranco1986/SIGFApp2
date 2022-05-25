package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.MonedaPactadaInstitucionSalud;
import com.areatecnica.sigf.entities.TrabajadorAdicionalSalud;
import com.areatecnica.sigf.facade.MonedaPactadaInstitucionSaludFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "monedaPactadaInstitucionSaludController")
@ViewScoped
public class MonedaPactadaInstitucionSaludController extends AbstractController<MonedaPactadaInstitucionSalud> {

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorAdicionalSaludListEmpty;

    public MonedaPactadaInstitucionSaludController() {
        // Inform the Abstract parent controller of the concrete MonedaPactadaInstitucionSalud Entity
        super(MonedaPactadaInstitucionSalud.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsTrabajadorAdicionalSaludListEmpty();
    }

    public boolean getIsTrabajadorAdicionalSaludListEmpty() {
        return this.isTrabajadorAdicionalSaludListEmpty;
    }

    private void setIsTrabajadorAdicionalSaludListEmpty() {
        MonedaPactadaInstitucionSalud selected = this.getSelected();
        if (selected != null) {
            MonedaPactadaInstitucionSaludFacade ejbFacade = (MonedaPactadaInstitucionSaludFacade) this.getFacade();
            this.isTrabajadorAdicionalSaludListEmpty = ejbFacade.isTrabajadorAdicionalSaludListEmpty(selected);
        } else {
            this.isTrabajadorAdicionalSaludListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TrabajadorAdicionalSalud
     * entities that are retrieved from MonedaPactadaInstitucionSalud and
     * returns the navigation outcome.
     *
     * @return navigation outcome for TrabajadorAdicionalSalud page
     */
    public String navigateTrabajadorAdicionalSaludList() {
        MonedaPactadaInstitucionSalud selected = this.getSelected();
        if (selected != null) {
            MonedaPactadaInstitucionSaludFacade ejbFacade = (MonedaPactadaInstitucionSaludFacade) this.getFacade();
            List<TrabajadorAdicionalSalud> selectedTrabajadorAdicionalSaludList = ejbFacade.findTrabajadorAdicionalSaludList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TrabajadorAdicionalSalud_items", selectedTrabajadorAdicionalSaludList);
        }
        return "/app/trabajadorAdicionalSalud/index";
    }

}
