package com.bigsale.service;

import com.bigsale.orm.dao.Repository;
import com.bigsale.orm.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 30/09/12
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService{
    @Autowired
    Repository addressRepository;

    @Override
    public void addAddress(Address address) {
        addressRepository.add(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressRepository.update(address);
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public Address getAddressById(Integer id) {
        return (Address) addressRepository.findById(id);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }
}
