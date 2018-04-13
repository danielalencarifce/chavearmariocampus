package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import dao.AlunoDao;
import dao.ArmarioDao;
import entities.Aluno;
import entities.Armario;

@ManagedBean(name = "deletarEmprestimoBean")
public class DeletarEmprestimoBean {
	private AlunoDao alunoDao;
	private ArmarioDao armarioDao = new ArmarioDao();
	
	public void deletarEmprestimo(Aluno aluno) {
		this.alunoDao = new AlunoDao();
		Armario armario = aluno.getArmario();
		armario.setDisponivel(true);
		armarioDao.atualizar(armario);
		aluno.setArmario(null);
    	alunoDao.atualizar(aluno);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Empréstimo removido com sucesso! "));
    }
}
