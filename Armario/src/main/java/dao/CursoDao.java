package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import beans.CursoBean;
import entities.Curso;

/**
 *
 * @author raylson
 */
public class CursoDao implements DAO {
	
	public void salvar(Object object) {
        CursoBean cursoBean = (CursoBean) object;
        Curso curso = new Curso();
        curso.setDescricao(cursoBean.getDescricao());
        curso.setCoordenacao(cursoBean.getCoordenacao());
        curso.setAlunos(cursoBean.getAlunos());
        
        Connection conn = new Connection();
        conn.getInstance().persist(curso);
        conn.getInstance().getTransaction().begin();
        conn.getInstance().getTransaction().commit();
        conn.close();
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Curso x WHERE x.id = :id";
        TypedQuery<Curso> consulta = conn.getInstance().createQuery(jpql, Curso.class);
        consulta.setParameter("id", id);
        return consulta.getSingleResult();
	}

	@Override
	public List<Curso> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Curso x";
        TypedQuery<Curso> consulta = conn.getInstance().createQuery(jpql, Curso.class);
        List<Curso> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}

	@Override
	public void atualizar(Object object) {
		Curso Curso = (Curso) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().merge(Curso);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Curso Curso = (Curso) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Curso.class, Curso.getId()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

}
