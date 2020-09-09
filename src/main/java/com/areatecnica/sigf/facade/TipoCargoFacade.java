/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoCargo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.TipoCargo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoCargoFacade extends AbstractFacade<TipoCargo> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCargoFacade() {
        super(TipoCargo.class);
    }

    public boolean isCargoBusListEmpty(TipoCargo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCargo> tipoCargo = cq.from(TipoCargo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCargo, entity), cb.isNotEmpty(tipoCargo.get(TipoCargo_.cargoBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CargoBus> findCargoBusList(TipoCargo entity) {
        TipoCargo mergedEntity = this.getMergedEntity(entity);
        List<CargoBus> cargoBusList = mergedEntity.getCargoBusList();
        cargoBusList.size();
        return cargoBusList;
    }

    public boolean isTipoCargoIdCuentaEmpty(TipoCargo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCargo> tipoCargo = cq.from(TipoCargo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCargo, entity), cb.isNotNull(tipoCargo.get(TipoCargo_.tipoCargoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTipoCargoIdCuenta(TipoCargo entity) {
        return this.getMergedEntity(entity).getTipoCargoIdCuenta();
    }
    
}
