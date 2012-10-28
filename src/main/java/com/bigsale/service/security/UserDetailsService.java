package com.bigsale.service.security;

import com.bigsale.orm.model.Admin;
import com.bigsale.orm.model.Level;
import com.bigsale.orm.model.Seller;
import com.bigsale.service.AdminService;
import com.bigsale.service.SellerService;
import com.bigsale.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Service("userDetailsService")
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService
{
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_SELLER = "ROLE_SELLER";
    public static final String ROLE_BUYER = "ROLE_BUYER";
    public static final String COOKIE_NAME_ADD = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    private ThreadLocal<User> currentUser = new ThreadLocal<User>();
    private ThreadLocal<String> currentPassword = new ThreadLocal<String>();

    private Map<String, String> usersToCookies;
    private Map<String, String> cookiesToUsers;
    private Map<String, Date> sessionValidity;


    private UserService userService;
    private SellerService sellerService;
    private AdminService adminService;
    private  Vector<GrantedAuthority> userAuthorities;
    private Level currentUserLevel;

    @Autowired
    public UserDetailsService(UserService userService,
                              SellerService sellerService,
                              AdminService adminService)
    {
        this.userService = userService;
        this.sellerService = sellerService;
        this.adminService = adminService;
        userAuthorities = new Vector<GrantedAuthority>();

        usersToCookies = new HashMap<String, String>();
        cookiesToUsers = new HashMap<String, String>();
        sessionValidity = new HashMap<String, Date>();
    }

    public String createSSOSession(String username)
    {
        logger.debug("Creating SSO session for " + username);
        String newCookieValue = COOKIE_NAME_ADD + username;
        String oldCookie = usersToCookies.get(username);
        removeSSOSession(oldCookie);
        cookiesToUsers.put(newCookieValue, username);
        Date now = new Date();
        Date sessionValidityTime = new Date(now.getTime() + 30 * 1000);
        sessionValidity.put(newCookieValue, sessionValidityTime);
        return newCookieValue;
    }

    public void removeSSOSession(String cookie)
    {
        if (cookie != null)
        {
            logger.debug("Removing SSO session: " + cookie);
            String username = cookiesToUsers.get(cookie);
            if (username != null)
                usersToCookies.remove(username);
            cookiesToUsers.remove(cookie);
            sessionValidity.remove(cookie);
        }
    }

    public UserDetails loadUserByCookie(String cookie) throws UsernameNotFoundException, DataAccessException
    {
        logger.debug("Loading user by cookie: " + cookie);
        Date sessionValidUntil = sessionValidity.get(cookie);
        Date now = new Date();
        logger.debug("Session valid until: " + sessionValidUntil + " now: " + now);
        if (sessionValidUntil == null || now.after(sessionValidUntil))
        {
            logger.debug("Session no longer valid.");
            cookie = null;
        }

        return loadUserByUsername(cookiesToUsers.get(cookie));
    }

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException, DataAccessException
    {
        userAuthorities.clear();

        if(userId == null) throw new UsernameNotFoundException("Invalid User ID");

        if (userId.equals("admin")){
            currentUserLevel = Level.PLATINUM;
            return loginAsAdmin();
        }

        //User
        com.bigsale.orm.model.User user = userService.getUserById(userId);
        if(user != null) {
            currentUserLevel = user.getUserLevel();
            return loginAsUser(user);
        }

        //Seller
        Seller seller = sellerService.getSellerById(userId);
        if(seller != null){
            currentUserLevel = seller.getSellerLevel();
            return loginAsSeller(seller);
        }

        throw new UsernameNotFoundException("User Id " + userId + " not found!");
    }

    public String getCurrentUserLevel(){
        return currentUserLevel.toString();
    }

    public void setCurrentUserLevel(Level level){
        currentUserLevel = level;
    }

    public User getCurrentUser()
    {
        return currentUser.get();
    }

    public void setCurrentUser(User user)
    {
        currentUser.set(user);
    }

    public String getPassword()
    {
        return currentPassword.get();
    }

    public void setPassword(String password)
    {
        currentPassword.set(password);
    }

    public void setUserService(UserService userService) {this.userService = userService;}

    public UserService getUserService() { return userService; }

    private UserDetails loginAsSeller(Seller seller)
    {
        createSSOSession(seller.getSellerId());
        userAuthorities.add(new GrantedAuthorityImpl(ROLE_SELLER));
        return getUserAuthorised(seller.getSellerId(), seller.getPassword());
    }

    private UserDetails loginAsUser(com.bigsale.orm.model.User user)
    {
        userService.updateUserInfoWithLogin(user);
        createSSOSession(user.getUserId());
        userAuthorities.add(new GrantedAuthorityImpl(ROLE_BUYER));

        return getUserAuthorised(user.getUserId(), user.getPassword());
    }

    private UserDetails loginAsAdmin()
    {
        userAuthorities.add(new GrantedAuthorityImpl(ROLE_ADMIN));
        Admin admin = adminService.getAdminById("admin");
        createSSOSession(admin.getAdminId());
        String password = admin.getPassword();
        return getUserAuthorised("admin", password);
    }
    private User getUserAuthorised(String id, String password)
    {
        User userAuthorised = new User(id, password,
                true, true,
                true, true, userAuthorities);
        currentUser.set(userAuthorised);
        return userAuthorised;
    }
}
