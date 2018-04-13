package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import beans.ChaveBean;
import entities.Chave;

/**
 *
 * @author raylson
 */
public class ChaveDao implements DAO {
	
	public void salvar(Object object) {
        
        Chave chave = (Chave) object;
        Connection conn = new Connection();
        conn.getInstance().persist(chave);
        conn.getInstance().getTransaction().begin();
        conn.getInstance().getTransaction().commit();
        conn.close();
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Chave x WHERE x.id = :id";
        TypedQuery<Chave> consulta = conn.getInstance().createQuery(jpql, Chave.class);
        consulta.setParameter("id", id);
        return consulta.getSingleResult();
	}

	@Override
	public List<Chave> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Chave x";
        TypedQuery<Chave> consulta = conn.getInstance().createQuery(jpql, Chave.class);
        List<Chave> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}

	@Override
	public void atualizar(Object object) {
		Chave Chave = (Chave) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().merge(Chave);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Chave Chave = (Chave) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Chave.class, Chave.getId()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

}
