/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Flota;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.EgresoFlota;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class FlotaFacade extends AbstractFacade<Flota> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlotaFacade() {
        super(Flota.class);
    }

    public boolean isFlotaIdCuentaEmpty(Flota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Flota> flota = cq.from(Flota.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(flota, entity), cb.isNotNull(flota.get(Flota_.flotaIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findFlotaIdCuenta(Flota entity) {
        return this.getMergedEntity(entity).getFlotaIdCuenta();
    }

    public boolean isBusListEmpty(Flota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Flota> flota = cq.from(Flota.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(flota, entity), cb.isNotEmpty(flota.get(Flota_.busList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Bus> findBusList(Flota entity) {
        Flota mergedEntity = this.getMergedEntity(entity);
        List<Bus> busList = mergedEntity.getBusList();
        busList.size();
        return busList;
    }

    public boolean isEgresoFlotaListEmpty(Flota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Flota> flota = cq.from(Flota.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(flota, entity), cb.isNotEmpty(flota.get(Flota_.egresoFlotaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<EgresoFlota> findEgresoFlotaList(Flota entity) {
        Flota mergedEntity = this.getMergedEntity(entity);
        List<EgresoFlota> egresoFlotaList = mergedEntity.getEgresoFlotaList();
        egresoFlotaList.size();
        return egresoFlotaList;
    }
    
}
