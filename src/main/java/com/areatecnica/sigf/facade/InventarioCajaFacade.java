/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.InventarioCaja;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.InventarioCaja_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.InventarioInterno;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class InventarioCajaFacade extends AbstractFacade<InventarioCaja> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioCajaFacade() {
        super(InventarioCaja.class);
    }
    
}
