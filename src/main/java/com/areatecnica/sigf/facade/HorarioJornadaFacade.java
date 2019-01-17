/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.HorarioJornada;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.HorarioJornada_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.JornadaLaboral;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class HorarioJornadaFacade extends AbstractFacade<HorarioJornada> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorarioJornadaFacade() {
        super(HorarioJornada.class);
    }

    public boolean isHorarioJornadaIdCuentaEmpty(HorarioJornada entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HorarioJornada> horarioJornada = cq.from(HorarioJornada.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(horarioJornada, entity), cb.isNotNull(horarioJornada.get(HorarioJornada_.horarioJornadaIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findHorarioJornadaIdCuenta(HorarioJornada entity) {
        return this.getMergedEntity(entity).getHorarioJornadaIdCuenta();
    }

    public boolean isJornadaLaboralListEmpty(HorarioJornada entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HorarioJornada> horarioJornada = cq.from(HorarioJornada.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(horarioJornada, entity), cb.isNotEmpty(horarioJornada.get(HorarioJornada_.jornadaLaboralList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<JornadaLaboral> findJornadaLaboralList(HorarioJornada entity) {
        HorarioJornada mergedEntity = this.getMergedEntity(entity);
        List<JornadaLaboral> jornadaLaboralList = mergedEntity.getJornadaLaboralList();
        jornadaLaboralList.size();
        return jornadaLaboralList;
    }
    
}
