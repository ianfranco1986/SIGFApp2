/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RecaudacionExtra;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RecaudacionExtraDataModel extends ListDataModel<RecaudacionExtra> implements SelectableDataModel<RecaudacionExtra> {

    public RecaudacionExtraDataModel() {
    }

    public RecaudacionExtraDataModel(List<RecaudacionExtra> list) {
        super(list);
    }

    @Override
    public String getRowKey(RecaudacionExtra object) {
        return String.valueOf(object.getRecaudacionExtraId());
    }

    @Override
    public RecaudacionExtra getRowData(String rowKey) {
        List<RecaudacionExtra> items = (List<RecaudacionExtra>) getWrappedData();

        for (RecaudacionExtra e : items) {
            if (e.getRecaudacionExtraId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
