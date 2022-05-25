/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.TarifaGrupoServicio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ianfr
 */
@Stateless
public class TarifaGrupoServicioFacade extends AbstractFacade<TarifaGrupoServicio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarifaGrupoServicioFacade() {
        super(TarifaGrupoServicio.class);
    }

    public boolean isTarifaGrupoServicioIdBoletoEmpty(TarifaGrupoServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TarifaGrupoServicio> tarifaGrupoServicio = cq.from(TarifaGrupoServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tarifaGrupoServicio, entity), cb.isNotNull(tarifaGrupoServicio.get(TarifaGrupoServicio_.tarifaGrupoServicioIdBoleto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Boleto findTarifaGrupoServicioIdBoleto(TarifaGrupoServicio entity) {
        return this.getMergedEntity(entity).getTarifaGrupoServicioIdBoleto();
    }

    public boolean isTarifaGrupoServicioIdGrupoEmpty(TarifaGrupoServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TarifaGrupoServicio> tarifaGrupoServicio = cq.from(TarifaGrupoServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tarifaGrupoServicio, entity), cb.isNotNull(tarifaGrupoServicio.get(TarifaGrupoServicio_.tarifaGrupoServicioIdGrupo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public GrupoServicio findTarifaGrupoServicioIdGrupo(TarifaGrupoServicio entity) {
        return this.getMergedEntity(entity).getTarifaGrupoServicioIdGrupo();
    }
    
}
