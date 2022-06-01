/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.PlanCuentaSubTipo;
import com.areatecnica.sigf.entities.TipoPlanCuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IPlanCuentaSubTipoDao<T> extends IGenericDAO<T> {

    List<PlanCuentaSubTipo> find();

    List<PlanCuentaSubTipo> findByTipoPlan(TipoPlanCuenta tipo);

    PlanCuentaSubTipo findById(int id);

}
