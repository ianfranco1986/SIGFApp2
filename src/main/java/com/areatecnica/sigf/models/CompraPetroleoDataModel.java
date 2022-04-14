/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.CompraPetroleo;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class CompraPetroleoDataModel extends ListDataModel<CompraPetroleo> implements SelectableDataModel<CompraPetroleo> {

    public CompraPetroleoDataModel() {
    }

    public CompraPetroleoDataModel(List<CompraPetroleo> list) {
        super(list);
    }

    @Override
    public String getRowKey(CompraPetroleo object) {
        return String.valueOf(object.getCompraPetroleoId());
    }

    @Override
    public CompraPetroleo getRowData(String rowKey) {
        List<CompraPetroleo> items = (List<CompraPetroleo>) getWrappedData();

        for (CompraPetroleo e : items) {
            if (e.getCompraPetroleoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
