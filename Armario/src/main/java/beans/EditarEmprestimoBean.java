package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import dao.AlunoDao;
import dao.ArmarioDao;
import dao.CoordenacaoDao;
import entities.Aluno;
import entities.Armario;
import entities.Coordenacao;

@ManagedBean(name = "editarEmprestimoBean")
@SessionScoped
public class EditarEmprestimoBean {
	private Aluno aluno;
	private Armario armario;
	private String novoArmario;
	private Boolean changed = false;
	private ArmarioDao armarioDao = new ArmarioDao();
	private AlunoDao alunoDao = new AlunoDao();

	public void openEditar(Aluno aluno) {
		this.aluno = aluno;
		setChanged(false);
		this.novoArmario = aluno.getArmario().getNumero_armario();
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 300);
	    options.put("contentHeight", "100%");
        PrimeFaces.current().dialog().openDynamic("/restricted/emprestimo/editarEmprestimo", options, null);
    }
	public void atualizarEmprestimo() {
		Armario armario_old = this.aluno.getArmario(); 
		armario_old.setDisponivel(true); /*Disponibiliza o armário anterior*/
		armarioDao.atualizar(armario_old);
		
		this.armario.setDisponivel(false); /*Indisponibiliza o armário escolhido*/
		armarioDao.atualizar(armario);
		
		this.aluno.setArmario(this.armario);
    	alunoDao.atualizar(this.aluno);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Empréstimo atualizado com sucesso!"));
        
    }
	public List<String> getArmariosDisponiveis() {
		List<String> numerosArmariosSemEmprestimo = new ArrayList();
		for (Armario armario: armarioDao.listarArmariosDisponiveis()) {
			numerosArmariosSemEmprestimo.add(armario.getNumero_armario());
		}
		return numerosArmariosSemEmprestimo;
	}
	public void buscarArmario() {
		this.armario = (Armario)armarioDao.buscarPorNumero(novoArmario);
		setChanged(true);
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public String getNovoArmario() {
		return novoArmario;
	}
	public void setNovoArmario(String novoArmario) {
		this.novoArmario = novoArmario;
	}
	public Armario getArmario() {
		return armario;
	}
	public void setArmario(Armario armario) {
		this.armario = armario;
	}
	public Boolean getChanged() {
		return changed;
	}
	public void setChanged(Boolean changed) {
		this.changed = changed;
	}

}
