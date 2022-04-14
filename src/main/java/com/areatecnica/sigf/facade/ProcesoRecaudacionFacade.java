/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.ProcesoRecaudacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CuentaBancoProceso;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class ProcesoRecaudacionFacade extends AbstractFacade<ProcesoRecaudacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoRecaudacionFacade() {
        super(ProcesoRecaudacion.class);
    }

    public boolean isCuentaBancoProcesoListEmpty(ProcesoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProcesoRecaudacion> procesoRecaudacion = cq.from(ProcesoRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(procesoRecaudacion, entity), cb.isNotEmpty(procesoRecaudacion.get(ProcesoRecaudacion_.cuentaBancoProcesoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CuentaBancoProceso> findCuentaBancoProcesoList(ProcesoRecaudacion entity) {
        ProcesoRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<CuentaBancoProceso> cuentaBancoProcesoList = mergedEntity.getCuentaBancoProcesoList();
        cuentaBancoProcesoList.size();
        return cuentaBancoProcesoList;
    }

    public boolean isBusListEmpty(ProcesoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProcesoRecaudacion> procesoRecaudacion = cq.from(ProcesoRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(procesoRecaudacion, entity), cb.isNotEmpty(procesoRecaudacion.get(ProcesoRecaudacion_.busList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Bus> findBusList(ProcesoRecaudacion entity) {
        ProcesoRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<Bus> busList = mergedEntity.getBusList();
        busList.size();
        return busList;
    }

    public boolean isEgresoProcesoRecaudacionListEmpty(ProcesoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProcesoRecaudacion> procesoRecaudacion = cq.from(ProcesoRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(procesoRecaudacion, entity), cb.isNotEmpty(procesoRecaudacion.get(ProcesoRecaudacion_.egresoProcesoRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<EgresoProcesoRecaudacion> findEgresoProcesoRecaudacionList(ProcesoRecaudacion entity) {
        ProcesoRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<EgresoProcesoRecaudacion> egresoProcesoRecaudacionList = mergedEntity.getEgresoProcesoRecaudacionList();
        egresoProcesoRecaudacionList.size();
        return egresoProcesoRecaudacionList;
    }

    public boolean isCajaProcesoListEmpty(ProcesoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProcesoRecaudacion> procesoRecaudacion = cq.from(ProcesoRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(procesoRecaudacion, entity), cb.isNotEmpty(procesoRecaudacion.get(ProcesoRecaudacion_.cajaProcesoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CajaProceso> findCajaProcesoList(ProcesoRecaudacion entity) {
        ProcesoRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<CajaProceso> cajaProcesoList = mergedEntity.getCajaProcesoList();
        cajaProcesoList.size();
        return cajaProcesoList;
    }

    public boolean isProcesoRecaudacionIdCuentaEmpty(ProcesoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProcesoRecaudacion> procesoRecaudacion = cq.from(ProcesoRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(procesoRecaudacion, entity), cb.isNotNull(procesoRecaudacion.get(ProcesoRecaudacion_.procesoRecaudacionIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findProcesoRecaudacionIdCuenta(ProcesoRecaudacion entity) {
        return this.getMergedEntity(entity).getProcesoRecaudacionIdCuenta();
    }
    
}
