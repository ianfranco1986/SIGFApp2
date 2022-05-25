/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.MonedaPactadaInstitucionSalud;
import com.areatecnica.sigf.entities.TrabajadorAdicionalSalud;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class MonedaPactadaInstitucionSaludFacade extends AbstractFacade<MonedaPactadaInstitucionSalud> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MonedaPactadaInstitucionSaludFacade() {
        super(MonedaPactadaInstitucionSalud.class);
    }

    public boolean isTrabajadorAdicionalSaludListEmpty(MonedaPactadaInstitucionSalud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<MonedaPactadaInstitucionSalud> monedaPactadaInstitucionSalud = cq.from(MonedaPactadaInstitucionSalud.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(monedaPactadaInstitucionSalud, entity), cb.isNotEmpty(monedaPactadaInstitucionSalud.get(MonedaPactadaInstitucionSalud_.trabajadorAdicionalSaludList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TrabajadorAdicionalSalud> findTrabajadorAdicionalSaludList(MonedaPactadaInstitucionSalud entity) {
        MonedaPactadaInstitucionSalud mergedEntity = this.getMergedEntity(entity);
        List<TrabajadorAdicionalSalud> trabajadorAdicionalSaludList = mergedEntity.getTrabajadorAdicionalSaludList();
        trabajadorAdicionalSaludList.size();
        return trabajadorAdicionalSaludList;
    }
    
}
