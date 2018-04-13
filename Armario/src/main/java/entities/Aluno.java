package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.List;

/**
 *
 * @author raylson
 */

@Entity
@PrimaryKeyJoinColumn(name = "cod_aluno", referencedColumnName = "id_pessoa")
public class Aluno extends Pessoa{

    @Column (unique = true, nullable = false)
    private String matricula;
    
    @Column (nullable = false, length=50)
    private String curso;
    
    @Column(nullable = false)
    @Temporal (TemporalType.TIMESTAMP) //Data e hora
    private Date ultimo_acesso;

    @OneToOne (cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Armario armario;

    public String getMatricula() {
        return matricula;
    }
     
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
	
    public Date getUltimo_acesso() {
        return ultimo_acesso;
    }

    public void setUltimo_acesso(Date ultimo_acesso) {
        this.ultimo_acesso = ultimo_acesso;
    }
    
    /*public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

 	*/
    public Armario getArmario() {
        return armario;
    }

    public void setArmario(Armario armario) {
        this.armario = armario;
    }

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
    
}
