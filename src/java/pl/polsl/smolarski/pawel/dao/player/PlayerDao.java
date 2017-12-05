/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.player;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.pojo.player.Player;
import pl.polsl.smolarski.pawel.utils.SessionUtils;

/**
 * Class which provides CRUD methods for ABCDTask
 *
 * @author psmolarski
 * @version 1.0
 */
public class PlayerDao 
{

    /**
     * Method to add player to table
     *
     * @param player to add
     *
     * @throws HibernateException
     */
    public void addPlayer(Player player) throws HibernateException
    {
        addPlayerTransaction(player);
    }

    private void addPlayerTransaction(Player player)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        session.save(player);
        trans.commit();
        session.close();
    }

    /**
     * Method to get players from table
     *
     * @return List of get players
     *
     * @throws HibernateException
     */
    public List<Player> retrievePlayer() throws HibernateException
    {
        List players = new ArrayList();
        players = retrievePlayerTransaction();
        return players;
    }

    private List<Player> retrievePlayerTransaction()
    {
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        Query query = session.createQuery("select p from Player p ORDER BY p.points desc").setMaxResults(10);
        List players = new ArrayList();
        players = query.list();
        session.close();
        return players;
    }

}
