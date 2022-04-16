/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ValorMinuto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.ValorMinuto_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import javax.persistence.Persistence;

/**
 *
 * @author ianfr
 */
@Stateless
public class ValorMinutoFacade extends AbstractFacade<ValorMinuto> {

    
    private EntityManager em = Persistence.createEntityManagerFactory( "com.areatecnica_SIGFapp_war_1PU").createEntityManager();

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValorMinutoFacade() {
        super(ValorMinuto.class);
    }

    public boolean isValorMinutoIdCuentaEmpty(ValorMinuto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ValorMinuto> valorMinuto = cq.from(ValorMinuto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(valorMinuto, entity), cb.isNotNull(valorMinuto.get(ValorMinuto_.valorMinutoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findValorMinutoIdCuenta(ValorMinuto entity) {
        return this.getMergedEntity(entity).getValorMinutoIdCuenta();
    }
    
}
