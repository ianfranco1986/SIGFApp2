/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Bus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author ianfr
 */
@Stateless
public class BusFacade extends AbstractFacade<Bus> {

    private final EntityManager em = Persistence.createEntityManagerFactory("com.areatecnica_SIGFapp_war_1PU").createEntityManager();

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BusFacade() {
        super(Bus.class);
    }

}
