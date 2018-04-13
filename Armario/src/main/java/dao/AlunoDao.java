package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import beans.CadastrarAlunoBean;
import entities.Aluno;
import entities.Usuario;

/**
 *
 * @author raylson
 */
public class AlunoDao implements DAO {
	public void salvar(Object object) {
        CadastrarAlunoBean alunoBean = (CadastrarAlunoBean) object;
        Aluno aluno = new Aluno();
        
        Usuario usuario = new Usuario();
        usuario.setLogin(alunoBean.getLogin());
        usuario.setSenha(alunoBean.getSenha());
        usuario.setActive(true);
        List<String> permissoes = new ArrayList<>();
        permissoes.add("ROLE_ALUNO");
        usuario.setPermissao(permissoes);
        usuario.setTipo_usuario("Aluno");
        
        aluno.setNome(alunoBean.getNome());
        aluno.setSobrenome(alunoBean.getSobrenome());
        aluno.setMatricula(alunoBean.getMatricula());
        aluno.setUsuario(usuario);
        aluno.setUltimo_acesso(alunoBean.getUltimo_acesso());
        aluno.setTelefone(alunoBean.getTelefone());
        aluno.setEmail(alunoBean.getEmail());
        aluno.setCurso(alunoBean.getCurso());
        try {        
	        Connection conn = new Connection();
	        conn.getInstance().persist(aluno);
	        conn.getInstance().getTransaction().begin();
	        conn.getInstance().getTransaction().commit();
	        conn.close();
        } catch (NoResultException e) {
            System.out.println("No result forund for... ");
        }
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Aluno x WHERE x.id = :id";
        try {
	        TypedQuery<Aluno> consulta = conn.getInstance().createQuery(jpql, Aluno.class);
	        consulta.setParameter("id", id);
	        return consulta.getSingleResult();
        }catch (NoResultException nre) {
        	System.out.println("Desculpe. Não existe aluno com o id: " + id);
        	return null;
		}
	}
	
	public Object buscarPorMatricula(String matricula) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Aluno x WHERE x.matricula = :matricula";
        try {
	        TypedQuery<Aluno> consulta = conn.getInstance().createQuery(jpql, Aluno.class);
	        consulta.setParameter("matricula", matricula);
	        Aluno aluno = consulta.getSingleResult();
	        conn.close();
	        return aluno;
        }catch (NoResultException nre) {
        	System.out.println("Desculpe. Não existe aluno com a matrícula: " + matricula);
        	return null;
		}
	}

	@Override
	public List<Aluno> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Aluno x";
        TypedQuery<Aluno> consulta = conn.getInstance().createQuery(jpql, Aluno.class);
        List<Aluno> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}
	public List<Aluno> listarAlunosComArmarios() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Aluno x WHERE x.armario <> 0";
        TypedQuery<Aluno> consulta = conn.getInstance().createQuery(jpql, Aluno.class);
        List<Aluno> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}
	public List<Aluno> listarAlunosSemArmarios() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Aluno x WHERE x.armario = null";
        TypedQuery<Aluno> consulta = conn.getInstance().createQuery(jpql, Aluno.class);
        List<Aluno> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}

	@Override
	public void atualizar(Object object) {
		Aluno Aluno = (Aluno) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin()	;
        conn.getInstance().merge(Aluno);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Aluno Aluno = (Aluno) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Aluno.class, Aluno.getId_pessoa()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}
	
}