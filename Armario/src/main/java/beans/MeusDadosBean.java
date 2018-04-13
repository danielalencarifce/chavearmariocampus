package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.AlunoDao;
import dao.FuncionarioDao;
import dao.UsuarioDao;
import entities.Aluno;
import entities.Funcionario;
import entities.Usuario;

@ManagedBean(name = "meusDadosBean")
public class MeusDadosBean {
	private Aluno aluno;
	private Funcionario funcionario;
	private Boolean isAluno = false;
	private Boolean isFuncionario = false;
	private Usuario usuario;
	private AlunoDao alunoDao = new AlunoDao();
	private FuncionarioDao funcionarioDao = new FuncionarioDao();
	private UsuarioDao usuarioDao = new UsuarioDao();
	
	@PostConstruct
    public void init(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		this.usuario = usuarioDao.buscarPorLogin(login);
		
		if (usuario.getTipo_usuario().equals("Aluno")) {
			this.aluno = (Aluno) alunoDao.buscarPorID(usuario.getId());
			setIsAluno(true);
		} else if (usuario.getTipo_usuario().equals("Funcionario")) {
			this.funcionario = (Funcionario) funcionarioDao.buscarPorID(usuario.getId());
			setIsFuncionario(true);
		}
    }

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Boolean getIsAluno() {
		return isAluno;
	}

	public void setIsAluno(Boolean isAluno) {
		this.isAluno = isAluno;
	}

	public Boolean getIsFuncionario() {
		return isFuncionario;
	}

	public void setIsFuncionario(Boolean isFuncionario) {
		this.isFuncionario = isFuncionario;
	}
	
}
