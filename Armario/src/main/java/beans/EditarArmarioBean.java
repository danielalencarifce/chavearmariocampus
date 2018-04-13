package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

import dao.ArmarioDao;
import dao.CoordenacaoDao;
import dao.EstanteDao;
import entities.Armario;
import entities.Coordenacao;
import entities.Estante;

@ManagedBean(name = "editarArmarioBean")
@SessionScoped
public class EditarArmarioBean {
	private Armario armario;
	private ArmarioDao armarioDao;
	private CoordenacaoDao coordenacaoDao;
	private EstanteDao estanteDao;

	public void openEditar(Armario armario) {
		System.out.println(armario.getNumero_armario());
		this.armario = armario;
		
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 300);
	    options.put("contentHeight", "100%");
        PrimeFaces.current().dialog().openDynamic("/restricted/armario/editarArmario", options, null);
	}
	
	public void onChangeCoordenacao(String coordenacao) {
		if (coordenacao != null && coordenacao != "") {
			this.coordenacaoDao = new CoordenacaoDao();
			armario.setCoordenacao((Coordenacao) this.coordenacaoDao.buscarPorNome(coordenacao));
		}		
	}
	public void onChangeEstante(String estante) {
		if (estante != null && estante != "") {
			this.estanteDao = new EstanteDao();
			armario.setEstante((Estante) this.estanteDao.buscarPorNumero(estante));
		}		
	}
	public void atualizarArmario() {
		this.armarioDao = new ArmarioDao();
		armarioDao.atualizar(this.armario);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Armário " + this.armario.getNumero_armario() + " atualizado com sucesso!"));
    }
	public Armario getArmario() {
		return armario;
	}
	public void setArmario(Armario armario) {
		this.armario = armario;
	}
	
	public List<String> getNomesCoordenacao() {
		this.coordenacaoDao = new CoordenacaoDao();
		List<String> nomesCoordenacao = new ArrayList();
		for (Coordenacao coordenacao: coordenacaoDao.listar()) {
			nomesCoordenacao.add(coordenacao.getNome());
		}
		return nomesCoordenacao;
	}
}
