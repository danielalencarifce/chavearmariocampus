package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import dao.FuncionarioDao;
import entities.Funcionario;

@ManagedBean(name = "deletarFuncionarioBean")
public class DeletarFuncionarioBean {
	private FuncionarioDao funcionarioDao;

	public void deletarFuncionario(Funcionario funcionario) {
		this.funcionarioDao = new FuncionarioDao();
        this.funcionarioDao.deletar(funcionario);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Funcionário "+funcionario.getNome()+" removido com sucesso! "));
    }
}
