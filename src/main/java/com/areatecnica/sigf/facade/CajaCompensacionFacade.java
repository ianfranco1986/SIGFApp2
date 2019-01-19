/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CajaCompensacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.CajaCompensacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CajaCompensacionFacade extends AbstractFacade<CajaCompensacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajaCompensacionFacade() {
        super(CajaCompensacion.class);
    }

    public boolean isEmpresaListEmpty(CajaCompensacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaCompensacion> cajaCompensacion = cq.from(CajaCompensacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaCompensacion, entity), cb.isNotEmpty(cajaCompensacion.get(CajaCompensacion_.empresaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Empresa> findEmpresaList(CajaCompensacion entity) {
        CajaCompensacion mergedEntity = this.getMergedEntity(entity);
        List<Empresa> empresaList = mergedEntity.getEmpresaList();
        empresaList.size();
        return empresaList;
    }

    public boolean isCajaCompensacionIdCuentaEmpty(CajaCompensacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CajaCompensacion> cajaCompensacion = cq.from(CajaCompensacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cajaCompensacion, entity), cb.isNotNull(cajaCompensacion.get(CajaCompensacion_.cajaCompensacionIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findCajaCompensacionIdCuenta(CajaCompensacion entity) {
        return this.getMergedEntity(entity).getCajaCompensacionIdCuenta();
    }
    
}
