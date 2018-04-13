package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import dao.AlunoDao;
import entities.Aluno;

@ManagedBean(name = "listarAlunoBean")
public class ListarAlunoBean {
	private String matricula;
	private AlunoDao alunoDao = new AlunoDao();
	private List<Aluno> alunos = new ArrayList<>();
	
	public ArrayList<String> autocompleteAluno() {
		ArrayList<String> matriculas = new ArrayList<>();
		for (Aluno aluno : this.alunoDao.listar()) {
			matriculas.add(aluno.getMatricula());
		}
		return matriculas;
	}
	public ArrayList<String> autocompleteAlunoSemArmario() {
		ArrayList<String> matriculas = new ArrayList<>();
		for (Aluno aluno : this.alunoDao.listarAlunosSemArmarios()) {
			matriculas.add(aluno.getMatricula());
		}
		return matriculas;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public List<Aluno> getAlunos() {
		if (this.matricula == null || this.matricula == "")
			this.alunos =  this.alunoDao.listar();
		return alunos;
	}
	public void setAlunos(Aluno aluno) {
		this.alunos.add(aluno);
	}
	public void buscarAluno() {
		this.alunos.clear();
		if (this.matricula != null)
			setAlunos((Aluno) alunoDao.buscarPorMatricula(matricula));
	}
}
