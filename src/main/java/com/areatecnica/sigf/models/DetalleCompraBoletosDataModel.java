/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class DetalleCompraBoletosDataModel extends ListDataModel<DetalleCompraBoleto> implements SelectableDataModel<DetalleCompraBoleto> {

    public DetalleCompraBoletosDataModel() {
    }

    public DetalleCompraBoletosDataModel(List<DetalleCompraBoleto> list) {
        super(list);
    }

    @Override
    public String getRowKey(DetalleCompraBoleto object) {
        return String.valueOf(object.getDetalleCompraBoletoId());
    }

    @Override
    public DetalleCompraBoleto getRowData(String rowKey) {
        List<DetalleCompraBoleto> items = (List<DetalleCompraBoleto>) getWrappedData();

        for (DetalleCompraBoleto e : items) {
            if (e.getDetalleCompraBoletoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
