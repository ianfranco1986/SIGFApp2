/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoCargo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.TipoCargo_;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoCargoFacade extends AbstractFacade<TipoCargo> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCargoFacade() {
        super(TipoCargo.class);
    }

}
