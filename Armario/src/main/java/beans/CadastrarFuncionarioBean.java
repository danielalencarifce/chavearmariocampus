package beans;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import dao.FuncionarioDao;

@ManagedBean(name = "cadastrarFuncionarioBean")
public class CadastrarFuncionarioBean {
	private String nome;
	private String sobrenome;
	private String login;
	private String senha;
	private String telefone;
	private String email;
	private String departamento;
	private FuncionarioDao funcionarioDao;
	
	public void adicionarFuncionario() {
		funcionarioDao = new FuncionarioDao();
		funcionarioDao.salvar(this);
	    FacesContext.getCurrentInstance().addMessage("sucess",new FacesMessage("Funcionário " + this.nome + " cadastrado com sucesso!"));
	}
	public void openCadastro() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
	    options.put("width", 650);
        options.put("height", 400);
	    options.put("contentHeight", "100%");
		
        PrimeFaces.current().dialog().openDynamic("/restricted/funcionario/cadastrarFuncionario", options, null);
    }
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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
	public Date getUltimo_acesso() {
		return ultimo_acesso;
	}
	public void setUltimo_acesso(Date ultimo_acesso) {
		this.ultimo_acesso = ultimo_acesso;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	private Date ultimo_acesso = new Date(System.currentTimeMillis());
}
