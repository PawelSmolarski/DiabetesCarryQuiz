/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.dao.diagramtask.DiagramTaskDao;
import pl.polsl.smolarski.pawel.pojo.player.Player;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 *
 * @author g50-70
 */
public class PlayerDao
{

    public void addPlayer(Player player)
    {

        try
        {
            addPlayerTransaction(player);
        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void addPlayerTransaction(Player player)
    {
        Transaction trans;
        Session session = SessionUtils.getSessionFactory().openSession();
        trans = session.beginTransaction();
        session.save(player);
        trans.commit();
        addMessage("Success!", "Task added correctly.");
    }

    public List<Player> retrievePlayer()
    {

        List players = new ArrayList();

        try
        {
            players = retrievePlayerTransaction();
        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return players;
    }

    private List<Player> retrievePlayerTransaction()
    {
        Session session = SessionUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select p from Player p ORDER BY p.points desc").setMaxResults(10);
        List players = query.list();
        session.getTransaction().commit();
        return players;
    }

}
