/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.DetalleCartola;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class DetalleCartolaDataModel extends ListDataModel<DetalleCartola> implements SelectableDataModel<DetalleCartola> {

    public DetalleCartolaDataModel() {
    }

    public DetalleCartolaDataModel(List<DetalleCartola> list) {
        super(list);
    }

    @Override
    public String getRowKey(DetalleCartola object) {
        return String.valueOf(object.getDetalleCartolaId());
    }

    @Override
    public DetalleCartola getRowData(String rowKey) {
        List<DetalleCartola> items = (List<DetalleCartola>) getWrappedData();

        for (DetalleCartola e : items) {
            if (e.getDetalleCartolaId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
