/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RecaudacionGuia;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

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
    public Object getRowKey(RecaudacionGuia object) {
        return object.getRecaudacionGuiaId();
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
