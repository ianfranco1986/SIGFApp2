/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.Compra;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class CompraDataModel extends ListDataModel<Compra> implements SelectableDataModel<Compra> {

    public CompraDataModel() {
    }

    public CompraDataModel(List<Compra> list) {
        super(list);
    }

    @Override
    public String getRowKey(Compra object) {
        return String.valueOf(object.getCompraId());
    }

    @Override
    public Compra getRowData(String rowKey) {
        List<Compra> items = (List<Compra>) getWrappedData();

        for (Compra e : items) {
            if (e.getCompraId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
