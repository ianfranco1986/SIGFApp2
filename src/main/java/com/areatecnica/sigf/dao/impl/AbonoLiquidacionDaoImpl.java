/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IAbonoLiquidacionDao;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.TipoAbono;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author ianfr
 */
public class AbonoLiquidacionDaoImpl extends GenericDAOImpl<AbonoLiquidacion> implements IAbonoLiquidacionDao<AbonoLiquidacion> {

    @Override
    public AbonoLiquidacion findById(int id) {
        try {
            return (AbonoLiquidacion) this.entityManager.createNamedQuery("AbonoLiquidacion.findByAbonoLiquidacionId").setParameter("cargoLiquidacionId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public AbonoLiquidacion findByLiquidacion(LiquidacionEmpresa liquidacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AbonoLiquidacion> findByEmpresaBetweenDates(Empresa empresa, Date desde, Date hasta) {
        return this.entityManager.createNamedQuery("AbonoLiquidacion.findByEmpresaBetweenDates")
                .setParameter("empresaId", empresa)
                .setParameter("from", desde)
                .setParameter("to", hasta)
                .getResultList();
    }

    @Override
    public AbonoLiquidacion findByEmpresaTipoBetweenDates(Empresa empresa, TipoAbono abono, Date desde, Date hasta) {
        try {
            return (AbonoLiquidacion) this.entityManager.createNamedQuery("AbonoLiquidacion.findByEmpresaTipoAbonoBetweenDates")
                    .setParameter("empresaId", empresa)
                    .setParameter("abonoLiquidacionTipoId", abono)
                    .setParameter("from", desde)
                    .setParameter("to", hasta)
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public long countByTipoAbono(TipoAbono abono, Date desde, Date hasta) {
        Query query = this.entityManager
                .createQuery("SELECT COUNT(a) FROM AbonoLiquidacion a WHERE a.abonoLiquidacionTipoId = :abonoLiquidacionTipoId AND a.abonoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaFechaLiquidacion BETWEEN :from AND :to")
                .setParameter("abonoLiquidacionTipoId", abono)
                .setParameter("from", desde)
                .setParameter("to", hasta);

        return (Long) query.getSingleResult();
    }

}
