/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoAbono;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoAbono_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoAbonoFacade extends AbstractFacade<TipoAbono> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoAbonoFacade() {
        super(TipoAbono.class);
    }

    public boolean isAbonoBusListEmpty(TipoAbono entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoAbono> tipoAbono = cq.from(TipoAbono.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoAbono, entity), cb.isNotEmpty(tipoAbono.get(TipoAbono_.abonoBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<AbonoBus> findAbonoBusList(TipoAbono entity) {
        TipoAbono mergedEntity = this.getMergedEntity(entity);
        List<AbonoBus> abonoBusList = mergedEntity.getAbonoBusList();
        abonoBusList.size();
        return abonoBusList;
    }

    public boolean isTipoAbonoIdCuentaEmpty(TipoAbono entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoAbono> tipoAbono = cq.from(TipoAbono.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoAbono, entity), cb.isNotNull(tipoAbono.get(TipoAbono_.tipoAbonoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTipoAbonoIdCuenta(TipoAbono entity) {
        return this.getMergedEntity(entity).getTipoAbonoIdCuenta();
    }
    
}
