/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.DescuentoExtraBus;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class DescuentoExtraBusDataModel extends ListDataModel<DescuentoExtraBus> implements SelectableDataModel<DescuentoExtraBus> {

    public DescuentoExtraBusDataModel() {
    }

    public DescuentoExtraBusDataModel(List<DescuentoExtraBus> list) {
        super(list);
    }

    @Override
    public Object getRowKey(DescuentoExtraBus object) {
        return object.getDescuentoExtraBusId();
    }

    @Override
    public DescuentoExtraBus getRowData(String rowKey) {
        List<DescuentoExtraBus> items = (List<DescuentoExtraBus>) getWrappedData();

        for (DescuentoExtraBus e : items) {
            if (e.getDescuentoExtraBusId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
