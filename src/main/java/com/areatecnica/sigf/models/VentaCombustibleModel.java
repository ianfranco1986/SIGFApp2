/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.VentaCombustible;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class VentaCombustibleModel extends ListDataModel<VentaCombustible> implements SelectableDataModel<VentaCombustible> {

    public VentaCombustibleModel() {
    }

    public VentaCombustibleModel(List<VentaCombustible> list) {
        super(list);
    }

    @Override
    public String getRowKey(VentaCombustible object) {
        return String.valueOf(object.getVentaCombustibleId());
    }

    @Override
    public VentaCombustible getRowData(String rowKey) {
        List<VentaCombustible> items = (List<VentaCombustible>) getWrappedData();

        for (VentaCombustible e : items) {
            if (e.getVentaCombustibleId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
