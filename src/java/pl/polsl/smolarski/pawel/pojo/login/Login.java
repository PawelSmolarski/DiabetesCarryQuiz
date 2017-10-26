/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.login;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import pl.polsl.smolarski.pawel.dao.login.LoginDao;
import pl.polsl.smolarski.pawel.utils.SessionUtils;

/**
 * ORM entity class for Login
 * Provides validation checking methods
 * 
 * @author psmolarski
 */
@ManagedBean
@SessionScoped
@Entity
@Table(name = "users")
public class Login implements Serializable
{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="login")
    private String login;
    
    @Column(name="password")
    private String password;

    /**
     * 
     * @param id
     * @param login
     * @param password 
     */
    public Login(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Login() {
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
        /**
         *  Method validates if given data are in database
         */
    public void validateUsernamePassword() {
        boolean loggedIn = LoginDao.validate(login, password);
       if (loggedIn) {
               HttpSession session = SessionUtils.getSession();
               session.setAttribute("username", login);
               ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

           try
           {
               context.redirect("admin_page.xhtml");
               
           } catch (IOException ex) 
           {
               Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       } 
    }

}
