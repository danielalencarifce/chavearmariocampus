package entities;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import dao.UsuarioDao;


/**
 *
 * @author raylson
 */

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (length = 50, unique = true, nullable = false)
    private String login;
    
    @Column (length = 100, nullable = false)
    private String senha;
    
    @Column (nullable = false)
    private String tipo_usuario;
    
    private Boolean active;

    @ElementCollection
    @CollectionTable(name = "usuario_permissao",
    joinColumns = @JoinColumn(name = "id"))
    private List<String> permissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

 
    public List<String> getPermissao() {
        return permissao;
    }

    public void setPermissao(List<String> permissoes) {
        this.permissao = permissoes;
    }
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
	public Object buscarPessoaPorLogin (String login) {
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.buscarPessoaPorLogin(login);
	}
	
	public Object buscarUsuarioPorLogin (String login) {
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.buscarPorLogin(login);
	}
}
