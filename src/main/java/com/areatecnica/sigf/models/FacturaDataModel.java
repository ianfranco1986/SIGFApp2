/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.Factura;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class FacturaDataModel extends ListDataModel<Factura> implements SelectableDataModel<Factura> {

    public FacturaDataModel() {
    }

    public FacturaDataModel(List<Factura> list) {
        super(list);
    }

    @Override
    public String getRowKey(Factura object) {
        return String.valueOf(object.getFacturaId());
    }

    @Override
    public Factura getRowData(String rowKey) {
        List<Factura> items = (List<Factura>) getWrappedData();

        for (Factura e : items) {
            if (e.getFacturaId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
