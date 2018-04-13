package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import beans.EstanteBean;
import beans.CadastrarEstanteBean;
import entities.Armario;
import entities.Estante;

/**
 *
 * @author raylson
 */
public class EstanteDao implements DAO {
	public void salvar(Object object) {
        CadastrarEstanteBean estanteBean = (CadastrarEstanteBean) object;
        Estante estante = new Estante();
        estante.setNumero_estante(estanteBean.getNumero_estante());
        estante.setCoordenacao(estanteBean.getCoordenacao());
        
        Connection conn = new Connection();
        conn.getInstance().persist(estante);
        conn.getInstance().getTransaction().begin();
        conn.getInstance().getTransaction().commit();
        conn.close();
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Estante x WHERE x.id = :id";
        TypedQuery<Estante> consulta = conn.getInstance().createQuery(jpql, Estante.class);
        consulta.setParameter("id", id);
        return consulta.getSingleResult();
	}

	@Override
	public List<Estante> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Estante x";
        TypedQuery<Estante> consulta = conn.getInstance().createQuery(jpql, Estante.class);
        List<Estante> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}
	
	public Object buscarPorNumero(String NumeroEstante) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Estante x WHERE x.numero_estante = :NumeroEstante";
        try {
        	TypedQuery<Estante> consulta = conn.getInstance().createQuery(jpql, Estante.class);
            consulta.setParameter("NumeroEstante", NumeroEstante);
            Estante estante = consulta.getSingleResult();
            conn.close();
            return estante;
        }catch (NoResultException nre) {
        	return null;
		}
	}

	@Override
	public void atualizar(Object object) {
		Estante Estante = (Estante) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().merge(Estante);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Estante Estante = (Estante) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Estante.class, Estante.getId()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}
}
