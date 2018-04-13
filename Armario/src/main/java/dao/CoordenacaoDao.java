package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import beans.CoordenacaoBean;
import entities.Aluno;
import entities.Coordenacao;

/**
 *
 * @author raylson
 */
public class CoordenacaoDao implements DAO {
	public void salvar(Object object) {
        CoordenacaoBean coordenacaoBean = (CoordenacaoBean) object;
        Coordenacao coordenacao = new Coordenacao();
        coordenacao.setNome(coordenacaoBean.getNome());
        
        Connection conn = new Connection();
        conn.getInstance().persist(coordenacao);
        conn.getInstance().getTransaction().begin();
        conn.getInstance().getTransaction().commit();
        conn.close();
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Coordenacao x WHERE x.id = :id";
        TypedQuery<Coordenacao> consulta = conn.getInstance().createQuery(jpql, Coordenacao.class);
        consulta.setParameter("id", id);
        return consulta.getSingleResult();
	}
	
	public Object buscarPorNome(String nome) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Coordenacao x WHERE x.nome = :nome";
        try {
	        TypedQuery<Coordenacao> consulta = conn.getInstance().createQuery(jpql, Coordenacao.class);
	        consulta.setParameter("nome", nome);
	        return consulta.getSingleResult();
        }catch (NoResultException nre) {
        	System.out.println("Desculpe. Não existe coordenação com o nome: " + nome);
        	return null;
		}        
	}

	@Override
	public List<Coordenacao> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Coordenacao x";
        TypedQuery<Coordenacao> consulta = conn.getInstance().createQuery(jpql, Coordenacao.class);
        List<Coordenacao> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}

	@Override
	public void atualizar(Object object) {
		Coordenacao Coordenacao = (Coordenacao) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().merge(Coordenacao);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Coordenacao Coordenacao = (Coordenacao) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Coordenacao.class, Coordenacao.getId()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}
}
