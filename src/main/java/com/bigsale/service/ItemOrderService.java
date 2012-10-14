package com.bigsale.service;

import com.bigsale.controller.dto.ItemOrderSearchDto;
import com.bigsale.orm.model.ItemOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ItemOrder: hanmoi
 * Date: 29/09/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemOrderService {
    void addItemOrder(ItemOrder itemOrder);

    void updateItemOrder(ItemOrder itemOrder);

    void deleteItemOrder(ItemOrder itemOrder);

    ItemOrder getItemOrderById(Integer id);

    List<ItemOrder> getAllItemOrders();

    boolean checkIdDuplication(String itemOrderId);

    @Transactional
    List<ItemOrder> findItemOrderBySearchCriteria(ItemOrderSearchDto itemOrderSearchDto);

}
