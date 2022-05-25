/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Ticket;
import com.areatecnica.sigf.entities.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ianfr
 */
@Stateless
public class TicketFacade extends AbstractFacade<Ticket> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketFacade() {
        super(Ticket.class);
    }

    public boolean isTicketIdUsuarioEmpty(Ticket entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ticket> ticket = cq.from(Ticket.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ticket, entity), cb.isNotNull(ticket.get(Ticket_.ticketIdUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findTicketIdUsuario(Ticket entity) {
        return this.getMergedEntity(entity).getTicketIdUsuario();
    }
    
}
