/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.login;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import pl.polsl.smolarski.pawel.dao.login.LoginDao;
import pl.polsl.smolarski.pawel.pojo.login.Login;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.ViewUtils.addMessage;

/**
 * Class bean for logining
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable
{

    /**
     * Login of user
     */
    private String login;

    /**
     * Password of user
     */
    private String password;

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Method which validates if provided login data are correct
     */
    public void validateUsernamePassword()
    {
        boolean loggedIn = false;
        try
        {
            loggedIn = LoginDao.validate(login, password);
        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, e);
        }
        if (loggedIn)
        {
            ExternalContext context = createSession();
            try
            {
                context.redirect("admin_page.xhtml");

            }
            catch (IOException ex)
            {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Create session for user
     *
     * @return context of session
     */
    private ExternalContext createSession()
    {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("username", login);
        return FacesContext.getCurrentInstance().getExternalContext();

    }
}
