/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.login;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;
import pl.polsl.smolarski.pawel.pojo.login.Login;

/**
 * Class which provides validating methods for Login
 *
 * @author psmolarski
 */
public class LoginDao
{

    public static boolean validate(String login, String password)
    {

        List<Login> users = new ArrayList();

        Transaction trans;
        Session session = SessionUtils.getSessionFactory().openSession();
        try
        {
            trans = session.beginTransaction();
            Query query = session.createQuery("select u from Login u where login= :login and password= :password");
            query.setString("login", login);
            query.setString("password", password);

            users = query.list();
            trans.commit();
            if (users.isEmpty())
            {
                throw new Exception("No data for this id");
            }
        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;

    }

}
