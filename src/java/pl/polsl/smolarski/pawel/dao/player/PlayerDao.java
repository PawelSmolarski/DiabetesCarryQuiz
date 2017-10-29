/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.player;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.pojo.player.Player;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;
/**
 *
 * @author g50-70
 */
public class PlayerDao {
    
      public void addPlayer(Player player)
    {
        Transaction trans;
        Session session=SessionUtils.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.save(player);
            trans.commit();
            addMessage("Success!", "Task added correctly.");

        } 
        catch (Exception e) 
        {
            addMessage("Error!", "Please try again.");
            e.printStackTrace();
        }
    }
      
        public List<Player> retrievePlayer()
        {

            List players=new ArrayList();
            Session session=SessionUtils.getSessionFactory().openSession();
            try
            {
                session.beginTransaction();
                Query query=session.createQuery("select p from Player p ORDER BY p.points desc").setMaxResults(10);
                players=query.list();
                session.getTransaction().commit();

            }
            catch(Exception e)
            {
                addMessage("Error!", "Please try again.");
                e.printStackTrace();
            }
            return players;
        }
    
}
