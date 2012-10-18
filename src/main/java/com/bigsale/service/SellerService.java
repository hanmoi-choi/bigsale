package com.bigsale.service;

import com.bigsale.controller.dto.UserSearchDto;
import com.bigsale.orm.model.Seller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Seller: hanmoi
 * Date: 29/09/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SellerService {
    void addSeller(Seller seller);

    void updateSeller(Seller seller);

    void deleteSeller(Seller seller);

    Seller getSellerById(String id);

    List<Seller> getAllSellers();

    boolean checkIdDuplication(String sellerId);

    boolean checkEmailDuplication(String email);

    List<Seller> findSellerBySearchCriteria(UserSearchDto sellerSearchDto);

    void updateSellerInfoWithLogin(Seller seller);
}
