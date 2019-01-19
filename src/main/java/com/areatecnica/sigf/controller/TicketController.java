package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.ITicketDaoImpl;
import com.areatecnica.sigf.entities.Ticket;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "ticketController")
@ViewScoped
public class TicketController extends AbstractController<Ticket> {

    private List<Ticket> items;
    
    @Inject
    private UsuarioController ticketIdUsuarioController;

    public TicketController() {
        // Inform the Abstract parent controller of the concrete Ticket Entity
        super(Ticket.class);
        this.items = new ITicketDaoImpl().findAllByUser(this.getCurrentUser());
    }

    public List<Ticket> getItems() {
        return items;
    }

    public void setItems(List<Ticket> items) {
        this.items = items;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ticketIdUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTicketIdUsuario(ActionEvent event) {
        Ticket selected = this.getSelected();
        if (selected != null && ticketIdUsuarioController.getSelected() == null) {
            ticketIdUsuarioController.setSelected(selected.getTicketIdUsuario());
        }
    }

    @Override
    public Ticket prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTicketIdUsuario(this.getCurrentUser());
        this.getSelected().setTicketEstado(new Short("0"));
        return this.getSelected();
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
    }

}
