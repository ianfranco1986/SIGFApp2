/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IRecaudacionGuiaDao;
import com.areatecnica.sigf.entities.*;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class IRecaudacionGuiaDaoImpl extends GenericDAOImpl<RecaudacionGuia> implements IRecaudacionGuiaDao<RecaudacionGuia> {

    public IRecaudacionGuiaDaoImpl() {
        super(RecaudacionGuia.class);
    }

    @Override
    public RecaudacionGuia findByCuentaFolio(Cuenta cuenta, int folio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecaudacionGuia findByRecaudacionEgreso(int recaudacion, Egreso egreso) {
        try {
            return (RecaudacionGuia) this.entityManager.createNamedQuery("RecaudacionGuia.findByRecaudacionGuiaIdRecaudacionEgreso").
                    setParameter("recaudacionId", recaudacion).
                    setParameter("recaudacionGuiaIdEgreso", egreso).
                    getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionGuia> findByBusBetweenFechaRecaudacion(Bus bus, Date inicio, Date termino) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionGuia.findByBusBetweenFechaRecaudacion").
                    setParameter("guiaIdBus", bus).
                    setParameter("from", inicio).
                    setParameter("to", termino).getResultList();
        } catch (NoResultException ne) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<RecaudacionGuia> findByCajaFecha(CajaRecaudacion caja, Date fecha) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionGuia.findByBusBetweenFechaRecaudacion").
                    setParameter("recaudacionIdCaja", caja).
                    setParameter("recaudacionFecha", fecha).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public int findByEgreso(Date from, Date to, Empresa empresa, int egreso) {
        try {

            BigDecimal aux = (BigDecimal) entityManager.createQuery("SELECT COALESCE(SUM(r.recaudacionGuiaMonto), 0) "
                    + "FROM  RecaudacionGuia r "
                    + "WHERE r.recaudacionGuiaIdGuia.guiaIdBus.busIdEmpresa = :empresa "
                    + "AND r.recaudacionGuiaIdEgreso.egresoId = :egreso "
                    + "AND r.recaudacionGuiaIdRecaudacion.recaudacionFecha "
                    + "BETWEEN :from AND :to").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("empresa", empresa).
                    setParameter("egreso", egreso)
                    .getSingleResult();
            int xua = aux.intValue();
            return xua;
        } catch (NoResultException ne) {
            return 0;
        }
    }

}
