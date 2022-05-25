package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.ITicketDaoImpl;
import com.areatecnica.sigf.entities.Ticket;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "ticketController")
@ViewScoped
public class TicketController extends AbstractController<Ticket> {

    private List<Ticket> items;
      

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
