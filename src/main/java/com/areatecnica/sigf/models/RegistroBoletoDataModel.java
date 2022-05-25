/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RegistroBoleto;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RegistroBoletoDataModel extends ListDataModel<RegistroBoleto> implements SelectableDataModel<RegistroBoleto> {

    public RegistroBoletoDataModel() {
    }

    public RegistroBoletoDataModel(List<RegistroBoleto> list) {
        super(list);
    }

    @Override
    public String getRowKey(RegistroBoleto object) {
        return String.valueOf(object.getRegistroBoletoId());
    }

    @Override
    public RegistroBoleto getRowData(String rowKey) {
        List<RegistroBoleto> items = (List<RegistroBoleto>) getWrappedData();

        for (RegistroBoleto e : items) {
            if (e.getRegistroBoletoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
