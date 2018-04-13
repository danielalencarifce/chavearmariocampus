package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import dao.CursoDao;
import entities.Coordenacao;
import entities.Curso;
import entities.Aluno;

@ManagedBean(name = "CursoBean")
public class CursoBean {
	private long id;
	private String descricao;
	private Coordenacao coordenacao;
	private List<Aluno> alunos;
	private Curso curso = new Curso();
	private CursoDao cursoDao = new CursoDao();

	public void adicionarCurso() {
		cursoDao.salvar(this);
	    /*FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage("Cliente " + this.nome + " cadastrado!"));*/
	}
	
	public void buscarPorID() {
        this.curso = (Curso) cursoDao.buscarPorID(id);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + this.cliente.getNome() + " encontrado!"));*/
    }

    public void atualizarCurso() {
    	cursoDao.atualizar(this.curso);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + this.cliente.getNome() + " atualizada com sucesso!"));*/
    }
    public void deletarCurso() {
        cursoDao.deletar(this.curso);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente "+this.cliente.getNome()+" removido com sucesso! "));*/
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
}
