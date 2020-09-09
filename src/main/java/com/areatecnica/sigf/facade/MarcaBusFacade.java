/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.MarcaBus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.MarcaBus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.ModeloMarcaBus;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class MarcaBusFacade extends AbstractFacade<MarcaBus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaBusFacade() {
        super(MarcaBus.class);
    }

    public boolean isModeloMarcaBusListEmpty(MarcaBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<MarcaBus> marcaBus = cq.from(MarcaBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(marcaBus, entity), cb.isNotEmpty(marcaBus.get(MarcaBus_.modeloMarcaBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ModeloMarcaBus> findModeloMarcaBusList(MarcaBus entity) {
        MarcaBus mergedEntity = this.getMergedEntity(entity);
        List<ModeloMarcaBus> modeloMarcaBusList = mergedEntity.getModeloMarcaBusList();
        modeloMarcaBusList.size();
        return modeloMarcaBusList;
    }
    
}
