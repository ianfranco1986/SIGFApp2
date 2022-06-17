/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Compra;
import com.areatecnica.sigf.entities.Proveedor;
import com.areatecnica.sigf.entities.TipoDocumento;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ICompraDao<T> extends IGenericDAO<T> {

    List<Compra> findCompraBetweenDates(Date from, Date to);

    Compra findByFolioProveedorTipo(int folio, Proveedor proveedor, TipoDocumento tipo);
    
}
