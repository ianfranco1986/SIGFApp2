/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Guia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Guia_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.RegistroBoleto;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class GuiaFacade extends AbstractFacade<Guia> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GuiaFacade() {
        super(Guia.class);
    }

    public boolean isRecaudacionGuiaListEmpty(Guia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Guia> guia = cq.from(Guia.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(guia, entity), cb.isNotEmpty(guia.get(Guia_.recaudacionGuiaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionGuia> findRecaudacionGuiaList(Guia entity) {
        Guia mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionGuia> recaudacionGuiaList = mergedEntity.getRecaudacionGuiaList();
        recaudacionGuiaList.size();
        return recaudacionGuiaList;
    }

    public boolean isGuiaIdBusEmpty(Guia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Guia> guia = cq.from(Guia.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(guia, entity), cb.isNotNull(guia.get(Guia_.guiaIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findGuiaIdBus(Guia entity) {
        return this.getMergedEntity(entity).getGuiaIdBus();
    }

    public boolean isGuiaIdTrabajadorEmpty(Guia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Guia> guia = cq.from(Guia.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(guia, entity), cb.isNotNull(guia.get(Guia_.guiaIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findGuiaIdTrabajador(Guia entity) {
        return this.getMergedEntity(entity).getGuiaIdTrabajador();
    }

    
    
}
