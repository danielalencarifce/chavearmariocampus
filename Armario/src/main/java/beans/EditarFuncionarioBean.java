package beans;

import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import dao.FuncionarioDao;
import entities.Funcionario;

@ManagedBean(name = "editarFuncionarioBean")
@SessionScoped
public class EditarFuncionarioBean {
	private Funcionario funcionario;
	private FuncionarioDao funcionarioDao;
	
	public void openEditar(Funcionario funcionario) {
		this.funcionario = funcionario;
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 250);
	    options.put("contentHeight", "100%");
		
        PrimeFaces.current().dialog().openDynamic("/restricted/funcionario/editarFuncionario", options, null);
    }
	public void atualizarFuncionario() {
		this.funcionarioDao = new FuncionarioDao();
    	funcionarioDao.atualizar(this.funcionario);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Funcionário " + this.funcionario.getNome() + " atualizado com sucesso!"));
    }
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
