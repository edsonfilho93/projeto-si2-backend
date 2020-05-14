package com.projetoSI.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.projetoSI.model.Livro;

public class ApiLivroRepository {

	private EntityManagerFactory entityManagerFactory;

	public ApiLivroRepository() {
		entityManagerFactory = Persistence.createEntityManagerFactory("apiLivrosConfig");
	}
	
	public void add(Livro livro) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			
			entityManager.persist(livro);
			
			transaction.commit();
			
		} catch(Throwable e){
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}
	
	public List<Livro> getAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT l FROM Livro l");
		
		return (List<Livro>) query.getResultList();
	}
	
	public Livro getById(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String sql = "SELECT l FROM Livro l WHERE id = " + String.valueOf(id);
		
		Query query = entityManager.createQuery(sql);
		
		return (Livro) query.getResultList().get(0);
	}

}
