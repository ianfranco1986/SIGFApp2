/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IMovimientoMesDao;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.MovimientoMes;
import com.areatecnica.sigf.entities.TipoMovimiento;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class MovimientoMesDaoImpl extends GenericDAOImpl<MovimientoMes> implements IMovimientoMesDao<MovimientoMes> {

    public MovimientoMesDaoImpl() {
        super(MovimientoMes.class);
    }

    @Override
    public List<MovimientoMes> findByDocumento(int documento) {
        try {
            return this.entityManager.createNamedQuery("MovimientoMes.findByMovimientoMesDocumento").
                    setParameter("movimientoMesDocumento", documento).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<MovimientoMes> findByDates(Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("MovimientoMes.findByMovimientoMesFechaMvtoDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<MovimientoMes> findByIngresosDates(Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("MovimientoMes.findByMovimientoMesFechaMvtoDatesIngresos").
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<MovimientoMes> findByEgresosDates(Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("MovimientoMes.findByMovimientoMesFechaMvtoDatesEgresos").
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<MovimientoMes> findByCuentaAndDates(CuentaBancaria cuenta, Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("MovimientoMes.findByMovimientoMesFechaMvtoDatesCuenta").
                    setParameter("movimientoMesCuentaId", cuenta).
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<MovimientoMes> findByEmpresaAndDates(Empresa empresa, Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("MovimientoMes.findByMovimientoMesFechaMvtoDatesEmpresa").
                    setParameter("movimientoMesEmpresaId", empresa).
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return new ArrayList<>();
        }
    }

    @Override
    public MovimientoMes findLastByCuenta(CuentaBancaria cuenta) {
        try {
            return (MovimientoMes) this.entityManager.createNamedQuery("MovimientoMes.findLastByCuenta").
                    setParameter("movimientoMesCuentaId", cuenta).
                    setMaxResults(1).
                    getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    @Override
    public List<MovimientoMes> findByTipoAndDates(TipoMovimiento tipo, Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("MovimientoMes.findByMovimientoMesFechaMvtoDatesTipo").
                    setParameter("movimientoMesMvtoId", tipo).
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
