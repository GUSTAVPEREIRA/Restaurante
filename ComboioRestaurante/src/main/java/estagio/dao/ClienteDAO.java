package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estagio.model.Cliente;
import estagio.persistence.JPAUtil;

public class ClienteDAO extends GenericDAO<Cliente>{
// Aqui foram implementados o crud isso é o generic dao desenvolvido pelo jobs


	public ClienteDAO() {
		super(Cliente.class);
	}

	@Override
	public Long getID(Cliente obj) {
		return obj.getId();
	}
	
    public List<Cliente> listar(String busca,String tipo) {
        String jpql;

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
        List<Cliente> retorno = new ArrayList<Cliente>();
        try
        {
            	jpql = "select m from Cliente m ";
            	if (tipo.equals("FISICA")==true) {
					jpql = jpql+"INNER JOIN ClientePF pf ON pf.id = m.id";
				}
            	else
            		jpql = jpql+"INNER JOIN ClientePJ pj ON pj.id = m.id";
                        
            if(busca.equals("")!= true)
                jpql =jpql+ " where m.nome like :pBusca "
                        + "OR m.cidade.nome like :pBusca";  

            TypedQuery<Cliente> query = em.createQuery(jpql,Cliente.class);
            if (busca.compareTo("")!=0) 
                query.setParameter("pBusca", "%"+busca+"%");  
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
