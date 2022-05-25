/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.VentaBoleto;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class VentaBoletoRecaudacionDataModel extends ListDataModel<VentaBoleto> implements SelectableDataModel<VentaBoleto> {

    public VentaBoletoRecaudacionDataModel() {
    }

    public VentaBoletoRecaudacionDataModel(List<VentaBoleto> list) {
        super(list);
    }

    @Override
    public String getRowKey(VentaBoleto object) {
        return String.valueOf(object.getVentaBoletoId());
    }

    @Override
    public VentaBoleto getRowData(String rowKey) {
        List<VentaBoleto> items = (List<VentaBoleto>) getWrappedData();

        for (VentaBoleto e : items) {
            if (e.getVentaBoletoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
