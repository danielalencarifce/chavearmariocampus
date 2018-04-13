package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/**
 *
 * @author raylson
 */

@Entity
public class Estante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column (unique = true, nullable = false)
    private String numero_estante;
    
    @OneToOne
    private Coordenacao coordenacao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNumero_estante() {
        return numero_estante;
    }

    public void setNumero_estante(String numero_estante) {
        this.numero_estante = numero_estante;
    }
	public Coordenacao getCoordenacao() {
		return coordenacao;
	}
	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}   
}
