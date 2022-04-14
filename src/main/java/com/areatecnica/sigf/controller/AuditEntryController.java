package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.AuditEntry;
import com.areatecnica.sigf.entities.AuditField;
import java.util.List;
import com.areatecnica.sigf.facade.AuditEntryFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "auditEntryController")
@ViewScoped
public class AuditEntryController extends AbstractController<AuditEntry> {

    // Flags to indicate if child collections are empty
    private boolean isAuditFieldListEmpty;

    public AuditEntryController() {
        // Inform the Abstract parent controller of the concrete AuditEntry Entity
        super(AuditEntry.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
//        this.setIsAuditFieldListEmpty();
    }

    public boolean getIsAuditFieldListEmpty() {
        return this.isAuditFieldListEmpty;
    }

//    private void setIsAuditFieldListEmpty() {
//        AuditEntry selected = this.getSelected();
//        if (selected != null) {
//            AuditEntryFacade ejbFacade = (AuditEntryFacade) this.getFacade();
//            this.isAuditFieldListEmpty = ejbFacade.isAuditFieldListEmpty(selected);
//        } else {
//            this.isAuditFieldListEmpty = true;
//        }
//    }

    /**
     * Sets the "items" attribute with a collection of AuditField entities that
     * are retrieved from AuditEntry and returns the navigation outcome.
     *
     * @return navigation outcome for AuditField page
     */
    public String navigateAuditFieldList() {
        AuditEntry selected = this.getSelected();
        if (selected != null) {
            AuditEntryFacade ejbFacade = (AuditEntryFacade) this.getFacade();
            List<AuditField> selectedAuditFieldList = ejbFacade.findAuditFieldList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AuditField_items", selectedAuditFieldList);
        }
        return "/app/auditField/index";
    }

}
