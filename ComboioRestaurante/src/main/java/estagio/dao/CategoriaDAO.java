/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Categoria;
import estagio.view.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pereira
 */
public class CategoriaDAO {
    
    
    @PersistenceContext
    EntityManager em;
    
    public CategoriaDAO() 
    {
        em = new JPAUtil().getEntityManager();
    }

    public void inserir(Categoria categoria) 
    {
        if(!em.isOpen())
            em = new JPAUtil().getEntityManager();
        else
            em.getTransaction().begin();
        try 
        {
            em.persist(categoria);
            em.getTransaction().commit();            
        } 
        catch (Exception e) 
        {
            em.getTransaction().rollback();
        }
        finally
        {
            em.close();
        }
        
         
    }
    
    public void alterar(Categoria categoria) 
    {
        if(!em.isOpen())
            em = new JPAUtil().getEntityManager();
        else
            em.getTransaction().begin();
        try 
        {
            Categoria aux = em.find(Categoria.class,categoria.getId());
            aux.setNome(categoria.getNome());
            em.merge(aux);
            em.getTransaction().commit();            
        } 
        catch (Exception e) 
        {
            em.getTransaction().rollback();
        }
        finally
        {
            em.close();
        }
    }

    public boolean Deletar(Categoria categoria) {
        boolean deletado = true;
        if(!em.isOpen())
            em = new JPAUtil().getEntityManager();
        else
            em.getTransaction().begin();
        try 
        {
            Categoria aux = em.find(Categoria.class,categoria.getId());
            em.remove(aux);
            em.getTransaction().commit();            
        } 
        catch (Exception e) 
        {
            em.getTransaction().rollback();
            deletado = false;
        }
        finally
        {
            em.close();
        }
        return deletado;
    }

    public Categoria listar(Long busca) {
        String jpql;
        Categoria categoria=null;
        List<Categoria> retorno = new ArrayList<Categoria>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            jpql = "select m from Categoria m where m.id = :pId";  
            Query query = em.createQuery(jpql);
            query.setParameter("pId", busca);  
            retorno = query.getResultList();            
            categoria = retorno.get(0);
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
        return categoria;
    }    
    
    public List<Categoria> listar(String busca) 
    {
        String jpql="";
        List<Categoria> retorno = new ArrayList<Categoria>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
                
        if(busca.compareTo("") == 0)
        jpql = "select m from Categoria m";
        else
        jpql = "select m from Categoria m where m.nome like :pNome";  
        Query query = em.createQuery(jpql);
            if (busca.compareTo("")!=0) 
                query.setParameter("pNome", busca+"%");  
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
