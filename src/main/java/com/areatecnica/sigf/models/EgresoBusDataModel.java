/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.EgresoBus;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class EgresoBusDataModel extends ListDataModel<EgresoBus> implements SelectableDataModel<EgresoBus> {

    public EgresoBusDataModel() {
    }

    public EgresoBusDataModel(List<EgresoBus> list) {
        super(list);
    }

    @Override
    public String getRowKey(EgresoBus object) {
        return String.valueOf(object.getEgresoBusId());
    }

    @Override
    public EgresoBus getRowData(String rowKey) {
        List<EgresoBus> items = (List<EgresoBus>) getWrappedData();

        for (EgresoBus e : items) {
            if (e.getEgresoBusId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
