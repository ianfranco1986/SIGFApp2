package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.UsuarioSession;
import com.areatecnica.sigf.entities.Despacho;
import com.areatecnica.sigf.entities.Log;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import java.util.List;
import com.areatecnica.sigf.facade.UsuarioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private CuentaController usuarioIdCuentaController;
    @Inject
    private RolController usuarioIdRolController;
    @Inject
    private TerminalController usuarioIdTerminalController;

    // Flags to indicate if child collections are empty
    private boolean isUsuarioSessionListEmpty;
    private boolean isDespachoListEmpty;
    private boolean isLogListEmpty;
    private boolean isCajaRecaudacionListEmpty;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuarioIdCuentaController.setSelected(null);
        usuarioIdRolController.setSelected(null);
        usuarioIdTerminalController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsUsuarioSessionListEmpty();
        this.setIsDespachoListEmpty();
        this.setIsLogListEmpty();
        this.setIsCajaRecaudacionListEmpty();
    }

    public boolean getIsUsuarioSessionListEmpty() {
        return this.isUsuarioSessionListEmpty;
    }

    private void setIsUsuarioSessionListEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isUsuarioSessionListEmpty = ejbFacade.isUsuarioSessionListEmpty(selected);
        } else {
            this.isUsuarioSessionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of UsuarioSession entities
     * that are retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for UsuarioSession page
     */
    public String navigateUsuarioSessionList() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            List<UsuarioSession> selectedUsuarioSessionList = ejbFacade.findUsuarioSessionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("UsuarioSession_items", selectedUsuarioSessionList);
        }
        return "/app/usuarioSession/index";
    }

    public boolean getIsDespachoListEmpty() {
        return this.isDespachoListEmpty;
    }

    private void setIsDespachoListEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isDespachoListEmpty = ejbFacade.isDespachoListEmpty(selected);
        } else {
            this.isDespachoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Despacho entities that
     * are retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Despacho page
     */
    public String navigateDespachoList() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            List<Despacho> selectedDespachoList = ejbFacade.findDespachoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Despacho_items", selectedDespachoList);
        }
        return "/app/despacho/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioIdCuenta(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && usuarioIdCuentaController.getSelected() == null) {
            usuarioIdCuentaController.setSelected(selected.getUsuarioIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioIdRol(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && usuarioIdRolController.getSelected() == null) {
            usuarioIdRolController.setSelected(selected.getUsuarioIdRol());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioIdTerminal(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && usuarioIdTerminalController.getSelected() == null) {
            usuarioIdTerminalController.setSelected(selected.getUsuarioIdTerminal());
        }
    }

    public boolean getIsLogListEmpty() {
        return this.isLogListEmpty;
    }

    private void setIsLogListEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isLogListEmpty = ejbFacade.isLogListEmpty(selected);
        } else {
            this.isLogListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Log entities that are
     * retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Log page
     */
    public String navigateLogList() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            List<Log> selectedLogList = ejbFacade.findLogList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Log_items", selectedLogList);
        }
        return "/app/log/index";
    }

    public boolean getIsCajaRecaudacionListEmpty() {
        return this.isCajaRecaudacionListEmpty;
    }

    private void setIsCajaRecaudacionListEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isCajaRecaudacionListEmpty = ejbFacade.isCajaRecaudacionListEmpty(selected);
        } else {
            this.isCajaRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CajaRecaudacion entities
     * that are retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for CajaRecaudacion page
     */
    public String navigateCajaRecaudacionList() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            List<CajaRecaudacion> selectedCajaRecaudacionList = ejbFacade.findCajaRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaRecaudacion_items", selectedCajaRecaudacionList);
        }
        return "/app/cajaRecaudacion/index";
    }

}
