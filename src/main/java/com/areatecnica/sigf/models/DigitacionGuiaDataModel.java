/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.Guia;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class DigitacionGuiaDataModel extends ListDataModel<Guia> implements SelectableDataModel<Guia> {

    public DigitacionGuiaDataModel() {
    }

    public DigitacionGuiaDataModel(List<Guia> list) {
        super(list);
    }

    @Override
    public String getRowKey(Guia object) {
        return String.valueOf(object.getGuiaId());
    }

    @Override
    public Guia getRowData(String rowKey) {
        List<Guia> items = (List<Guia>) getWrappedData();

        for (Guia e : items) {
            if (e.getGuiaId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
