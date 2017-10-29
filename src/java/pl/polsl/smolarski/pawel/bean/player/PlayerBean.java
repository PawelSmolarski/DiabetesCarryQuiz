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
 *
 * @author g50-70
 */
@ManagedBean
@ViewScoped
public class PlayerBean implements Serializable {
    
        private static final PlayerDao playerDao = new PlayerDao();

    
     public void save(Player player)
    {
        playerDao.addPlayer(player);
    }
     
      public List<Player> getAllRecords()
    {
        List<Player> tasks=playerDao.retrievePlayer();
        return tasks;
    }
}
