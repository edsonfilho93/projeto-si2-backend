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
		try {

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			String sql = "SELECT l FROM Livro l WHERE id = " + String.valueOf(id);

			return entityManager.createQuery(sql).getResultList();

		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		try {

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			String sql = "DELETE FROM Livro WHERE id = " + String.valueOf(id);

			entityManager.createQuery(sql).executeUpdate();

		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public void edit(int id) {

		try {

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Livro livro = (Livro) getById(id).get(0);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE livro SET");
			sql.append("titulo = " + livro.getTitulo());
			sql.append("qtdPaginas = " + livro.getQtdPaginas());
			sql.append("codEditora = " + String.valueOf(livro.getCodEditora()));
			sql.append("WHERE id = " + String.valueOf(id));

			entityManager.createQuery(sql.toString()).executeUpdate();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
}
