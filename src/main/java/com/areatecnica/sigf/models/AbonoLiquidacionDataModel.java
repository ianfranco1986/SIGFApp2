/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.AbonoLiquidacion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class AbonoLiquidacionDataModel extends ListDataModel<AbonoLiquidacion> implements SelectableDataModel<AbonoLiquidacion> {

    public AbonoLiquidacionDataModel() {
    }

    public AbonoLiquidacionDataModel(List<AbonoLiquidacion> list) {
        super(list);
    }

    @Override
    public String getRowKey(AbonoLiquidacion object) {
        return String.valueOf(object.getAbonoLiquidacionId());
    }

    @Override
    public AbonoLiquidacion getRowData(String rowKey) {
        List<AbonoLiquidacion> items = (List<AbonoLiquidacion>) getWrappedData();

        for (AbonoLiquidacion e : items) {
            if (e.getAbonoLiquidacionId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
