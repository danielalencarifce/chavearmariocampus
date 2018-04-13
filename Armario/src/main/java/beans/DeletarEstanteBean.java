package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import dao.EstanteDao;
import entities.Estante;

@ManagedBean(name = "deletarEstanteBean")
public class DeletarEstanteBean {
	private EstanteDao estanteDao;

	public void deletarArmario(Estante estante) {
		this.estanteDao = new EstanteDao();
		estanteDao.deletar(estante);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Estante "+estante.getNumero_estante()+" removido com sucesso! "));
    }
}
