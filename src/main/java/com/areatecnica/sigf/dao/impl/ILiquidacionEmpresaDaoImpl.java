/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ILiquidacionEmpresaDao;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import java.util.Date;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ILiquidacionEmpresaDaoImpl extends GenericDAOImpl<LiquidacionEmpresa> implements ILiquidacionEmpresaDao<LiquidacionEmpresa> {

    public ILiquidacionEmpresaDaoImpl() {
        super(LiquidacionEmpresa.class);
    }

    @Override
    public LiquidacionEmpresa findByEmpresaBetweenDate(Empresa empresa, Date from, Date to) {
        try {
            return (LiquidacionEmpresa) this.entityManager.
                    createNamedQuery("LiquidacionEmpresa.findByEmpresaFecha").
                    setParameter("liquidacionEmpresaIdEmpresa", empresa).
                    setParameter("from", from).
                    setParameter("to", to).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
