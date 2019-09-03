/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.controller.RegistroLiquidacionBusController;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class RecaudacionLiquidacionDataModel extends ListDataModel<RegistroLiquidacionBusController.RecaudacionGuiaHelper> implements SelectableDataModel<RegistroLiquidacionBusController.RecaudacionGuiaHelper> {

    public RecaudacionLiquidacionDataModel() {
    }

    public RecaudacionLiquidacionDataModel(List<RegistroLiquidacionBusController.RecaudacionGuiaHelper> list) {
        super(list);
    }

    @Override
    public Object getRowKey(RegistroLiquidacionBusController.RecaudacionGuiaHelper object) {
        return object.getId();
    }

    @Override
    public RegistroLiquidacionBusController.RecaudacionGuiaHelper getRowData(String rowKey) {
        List<RegistroLiquidacionBusController.RecaudacionGuiaHelper> items = (List<RegistroLiquidacionBusController.RecaudacionGuiaHelper>) getWrappedData();

        for (RegistroLiquidacionBusController.RecaudacionGuiaHelper e : items) {
            if (e.getId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
