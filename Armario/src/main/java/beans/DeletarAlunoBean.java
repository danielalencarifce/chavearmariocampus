package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import dao.AlunoDao;
import entities.Aluno;

@ManagedBean(name = "deletarAlunoBean")
public class DeletarAlunoBean {
	private AlunoDao alunoDao;

	public void deletarAluno(Aluno aluno) {
		this.alunoDao = new AlunoDao();
        alunoDao.deletar(aluno);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Aluno "+aluno.getNome()+" removido com sucesso! "));
    }
}
