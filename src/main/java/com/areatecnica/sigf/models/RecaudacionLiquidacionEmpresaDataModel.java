/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.dto.RecaudacionGuiaDTO;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class RecaudacionLiquidacionEmpresaDataModel extends ListDataModel<RecaudacionGuiaDTO> implements SelectableDataModel<RecaudacionGuiaDTO> {

    public RecaudacionLiquidacionEmpresaDataModel() {
    }

    public RecaudacionLiquidacionEmpresaDataModel(List<RecaudacionGuiaDTO> list) {
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
