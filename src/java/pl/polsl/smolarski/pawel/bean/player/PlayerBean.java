/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import pl.polsl.smolarski.pawel.dao.player.PlayerDao;
import pl.polsl.smolarski.pawel.pojo.player.Player;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 * Class bean for player
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class PlayerBean implements Serializable
{

    /**
     * Variable for player dao
     */
    private static final PlayerDao PLAYER_DAO = new PlayerDao();

    /**
     * Method to save player in table
     *
     * @param player to save
     */
    public void save(Player player)
    {
        try
        {
            PLAYER_DAO.addPlayer(player);
            addMessage("Success!", "Task added correctly.");
        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Method to get all players
     *
     * @return List of all players
     */
    public List<Player> getAllRecords()
    {
        List<Player> tasks = new ArrayList<>();
        try
        {
            tasks = PLAYER_DAO.retrievePlayer();
        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return tasks;
    }
}
