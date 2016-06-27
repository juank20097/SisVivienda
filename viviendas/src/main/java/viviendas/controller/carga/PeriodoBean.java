package viviendas.controller.carga;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import viviendas.model.dao.entities.ArrPeriodo;
import viviendas.model.generic.Funciones;
import viviendas.model.generic.Mensaje;
import viviendas.model.manager.ManagerCarga;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class PeriodoBean {

	// Atributos de la Clase
	@EJB
	private ManagerCarga manager;

	private String prdId;
	private String prdDescripcion;
	private String prdEstado;
	private Date prdFechaFin;
	private Date prdFechaInicio;
	private boolean edicion;

	public PeriodoBean() {
		
	}
	
	@PostConstruct
	public void pub() {
		edicion = false;
	}

	/**
	 * @return the edicion
	 */
	public boolean isEdicion() {
		return edicion;
	}

	/**
	 * @param edicion
	 *            the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	/**
	 * @return the prdId
	 */
	public String getPrdId() {
		return prdId;
	}

	/**
	 * @param prdId
	 *            the prdId to set
	 */
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	/**
	 * @return the prdDescripcion
	 */
	public String getPrdDescripcion() {
		return prdDescripcion;
	}

	/**
	 * @param prdDescripcion
	 *            the prdDescripcion to set
	 */
	public void setPrdDescripcion(String prdDescripcion) {
		this.prdDescripcion = prdDescripcion;
	}

	/**
	 * @return the prdEstado
	 */
	public String getPrdEstado() {
		return prdEstado;
	}

	/**
	 * @param prdEstado
	 *            the prdEstado to set
	 */
	public void setPrdEstado(String prdEstado) {
		this.prdEstado = prdEstado;
	}

	/**
	 * @return the prdFechaFin
	 */
	public Date getPrdFechaFin() {
		return prdFechaFin;
	}

	/**
	 * @param prdFechaFin
	 *            the prdFechaFin to set
	 */
	public void setPrdFechaFin(Date prdFechaFin) {
		this.prdFechaFin = prdFechaFin;
	}

	/**
	 * @return the prdFechaInicio
	 */
	public Date getPrdFechaInicio() {
		return prdFechaInicio;
	}

	/**
	 * @param prdFechaInicio
	 *            the prdFechaInicio to set
	 */
	public void setPrdFechaInicio(Date prdFechaInicio) {
		this.prdFechaInicio = prdFechaInicio;
	}

	public List<ArrPeriodo> getListPeriodo() {
		return manager.todosPeriodos();
	}

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevoPeriodo() {
		edicion = false;
		return "nperiodo?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de una institucion
	 * 
	 * @return
	 */
	public String crearPeriodo() {
		String r = "";
		try {
			if (prdFechaInicio==null || prdFechaFin==null )
				Mensaje.crearMensajeERROR("Las fechas son valores invalidos");
			if (edicion) {	
				manager.editarPeriodo(prdId, prdDescripcion, prdFechaInicio,
						prdFechaFin, prdEstado);
				Mensaje.crearMensajeINFO("Actualizado - Periodo Modificada");
				r = "periodo?faces-redirect=true";
				// limpiar datos
				prdId = "";
				prdDescripcion = "";
				prdFechaInicio = null;
				prdFechaFin = null;
				prdEstado = "A";
				edicion = false;
			} else {
				if (prdFechaInicio.after(prdFechaFin)){
					Mensaje.crearMensajeERROR("Las fecha fin es menor que la fecha inicio");
					}
				else{
				this.cambioEstado();
				manager.insertarPeriodo(prdId, prdDescripcion, prdFechaInicio,
						prdFechaFin, "A");
				Mensaje.crearMensajeINFO("Registrado - Periodo Creada");
				r = "periodo?faces-redirect=true";
				// limpiar datos
				prdId = "";
				prdDescripcion = "";
				prdFechaInicio = null;
				prdFechaFin = null;
				prdEstado = "A";
				edicion = false;
				}
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}
	
	public void cambioEstado(){
			try {
				List<ArrPeriodo> p=manager.PeriodosActivos();
				if (p!=null){
				for (ArrPeriodo per : p) {
				manager.editarPeriodoEstado(per.getPrdId(), "I");}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Metodo para cargar una Intitucion para su edicion
	 * 
	 * @param t
	 * @return
	 */
	public String cargarPeriodo(ArrPeriodo t) {
		prdId = t.getPrdId();
		prdDescripcion = t.getPrdDescripcion();
		prdFechaFin = t.getPrdFechaFin();
		prdFechaInicio = t.getPrdFechaInicio();
		prdEstado = t.getPrdEstado();
		edicion = true;
		return "nperiodo?faces-redirect=true";
	}

	/**
	 * Cancela la accion de modificar o crear Institucion
	 * 
	 * @return
	 */
	public String salir() {
		// limpiar datos
		prdId = "";
		prdDescripcion = "";
		prdFechaInicio = null;
		prdFechaFin = null;
		prdEstado = "A";
		edicion = false;
		return "periodo?faces-redirect=true";
	}

	/**
	 * Lista de estados
	 * 
	 * @return lista de items de estados
	 */
	public List<SelectItem> getlistEstados() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.estadoActivo, Funciones.estadoActivo
				+ " : " + Funciones.valorEstadoActivo));
		lista.add(new SelectItem(Funciones.estadoInactivo,
				Funciones.estadoInactivo + " : "
						+ Funciones.valorEstadoInactivo));
		return lista;
	}

}
