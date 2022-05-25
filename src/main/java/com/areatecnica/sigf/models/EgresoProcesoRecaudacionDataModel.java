/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class EgresoProcesoRecaudacionDataModel extends ListDataModel<EgresoProcesoRecaudacion> implements SelectableDataModel<EgresoProcesoRecaudacion> {

    public EgresoProcesoRecaudacionDataModel() {
    }

    public EgresoProcesoRecaudacionDataModel(List<EgresoProcesoRecaudacion> list) {
        super(list);
    }

    @Override
    public String getRowKey(EgresoProcesoRecaudacion object) {
        return String.valueOf(object.getEgresoProcesoRecaudacionId());
    }

    @Override
    public EgresoProcesoRecaudacion getRowData(String rowKey) {
        List<EgresoProcesoRecaudacion> items = (List<EgresoProcesoRecaudacion>) getWrappedData();

        for (EgresoProcesoRecaudacion e : items) {
            if (e.getEgresoProcesoRecaudacionId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
