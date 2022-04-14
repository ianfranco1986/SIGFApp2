/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Empresa;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IEmpresaDao<T> extends IGenericDAO<T> {
    
    public List<Empresa> findByCuenta(Cuenta cuenta);
    
    public Empresa findById(int id);
}
