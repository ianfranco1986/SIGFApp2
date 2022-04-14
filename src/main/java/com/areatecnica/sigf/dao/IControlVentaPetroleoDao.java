/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.ControlVentaPetroleo;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IControlVentaPetroleoDao<T> extends IGenericDAO<T> {

    public ControlVentaPetroleo findById(int id);

    public List<ControlVentaPetroleo> findByDates(Date from, Date to, Cuenta cuenta);
}
