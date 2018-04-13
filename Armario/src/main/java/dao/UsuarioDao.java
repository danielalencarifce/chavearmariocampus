package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import beans.UsuarioBean;
import entities.Pessoa;
import entities.Usuario;

/**
 *
 * @author raylson
 */
public class UsuarioDao implements DAO {
	public void salvar(Object object) {
        Usuario usuario = (Usuario) object;     
        Connection conn = new Connection();
        conn.getInstance().persist(usuario);
        conn.getInstance().getTransaction().begin();
        conn.getInstance().getTransaction().commit();
        conn.close();
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Usuario x WHERE x.id = :id";
        TypedQuery<Usuario> consulta = conn.getInstance().createQuery(jpql, Usuario.class);
        consulta.setParameter("id", id);
        return consulta.getSingleResult();
	}

	public Usuario buscarPorLogin(String login) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Usuario x WHERE x.login = :login";
        TypedQuery<Usuario> consulta = conn.getInstance().createQuery(jpql, Usuario.class);
        consulta.setParameter("login", login);
        return consulta.getSingleResult();
    }
	
	@Override
	public List<Usuario> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Usuario x";
        TypedQuery<Usuario> consulta = conn.getInstance().createQuery(jpql, Usuario.class);
        List<Usuario> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}

	@Override
	public void atualizar(Object object) {
		Usuario Usuario = (Usuario) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().merge(Usuario);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Usuario Usuario = (Usuario) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Usuario.class, Usuario.getId()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}
	
	public Object buscarPessoaPorLogin (String login) {
		Connection conn = new Connection();
		String jpql = "SELECT p FROM Pessoa p JOIN Usuario u WHERE (SELECT u.id FROM Usuario WHERE u.login = :login) = p.id_pessoa";
        TypedQuery<Pessoa> consulta = conn.getInstance().createQuery(jpql, Pessoa.class);
        consulta.setParameter("login", login);
        return consulta.getSingleResult();
	}
}
