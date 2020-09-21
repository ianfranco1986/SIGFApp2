/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Compra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.entities.Proveedor;
import com.areatecnica.sigf.entities.TipoDocumento;

/**
 *
 * @author ianfr
 */
@Stateless
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }

    public CuentaMayor findCompraCuentaId(Compra entity) {
        return this.getMergedEntity(entity).getCompraCuentaMayorId();
    }

    public Proveedor findCompraProveedorId(Compra entity) {
        return this.getMergedEntity(entity).getCompraProveedorId();
    }

    public TipoDocumento findCompraTipoDocumentoId(Compra entity) {
        return this.getMergedEntity(entity).getCompraTipoDocumentoId();
    }

}
