package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.ProgramacionTrabajador;
import com.areatecnica.sigf.entities.AdministradorFlota;
import com.areatecnica.sigf.entities.AdministradorBus;
import java.util.List;
import com.areatecnica.sigf.facade.AdministradorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "administradorController")
@ViewScoped
public class AdministradorController extends AbstractController<Administrador> {

    // Flags to indicate if child collections are empty
    private boolean isProgramacionTrabajadorListEmpty;
    private boolean isAdministradorFlotaListEmpty;
    private boolean isAdministradorBusListEmpty;

    public AdministradorController() {
        // Inform the Abstract parent controller of the concrete Administrador Entity
        super(Administrador.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        //this.setIsProgramacionTrabajadorListEmpty();
        this.setIsAdministradorFlotaListEmpty();
        this.setIsAdministradorBusListEmpty();
    }

    public boolean getIsProgramacionTrabajadorListEmpty() {
        return this.isProgramacionTrabajadorListEmpty;
    }

//    private void setIsProgramacionTrabajadorListEmpty() {
//        Administrador selected = this.getSelected();
//        if (selected != null) {
//            AdministradorFacade ejbFacade = (AdministradorFacade) this.getFacade();
//            this.isProgramacionTrabajadorListEmpty = ejbFacade.isProgramacionTrabajadorListEmpty(selected);
//        } else {
//            this.isProgramacionTrabajadorListEmpty = true;
//        }
//    }

    /**
     * Sets the "items" attribute with a collection of ProgramacionTrabajador
     * entities that are retrieved from Administrador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ProgramacionTrabajador page
     */
//    public String navigateProgramacionTrabajadorList() {
//        Administrador selected = this.getSelected();
//        if (selected != null) {
//            AdministradorFacade ejbFacade = (AdministradorFacade) this.getFacade();
//            List<ProgramacionTrabajador> selectedProgramacionTrabajadorList = ejbFacade.findProgramacionTrabajadorList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ProgramacionTrabajador_items", selectedProgramacionTrabajadorList);
//        }
//        return "/app/programacionTrabajador/index";
//    }

    public boolean getIsAdministradorFlotaListEmpty() {
        return this.isAdministradorFlotaListEmpty;
    }

    private void setIsAdministradorFlotaListEmpty() {
        Administrador selected = this.getSelected();
        if (selected != null) {
            AdministradorFacade ejbFacade = (AdministradorFacade) this.getFacade();
            this.isAdministradorFlotaListEmpty = ejbFacade.isAdministradorFlotaListEmpty(selected);
        } else {
            this.isAdministradorFlotaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of AdministradorFlota
     * entities that are retrieved from Administrador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for AdministradorFlota page
     */
    public String navigateAdministradorFlotaList() {
        Administrador selected = this.getSelected();
        if (selected != null) {
            AdministradorFacade ejbFacade = (AdministradorFacade) this.getFacade();
            List<AdministradorFlota> selectedAdministradorFlotaList = ejbFacade.findAdministradorFlotaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AdministradorFlota_items", selectedAdministradorFlotaList);
        }
        return "/app/administradorFlota/index";
    }

    public boolean getIsAdministradorBusListEmpty() {
        return this.isAdministradorBusListEmpty;
    }

    private void setIsAdministradorBusListEmpty() {
        Administrador selected = this.getSelected();
        if (selected != null) {
            AdministradorFacade ejbFacade = (AdministradorFacade) this.getFacade();
            this.isAdministradorBusListEmpty = ejbFacade.isAdministradorBusListEmpty(selected);
        } else {
            this.isAdministradorBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of AdministradorBus entities
     * that are retrieved from Administrador and returns the navigation outcome.
     *
     * @return navigation outcome for AdministradorBus page
     */
    public String navigateAdministradorBusList() {
        Administrador selected = this.getSelected();
        if (selected != null) {
            AdministradorFacade ejbFacade = (AdministradorFacade) this.getFacade();
            List<AdministradorBus> selectedAdministradorBusList = ejbFacade.findAdministradorBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AdministradorBus_items", selectedAdministradorBusList);
        }
        return "/app/administradorBus/index";
    }

}
