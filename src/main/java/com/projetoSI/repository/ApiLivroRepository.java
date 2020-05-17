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

	private static ApiLivroRepository instance;

	public static ApiLivroRepository instance() {
		if (instance == null)
			instance = new ApiLivroRepository();

		return instance;
	}

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

		} catch (Throwable e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Livro> getAll() throws Exception {
		try {
			
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Query query = entityManager.createQuery("SELECT l FROM Livro l");

			return (List<Livro>) query.getResultList();
			
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public List<?> getById(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String sql = "SELECT l FROM Livro l WHERE id = " + String.valueOf(id);

		try {
			
			return entityManager.createQuery(sql).getResultList();

		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	public void delete(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		Livro livro = entityManager.find(Livro.class, id);

		try {

			transaction.begin();

			entityManager.remove(livro);

			transaction.commit();

		} catch (Throwable e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}

	public void edit(int id, Livro livro) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		Livro livroAux = entityManager.find(Livro.class, id);
		
		livroAux.setTitulo(livro.getTitulo());
		livroAux.setCodEditora(livro.getCodEditora());
		livroAux.setQtdPaginas(livro.getQtdPaginas());

		try {

			transaction.begin();

			entityManager.merge(livroAux);

			transaction.commit();

		} catch (Throwable e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}
}
