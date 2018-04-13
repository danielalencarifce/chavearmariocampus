package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author raylson
 */

@Entity
@PrimaryKeyJoinColumn(name="cod_funcionario", referencedColumnName="id_pessoa")
public class Funcionario extends Pessoa{
	
	@Column (nullable=false,length=50)
	private String departamento;
    
    @Temporal (TemporalType.TIMESTAMP)
    private Date ultimo_acesso;

    public Date getUltimo_acesso() {
        return ultimo_acesso;
    }

    public void setUltimo_acesso(Date ultimo_acesso) {
        this.ultimo_acesso = ultimo_acesso;
    }
    
    public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
}
