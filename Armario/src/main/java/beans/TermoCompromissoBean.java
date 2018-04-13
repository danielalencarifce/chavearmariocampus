package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import dao.AlunoDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import entities.*;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 *
 *@author raylson
 */

@ManagedBean(name = "termoCompromissoBean")
public class TermoCompromissoBean  {
	private String matricula;
	private String nomeCompleto;
	private List<Aluno> alunosComArmario;
	private AlunoDao alunoDao = new AlunoDao();

	public void emitirTermo (Aluno aluno) throws JRException {
		this.nomeCompleto = aluno.getNome()+" "+aluno.getSobrenome();
		
		//Obtem o valor atual do sistema
		long inicioContagem = System.currentTimeMillis();
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nome", this.nomeCompleto);
		parameters.put("matricula", aluno.getMatricula());
		parameters.put("armario", aluno.getArmario().getNumero_armario());
		
		
		//Compilacao no formato jasper para o jrprint
		JasperFillManager.fillReportToFile("/home/raylson/eclipse-workspace/Armario/reports/TermoIFCE.jasper", parameters, new JREmptyDataSource(1));
		System.err.println("Tempo de compilacao jasper -> jrprint: " + (System.currentTimeMillis() - inicioContagem));
		
		//Reinicia o contador
		inicioContagem = System.currentTimeMillis();
		
		//Geracao do PDF
		JasperExportManager.exportReportToPdfFile("/home/raylson/eclipse-workspace/Armario/reports/TermoIFCE.jrprint");
		System.err.println("Tempo de geracao do PDF: " + (System.currentTimeMillis() - inicioContagem));
		FacesContext.getCurrentInstance().addMessage("sucess", new FacesMessage("Termo do aluno " + aluno.getNome() + " gerado com sucesso!"));
	}
	
	public ArrayList<String> autocompleteAluno() {
		ArrayList<String> matriculas = new ArrayList<>();
		for (Aluno aluno : this.alunoDao.listarAlunosComArmarios()) {
			matriculas.add(aluno.getMatricula());
		}
		return matriculas;
	}
	
	public void buscarAluno() {
		this.getAlunosComArmario().clear();
		if (this.matricula != null) {}
			setAlunosComArmario((Aluno) alunoDao.buscarPorMatricula(matricula));
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Aluno> getAlunosComArmario() {
		if (this.matricula == null || this.matricula == "")
			this.alunosComArmario =  this.alunoDao.listarAlunosComArmarios();
		return alunosComArmario;
	}

	public void setAlunosComArmario(Aluno aluno) {
		this.alunosComArmario.add(aluno);
	}

	
	
}