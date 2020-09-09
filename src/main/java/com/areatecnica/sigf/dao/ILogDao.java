/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Log;

/**
 *
 * @author ianfr
 */
public interface ILogDao<T> extends IGenericDAO<T> {

    public Log findById(int id);

}
