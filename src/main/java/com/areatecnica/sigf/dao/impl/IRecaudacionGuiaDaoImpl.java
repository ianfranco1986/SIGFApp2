/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IRecaudacionGuiaDao;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

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
    public List<RecaudacionGuia> findByBusBetweenFechaRecaudacion(Bus bus, Date inicio, Date termino) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionGuia.findByBusBetweenFechaRecaudacion").
                    setParameter("guiaIdBus", bus).
                    setParameter("from", inicio).
                    setParameter("to", termino).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    
}
