package beans;

import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import dao.AlunoDao;
import entities.Aluno;

@ManagedBean(name = "editarAlunoBean")
@SessionScoped
public class EditarAlunoBean {
	private Aluno aluno;
	private AlunoDao alunoDao;
	
	public void openEditar(Aluno aluno) {
		this.aluno = aluno;
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 300);
	    options.put("contentHeight", "100%");
		
        PrimeFaces.current().dialog().openDynamic("/restricted/aluno/editarAluno", options, null);
    }
	public void atualizarAluno() {
		this.alunoDao = new AlunoDao();
    	alunoDao.atualizar(this.aluno);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Aluno " + this.aluno.getNome() + " atualizado com sucesso!"));
    }
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
