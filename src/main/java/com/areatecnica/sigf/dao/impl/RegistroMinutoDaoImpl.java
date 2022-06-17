/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IRegistroMinutoDao;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.entities.Terminal;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RegistroMinutoDaoImpl extends GenericDAOImpl<RegistroMinuto> implements IRegistroMinutoDao<RegistroMinuto> {

    public RegistroMinutoDaoImpl() {
        super(RegistroMinuto.class);
    }

    @Override
    public List<RegistroMinuto> findByDate(Date fecha) {
        return this.entityManager.createNamedQuery("RegistroMinuto.findByRegistroMinutoFechaMinuto").setParameter("registroMinutoFechaMinuto", fecha).getResultList();
    }

    @Override
    public List<RegistroMinuto> findByBusPagaDate(Bus bus, Date fecha) {
        return this.entityManager.createNamedQuery("RegistroMinuto.findByRegistroMinutoDesdeIdBus").setParameter("registroMinutoDesdeIdBus", bus).setParameter("registroMinutoFechaMinuto", fecha).getResultList();
    }

    @Override
    public List<RegistroMinuto> findByBusRecibeDate(Bus bus, Date fecha) {
        return this.entityManager.createNamedQuery("RegistroMinuto.findByRegistroMinutoHastaIdBus").setParameter("registroMinutoHastaIdBus", bus).setParameter("registroMinutoFechaMinuto", fecha).getResultList();
    }

    @Override
    public List<RegistroMinuto> findByBusSinRecaudar(Bus bus) {
        return this.entityManager.createNamedQuery("RegistroMinuto.findByRegistroMinutoDesdeSinRecaudar").setParameter("registroMinutoDesdeIdBus", bus).setParameter("registroMinutoRecaudado", Boolean.FALSE).getResultList();
    }

    @Override
    public List<RegistroMinuto> findBySinRecaudar() {
        return this.entityManager.createNamedQuery("RegistroMinuto.findByRegistroMinutoRecaudado").setParameter("registroMinutoRecaudado", Boolean.FALSE).getResultList();
    }

    @Override
    public List<RegistroMinuto> findByTerminalSinRecaudar(Terminal terminal) {
        try {
            return this.entityManager.
                    createNamedQuery("RegistroMinuto.findByRegistroMinutoSinRecaudar").
                    setParameter("registroMinutoRecaudado", Boolean.FALSE).
                    setParameter("busIdTerminal", terminal).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RegistroMinuto> findByBusPagaDates(Bus selected, Date fecha, Date maxDate) {
        return this.entityManager.createNamedQuery("RegistroMinuto.findByRegistroMinutoDesdeIdBus").setParameter("registroMinutoDesdeIdBus", selected).setParameter("from", fecha).setParameter("to", maxDate).getResultList();
    }

}
