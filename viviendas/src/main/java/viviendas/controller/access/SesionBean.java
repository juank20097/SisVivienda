package viviendas.controller.access;



import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import viviendas.model.access.Menu;
import viviendas.model.generic.Mensaje;
import viviendas.model.manager.ManagerAcceso;



@ManagedBean
@SessionScoped
public class SesionBean implements Serializable{

	/**
	 * SERIAL ID
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerAcceso mngAcc;
	
	private String usuario;
	private String pass;
	private List<Menu> menu;
	
	public SesionBean() {
		menu = new ArrayList<Menu>();
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
			
	/**
	 * @return the menu
	 */
	public List<Menu> getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	
	
	/**
	 * Permite ingresar al sistema
	 * @return
	 */
	public String logIn(){
		try {
			if(getUsuario()==null || getUsuario().isEmpty() || getPass()==null || getPass().isEmpty()){
				Mensaje.crearMensajeWARN("Campos usuario y contraseña requeridos");
				return "";
			}else{
				setMenu(mngAcc.loginWS(getUsuario(), getPass(), "ARRHAB"));
				setPass(null);
				return "/admin/views/index?faces-redirect=true";
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());setPass(null);
			return "";
		}
	}
	
	/**
	 * Permite salir del Sistema
	 * @return
	 */
	public String logOut(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        setPass(null);setUsuario(null);setMenu(new ArrayList<Menu>());
        return "/admin/index?faces-redirect=true";
	}
	
//	/**
//	 * Verifica y devuelve el usuario en sesión
//	 * @param vista página principal de acceso
//	 * @return String
//	 */
//	public String validarSesion(String vista){
//		 HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//	                .getExternalContext().getSession(false);
//		 session.setMaxInactiveInterval(1200);
//	     SesionBean user = (SesionBean) session.getAttribute("sesionBean");
//	     System.out.println(user.getUsuario());
//	     if (user==null || user.getUsuario() == null) {
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/viviendas/admin/index.xhtml");
//            } catch (IOException ex) {
//            	Mensaje.crearMensajeERROR(ex.getMessage());
//            }
//            return null;
//        }else{
//        	ManagerAcceso ma = new ManagerAcceso();
//        	if(ma.poseePermiso(vista, user.getMenu()))
//        		return user.getUsuario();
//        	else{
//        		try {
//       				FacesContext.getCurrentInstance().getExternalContext().redirect("/viviendas/admin/views/index.xhtml");
//	            } catch (IOException ex) {
//	            	Mensaje.crearMensajeERROR(ex.getMessage());
//	            }
//	            return null;
//        	}
//        }
//	}
	
	/**
	 * Verifica y devuelve el usuario en sesión
	 * @param vista página principal de acceso
	 * @return String
	 */
	public String validarSesion(){
		 HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	                .getExternalContext().getSession(false);
		 session.setMaxInactiveInterval(1200);
	     SesionBean user = (SesionBean) session.getAttribute("sesionBean");
	     System.out.println(user.getUsuario());
	     if (user==null || user.getUsuario() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/viviendas/admin/index.xhtml");
            } catch (IOException ex) {
            	Mensaje.crearMensajeERROR(ex.getMessage());
            }
            return null;
        }else{
        	return user.getUsuario();
        }
	}

}
