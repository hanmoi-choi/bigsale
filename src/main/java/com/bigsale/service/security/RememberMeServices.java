package com.bigsale.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Spring Security service that provides user details based on a UMS single sign-on cookie.
 *
 * If a user who is not logged in tries to access a secure resource, Spring Security will use this service in an attempt
 * to authenticate the user before resorting to a login form.
 */
public class RememberMeServices extends AbstractRememberMeServices
{
    private Logger logger = LoggerFactory.getLogger(RememberMeServices.class);

    /**
     * Attempt to authenticate a user using a UMS single sign-on cookie.
     */
    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
            HttpServletResponse response) throws RememberMeAuthenticationException, UsernameNotFoundException
    {
        logger.debug("RememberMeServices.processAutoLoginCookie");
        UserDetailsService userDetailsService = (UserDetailsService) getUserDetailsService();
        UserDetails user = null;
        String cookieValue = getCookieValue(request, getCookieName());
        if (cookieValue != null)
            user = userDetailsService.loadUserByCookie(cookieValue);
        if (user != null)
        {
            request.getSession().setAttribute(getCookieName(), cookieValue);
            request.getSession().setAttribute("user", userDetailsService.getCurrentUser());
            return user;
        }
        else
            throw new UsernameNotFoundException("Couldn't load user details via cookie: " + getCookieName());
    }

    /**
     * On logout, clear the single sign-on cookie (always attached to "/").
     */
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        logger.debug("RememberMeServices.logout");
        String cookieName = getCookieName();
        String sessionSso = (String) request.getSession().getAttribute(cookieName);
        if (sessionSso != null)
        {
            UserDetailsService userDetailsService = (UserDetailsService) getUserDetailsService();
            userDetailsService.removeSSOSession(sessionSso);
        }
    }

    protected String getCookieValue(HttpServletRequest request, String cookieName)
    {
        logger.debug("RememberMeServices.getCookieValue - cookieName: " + cookieName);
        String cookieValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(cookieName))
                {
                    cookieValue = cookie.getValue();
                    break;
                }
        return cookieValue;
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication successfulAuthentication)
    {
        logger.debug("RememberMeServices.onLoginSuccess");
    }
}
