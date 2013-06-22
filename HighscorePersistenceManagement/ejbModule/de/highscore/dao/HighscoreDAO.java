package de.highscore.dao;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.highscore.entities.*;

@Stateless
public class HighscoreDAO implements HighscoreDAOLocal {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ArrayList<Highscore> getTopTwenty() {
		
		Query query = em.createQuery("SELECT e.username, e.credits FROM Highscore e ORDER_BY credits LIMIT 20");
		return (ArrayList<Highscore>) query.getResultList();

		
	}

	@Override
	public int getCredits(String username) {
		
		return em.find(Highscore.class, username).getCredits();

	}

	@Override
	public Highscore setHighscore(String username, Integer credits) {

		if(em.find(Highscore.class, username) != null){
			if(em.find(Highscore.class, username).getUsername() == username) {
				Highscore h = em.find(Highscore.class, username);
				h.setCredits(h.getCredits() + credits);
				em.persist(h);
			}
		}
		else {
			Highscore h = new Highscore(username);
			h.setCredits(credits);
			h.setUsername(username);
			em.persist(h);
		}
		
		return null;
	}
		
}

	
	

