/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CargaTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.CargaTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.ParentescoCarga;
import com.areatecnica.sigf.entities.TipoCargaFamiliar;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.CargaRetroactiva;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CargaTrabajadorFacade extends AbstractFacade<CargaTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargaTrabajadorFacade() {
        super(CargaTrabajador.class);
    }

    public boolean isCargaTrabajadorIdParentescoEmpty(CargaTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CargaTrabajador> cargaTrabajador = cq.from(CargaTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargaTrabajador, entity), cb.isNotNull(cargaTrabajador.get(CargaTrabajador_.cargaTrabajadorIdParentesco)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ParentescoCarga findCargaTrabajadorIdParentesco(CargaTrabajador entity) {
        return this.getMergedEntity(entity).getCargaTrabajadorIdParentesco();
    }

    public boolean isCargaTrabajadorIdTipoEmpty(CargaTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CargaTrabajador> cargaTrabajador = cq.from(CargaTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargaTrabajador, entity), cb.isNotNull(cargaTrabajador.get(CargaTrabajador_.cargaTrabajadorIdTipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoCargaFamiliar findCargaTrabajadorIdTipo(CargaTrabajador entity) {
        return this.getMergedEntity(entity).getCargaTrabajadorIdTipo();
    }

    public boolean isCargaTrabajadorIdTrabajadorEmpty(CargaTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CargaTrabajador> cargaTrabajador = cq.from(CargaTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargaTrabajador, entity), cb.isNotNull(cargaTrabajador.get(CargaTrabajador_.cargaTrabajadorIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findCargaTrabajadorIdTrabajador(CargaTrabajador entity) {
        return this.getMergedEntity(entity).getCargaTrabajadorIdTrabajador();
    }

    public boolean isCargaRetroactivaListEmpty(CargaTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CargaTrabajador> cargaTrabajador = cq.from(CargaTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargaTrabajador, entity), cb.isNotEmpty(cargaTrabajador.get(CargaTrabajador_.cargaRetroactivaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CargaRetroactiva> findCargaRetroactivaList(CargaTrabajador entity) {
        CargaTrabajador mergedEntity = this.getMergedEntity(entity);
        List<CargaRetroactiva> cargaRetroactivaList = mergedEntity.getCargaRetroactivaList();
        cargaRetroactivaList.size();
        return cargaRetroactivaList;
    }
    
}
