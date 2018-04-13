package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import dao.AlunoDao;
import entities.Aluno;

@ManagedBean(name = "listarEmprestimoBean")
public class ListarEmprestimoBean {
	private String matricula;
	private List<Aluno> alunosComArmario = new ArrayList<>();
	private AlunoDao alunoDao = new AlunoDao();
	
	public ArrayList<String> autocompleteEmprestimo() {
		ArrayList<String> alunosComEmprestimo = new ArrayList<>();
		for (Aluno aluno: this.alunoDao.listarAlunosComArmarios()) { /*Apenas matrículas de alunos com armário*/
			alunosComEmprestimo.add(aluno.getMatricula());
		}
		return alunosComEmprestimo;
	}
	
	public void buscarEmprestimo() {
		this.alunosComArmario.clear();
		if (this.matricula != null) {
			setAlunosComArmario((Aluno) alunoDao.buscarPorMatricula(this.matricula));
		}
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Aluno> getAlunosComArmario() {
		if (this.matricula == null || this.matricula == "")
			this.alunosComArmario = alunoDao.listarAlunosComArmarios();
		return alunosComArmario;
	}

	public void setAlunosComArmario(Aluno aluno) {
		this.alunosComArmario.add(aluno);
	}

	

}
