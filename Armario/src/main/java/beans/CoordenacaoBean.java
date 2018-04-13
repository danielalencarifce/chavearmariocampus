package beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.CoordenacaoDao;
import entities.Armario;
import entities.Coordenacao;

@ManagedBean(name = "CoordenacaoBean")
public class CoordenacaoBean {
	private long id;
	private String nome;
	private ArrayList<Armario> armarios;
	private Coordenacao coordenacao = new Coordenacao();
	private CoordenacaoDao coordenacaoDao = new CoordenacaoDao();

	public void adicionarCoordenacao() {
		coordenacaoDao.salvar(this);
	    /*FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage("Cliente " + this.nome + " cadastrado!"));*/
	}
	
	public void buscarPorID() {
        this.coordenacao = (Coordenacao) coordenacaoDao.buscarPorID(id);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + this.cliente.getNome() + " encontrado!"));*/
    }

    public void atualizarCoordenacao() {
    	coordenacaoDao.atualizar(this.coordenacao);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + this.cliente.getNome() + " atualizada com sucesso!"));*/
    }
    public void deletarCoordenacao() {
        coordenacaoDao.deletar(this.coordenacao);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente "+this.cliente.getNome()+" removido com sucesso! "));*/
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Armario> getArmarios() {
		return armarios;
	}

	public void setArmarios(ArrayList<Armario> armarios) {
		this.armarios = armarios;
	}
}
