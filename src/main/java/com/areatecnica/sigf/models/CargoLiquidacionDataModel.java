/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.CargoLiquidacion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class CargoLiquidacionDataModel extends ListDataModel<CargoLiquidacion> implements SelectableDataModel<CargoLiquidacion> {

    public CargoLiquidacionDataModel() {
    }

    public CargoLiquidacionDataModel(List<CargoLiquidacion> list) {
        super(list);
    }

    @Override
    public String getRowKey(CargoLiquidacion object) {
        return String.valueOf(object.getCargoLiquidacionId());
    }

    @Override
    public CargoLiquidacion getRowData(String rowKey) {
        List<CargoLiquidacion> items = (List<CargoLiquidacion>) getWrappedData();

        for (CargoLiquidacion e : items) {
            if (e.getCargoLiquidacionId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
