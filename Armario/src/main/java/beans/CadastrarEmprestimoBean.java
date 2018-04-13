package beans;

import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import dao.AlunoDao;
import dao.ArmarioDao;
import entities.Aluno;
import entities.Armario;

@ManagedBean(name = "cadastrarEmprestimoBean")
public class CadastrarEmprestimoBean {
	private String matricula;
	private String armario;
	private ArmarioDao armarioDao = new ArmarioDao();
	private AlunoDao alunoDao = new AlunoDao();

	public void adicionarEmprestimo() {
		Aluno aluno = (Aluno) alunoDao.buscarPorMatricula(this.matricula);
		Armario armario = (Armario) armarioDao.buscarPorNumero(this.armario);
		armario.setDisponivel(false);
		armarioDao.atualizar(armario); /*Atualiza novo status do armário.*/
		aluno.setArmario(armario);
		alunoDao.atualizar(aluno);
	    FacesContext.getCurrentInstance().addMessage("sucess", new FacesMessage("Empréstimo cadastrado com sucesso!"));
	}
	public void openCadastro() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 250);
	    options.put("contentHeight", "100%");
		
        PrimeFaces.current().dialog().openDynamic("/restricted/emprestimo/cadastrarEmprestimo", options, null);
    }
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getArmario() {
		return armario;
	}
	public void setArmario(String armario) {
		this.armario = armario;
	}
}
