/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.login;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import pl.polsl.smolarski.pawel.pojo.login.Login;
import pl.polsl.smolarski.pawel.utils.SessionUtils;

/**
 * Class which provides validating methods for Login
 *
 * @author psmolarski
 */
public class LoginDao
{

    /**
     * Method to check if given values are in user table
     *
     * @param login
     * @param password
     * @return are login and password correct
     *
     * @throws HibernateException
     */
    public static boolean validate(String login, String password) throws HibernateException
    {

        List<Login> users = new ArrayList();

        Session session = SessionUtils.getSESSION_FACTORY().openSession();

        Query query = session.createQuery("select u from Login u where login= :login and password= :password");
        query.setString("login", login);
        query.setString("password", password);

        users = query.list();
        session.close();
        return !users.isEmpty();

    }

}
