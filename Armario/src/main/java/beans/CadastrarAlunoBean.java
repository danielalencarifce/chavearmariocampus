package beans;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import dao.AlunoDao;

@ManagedBean(name = "cadastrarAlunoBean")
public class CadastrarAlunoBean {
	private String nome;
	private String sobrenome;
	private String matricula;
	private String login;
	private String senha;
	private String telefone;
	private String email;
	private String curso;
	private Date ultimo_acesso = new Date(System.currentTimeMillis());
	private AlunoDao alunoDao;
	
	public void adicionarAluno() {
		alunoDao = new AlunoDao();
		alunoDao.salvar(this);
	    FacesContext.getCurrentInstance().addMessage("sucess", new FacesMessage("Aluno " + this.nome + " cadastrado com sucesso!"));
	}
	public void openCadastro() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 400);
	    options.put("contentHeight", "100%");
		
        PrimeFaces.current().dialog().openDynamic("/restricted/aluno/cadastrarAluno", options, null);
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
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
}
