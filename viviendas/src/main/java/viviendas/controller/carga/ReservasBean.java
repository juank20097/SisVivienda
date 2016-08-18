package viviendas.controller.carga;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import viviendas.controller.access.SesionBean;
import viviendas.model.dao.entities.ArrMatriculado;
import viviendas.model.dao.entities.ArrPeriodo;
import viviendas.model.dao.entities.ArrReserva;
import viviendas.model.generic.Funciones;
import viviendas.model.generic.Mensaje;
import viviendas.model.manager.ManagerCarga;

/**
 * @author jestevez
 * 
 */
/**
 * @author jestevez
 *
 */
@ViewScoped
@ManagedBean
public class ReservasBean {

	// Atributos de la Clase
	@EJB
	private ManagerCarga manager;
	@Inject
	private SesionBean session;

	// Atributos de PK
	private String prdId;
	
	//atributo de url_pdf
	private String url_pdf;

	private List<ArrReserva> reservas;

	public ReservasBean() {
	}

	@PostConstruct
	public void ini() {
		session.validarSesion();
		reservas = new ArrayList<ArrReserva>();
		url_pdf=manager.ParametroByID("dir_contratos");
	}

	/**
	 * @return the prdId
	 */
	public String getPrdId() {
		return prdId;
	}

	/**
	 * @param prdId the prdId to set
	 */
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	/**
	 * Método para cambiar de estado
	 * 
	 * @param a
	 * @return
	 */
	public String estadosX(String a) {
		if (a.equals("A"))
			return "Activado";
		else
			return "Finalizado";
	}

	/**
	 * Método para obtener el periodo seleccionado
	 */
	public void validarYCarga() {
		System.out.println(prdId);
	}

	/**
	 * Lista de periodos
	 * 
	 * @return lista de items de estados
	 */
	public List<SelectItem> getlistPeriodoAll() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<ArrPeriodo> per = manager.findAllPeriodos();
		if (per != null)
			for (ArrPeriodo p : per) {
				lista.add(new SelectItem(p.getPrdId(), p.getPrdId()));
			}
		return lista;
	}

	/**
	 * Lista de periodos
	 * 
	 * @return lista de items de estados
	 */
	public List<ArrReserva> getlistReserva() {
		try {
			reservas = manager.ReservaByPeriodo(prdId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservas;
	}

	/**
	 * metodo para eliminar una reserva
	 * 
	 * @param res
	 */
	public void eliminarR(ArrReserva res) {
		if (res.getArrSitioPeriodo().getArrPeriodo().getPrdEstado().equals("A")) {
			manager.eliminarReserva(res);
		} else {
			Mensaje.crearMensajeWARN("La Reserva en dicho Periodo no puede ser Eliminada por ser un periodo Inactivo");
		}

	}

	/**
	 * Metodo para activar el boton de finalización
	 * 
	 * @param est
	 * @return
	 */
	public boolean estado(String est) {
		if (est.equals("F")) {
			return true;
		} else {
			return false;
		}
	}

	public String finalizar(ArrReserva res) {
		try {
			manager.cambiarEstado(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public String nombre(String ci) {
		ArrMatriculado m;
		String re = "";
		try {
			m = manager.MatriculadoByCI(ci.trim());
			re = m.getMatNombre();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}

	public void bajarContrato(String dni) {
		try {
			Funciones.descargarPdf(url_pdf +prdId+"_"+dni+".pdf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Funciones.descargarPdf(url_pdf + "error.pdf");
			e.printStackTrace();
		}
	}

}
