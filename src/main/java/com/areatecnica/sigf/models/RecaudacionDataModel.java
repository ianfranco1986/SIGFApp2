/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.controller.RecaudacionGuiaController;
import com.areatecnica.sigf.dto.RecaudacionGuiaDTO;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RecaudacionDataModel extends ListDataModel<RecaudacionGuiaDTO> implements SelectableDataModel<RecaudacionGuiaDTO> {

    public RecaudacionDataModel() {
    }

    public RecaudacionDataModel(List<RecaudacionGuiaDTO> list) {
        super(list);
    }

    @Override
    public String getRowKey(RecaudacionGuiaDTO object) {
        return String.valueOf(object.getId());
    }

    @Override
    public RecaudacionGuiaDTO getRowData(String rowKey) {
        List<RecaudacionGuiaDTO> items = (List<RecaudacionGuiaDTO>) getWrappedData();

        for (RecaudacionGuiaDTO e : items) {
            if (e.getId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
