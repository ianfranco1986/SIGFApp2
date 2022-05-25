/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.Recaudacion;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class DigitacionDataModel extends ListDataModel<Recaudacion> implements SelectableDataModel<Recaudacion> {

    public DigitacionDataModel() {
    }

    public DigitacionDataModel(List<Recaudacion> list) {
        super(list);
    }

    @Override
    public String getRowKey(Recaudacion object) {
        return String.valueOf(object.getRecaudacionId());
    }

    @Override
    public Recaudacion getRowData(String rowKey) {
        List<Recaudacion> items = (List<Recaudacion>) getWrappedData();

        for (Recaudacion e : items) {
            if (e.getRecaudacionId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
