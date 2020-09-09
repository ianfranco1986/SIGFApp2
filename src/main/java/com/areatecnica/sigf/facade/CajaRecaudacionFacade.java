/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CajaRecaudacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.CajaRecaudacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.ResumenRecaudacion;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.InventarioCaja;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CajaRecaudacionFacade extends AbstractFacade<CajaRecaudacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajaRecaudacionFacade() {
        super(CajaRecaudacion.class);
    }

    public boolean isEgresoCajaRecaudacionListEmpty(CajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaRecaudacion> cajaRecaudacion = cq.from(CajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaRecaudacion, entity), cb.isNotEmpty(cajaRecaudacion.get(CajaRecaudacion_.egresoCajaRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<EgresoCajaRecaudacion> findEgresoCajaRecaudacionList(CajaRecaudacion entity) {
        CajaRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<EgresoCajaRecaudacion> egresoCajaRecaudacionList = mergedEntity.getEgresoCajaRecaudacionList();
        egresoCajaRecaudacionList.size();
        return egresoCajaRecaudacionList;
    }

    public boolean isRecaudacionListEmpty(CajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaRecaudacion> cajaRecaudacion = cq.from(CajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaRecaudacion, entity), cb.isNotEmpty(cajaRecaudacion.get(CajaRecaudacion_.recaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Recaudacion> findRecaudacionList(CajaRecaudacion entity) {
        CajaRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<Recaudacion> recaudacionList = mergedEntity.getRecaudacionList();
        recaudacionList.size();
        return recaudacionList;
    }

    public boolean isCajaProcesoListEmpty(CajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaRecaudacion> cajaRecaudacion = cq.from(CajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaRecaudacion, entity), cb.isNotEmpty(cajaRecaudacion.get(CajaRecaudacion_.cajaProcesoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CajaProceso> findCajaProcesoList(CajaRecaudacion entity) {
        CajaRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<CajaProceso> cajaProcesoList = mergedEntity.getCajaProcesoList();
        cajaProcesoList.size();
        return cajaProcesoList;
    }

    public boolean isResumenRecaudacionListEmpty(CajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaRecaudacion> cajaRecaudacion = cq.from(CajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaRecaudacion, entity), cb.isNotEmpty(cajaRecaudacion.get(CajaRecaudacion_.resumenRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ResumenRecaudacion> findResumenRecaudacionList(CajaRecaudacion entity) {
        CajaRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<ResumenRecaudacion> resumenRecaudacionList = mergedEntity.getResumenRecaudacionList();
        resumenRecaudacionList.size();
        return resumenRecaudacionList;
    }

    public boolean isCajaRecaudacionIdCuentaEmpty(CajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaRecaudacion> cajaRecaudacion = cq.from(CajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaRecaudacion, entity), cb.isNotNull(cajaRecaudacion.get(CajaRecaudacion_.cajaRecaudacionIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findCajaRecaudacionIdCuenta(CajaRecaudacion entity) {
        return this.getMergedEntity(entity).getCajaRecaudacionIdCuenta();
    }

    public boolean isCajaRecaudacionIdTerminalEmpty(CajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaRecaudacion> cajaRecaudacion = cq.from(CajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaRecaudacion, entity), cb.isNotNull(cajaRecaudacion.get(CajaRecaudacion_.cajaRecaudacionIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findCajaRecaudacionIdTerminal(CajaRecaudacion entity) {
        return this.getMergedEntity(entity).getCajaRecaudacionIdTerminal();
    }

    public boolean isCajaRecaudacionIdUsuarioEmpty(CajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaRecaudacion> cajaRecaudacion = cq.from(CajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaRecaudacion, entity), cb.isNotNull(cajaRecaudacion.get(CajaRecaudacion_.cajaRecaudacionIdUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findCajaRecaudacionIdUsuario(CajaRecaudacion entity) {
        return this.getMergedEntity(entity).getCajaRecaudacionIdUsuario();
    }

    public boolean isInventarioCajaListEmpty(CajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaRecaudacion> cajaRecaudacion = cq.from(CajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaRecaudacion, entity), cb.isNotEmpty(cajaRecaudacion.get(CajaRecaudacion_.inventarioCajaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<InventarioCaja> findInventarioCajaList(CajaRecaudacion entity) {
        CajaRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<InventarioCaja> inventarioCajaList = mergedEntity.getInventarioCajaList();
        inventarioCajaList.size();
        return inventarioCajaList;
    }
    
}
