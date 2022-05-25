/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.Bus;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class BusDataModel extends ListDataModel<Bus> implements SelectableDataModel<Bus> {

    public BusDataModel() {
    }

    public BusDataModel(List<Bus> list) {
        super(list);
    }

    @Override
    public String getRowKey(Bus object) {
        return String.valueOf(object.getBusId());
    }

    @Override
    public Bus getRowData(String rowKey) {
        List<Bus> items = (List<Bus>) getWrappedData();

        for (Bus e : items) {
            if (e.getBusId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
