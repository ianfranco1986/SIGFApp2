/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.controller.LiquidacionEmpresaLoteController;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RecaudacionLiquidacionLoteDataModel extends ListDataModel<LiquidacionEmpresaLoteController.LiquidacionHelper> implements SelectableDataModel<LiquidacionEmpresaLoteController.LiquidacionHelper> {

    public RecaudacionLiquidacionLoteDataModel() {
    }

    public RecaudacionLiquidacionLoteDataModel(List<LiquidacionEmpresaLoteController.LiquidacionHelper> list) {
        super(list);
    }

    @Override
    public String getRowKey(LiquidacionEmpresaLoteController.LiquidacionHelper object) {
        return String.valueOf(object.getId());
    }

    @Override
    public LiquidacionEmpresaLoteController.LiquidacionHelper getRowData(String rowKey) {
        List<LiquidacionEmpresaLoteController.LiquidacionHelper> items = (List<LiquidacionEmpresaLoteController.LiquidacionHelper>) getWrappedData();

        for (LiquidacionEmpresaLoteController.LiquidacionHelper e : items) {
            if (e.getId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
