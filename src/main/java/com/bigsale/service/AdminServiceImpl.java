package com.bigsale.service;

import com.bigsale.orm.dao.Repository;
import com.bigsale.orm.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Admin: hanmoi
 * Date: 29/09/12
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    Repository adminRepository;

    @Override
    public void addAdmin(Admin admin)
    {
        adminRepository.add(admin);
    }

    @Override
    public void updateAdmin(Admin admin)
    {
        adminRepository.update(admin);
    }

    @Override
    public void deleteAdmin(Admin admin)
    {
        adminRepository.delete(admin);
    }

    @Override
    public Admin getAdminById(String adminId)
    {
        return (Admin) adminRepository.findById(adminId);
    }

    @Override
    public List<Admin> getAllAdmins()
    {
        return adminRepository.findAll();
    }

    @Override
    public boolean checkIdDuplication(String adminId)
    {
        Admin admin = (Admin) adminRepository.findById(adminId);

        return admin != null;
    }
}
