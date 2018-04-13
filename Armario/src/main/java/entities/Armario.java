package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raylson
 */

@Entity
public class Armario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numero_armario;
    
    private String bloco;
    
    @OneToMany
    private List<Chave> chaves;
    
    @OneToOne
    private Coordenacao coordenacao;
    
    @OneToOne
    private Estante estante;
    
    private boolean disponivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Chave> getChaves() {
        return chaves;
    }

    public void setChaves(List<Chave> chaves) {
        this.chaves = chaves;
    }

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Estante getEstante() {
		return estante;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
	}    

}
