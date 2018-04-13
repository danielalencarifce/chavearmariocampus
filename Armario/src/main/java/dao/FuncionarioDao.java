package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import beans.CadastrarFuncionarioBean;
import entities.Aluno;
import entities.Funcionario;
import entities.Usuario;

/**
 *
 * @author raylson
 */
public class FuncionarioDao implements DAO {
	public void salvar(Object object) {
        CadastrarFuncionarioBean funcionarioBean = (CadastrarFuncionarioBean) object;
        Funcionario funcionario = new Funcionario();
        
        Usuario usuario = new Usuario();
        usuario.setLogin(funcionarioBean.getLogin());
        usuario.setSenha(funcionarioBean.getSenha());
        usuario.setActive(true);
        List<String> permissoes = new ArrayList<>();
        permissoes.add("ROLE_FUNCIONARIO");
        usuario.setPermissao(permissoes);
        usuario.setTipo_usuario("Funcionario");
                
        funcionario.setNome(funcionarioBean.getNome());
        funcionario.setSobrenome(funcionarioBean.getSobrenome());
        funcionario.setTelefone(funcionarioBean.getTelefone());
        funcionario.setEmail(funcionarioBean.getEmail());
        funcionario.setDepartamento(funcionarioBean.getDepartamento());
        funcionario.setUsuario(usuario);
        funcionario.setUltimo_acesso(funcionarioBean.getUltimo_acesso());
        
        Connection conn = new Connection();
        conn.getInstance().persist(funcionario);
        conn.getInstance().getTransaction().begin();
        conn.getInstance().getTransaction().commit();
        conn.close();
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Funcionario x WHERE x.id = :id";
        TypedQuery<Funcionario> consulta = conn.getInstance().createQuery(jpql, Funcionario.class);
        consulta.setParameter("id", id);
        return consulta.getSingleResult();
	}

	@Override
	public List<Funcionario> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Funcionario x";
        TypedQuery<Funcionario> consulta = conn.getInstance().createQuery(jpql, Funcionario.class);
        List<Funcionario> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}
	
	public List<Funcionario> buscarPorNome(String nome) { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Funcionario x WHERE x.nome = :nome";
        try {
	        TypedQuery<Funcionario> consulta = conn.getInstance().createQuery(jpql, Funcionario.class);
	        consulta.setParameter("nome", nome);
	        List<Funcionario> resultado = consulta.getResultList();
	        conn.close();
	        return resultado;
        }catch (NoResultException nre) {
        	System.out.println("Desculpe. Não existe funcionário com o nome: " + nome);
        	return null;
		}
	}

	@Override
	public void atualizar(Object object) {
		Funcionario Funcionario = (Funcionario) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().merge(Funcionario);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Funcionario Funcionario = (Funcionario) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Funcionario.class, Funcionario.getId_pessoa()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}
}
