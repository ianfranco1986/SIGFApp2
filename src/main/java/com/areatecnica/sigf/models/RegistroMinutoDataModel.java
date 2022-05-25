/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RegistroMinuto;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RegistroMinutoDataModel extends ListDataModel<RegistroMinuto> implements SelectableDataModel<RegistroMinuto> {

    public RegistroMinutoDataModel() {
    }

    public RegistroMinutoDataModel(List<RegistroMinuto> list) {
        super(list);
    }

    @Override
    public String getRowKey(RegistroMinuto object) {
        return String.valueOf(object.getRegistroMinutoId());
    }

    @Override
    public RegistroMinuto getRowData(String rowKey) {
        List<RegistroMinuto> items = (List<RegistroMinuto>) getWrappedData();

        for (RegistroMinuto e : items) {
            if (e.getRegistroMinutoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
