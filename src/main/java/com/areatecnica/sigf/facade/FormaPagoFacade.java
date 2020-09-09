/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.FormaPago;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.FormaPago_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class FormaPagoFacade extends AbstractFacade<FormaPago> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormaPagoFacade() {
        super(FormaPago.class);
    }

    public boolean isTrabajadorListEmpty(FormaPago entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FormaPago> formaPago = cq.from(FormaPago.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(formaPago, entity), cb.isNotEmpty(formaPago.get(FormaPago_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(FormaPago entity) {
        FormaPago mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }
    
}
