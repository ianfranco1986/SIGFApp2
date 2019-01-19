/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.CargoBus;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class CargoBusDataModel extends ListDataModel<CargoBus> implements SelectableDataModel<CargoBus> {

    public CargoBusDataModel() {
    }

    public CargoBusDataModel(List<CargoBus> list) {
        super(list);
    }

    @Override
    public Object getRowKey(CargoBus object) {
        return object.getCargoBusId();
    }

    @Override
    public CargoBus getRowData(String rowKey) {
        List<CargoBus> items = (List<CargoBus>) getWrappedData();

        for (CargoBus e : items) {
            if (e.getCargoBusId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
