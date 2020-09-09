/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Egreso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Egreso_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.EgresoBus;
import com.areatecnica.sigf.entities.EgresoFlota;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class EgresoFacade extends AbstractFacade<Egreso> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EgresoFacade() {
        super(Egreso.class);
    }

    public boolean isEgresoIdCuentaEmpty(Egreso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Egreso> egreso = cq.from(Egreso.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egreso, entity), cb.isNotNull(egreso.get(Egreso_.egresoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findEgresoIdCuenta(Egreso entity) {
        return this.getMergedEntity(entity).getEgresoIdCuenta();
    }

    public boolean isEgresoCajaRecaudacionListEmpty(Egreso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Egreso> egreso = cq.from(Egreso.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egreso, entity), cb.isNotEmpty(egreso.get(Egreso_.egresoCajaRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<EgresoCajaRecaudacion> findEgresoCajaRecaudacionList(Egreso entity) {
        Egreso mergedEntity = this.getMergedEntity(entity);
        List<EgresoCajaRecaudacion> egresoCajaRecaudacionList = mergedEntity.getEgresoCajaRecaudacionList();
        egresoCajaRecaudacionList.size();
        return egresoCajaRecaudacionList;
    }

    public boolean isEgresoProcesoRecaudacionListEmpty(Egreso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Egreso> egreso = cq.from(Egreso.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egreso, entity), cb.isNotEmpty(egreso.get(Egreso_.egresoProcesoRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<EgresoProcesoRecaudacion> findEgresoProcesoRecaudacionList(Egreso entity) {
        Egreso mergedEntity = this.getMergedEntity(entity);
        List<EgresoProcesoRecaudacion> egresoProcesoRecaudacionList = mergedEntity.getEgresoProcesoRecaudacionList();
        egresoProcesoRecaudacionList.size();
        return egresoProcesoRecaudacionList;
    }

    public boolean isRecaudacionGuiaListEmpty(Egreso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Egreso> egreso = cq.from(Egreso.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egreso, entity), cb.isNotEmpty(egreso.get(Egreso_.recaudacionGuiaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionGuia> findRecaudacionGuiaList(Egreso entity) {
        Egreso mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionGuia> recaudacionGuiaList = mergedEntity.getRecaudacionGuiaList();
        recaudacionGuiaList.size();
        return recaudacionGuiaList;
    }

    public boolean isEgresoBusListEmpty(Egreso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Egreso> egreso = cq.from(Egreso.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egreso, entity), cb.isNotEmpty(egreso.get(Egreso_.egresoBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<EgresoBus> findEgresoBusList(Egreso entity) {
        Egreso mergedEntity = this.getMergedEntity(entity);
        List<EgresoBus> egresoBusList = mergedEntity.getEgresoBusList();
        egresoBusList.size();
        return egresoBusList;
    }

    public boolean isEgresoFlotaListEmpty(Egreso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Egreso> egreso = cq.from(Egreso.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egreso, entity), cb.isNotEmpty(egreso.get(Egreso_.egresoFlotaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<EgresoFlota> findEgresoFlotaList(Egreso entity) {
        Egreso mergedEntity = this.getMergedEntity(entity);
        List<EgresoFlota> egresoFlotaList = mergedEntity.getEgresoFlotaList();
        egresoFlotaList.size();
        return egresoFlotaList;
    }
    
}
