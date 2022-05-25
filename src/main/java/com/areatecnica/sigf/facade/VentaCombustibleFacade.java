/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.Surtidor;
import com.areatecnica.sigf.entities.VentaCombustible;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class VentaCombustibleFacade extends AbstractFacade<VentaCombustible> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaCombustibleFacade() {
        super(VentaCombustible.class);
    }

   

    public List<RecaudacionCombustible> findRecaudacionCombustibleList(VentaCombustible entity) {
        VentaCombustible mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionCombustible> recaudacionCombustibleList = mergedEntity.getRecaudacionCombustibleList();
        recaudacionCombustibleList.size();
        return recaudacionCombustibleList;
    }


    public Bus findVentaCombustibleIdBus(VentaCombustible entity) {
        return this.getMergedEntity(entity).getVentaCombustibleIdBus();
    }

    public Surtidor findVentaCombustibleIdSurtidor(VentaCombustible entity) {
        return this.getMergedEntity(entity).getVentaCombustibleIdSurtidor();
    }
    
}
