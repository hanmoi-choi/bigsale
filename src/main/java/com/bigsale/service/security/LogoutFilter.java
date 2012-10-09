package com.bigsale.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutFilter extends org.springframework.security.web.authentication.logout.LogoutFilter
{
    private Logger logger = LoggerFactory.getLogger(LogoutFilter.class);

    protected String cookieName;

    public LogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers)
    {
        super(logoutSuccessUrl, handlers);
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
    protected boolean requiresLogout(HttpServletRequest request, HttpServletResponse response)
    {
        logger.debug("LogoutFilter.requiresLogout");
        // Normal logout processing (i.e. detect logout URL)
        if (super.requiresLogout(request, response))
            return true;
        // If SSO cookie is stale, clear session contents
        String cookieName = getCookieName();
        HttpSession session = request.getSession();
        String sessionSso = (String) request.getSession().getAttribute(cookieName);
        if (sessionSso != null)
        {
            String browserSso = getCookieValue(request, cookieName);
            if (!sessionSso.equals(browserSso))
            {
                logger.debug("Invalidating stale session: " + sessionSso);
                session.invalidate();
            }
        }
        return false;
    }

    protected String getCookieValue(HttpServletRequest request, String cookieName)
    {
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
}
