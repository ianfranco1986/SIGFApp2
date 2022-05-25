/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.CartolaBanco;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class CartolaBancoDataModel extends ListDataModel<CartolaBanco> implements SelectableDataModel<CartolaBanco> {

    public CartolaBancoDataModel() {
    }

    public CartolaBancoDataModel(List<CartolaBanco> list) {
        super(list);
    }

    @Override
    public String getRowKey(CartolaBanco object) {
        return String.valueOf(object.getCartolaBancoId());
    }

    @Override
    public CartolaBanco getRowData(String rowKey) {
        List<CartolaBanco> items = (List<CartolaBanco>) getWrappedData();

        for (CartolaBanco e : items) {
            if (e.getCartolaBancoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
