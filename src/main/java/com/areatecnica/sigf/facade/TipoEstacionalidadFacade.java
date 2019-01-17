/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoEstacionalidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoEstacionalidad_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.HorarioServicio;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoEstacionalidadFacade extends AbstractFacade<TipoEstacionalidad> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEstacionalidadFacade() {
        super(TipoEstacionalidad.class);
    }

    public boolean isHorarioServicioListEmpty(TipoEstacionalidad entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoEstacionalidad> tipoEstacionalidad = cq.from(TipoEstacionalidad.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoEstacionalidad, entity), cb.isNotEmpty(tipoEstacionalidad.get(TipoEstacionalidad_.horarioServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<HorarioServicio> findHorarioServicioList(TipoEstacionalidad entity) {
        TipoEstacionalidad mergedEntity = this.getMergedEntity(entity);
        List<HorarioServicio> horarioServicioList = mergedEntity.getHorarioServicioList();
        horarioServicioList.size();
        return horarioServicioList;
    }
    
}
