/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IBoletoDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Cuenta;

import java.util.List;

/**
 *
 * @author ianfr
 */
public class IBoletoDaoImpl extends GenericDAOImpl<Boleto> implements IBoletoDao<Boleto> {

    public IBoletoDaoImpl() {
        super(Boleto.class);
    }

    @Override
    public List<Boleto> findByCuenta(Cuenta cuenta) {
        return this.entityManager.createNamedQuery("Boleto.findByCuenta").setParameter("boletoIdCuenta", cuenta).getResultList();
    }

}
