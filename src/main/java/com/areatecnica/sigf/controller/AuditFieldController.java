package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.AuditField;
import com.areatecnica.sigf.facade.AuditFieldFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "auditFieldController")
@ViewScoped
public class AuditFieldController extends AbstractController<AuditField> {

    @Inject
    private AuditEntryController auditEntryIdController;

    public AuditFieldController() {
        // Inform the Abstract parent controller of the concrete AuditField Entity
        super(AuditField.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        auditEntryIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the AuditEntry controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAuditEntryId(ActionEvent event) {
        AuditField selected = this.getSelected();
        if (selected != null && auditEntryIdController.getSelected() == null) {
            auditEntryIdController.setSelected(selected.getAuditEntryId());
        }
    }

}
