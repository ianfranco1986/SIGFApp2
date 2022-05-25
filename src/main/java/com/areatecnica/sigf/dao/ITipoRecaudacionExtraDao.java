/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.TipoRecaudacionExtra;

import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ITipoRecaudacionExtraDao<T> extends IGenericDAO<T> {

    List<TipoRecaudacionExtra> findByCuenta(Cuenta cuenta);

}
