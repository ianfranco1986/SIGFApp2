/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITipoDocumentoDao;
import com.areatecnica.sigf.entities.TipoDocumento;
import com.areatecnica.sigf.entities.TipoMovimiento;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class TipoDocumentoDaoImpl extends GenericDAOImpl<TipoDocumento> implements ITipoDocumentoDao<TipoDocumento> {

    public TipoDocumentoDaoImpl() {
        super(TipoDocumento.class);
    }

    @Override
    public TipoDocumento findById(int tipoAbonoId) {
        try {
            return (TipoDocumento) this.entityManager.createNamedQuery("TipoDocumento.findByTipoDocumentoId").setParameter("tipoDocumentoId", tipoAbonoId).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public List<TipoMovimiento> findALL() {
        try {
            return this.entityManager.createNamedQuery("TipoMovimiento.findAll").
                    getResultList();
        } catch (NoResultException ne) {
            return new ArrayList<>();
        }
    }

}
