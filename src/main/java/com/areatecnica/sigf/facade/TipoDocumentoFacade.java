/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoDocumento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoDocumento_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Compra;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoDocumentoFacade extends AbstractFacade<TipoDocumento> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDocumentoFacade() {
        super(TipoDocumento.class);
    }

    public boolean isCompraListEmpty(TipoDocumento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDocumento> tipoDocumento = cq.from(TipoDocumento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDocumento, entity), cb.isNotEmpty(tipoDocumento.get(TipoDocumento_.compraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Compra> findCompraList(TipoDocumento entity) {
        TipoDocumento mergedEntity = this.getMergedEntity(entity);
        List<Compra> compraList = mergedEntity.getCompraList();
        compraList.size();
        return compraList;
    }
    
}
