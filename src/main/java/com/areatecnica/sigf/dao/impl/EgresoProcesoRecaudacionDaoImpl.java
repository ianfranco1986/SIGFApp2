/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IEgresoProcesoRecaudacionDao;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class EgresoProcesoRecaudacionDaoImpl extends GenericDAOImpl<EgresoProcesoRecaudacion> implements IEgresoProcesoRecaudacionDao<EgresoProcesoRecaudacion> {

    @Override
    public List<Egreso> findAllByProceso(ProcesoRecaudacion procesoRecaudacion) {
        try {
            return this.entityManager.createNamedQuery("EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionIdProceso").setParameter("egresoProcesoRecaudacionIdProceso", procesoRecaudacion).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
