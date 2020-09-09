/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.FeriadoLegal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.FeriadoLegal_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class FeriadoLegalFacade extends AbstractFacade<FeriadoLegal> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeriadoLegalFacade() {
        super(FeriadoLegal.class);
    }

    public boolean isFeriadoLegalIdTrabajadorEmpty(FeriadoLegal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FeriadoLegal> feriadoLegal = cq.from(FeriadoLegal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(feriadoLegal, entity), cb.isNotNull(feriadoLegal.get(FeriadoLegal_.feriadoLegalIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findFeriadoLegalIdTrabajador(FeriadoLegal entity) {
        return this.getMergedEntity(entity).getFeriadoLegalIdTrabajador();
    }
    
}
