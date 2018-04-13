package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "usuarioBean")
public class UsuarioBean {
	
	public void logout() {
      HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
      sessao.invalidate();   
    }
}
