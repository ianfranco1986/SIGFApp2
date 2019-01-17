/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoControl;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoControl_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Control;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoControlFacade extends AbstractFacade<TipoControl> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoControlFacade() {
        super(TipoControl.class);
    }

    public boolean isTipoControlIdCuentaEmpty(TipoControl entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoControl> tipoControl = cq.from(TipoControl.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoControl, entity), cb.isNotNull(tipoControl.get(TipoControl_.tipoControlIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTipoControlIdCuenta(TipoControl entity) {
        return this.getMergedEntity(entity).getTipoControlIdCuenta();
    }

    public boolean isControlListEmpty(TipoControl entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoControl> tipoControl = cq.from(TipoControl.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoControl, entity), cb.isNotEmpty(tipoControl.get(TipoControl_.controlList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Control> findControlList(TipoControl entity) {
        TipoControl mergedEntity = this.getMergedEntity(entity);
        List<Control> controlList = mergedEntity.getControlList();
        controlList.size();
        return controlList;
    }
    
}
