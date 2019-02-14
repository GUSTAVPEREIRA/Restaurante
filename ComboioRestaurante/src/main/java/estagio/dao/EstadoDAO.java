/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Estado;
import estagio.view.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Pereira
 */

public class EstadoDAO {
    
    @PersistenceContext
    EntityManager em;
    
    public EstadoDAO() 
    {
        em = new JPAUtil().getEntityManager();
    }
    
    @Transactional
    public void inserir(Estado estado) 
    {
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            em.persist(estado);
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
    }
    
    public void alterar(Estado estado) 
    {
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            Estado aux = em.find(Estado.class,estado.getId());
            aux.setNome(estado.getNome());
            aux.setUf(estado.getUf());
            em.merge(aux);
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
    }

    public boolean Deletar(Estado estado) {
        boolean deletado = false;

        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            Estado aux = em.find(Estado.class,estado.getId());
            em.remove(aux);            
            deletado = true;
            em.getTransaction().commit();
        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
            return deletado;
        }
    }
    public Estado busca(String busca) {
        Estado estado = new Estado();

        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            estado = em.find(Estado.class,busca);
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
        return estado;
    }
    
    public Estado listar(Long busca) {
        String jpql;
        Estado estado=null;
        List<Estado> retorno = new ArrayList<Estado>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            jpql = "select m from Estado m where m.id = :pId";  
            Query query = em.createQuery(jpql);
            query.setParameter("pId", busca);  
            retorno = query.getResultList();            
            estado = retorno.get(0);
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
        return estado;
    }
    
    public List<Estado> listar(String busca) {
        String jpql;
        List<Estado> retorno = new ArrayList<Estado>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            if(busca.compareTo("") == 0)
                jpql = "select m from Estado m order by m.nome";
            else
                jpql = "select m from Estado m where m.nome like :pNome order by m.nome";  

            Query query = em.createQuery(jpql);
            if (busca.compareTo("")!=0) 
                query.setParameter("pNome", "%"+busca+"%");  
            retorno = query.getResultList();
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
        return retorno;
    }
        
}
