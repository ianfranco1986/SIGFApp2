/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ICuentaBancariaDao<T> extends IGenericDAO<T> {
    
    public List<CartolaBanco> findByCuenta(Cuenta cuenta);
   
}