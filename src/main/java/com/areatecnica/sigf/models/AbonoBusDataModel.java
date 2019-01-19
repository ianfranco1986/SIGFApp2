/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.AbonoBus;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class AbonoBusDataModel extends ListDataModel<AbonoBus> implements SelectableDataModel<AbonoBus> {

    public AbonoBusDataModel() {
    }

    public AbonoBusDataModel(List<AbonoBus> list) {
        super(list);
    }

    @Override
    public Object getRowKey(AbonoBus object) {
        return object.getAbonoBusId();
    }

    @Override
    public AbonoBus getRowData(String rowKey) {
        List<AbonoBus> items = (List<AbonoBus>) getWrappedData();

        for (AbonoBus e : items) {
            if (e.getAbonoBusId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
