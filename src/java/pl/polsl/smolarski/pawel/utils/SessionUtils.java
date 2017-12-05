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

    /**
     * Variable for creating session
     */
    private static final SessionFactory SESSION_FACTORY;

    static
    {
        try
        {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SESSION_FACTORY = configuration.buildSessionFactory(ssrb.build());

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
    public static SessionFactory getSESSION_FACTORY()
    {
        return SESSION_FACTORY;
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

}