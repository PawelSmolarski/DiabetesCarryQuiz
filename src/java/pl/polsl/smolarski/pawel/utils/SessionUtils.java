/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Utility class. Provides all methods connected with working on session.
 *
 * @author psmolarski
 */
public class SessionUtils
{

    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(ssrb.build());

        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Getter for SessionFactory component
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /**
     * Add FacesMessage to current instance
     *
     * @param summary Header of message
     * @param detail Details of message
     */
    public static void addMessage(String summary, String detail)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     * @return HttpSession of current session
     */
    public static HttpSession getSession()
    {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static boolean areEqualLists(List<String> one, List<String> two)
    {

        if ( one.size() != two.size())
        {
            return false;
        }

        one = new ArrayList<String>(one);
        two = new ArrayList<String>(two);

        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }

}
