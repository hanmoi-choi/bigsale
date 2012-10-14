package com.bigsale.service;

import com.bigsale.controller.dto.UserSearchDto;
import com.bigsale.orm.dao.Repository;
import com.bigsale.orm.model.Seller;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Seller: hanmoi
 * Date: 29/09/12
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("sellerService")
public class SellerServiceImpl implements SellerService {
    @Autowired
    Repository sellerRepository;
    static final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);
    @Override
    public void addSeller(Seller seller)
    {
        sellerRepository.add(seller);
    }

    @Override
    public void updateSeller(Seller seller)
    {
        sellerRepository.update(seller);
    }

    @Override
    public void deleteSeller(Seller seller)
    {
        sellerRepository.delete(seller);
    }

    @Override
    public Seller getSellerById(String sellerId)
    {
        return (Seller) sellerRepository.findById(sellerId);
    }

    @Override
    public List<Seller> getAllSellers()
    {
        return sellerRepository.findAll();
    }

    @Override
    public boolean checkIdDuplication(String sellerId)
    {
        Seller seller = (Seller) sellerRepository.findById(sellerId);
        return seller != null;
    }

    @Override

    public List<Seller> findSellerBySearchCriteria(UserSearchDto sellerSearchDto){
        Session session = sellerRepository.getSessionFactory().openSession();
        session.beginTransaction();

        List<Seller> sellerList = (List<Seller>) session.createCriteria(Seller.class)
                .add(Restrictions.ilike("sellerId", sellerSearchDto.getUserId(), MatchMode.ANYWHERE))
                .addOrder(Order.asc("sellerId"))
                .list();

        logger.debug("Size: {}", sellerList.size());
        session.close();
        return sellerList;
    }

    @Override
    public void updateSellerInfoWithLogin(Seller seller)
    {
        seller.increaseLoginCount();
        seller.UpdateLevel();
        sellerRepository.update(seller);
    }
}
