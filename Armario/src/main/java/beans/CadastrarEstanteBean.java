package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import dao.CoordenacaoDao;
import dao.EstanteDao;
import entities.Coordenacao;

@ManagedBean(name = "cadastrarEstanteBean")
public class CadastrarEstanteBean {
	private String numero_estante;
	private String nomeCoordenacao;
    private Coordenacao coordenacao;
	private EstanteDao estanteDao;
	private CoordenacaoDao coordenacaoDao;
	
	public void adicionarEstante() {
		estanteDao = new EstanteDao();
		estanteDao.salvar(this);
	    FacesContext.getCurrentInstance().addMessage("sucess", new FacesMessage("Estante " + this.getNumero_estante() + " cadastrado com sucesso!"));
	}
	public void openCadastro() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 250);
	    options.put("contentHeight", "100%");
		
        PrimeFaces.current().dialog().openDynamic("/restricted/estante/cadastrarEstante.xhtml", options, null);
    }
	public List<String> getNomesCoordenacao() {
		this.coordenacaoDao = new CoordenacaoDao();
		List<String> nomesCoordenacao = new ArrayList();
		for (Coordenacao coordenacao: coordenacaoDao.listar()) {
			nomesCoordenacao.add(coordenacao.getNome());
		}
		return nomesCoordenacao;
	}
	public Coordenacao getCoordenacao() {
		return coordenacao;
	}
	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}
	public String getNomeCoordenacao() {
		return nomeCoordenacao;
	}
	public void setNomeCoordenacao(String nomeCoordenacao) {
		this.nomeCoordenacao = nomeCoordenacao;
		this.coordenacao = (Coordenacao) this.coordenacaoDao.buscarPorNome(nomeCoordenacao);
	}
	public String getNumero_estante() {
		return numero_estante;
	}
	public void setNumero_estante(String numero_estante) {
		this.numero_estante = numero_estante;
	}	
}
