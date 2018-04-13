package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import dao.EstanteDao;
import entities.Estante;
import entities.Armario;

@ManagedBean(name = "estanteBean")
public class EstanteBean {
	private long id;
	private String numero_tombamento;
	private String numero_estante;
	private List<Armario> armarios;
	private Estante estante = new Estante();
	private EstanteDao estanteDao = new EstanteDao();

	public void adicionarEstante() {
		estanteDao.salvar(this);
	    /*FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage("Cliente " + this.nome + " cadastrado!"));*/
	}
	
	public void buscarPorID() {
        this.estante = (Estante) estanteDao.buscarPorID(id);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + this.cliente.getNome() + " encontrado!"));*/
    }

    public void atualizarEstante() {
    	estanteDao.atualizar(this.estante);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + this.cliente.getNome() + " atualizada com sucesso!"));*/
    }
    public void deletarEstante() {
        estanteDao.deletar(this.estante);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente "+this.cliente.getNome()+" removido com sucesso! "));*/
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero_tombamento() {
		return numero_tombamento;
	}

	public void setNumero_tombamento(String numero_tombamento) {
		this.numero_tombamento = numero_tombamento;
	}

	public String getNumero_estante() {
		return numero_estante;
	}

	public void setNumero_estante(String numero_estante) {
		this.numero_estante = numero_estante;
	}

	public List<Armario> getArmarios() {
		return armarios;
	}

	public void setArmarios(List<Armario> armarios) {
		this.armarios = armarios;
	}

	
}
