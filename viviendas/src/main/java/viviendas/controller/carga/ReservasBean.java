package viviendas.controller.carga;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
@SessionScoped
@ManagedBean
public class ReservasBean {

	// Atributos de la Clase
	@EJB
	private ManagerCarga manager;

	// Atributos de PK
	private String prdId;

	private List<ArrReserva> reservas;

	private StreamedContent file3;

	public ReservasBean() {
	}

	@PostConstruct
	public void ini() {
		reservas = new ArrayList<ArrReserva>();
		InputStream stream3 = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/contratos/error.pdf");
		file3 = new DefaultStreamedContent(stream3, "application/pdf", "error.pdf");
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
	 * @return the file3
	 */
	public StreamedContent getFile3() {
		return file3;
	}

	/**
	 * @param file3
	 *            the file3 to set
	 */
	public void setFile3(StreamedContent file3) {
		this.file3 = file3;
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

	public void setearContrato(String dni) {
		try {
			file3 = new DefaultStreamedContent(
					new FileInputStream(new File(Funciones.ruta_pdf + prdId + "_" + dni + ".pdf")), "application/pdf",
					prdId + "_" + dni + ".pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
