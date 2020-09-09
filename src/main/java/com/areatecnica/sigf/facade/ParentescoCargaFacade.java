/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ParentescoCarga;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.ParentescoCarga_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CargaTrabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class ParentescoCargaFacade extends AbstractFacade<ParentescoCarga> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParentescoCargaFacade() {
        super(ParentescoCarga.class);
    }

    public boolean isCargaTrabajadorListEmpty(ParentescoCarga entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ParentescoCarga> parentescoCarga = cq.from(ParentescoCarga.class);
//        cq.select(cb.literal(1L)).distinct(true).where(c"22cSb.equal(parentescoCarga, entity), cb.isNotEmpty(parentescoCarga.get(ParentescoCarga_.cargaTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CargaTrabajador> findCargaTrabajadorList(ParentescoCarga entity) {
        ParentescoCarga mergedEntity = this.getMergedEntity(entity);
        List<CargaTrabajador> cargaTrabajadorList = mergedEntity.getCargaTrabajadorList();
        cargaTrabajadorList.size();
        return cargaTrabajadorList;
    }
    
}
