package beans;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import dao.UsuarioDao;
import entities.Usuario;

@ManagedBean(name = "alterarSenhaBean")
public class AlterarSenhaBean {
	private Usuario usuario;
	private String senha_old;
	private String senha_new;
	private UsuarioDao usuarioDao = new UsuarioDao();
	
	@PostConstruct
    public void init(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		this.setUsuario(usuarioDao.buscarPorLogin(login));
    }
	
	public void openAlterarSenha() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
	    options.put("draggable", true);
        options.put("height", 500);
	    options.put("contentHeight", "100%");
		
        PrimeFaces.current().dialog().openDynamic("/restricted/dados/alterarSenha", options, null);
    }
	
	public void alterarSenha() {
		try {
			String gRecaptchaResponse = FacesContext.getCurrentInstance().
					getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
	        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
	        if(verify){
	             if (this.senha_old.equals(usuario.getSenha())) {
	            	 this.usuario.setSenha(senha_new);
	            	 this.usuarioDao.atualizar(this.usuario);
	            	 FacesContext context = FacesContext.getCurrentInstance();
		             context.addMessage( "sucess", new FacesMessage("Senha atualizada com sucesso!") );
	             } else {
	            	 FacesContext context = FacesContext.getCurrentInstance();
		             context.addMessage( "negative", new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não coincidem!", "") );
	             }
	        }else{
	             FacesContext context = FacesContext.getCurrentInstance();
	             context.addMessage( null, new FacesMessage( "Select Captcha") );
	          }
	         } catch (Exception e) {
	             System.out.println(e);
	         }
		System.out.println("yay");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenha_old() {
		return senha_old;
	}

	public void setSenha_old(String senha_old) {
		this.senha_old = senha_old;
	}

	public String getSenha_new() {
		return senha_new;
	}

	public void setSenha_new(String senha_new) {
		this.senha_new = senha_new;
	}
}
