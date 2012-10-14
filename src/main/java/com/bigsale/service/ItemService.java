package com.bigsale.service;

import com.bigsale.controller.dto.ItemSearchDto;
import com.bigsale.orm.model.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Item: hanmoi
 * Date: 29/09/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemService {
    void addItem(Item item);

    void updateItem(Item item);

    void deleteItem(Item item);

    Item getItemById(Integer id);

    List<Item> getAllItems();

    boolean checkIdDuplication(String itemId);

    @Transactional
    List<Item> findItemBySearchCriteria(ItemSearchDto itemSearchDto);

}
