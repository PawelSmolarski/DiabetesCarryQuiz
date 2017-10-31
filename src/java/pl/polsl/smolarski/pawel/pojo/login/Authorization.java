/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.login;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.player.PlayerDao;

/**
 * Class which provides secure filtering
 *
 * @author psmolarski
 */
@WebFilter(filterName = "AuthFilter", urlPatterns =
{
    "*.xhtml"
})
public class Authorization implements Filter
{

    public Authorization()
    {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {

        try
        {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            
//            if ((ses != null && ses.getAttribute("player") != null) && reqURI.contentEquals("/"))
//            {
//                // TODO przenoszeie do gy jeżeli juz trwa rozgrywka, a ktos wlazł do index 
//                QuizBean.getView(QuizBean.getPresentTask().getType());
//            }
//            else 
                if (reqURI.contains("/login.xhtml")
                    || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.contains("/public/")
                    || reqURI.contains("javax.faces.resource")
                    || reqURI.contentEquals("/") || reqURI.contains("/game/"))
            {
                chain.doFilter(request, response);
            }
            else if (reqURI.contains("/game/") && (ses == null || ses.getAttribute("player") == null))
            {
                resp.sendRedirect(reqt.getContextPath() + "/public/index.xhtml");
            }
            else
            {
                resp.sendRedirect(reqt.getContextPath() + "/secure/login.xhtml");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void destroy()
    {

    }

}
