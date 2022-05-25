/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RecaudacionMinuto;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RecaudacionMinutoDataModel extends ListDataModel<RecaudacionMinuto> implements SelectableDataModel<RecaudacionMinuto> {

    public RecaudacionMinutoDataModel() {
    }

    public RecaudacionMinutoDataModel(List<RecaudacionMinuto> list) {
        super(list);
    }

    @Override
    public String getRowKey(RecaudacionMinuto object) {
        return String.valueOf(object.getRecaudacionMinutoId());
    }

    @Override
    public RecaudacionMinuto getRowData(String rowKey) {
        List<RecaudacionMinuto> items = (List<RecaudacionMinuto>) getWrappedData();

        for (RecaudacionMinuto e : items) {
            if (e.getRecaudacionMinutoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
