package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import dao.EstanteDao;
import entities.Estante;

@ManagedBean(name = "listarEstanteBean")
public class ListarEstanteBean {
	private String numero_estante;
	private List<Estante> estantes = new ArrayList<>();
	private EstanteDao estanteDao = new EstanteDao();
	
	public ArrayList<String> autocompleteEstante() {
		ArrayList<String> numerosEstantes = new ArrayList<>();
		for (Estante estante: this.estanteDao.listar()) {
			numerosEstantes.add(estante.getNumero_estante());
		}
		return numerosEstantes;
	}
	
	public void buscarEstante() {
		this.getEstantes().clear();
		if (this.getNumero_estante() != null || this.getNumero_estante() == "")
			setEstantes((Estante) estanteDao.buscarPorNumero(getNumero_estante()));
	}

	public String getNumero_estante() {
		return numero_estante;
	}

	public void setNumero_estante(String numero_estante) {
		this.numero_estante = numero_estante;
	}

	public List<Estante> getEstantes() {
		if (this.numero_estante == null)
			this.estantes =  this.estanteDao.listar();
		return estantes;
	}

	public void setEstantes(Estante estante) {
		this.estantes.add(estante);
	}
	
	
}
