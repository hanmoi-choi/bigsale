package com.bigsale.spikes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 30/08/12
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleGetServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");

        resp.getWriter().write("<HTML><BODY>");
        resp.getWriter().write("Hello " + name);
        resp.getWriter().write("</BODY></HTML>");
    }
}
