/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RecaudacionGuia;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RecaudacionGuiaDataModel extends ListDataModel<RecaudacionGuia> implements SelectableDataModel<RecaudacionGuia> {

    public RecaudacionGuiaDataModel() {
    }

    public RecaudacionGuiaDataModel(List<RecaudacionGuia> list) {
        super(list);
    }

    @Override
    public String getRowKey(RecaudacionGuia object) {
        return String.valueOf(object.getRecaudacionGuiaId());
    }

    @Override
    public RecaudacionGuia getRowData(String rowKey) {
        List<RecaudacionGuia> items = (List<RecaudacionGuia>) getWrappedData();

        for (RecaudacionGuia e : items) {
            if (e.getRecaudacionGuiaId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
