/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import estagio.model.Cidade;
import estagio.model.Estado;
import estagio.view.util.JPAUtil;

/**
 *
 * @author Pereira
 */
public class CidadeDAO {

	@PersistenceContext
	EntityManager em;

	public CidadeDAO() {
		em = new JPAUtil().getEntityManager();
	}

	@Transactional
	public void inserir(Cidade cidade) {
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			em.persist(cidade);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void alterar(Cidade cidade) {
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			Cidade aux = em.find(Cidade.class, cidade.getId());
			aux.setNome(cidade.getNome());
			aux.setEstado(cidade.getEstado());
			em.merge(aux);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public boolean Deletar(Cidade cidade) {
		boolean deletado = false;

		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			Cidade aux = em.find(Cidade.class, cidade.getId());
			em.remove(aux);
			deletado = true;
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();			
		}
		return deletado;
	}

	public Cidade busca(String busca) {
		Cidade cidade = new Cidade();

		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			cidade = em.find(Cidade.class, busca);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return cidade;
	}

	public Cidade listar(Long busca) {
		String jpql = "";
		Cidade cidade = null;
		List<Cidade> retorno = new ArrayList<Cidade>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			jpql = "select m from Cidade m where m.id = :pId";
			TypedQuery<Cidade> query = em.createQuery(jpql, Cidade.class);
			query.setParameter("pId", busca);
			retorno = query.getResultList();
			cidade = retorno.get(0);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return cidade;
	}

	public List<Cidade> listCidadesPEstado(Estado busca) {
		String jpql = "";
		List<Cidade> retorno = new ArrayList<Cidade>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			jpql = "select r from Cidade r where r.estado.id=:pEstado order by r.nome";
			TypedQuery<Cidade> query = em.createQuery(jpql, Cidade.class);
			if (busca != null)
				query.setParameter("pEstado", busca.getId());
			retorno = query.getResultList();
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return retorno;
	}

	public List<Cidade> listar(String busca) {
		String jpql = "";
		List<Cidade> retorno = new ArrayList<Cidade>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			if (busca.compareTo("") == 0)
				jpql = "select m from Cidade m";
			else
				jpql = "select m from Cidade m where m.nome like :pBusca " + "OR m.estado.nome like :pBusca";

			TypedQuery<Cidade> query = em.createQuery(jpql, Cidade.class);
			if (busca.compareTo("") != 0)
				query.setParameter("pBusca", "%" + busca + "%");
			retorno = query.getResultList();
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return retorno;
	}
}
