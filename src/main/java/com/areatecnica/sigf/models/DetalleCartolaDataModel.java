/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.DetalleCartola;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

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
    public Object getRowKey(DetalleCartola object) {
        return object.getDetalleCartolaId();
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
