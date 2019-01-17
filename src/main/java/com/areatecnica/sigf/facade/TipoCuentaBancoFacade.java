/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoCuentaBanco;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoCuentaBanco_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CuentaBancaria;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoCuentaBancoFacade extends AbstractFacade<TipoCuentaBanco> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCuentaBancoFacade() {
        super(TipoCuentaBanco.class);
    }

    public boolean isCuentaBancariaListEmpty(TipoCuentaBanco entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCuentaBanco> tipoCuentaBanco = cq.from(TipoCuentaBanco.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCuentaBanco, entity), cb.isNotEmpty(tipoCuentaBanco.get(TipoCuentaBanco_.cuentaBancariaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CuentaBancaria> findCuentaBancariaList(TipoCuentaBanco entity) {
        TipoCuentaBanco mergedEntity = this.getMergedEntity(entity);
        List<CuentaBancaria> cuentaBancariaList = mergedEntity.getCuentaBancariaList();
        cuentaBancariaList.size();
        return cuentaBancariaList;
    }
    
}
