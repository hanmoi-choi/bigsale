package com.bigsale.service;

import com.bigsale.orm.model.Admin;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Admin: hanmoi
 * Date: 29/09/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AdminService {
    void addAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(Admin admin);

    Admin getAdminById(String id);

    List<Admin> getAllAdmins();

    boolean checkIdDuplication(String adminId);

}
