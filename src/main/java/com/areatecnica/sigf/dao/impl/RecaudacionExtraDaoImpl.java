/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IRecaudacionExtraDao;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionExtra;
import com.areatecnica.sigf.entities.Terminal;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RecaudacionExtraDaoImpl extends GenericDAOImpl<RecaudacionExtra> implements IRecaudacionExtraDao<RecaudacionExtra> {

    public RecaudacionExtraDaoImpl() {
        super(RecaudacionExtra.class);
    }

    @Override
    public List<RecaudacionExtra> findByDate(Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecaudacionExtra> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionExtra.findByFechaRecaudacionCaja").
                    setParameter("recaudacionIdCaja", cajaRecaudacion).
                    setParameter("recaudacionFecha", fechaVenta).
                    getResultList();
        } catch (NoResultException ne) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<RecaudacionExtra> findByTerminalDate(Terminal terminal, Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
