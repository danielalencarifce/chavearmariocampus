package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import dao.ArmarioDao;
import entities.Armario;

@ManagedBean(name = "deletarArmarioBean")
public class DeletarArmarioBean {
	private ArmarioDao armarioDao;

	public void deletarArmario(Armario armario) {
		this.armarioDao = new ArmarioDao();
        armarioDao.deletar(armario);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Armário "+armario.getNumero_armario()+" removido com sucesso! "));
    }
}
