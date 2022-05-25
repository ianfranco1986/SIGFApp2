/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.EgresoFlota;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class EgresoFlotaDataModel extends ListDataModel<EgresoFlota> implements SelectableDataModel<EgresoFlota> {

    public EgresoFlotaDataModel() {
    }

    public EgresoFlotaDataModel(List<EgresoFlota> list) {
        super(list);
    }

    @Override
    public String getRowKey(EgresoFlota object) {
        return String.valueOf(object.getEgresoFlotaId());
    }

    @Override
    public EgresoFlota getRowData(String rowKey) {
        List<EgresoFlota> items = (List<EgresoFlota>) getWrappedData();

        for (EgresoFlota e : items) {
            if (e.getEgresoFlotaId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
