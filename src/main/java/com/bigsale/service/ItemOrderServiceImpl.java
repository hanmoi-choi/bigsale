package com.bigsale.service;

import com.bigsale.controller.dto.ItemOrderSearchDto;
import com.bigsale.orm.dao.Repository;
import com.bigsale.orm.model.ItemOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ItemOrder: hanmoi
 * Date: 29/09/12
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("itemOrderService")
public class ItemOrderServiceImpl implements ItemOrderService {
    @Autowired
    Repository itemOrderRepository;

    @Override
    public void addItemOrder(ItemOrder itemOrder)
    {
        itemOrderRepository.add(itemOrder);
    }

    @Override
    public void updateItemOrder(ItemOrder itemOrder)
    {
        itemOrderRepository.update(itemOrder);
    }

    @Override
    public void deleteItemOrder(ItemOrder itemOrder)
    {
        itemOrderRepository.delete(itemOrder);
    }

    @Override
    public ItemOrder getItemOrderById(Integer itemOrderId)
    {
        return (ItemOrder) itemOrderRepository.findById(itemOrderId);
    }

    @Override
    public List<ItemOrder> getAllItemOrders()
    {
        return itemOrderRepository.findAll();
    }

    @Override
    public boolean checkIdDuplication(String itemOrderId)
    {
        ItemOrder itemOrder = (ItemOrder) itemOrderRepository.findById(itemOrderId);
        return itemOrder != null;
    }

    @Override
    public List<ItemOrder> findItemOrderBySearchCriteria(ItemOrderSearchDto itemOrderSearchDto){
//        Session session = itemOrderRepository.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        List<ItemOrder> itemOrderList = (List<ItemOrder>) session.createCriteria(ItemOrder.class)
//                .add(Restrictions.ilike("itemOrderName", itemOrderSearchDto.getItemOrderName(), MatchMode.ANYWHERE))
//                .addOrder(Order.asc("itemOrderName"))
//                .list();
//
//        session.close();
//
        return null;
    }

}
