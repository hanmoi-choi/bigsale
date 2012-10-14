package com.bigsale.service;

import com.bigsale.controller.dto.ItemSearchDto;
import com.bigsale.orm.dao.Repository;
import com.bigsale.orm.model.Item;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Item: hanmoi
 * Date: 29/09/12
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    Repository itemRepository;

    @Override
    public void addItem(Item item)
    {
        itemRepository.add(item);
    }

    @Override
    public void updateItem(Item item)
    {
        itemRepository.update(item);
    }

    @Override
    public void deleteItem(Item item)
    {
        itemRepository.delete(item);
    }

    @Override
    public Item getItemById(Integer itemId)
    {
        return (Item) itemRepository.findById(itemId);
    }

    @Override
    public List<Item> getAllItems()
    {
        return itemRepository.findAll();
    }

    @Override
    public boolean checkIdDuplication(String itemId)
    {
        Item item = (Item) itemRepository.findById(itemId);
        return item != null;
    }

    @Override
    public List<Item> findItemBySearchCriteria(ItemSearchDto itemSearchDto){
        Session session = itemRepository.getSessionFactory().openSession();
        session.beginTransaction();

        List<Item> itemList = (List<Item>) session.createCriteria(Item.class)
                .add(Restrictions.ilike("itemName", itemSearchDto.getItemName(), MatchMode.ANYWHERE))
                .addOrder(Order.asc("itemName"))
                .list();

        session.close();

        return itemList;
    }

}
