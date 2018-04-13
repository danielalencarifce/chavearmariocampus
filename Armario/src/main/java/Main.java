import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dao.UsuarioDao;
import entities.Funcionario;
import entities.Usuario;
/**
 *
 *@author raylson
 */
public class Main {
    public static void main(String[] args) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("armarios");
		EntityManager db = factory.createEntityManager();
		
		Funcionario funcionario = new Funcionario();
        Usuario usuario = new Usuario();
        usuario.setLogin("raylson");
        usuario.setSenha("12345");
        usuario.setActive(true);
        List<String> permissoes = new ArrayList<>();
        permissoes.add("ROLE_FUNCIONARIO");
        permissoes.add("ROLE_ADMIN");
        usuario.setPermissao(permissoes);
        usuario.setTipo_usuario("Funcionario");
        
        funcionario.setNome("Raylson");
        funcionario.setSobrenome("Silva de Lima");
        funcionario.setTelefone("988776655");
        funcionario.setEmail("raylson@gmail.com");
        funcionario.setDepartamento("Computação");
        funcionario.setUsuario(usuario);
        funcionario.setUltimo_acesso(new Date(System.currentTimeMillis()));
        
        db.persist(funcionario);
        db.getTransaction().begin();
        db.getTransaction().commit();
        db.close();
		factory.close();
    }
}
