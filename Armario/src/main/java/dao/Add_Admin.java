package dao;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import beans.UsuarioBean;
import entities.Usuario;

public class Add_Admin {
    public static void main(String[] args) {        
    	EntityManagerFactory conn = Persistence.createEntityManagerFactory("armarios");
        EntityManager bd = conn.createEntityManager();
        Usuario usuario = new Usuario();
        usuario.setLogin("lucas");
        usuario.setSenha("12345");

        ArrayList<String> permissoes = new ArrayList<>();
       /* permissoes.add("ROLE_ADMIN");*/
        permissoes.add("ROLE_ALUNO");
        usuario.setPermissao(permissoes);
        usuario.setActive(true);

        bd.persist(usuario);
        bd.getTransaction().begin();
        bd.getTransaction().commit();

        bd.close();
        conn.close();
    }
}
