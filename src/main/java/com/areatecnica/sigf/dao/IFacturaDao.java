/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Factura;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IFacturaDao<T> extends IGenericDAO<T> {
    
    public Factura findById(int id);
    
    public Factura findByFolio(int folio);
    
    public List<Factura> findBetweenDates(Date from, Date to);
}
