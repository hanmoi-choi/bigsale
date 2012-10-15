package com.bigsale.service.security;

import com.bigsale.util.CipherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter
{
    private UserDetailsService userDetailsService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String cookieName;

    @Override
    protected String obtainPassword(HttpServletRequest request)
    {
        String password = super.obtainPassword(request);
        String passwordHash = CipherUtil.hashPassword(password);
        logger.debug("passwordHash: {}", passwordHash);
        userDetailsService.setPassword(passwordHash);
        return password;
    }

    public String getCookieName()
    {
        return cookieName;
    }

    public void setCookieName(String cookieName)
    {
        this.cookieName = cookieName;
    }

    @Override
    protected void successfulAuthentication(
                                HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authResult) throws ServletException, IOException
    {
        logger.debug("successful authentication");
        User user = userDetailsService.getCurrentUser();

        String password = userDetailsService.getPassword();
        String currentCookie = getCookie(request);
        Cookie sso = createServerSSOSession(user, password, currentCookie);

        response.addCookie(sso);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("userId", user.getUsername());
        logger.warn("userId Set as {}", user.getUsername());
        request.getSession().setAttribute(cookieName, currentCookie);
        super.successfulAuthentication(request, response, authResult);
    }

    private String getCookie(HttpServletRequest request)
    {
        // Check whether the browser already has a SSO cookie
        String currentCookie = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++)
                if (cookieName.equals(cookies[i].getName()))
                {
                    currentCookie = cookies[i].getValue();
                    break;
                }
        return currentCookie;
    }

    private Cookie createServerSSOSession(User user, String password, String currentCookie)
    {
        // Cookie value for authenticated session
        logger.debug("Creating a server SSO session: " + currentCookie);
        String newCookieValue = userDetailsService.createSSOSession(user.getUsername());
        String cookieToUse = currentCookie;
        if (!currentCookie.equals(newCookieValue))
            cookieToUse = newCookieValue;
        logger.debug("Cookie to use: " + cookieToUse);
        Cookie cookie = new Cookie(cookieName, cookieToUse);
        cookie.setMaxAge(10 * 60);
        cookie.setPath("/");
        return cookie;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws ServletException, IOException
    {
        logger.debug("Unsuccessful authentication");
        userDetailsService.setCurrentUser(null);
        userDetailsService.setPassword(null);
        super.unsuccessfulAuthentication(request, response, failed);
    }

    public UserDetailsService getUserDetailsService()
    {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }
}
