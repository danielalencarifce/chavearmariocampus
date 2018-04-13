package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import dao.ArmarioDao;
import dao.CoordenacaoDao;
import dao.EstanteDao;
import entities.Coordenacao;
import entities.Estante;

@ManagedBean(name = "cadastrarArmarioBean")
public class CadastrarArmarioBean {
	private String numero_armario;
    private String bloco;
    private String nomeCoordenacao;
    private String numeroEstante;
    private Coordenacao coordenacao;
    private Estante estante;
	private ArmarioDao armarioDao;
	private CoordenacaoDao coordenacaoDao;
	private EstanteDao estanteDao;
	
	public void adicionarArmario() {
		System.out.println("fffoi n");
		this.estante = (Estante) this.estanteDao.buscarPorNumero(numeroEstante);
		System.out.println(this.estante.getNumero_estante());
		armarioDao = new ArmarioDao();
		armarioDao.salvar(this);
	    FacesContext.getCurrentInstance().addMessage("sucess", new FacesMessage("Armário " + this.numero_armario + " cadastrado com sucesso!"));
	}
	public void openCadastro() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 250);
	    options.put("contentHeight", "100%");
		
        PrimeFaces.current().dialog().openDynamic("/restricted/armario/cadastrarArmario.xhtml", options, null);
    }
	public String getNumero_armario() {
		return numero_armario;
	}
	public void setNumero_armario(String numero_armario) {
		this.numero_armario = numero_armario;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public List<String> getNomesCoordenacao() {
		this.coordenacaoDao = new CoordenacaoDao();
		List<String> nomesCoordenacao = new ArrayList();
		for (Coordenacao coordenacao: coordenacaoDao.listar()) {
			nomesCoordenacao.add(coordenacao.getNome());
		}
		return nomesCoordenacao;
	}
	public List<String> getNumerosEstantes() {
		this.estanteDao = new EstanteDao();
		List<String> numerosEstantes = new ArrayList<String>();
		for (Estante estante: estanteDao.listar()) {
			numerosEstantes.add(estante.getNumero_estante());
		}
		return numerosEstantes;
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
	public Estante getEstante() {
		return estante;
	}
	public void setEstante(Estante estante) {
		this.estante = estante;
	}
	public String getNumeroEstante() {
		return numeroEstante;
	}
	public void setNumeroEstante(String numeroEstante) {
		this.numeroEstante = numeroEstante;
		
	}
	
}
