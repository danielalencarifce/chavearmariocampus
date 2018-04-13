package routers;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "routers")
public class Routers {
	private String termoResponsabilidade = "/restricted/funcionario/emitirTermo.xhtml";
	private String meusDados = "/restricted/dados/meusDados.xhtml";
	private String alterarSenha = "/restricted/dados/alterarSenha.xhtml";
	private String principalAluno = "/restricted/aluno/principalAluno.xhtml";
	private String principalFuncionario = "/restricted/funcionario/principalFuncionario.xhtml";
	private String principalArmario = "/restricted/armario/principalArmario.xhtml";
	private String principalEmprestimo = "/restricted/emprestimo/principalEmprestimo.xhtml";
	private String principalEstante = "/restricted/estante/principalEstante.xhtml";
	
	public String getPrincipalAluno() {
		return principalAluno;
	}

	public void setPrincipalAluno(String principalAluno) {
		this.principalAluno = principalAluno;
	}
	public String getPrincipalFuncionario() {
		return principalFuncionario;
	}

	public void setPrincipalFuncionario(String principalFuncionario) {
		this.principalFuncionario = principalFuncionario;
	}
	
	public String getTermoResponsabilidade() {
		return termoResponsabilidade;
	}
	
	public void setTermoResponsabilidade(String termoResponsabilidade) {
		this.termoResponsabilidade = termoResponsabilidade;
	}
	
	public String getMeusDados() {
		return meusDados;
	}
	
	public void setMeusDados(String meusDados) {
		this.meusDados = meusDados;
	}
	
	public String getPrincipalArmario() {
		return principalArmario;
	}

	public void setPrincipalArmario(String principalArmario) {
		this.principalArmario = principalArmario;
	}

	public String getPrincipalEmprestimo() {
		return principalEmprestimo;
	}

	public void setPrincipalEmprestimo(String principalEmprestimo) {
		this.principalEmprestimo = principalEmprestimo;
	}

	public String getAlterarSenha() {
		return alterarSenha;
	}

	public void setAlterarSenha(String alterarSenha) {
		this.alterarSenha = alterarSenha;
	}

	public String getPrincipalEstante() {
		return principalEstante;
	}

	public void setPrincipalEstante(String principalEstante) {
		this.principalEstante = principalEstante;
	}

}
