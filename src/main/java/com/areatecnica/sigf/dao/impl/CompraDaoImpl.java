/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICompraDao;
import com.areatecnica.sigf.entities.Compra;
import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.entities.Proveedor;
import com.areatecnica.sigf.entities.TipoDocumento;

import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author ianfr
 */
public class CompraDaoImpl extends GenericDAOImpl<Compra> implements ICompraDao<Compra> {

    public CompraDaoImpl() {
        super(Compra.class);
    }

    @Override
    public List<Compra> findCompraBetweenDates(Date from, Date to) {
        return this.entityManager.createNamedQuery("Compra.findByCompraBetweenDates").setParameter("from", from).setParameter("to", to).getResultList();
    }

    public long countByCuentaMayor(CuentaMayor c) {
        Query query = this.entityManager.createQuery("SELECT COUNT(c) FROM Compra c WHERE c.compraCuentaMayorId = :compraCuentaMayorId").setParameter("compraCuentaMayorId", c);

        return (Long) query.getSingleResult();
    }

    @Override
    public Compra findByFolioProveedorTipo(int folio, Proveedor proveedor, TipoDocumento tipo) {
        try {
            return (Compra) this.entityManager.createNamedQuery("Compra.findByFolioProveedorTipo")
                    .setParameter("compraFolio", folio)
                    .setParameter("compraProveedorId", proveedor)
                    .setParameter("compraTipoDocumentoId", tipo).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
