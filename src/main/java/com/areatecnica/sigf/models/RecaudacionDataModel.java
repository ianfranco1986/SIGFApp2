/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.controller.RecaudacionGuiaController;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RecaudacionDataModel extends ListDataModel<RecaudacionGuiaController.RecaudacionGuiaHelper> implements SelectableDataModel<RecaudacionGuiaController.RecaudacionGuiaHelper> {

    public RecaudacionDataModel() {
    }

    public RecaudacionDataModel(List<RecaudacionGuiaController.RecaudacionGuiaHelper> list) {
        super(list);
    }

    @Override
    public String getRowKey(RecaudacionGuiaController.RecaudacionGuiaHelper object) {
        return String.valueOf(object.getId());
    }

    @Override
    public RecaudacionGuiaController.RecaudacionGuiaHelper getRowData(String rowKey) {
        List<RecaudacionGuiaController.RecaudacionGuiaHelper> items = (List<RecaudacionGuiaController.RecaudacionGuiaHelper>) getWrappedData();

        for (RecaudacionGuiaController.RecaudacionGuiaHelper e : items) {
            if (e.getId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
