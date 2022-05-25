/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.InstitucionSalud;
import com.areatecnica.sigf.entities.Trabajador;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class InstitucionSaludFacade extends AbstractFacade<InstitucionSalud> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstitucionSaludFacade() {
        super(InstitucionSalud.class);
    }

    public boolean isInstitucionSaludIdCuentaEmpty(InstitucionSalud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InstitucionSalud> institucionSalud = cq.from(InstitucionSalud.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(institucionSalud, entity), cb.isNotNull(institucionSalud.get(InstitucionSalud_.institucionSaludIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findInstitucionSaludIdCuenta(InstitucionSalud entity) {
        return this.getMergedEntity(entity).getInstitucionSaludIdCuenta();
    }

    public boolean isTrabajadorListEmpty(InstitucionSalud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InstitucionSalud> institucionSalud = cq.from(InstitucionSalud.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(institucionSalud, entity), cb.isNotEmpty(institucionSalud.get(InstitucionSalud_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(InstitucionSalud entity) {
        InstitucionSalud mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }
    
}
