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
import pl.polsl.smolarski.pawel.dao.login.LoginDao;
import pl.polsl.smolarski.pawel.pojo.login.Login;
import pl.polsl.smolarski.pawel.utils.SessionUtils;

/**
 *
 * @author g50-70
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable
{

    private String login;
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

    public void validateUsernamePassword()
    {
        boolean loggedIn = LoginDao.validate(login, password);
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

    private ExternalContext createSession()
    {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("username", login);
        return FacesContext.getCurrentInstance().getExternalContext();

    }
}
