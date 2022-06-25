/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICargoLiquidacionDao;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.TipoCargo;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class CargoLiquidacionDaoImpl extends GenericDAOImpl<CargoLiquidacion> implements ICargoLiquidacionDao<CargoLiquidacion> {

    @Override
    public CargoLiquidacion findById(int id) {
        try {
            return (CargoLiquidacion) this.entityManager.createNamedQuery("CargoLiquidacion.findByCargoLiquidacionId").setParameter("cargoLiquidacionId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public CargoLiquidacion findByLiquidacion(LiquidacionEmpresa liquidacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CargoLiquidacion> findByEmpresaBetweenDates(Empresa empresa, Date desde, Date hasta) {
        return this.entityManager
                .createNamedQuery("CargoLiquidacion.findByEmpresaBetweenDates")
                .setParameter("empresaId", empresa)
                .setParameter("from", desde)
                .setParameter("to", hasta)
                .getResultList();
    }

    @Override
    public CargoLiquidacion findByEmpresaTipoBetweenDates(Empresa empresa, TipoCargo cargo, Date desde, Date hasta) {
        try {
            return (CargoLiquidacion) this.entityManager.createNamedQuery("CargoLiquidacion.findByEmpresaTipoCargoBetweenDates")
                    .setParameter("empresaId", empresa)
                    .setParameter("cargoLiquidacionCargoId", cargo)
                    .setParameter("from", desde)
                    .setParameter("to", hasta)
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
