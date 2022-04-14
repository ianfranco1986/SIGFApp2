/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.MovimientoMes;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class MovimientoMesDataModel extends ListDataModel<MovimientoMes> implements SelectableDataModel<MovimientoMes> {

    public MovimientoMesDataModel() {
    }

    public MovimientoMesDataModel(List<MovimientoMes> list) {
        super(list);
    }

    @Override
    public String getRowKey(MovimientoMes object) {
        return String.valueOf(object.getMovimientoMesId());
    }

    @Override
    public MovimientoMes getRowData(String rowKey) {
        List<MovimientoMes> items = (List<MovimientoMes>) getWrappedData();

        for (MovimientoMes e : items) {
            if (e.getMovimientoMesId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
