/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ILiquidacionEmpresaDao;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;

import javax.persistence.NoResultException;
import java.util.Date;

/**
 *
 * @author ianfr
 */
public class LiquidacionEmpresaDaoImpl extends GenericDAOImpl<LiquidacionEmpresa> implements ILiquidacionEmpresaDao<LiquidacionEmpresa> {

    public LiquidacionEmpresaDaoImpl() {
        super(LiquidacionEmpresa.class);
    }

    @Override
    public LiquidacionEmpresa findByEmpresaFechaLiquidacion(Empresa empresa, Date date) {
        try {
            return (LiquidacionEmpresa) this.entityManager.
                    createNamedQuery("LiquidacionEmpresa.findByEmpresaFechaLiquidacion").
                    setParameter("liquidacionEmpresaIdEmpresa", empresa).
                    setParameter("fecha", date).
                    getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
