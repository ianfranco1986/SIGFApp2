/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Comuna;
import com.areatecnica.sigf.entities.Factura;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }


    public Comuna findClienteComunaId(Cliente entity) {
        return this.getMergedEntity(entity).getClienteComunaId();
    }

    public List<Factura> findFacturaList(Cliente entity) {
        Cliente mergedEntity = this.getMergedEntity(entity);
        List<Factura> facturaList = mergedEntity.getFacturaList();
        facturaList.size();
        return facturaList;
    }
    
}
