/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ModeloMarcaBus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.ModeloMarcaBus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.MarcaBus;
import com.areatecnica.sigf.entities.Bus;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class ModeloMarcaBusFacade extends AbstractFacade<ModeloMarcaBus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModeloMarcaBusFacade() {
        super(ModeloMarcaBus.class);
    }

    public boolean isModeloMarcaBusIdMarcaEmpty(ModeloMarcaBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ModeloMarcaBus> modeloMarcaBus = cq.from(ModeloMarcaBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(modeloMarcaBus, entity), cb.isNotNull(modeloMarcaBus.get(ModeloMarcaBus_.modeloMarcaBusIdMarca)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public MarcaBus findModeloMarcaBusIdMarca(ModeloMarcaBus entity) {
        return this.getMergedEntity(entity).getModeloMarcaBusIdMarca();
    }

    public boolean isBusListEmpty(ModeloMarcaBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ModeloMarcaBus> modeloMarcaBus = cq.from(ModeloMarcaBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(modeloMarcaBus, entity), cb.isNotEmpty(modeloMarcaBus.get(ModeloMarcaBus_.busList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Bus> findBusList(ModeloMarcaBus entity) {
        ModeloMarcaBus mergedEntity = this.getMergedEntity(entity);
        List<Bus> busList = mergedEntity.getBusList();
        busList.size();
        return busList;
    }
    
}
