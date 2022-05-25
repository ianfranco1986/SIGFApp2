/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.InstitucionApv;
import com.areatecnica.sigf.entities.TipoInstitucionApv;

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
public class TipoInstitucionApvFacade extends AbstractFacade<TipoInstitucionApv> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoInstitucionApvFacade() {
        super(TipoInstitucionApv.class);
    }

    public boolean isInstitucionApvListEmpty(TipoInstitucionApv entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoInstitucionApv> tipoInstitucionApv = cq.from(TipoInstitucionApv.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoInstitucionApv, entity), cb.isNotEmpty(tipoInstitucionApv.get(TipoInstitucionApv_.institucionApvList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<InstitucionApv> findInstitucionApvList(TipoInstitucionApv entity) {
        TipoInstitucionApv mergedEntity = this.getMergedEntity(entity);
        List<InstitucionApv> institucionApvList = mergedEntity.getInstitucionApvList();
        institucionApvList.size();
        return institucionApvList;
    }
    
}
