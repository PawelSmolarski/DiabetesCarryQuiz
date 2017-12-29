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

/**
 * Class which provides secure filtering
 *
 * @author psmolarski
 * @version 1.0
 */
@WebFilter(filterName = "AuthFilter", urlPatterns =
{
    "*.xhtml"
})
public class Authorization implements Filter
{

    /**
     * Variable of session bean to control game
     */
    private QuizBean quizBean = new QuizBean();

    public Authorization()
    {
    }

    @Override
    public void destroy()
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
            
            //Receiving quizBean if session exists
            if (ses != null)
            {
                quizBean = (QuizBean) ses.getAttribute("quizBean");
            }

            /*Check if game takes place and player session exists or user 
              want to redirect into welcome page before game end */
            if ((ses != null && ses.getAttribute("player") != null) && (reqURI.contentEquals("/") || reqURI.contains("/public/index.xhtml")) && quizBean.getPresentTask() != null)
            {
                resp.sendRedirect(quizBean.getPresentTask().getType().getURL());
            }
            /* Check if user want to go into game resources but game not take place at the moment */
            else if (reqURI.contains("/game/") && (ses == null || ses.getAttribute("player") == null || quizBean.getPresentTask() == null))
            {
                resp.sendRedirect(reqt.getContextPath() + "/public/index.xhtml");
            }
            /* If user want to login or start game or redirect to public resources 
               let redirection arrive */
            else if (reqURI.contains("/login.xhtml")
                    || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.contains("/public/")
                    || reqURI.contains("javax.faces.resource")
                    || reqURI.contentEquals("/") || reqURI.contains("/game/"))
            {
                chain.doFilter(request, response);
            }
            /* In other case send to lign page */
            else
            {
                resp.sendRedirect(reqt.getContextPath() + "/secure/login.xhtml");
            }
        }
        catch (IOException | ServletException | IllegalStateException e)
        {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public QuizBean getQuizBean()
    {
        return quizBean;
    }

    public void setQuizBean(QuizBean quizBean)
    {
        this.quizBean = quizBean;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

}
