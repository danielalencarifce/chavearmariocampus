package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import dao.CoordenacaoDao;
import dao.EstanteDao;
import entities.Coordenacao;
import entities.Estante;

@ManagedBean(name = "editarEstanteBean")
@SessionScoped
public class EditarEstanteBean {
	private Estante estante;
	private EstanteDao estanteDao;
	private CoordenacaoDao coordenacaoDao;

	public void openEditar(Estante estante) {
		this.estante = estante;
		
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 300);
	    options.put("contentHeight", "100%");
        PrimeFaces.current().dialog().openDynamic("/restricted/estante/editarEstante", options, null);
	}
	
	public void onChangeCoordenacao(String coordenacao) {
		if (coordenacao != null && coordenacao != "") {
			this.coordenacaoDao = new CoordenacaoDao();
			estante.setCoordenacao((Coordenacao) this.coordenacaoDao.buscarPorNome(coordenacao));
		}		
	}
	public void atualizarEstante() {
		this.estanteDao = new EstanteDao();
		estanteDao.atualizar(this.estante);
        FacesContext.getCurrentInstance().addMessage("sucess",
                new FacesMessage("Estante " + this.estante.getNumero_estante() + " atualizado com sucesso!"));
	}
	
	public Estante getEstante() {
		return estante;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
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
