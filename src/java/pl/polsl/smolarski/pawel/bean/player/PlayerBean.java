/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.player;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pl.polsl.smolarski.pawel.dao.player.PlayerDao;
import pl.polsl.smolarski.pawel.pojo.player.Player;

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
        PLAYER_DAO.addPlayer(player);
    }
    
    /**
     * Method to get all players
     * 
     * @return List of all players
     */
    public List<Player> getAllRecords()
    {
        List<Player> tasks = PLAYER_DAO.retrievePlayer();
        return tasks;
    }
}
