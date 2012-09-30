package com.bigsale.service;

import com.bigsale.orm.model.Address;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Address: hanmoi
 * Date: 30/09/12
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AddressService {
    void addAddress(Address user);
    void updateAddress(Address user);
    void deleteAddress(Address user);
    Address getAddressById(Integer id);
    List<Address> getAllAddress();
}
