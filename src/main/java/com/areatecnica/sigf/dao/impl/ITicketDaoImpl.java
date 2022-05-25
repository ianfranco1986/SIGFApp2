/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITicketDao;
import com.areatecnica.sigf.entities.Ticket;
import com.areatecnica.sigf.entities.Usuario;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class ITicketDaoImpl extends GenericDAOImpl<Ticket> implements ITicketDao<Ticket> {

    public ITicketDaoImpl() {
        super(Ticket.class);
    }

    @Override
    public List<Ticket> findAllByUser(Usuario usuario) {
        try {
            return this.entityManager.createNamedQuery("Ticket.findAllByUser").setParameter("ticketIdUsuario", usuario).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
