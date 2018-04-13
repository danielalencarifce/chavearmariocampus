package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import dao.ArmarioDao;
import entities.Armario;

@ManagedBean(name = "listarArmarioBean")
public class ListarArmarioBean {
	private String numero_armario;
	private List<Armario> armarios = new ArrayList<>();
	private ArmarioDao armarioDao = new ArmarioDao();
	
	public ArrayList<String> autocompleteArmario() {
		ArrayList<String> numerosArmarios = new ArrayList<>();
		for (Armario armario: this.armarioDao.listar()) {
			numerosArmarios.add(armario.getNumero_armario());
		}
		return numerosArmarios;
	}
	
	public void buscarArmario() {
		this.armarios.clear();
		if (this.numero_armario != null || this.numero_armario == "")
			setArmarios((Armario) armarioDao.buscarPorNumero(numero_armario));
	}
	
	public String getNumero_armario() {
		return numero_armario;
	}
	public void setNumero_armario(String numero_armario) {
		this.numero_armario = numero_armario;
	}
	public List<Armario> getArmarios() {
		if (this.numero_armario == null)
			this.armarios =  this.armarioDao.listar();
		return armarios;
	}
	public void setArmarios(Armario armario) {
		this.armarios.add(armario);
	}
	public String statusArmario(Armario armario) {
		if (armario.isDisponivel())
			return "Disponível";
		return "Emprestado";
	}
	public ArrayList<String> autocompleteArmarioDisponiveis() {
		ArrayList<String> numerosArmariosDisponiveis = new ArrayList<>();
		for (Armario armario: this.armarioDao.listarArmariosDisponiveis()) {
			numerosArmariosDisponiveis.add(armario.getNumero_armario());
		}
		return numerosArmariosDisponiveis;
	}
}
