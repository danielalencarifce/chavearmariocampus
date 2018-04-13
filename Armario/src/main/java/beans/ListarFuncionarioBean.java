package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;

import dao.FuncionarioDao;
import entities.Armario;
import entities.Funcionario;

@ManagedBean(name = "listarFuncionarioBean")
public class ListarFuncionarioBean {	
	private String nome;
	private FuncionarioDao funcionarioDao = new FuncionarioDao();
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	public ArrayList<String> autocompleteFuncionario() {
		ArrayList<String> nomesFuncionarios = new ArrayList<>();
		for (Funcionario funcionario : this.funcionarioDao.listar()) {
			nomesFuncionarios.add(funcionario.getNome());
		}
		return nomesFuncionarios;
	}
	public void buscarFuncionario() {
		this.funcionarios.clear();
		if (this.nome != null)
			setFuncionarios((ArrayList<Funcionario>)funcionarioDao.buscarPorNome(this.nome));
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Funcionario> getFuncionarios() {
		if (this.nome == null || this.nome == "")
			this.funcionarios =  this.funcionarioDao.listar();
		return this.funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
}
