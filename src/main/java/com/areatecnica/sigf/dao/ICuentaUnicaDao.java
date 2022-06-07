/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CuentaUnica;
import com.areatecnica.sigf.entities.PlanCuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ICuentaUnicaDao<T> extends IGenericDAO<T> {

    List<CuentaUnica> find();

    List<CuentaUnica> findAllActivated();
    List<CuentaUnica> findAllDeactivated();

    CuentaUnica findById(int id);

}
