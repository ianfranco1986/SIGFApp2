/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CuentaMayor;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ICuentaMayorDao<T> extends IGenericDAO<T> {

    public List<CuentaMayor> findALL();

}
