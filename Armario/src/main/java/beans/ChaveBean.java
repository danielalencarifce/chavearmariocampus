package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ChaveDao;
import entities.Chave;
import entities.Status_Chave;

@ManagedBean(name = "ChaveBean")
public class ChaveBean {
	private long id;
	private Status_Chave status_chave;
	private Chave chave = new Chave();
	private ChaveDao chaveDao = new ChaveDao();

	public void adicionarChave() {
		chaveDao.salvar(this);
	    /*FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage("Cliente " + this.nome + " cadastrado!"));*/
	}
	
	public void buscarPorID() {
        this.chave = (Chave) chaveDao.buscarPorID(id);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + this.cliente.getNome() + " encontrado!"));*/
    }

    public void atualizarChave() {
    	chaveDao.atualizar(this.chave);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + this.cliente.getNome() + " atualizada com sucesso!"));*/
    }
    public void deletarChave() {
        chaveDao.deletar(this.chave);
        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente "+this.cliente.getNome()+" removido com sucesso! "));*/
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Status_Chave getStatus_chave() {
		return status_chave;
	}

	public void setStatus_chave(Status_Chave status_chave) {
		this.status_chave = status_chave;
	}

}
