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
		
		Query query = em.createQuery("SELECT e FROM Highscore e ORDER_BY points LIMIT 20");
		return (ArrayList<Highscore>) query.getResultList();
		
	}

	@Override
	public Highscore getScore(String username) {
		return em.find(Highscore.class, username);
		
	}

	
	
}
