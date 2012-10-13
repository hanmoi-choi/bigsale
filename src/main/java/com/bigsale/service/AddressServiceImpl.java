package com.bigsale.service;

import com.bigsale.controller.dto.AddressSearchDto;
import com.bigsale.orm.dao.Repository;
import com.bigsale.orm.model.Address;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 30/09/12
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    Repository addressRepository;

    @Override
    @Transactional
    public void addAddress(Address address)
    {
        Session session = addressRepository.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(address);

        session.close();
    }

    @Override
    public void updateAddress(Address address)
    {
        addressRepository.update(address);
    }

    @Override
    public void deleteAddress(Address address)
    {
        addressRepository.delete(address);
    }

    @Override
    public Address getAddressById(Integer id)
    {
        return (Address) addressRepository.findById(id);
    }

    @Override
    public List<Address> getAllAddress()
    {
        return addressRepository.findAll();
    }

    @Override
    public Address findAddressBySearchCriteria(AddressSearchDto addressSearchDto){
        Session session = addressRepository.getSessionFactory().openSession();
        session.beginTransaction();

        List<Address> addressList = (List<Address>) session.createCriteria(Address.class)
                .add(Restrictions.ilike("street", addressSearchDto.getStreet(), MatchMode.EXACT))
                .add(Restrictions.ilike("city", addressSearchDto.getCity(), MatchMode.EXACT))
                .add(Restrictions.ilike("state", addressSearchDto.getState(), MatchMode.EXACT))
                .add(Restrictions.ilike("zipcode", addressSearchDto.getZipcode(), MatchMode.EXACT))
                .list();

        session.close();

        if(addressList.isEmpty()) return null;

        return addressList.get(0);
    }
}
