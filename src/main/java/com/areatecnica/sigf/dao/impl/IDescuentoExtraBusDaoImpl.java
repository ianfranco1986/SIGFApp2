/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IDescuentoExtraBusDao;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.DescuentoExtraBus;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IDescuentoExtraBusDaoImpl extends GenericDAOImpl<DescuentoExtraBus> implements IDescuentoExtraBusDao<DescuentoExtraBus> {

    public IDescuentoExtraBusDaoImpl() {
        super(DescuentoExtraBus.class);
    }

    @Override
    public List<DescuentoExtraBus> findByBus(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("DescuentoExtraBus.findByBus").setParameter("descuentoExtraBusIdBus", bus).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<DescuentoExtraBus> findByDescuentoExtra(DescuentoExtraBus extra) {
        try {
            return this.entityManager.createNamedQuery("DescuentoExtraBus.findByDescuento").setParameter("descuentoExtraBusIdDescuento", extra).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
