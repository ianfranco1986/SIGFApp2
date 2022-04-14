/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Compra;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ICompraDao<T> extends IGenericDAO<T> {

    public List<Compra> findCompraBetweenDates(Date from, Date to);

}
