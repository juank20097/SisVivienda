package viviendas.controller.reserve;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.DocumentException;

import viviendas.model.dao.entities.ArrMatriculado;
import viviendas.model.dao.entities.ArrPeriodo;
import viviendas.model.dao.entities.ArrReserva;
import viviendas.model.dao.entities.ArrSitioPeriodo;
import viviendas.model.generic.Contrato;
import viviendas.model.generic.Funciones;
import viviendas.model.generic.Mensaje;
import viviendas.model.manager.ManagerReserva;

@ManagedBean
@ViewScoped
public class ReservaBean implements Serializable {

	/**
	 * SERIAL ID
	 */
	private static final long serialVersionUID = 6733054739694344059L;

	@EJB
	private ManagerReserva mngRes;

	private String dniEstudiante;
	private String token;
	private ArrPeriodo periodo;
	private String dniRepresentante;
	private String nombreRepresentante;
	private ArrMatriculado estudiante;
	private ArrReserva reserva;
	private String sitioId;
	private HashMap<String, ArrSitioPeriodo> hashSitios;
	private ArrSitioPeriodo sitio;
	private List<SelectItem> sitiosLibres;
	private List<ArrMatriculado> reservasSitio;

	private boolean tokenOk;
	private boolean mayorEdad;
	private boolean finalizado;

	private boolean reservar;
	private Integer libres;

	private StreamedContent archivo;

	public ReservaBean() {

	}

	@PostConstruct
	public void init() {
		periodo = mngRes.buscarPeriodoActivo();
		hashSitios = new HashMap<String, ArrSitioPeriodo>();
		sitiosLibres = new ArrayList<SelectItem>();
		reservasSitio = new ArrayList<ArrMatriculado>();
		tokenOk = false;
		mayorEdad = true;
		finalizado = false;
		libres = null;
		reservar = false;
		sitioId = null;
		archivo = new DefaultStreamedContent(
				((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
						.getResourceAsStream(File.separatorChar + "contratos" + File.separatorChar + "error.pdf"),
				"texto/pdf", "error.pdf");
	}

	/**
	 * @return the libres
	 */
	public Integer getLibres() {
		return libres;
	}

	/**
	 * @param libres
	 *            the libres to set
	 */
	public void setLibres(Integer libres) {
		this.libres = libres;
	}

	/**
	 * @return the reservar
	 */
	public boolean isReservar() {
		return reservar;
	}

	/**
	 * @param reservar
	 *            the reservar to set
	 */
	public void setReservar(boolean reservar) {
		this.reservar = reservar;
	}

	/**
	 * @return the dniEstudiante
	 */
	public String getDniEstudiante() {
		return dniEstudiante;
	}

	/**
	 * @param dniEstudiante
	 *            the dniEstudiante to set
	 */
	public void setDniEstudiante(String dniEstudiante) {
		this.dniEstudiante = dniEstudiante;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the dniRepresentante
	 */
	public String getDniRepresentante() {
		return dniRepresentante;
	}

	/**
	 * @param dniRepresentante
	 *            the dniRepresentante to set
	 */
	public void setDniRepresentante(String dniRepresentante) {
		this.dniRepresentante = dniRepresentante;
	}

	/**
	 * @return the nombreRepresentante
	 */
	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	/**
	 * @param nombreRepresentante
	 *            the nombreRepresentante to set
	 */
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	/**
	 * @return the estudiante
	 */
	public ArrMatriculado getEstudiante() {
		return estudiante;
	}

	/**
	 * @param estudiante
	 *            the estudiante to set
	 */
	public void setEstudiante(ArrMatriculado estudiante) {
		this.estudiante = estudiante;
	}

	/**
	 * @return the sitioId
	 */
	public String getSitioId() {
		return sitioId;
	}

	/**
	 * @param sitioId
	 *            the sitioId to set
	 */
	public void setSitioId(String sitioId) {
		this.sitioId = sitioId;
	}

	/**
	 * @return the sitio
	 */
	public ArrSitioPeriodo getSitio() {
		return sitio;
	}

	/**
	 * @param sitio
	 *            the sitio to set
	 */
	public void setSitio(ArrSitioPeriodo sitio) {
		this.sitio = sitio;
	}

	/**
	 * @return the reserva
	 */
	public ArrReserva getReserva() {
		return reserva;
	}

	/**
	 * @param reserva
	 *            the reserva to set
	 */
	public void setReserva(ArrReserva reserva) {
		this.reserva = reserva;
	}

	/**
	 * @return the sitiosLibres
	 */
	public List<SelectItem> getSitiosLibres() {
		return sitiosLibres;
	}

	/**
	 * @return the reservasSitio
	 */
	public List<ArrMatriculado> getReservasSitio() {
		return reservasSitio;
	}

	/**
	 * @return the tokenOk
	 */
	public boolean isTokenOk() {
		return tokenOk;
	}

	/**
	 * @return the mayorEdad
	 */
	public boolean isMayorEdad() {
		return mayorEdad;
	}

	/**
	 * @return the finalizado
	 */
	public boolean isFinalizado() {
		return finalizado;
	}

	/**
	 * @return the archivo
	 */
	public StreamedContent getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(StreamedContent archivo) {
		this.archivo = archivo;
	}

	/**
	 * Verifica si un estudiante se encuentra matriculado para poder continuar
	 * con el proceso de reserva
	 */
	public void verificarMatriculado() {
		try {
			if (periodo == null)
				Mensaje.crearMensajeERROR("ERROR AL BUSCAR PERIODO HABILITADO");
			else {
				if (mngRes.buscarNegadoPeriodo(getDniEstudiante(), periodo.getPrdId()))
					Mensaje.crearMensajeWARN(
							"Usted no puede realizar una reserva. Para más información diríjase a las oficinas de Bienestar Universitario.");
				else {
					estudiante = mngRes.buscarMatriculadoPeriodo(getDniEstudiante(), periodo.getPrdId());
					if (estudiante == null) {
						Mensaje.crearMensajeWARN(
								"Usted no esta registrado. Para más información diríjase a las oficinas de Bienestar Universitario.");
					} else {
						mngRes.generarEnviarToken(getEstudiante());
						RequestContext.getCurrentInstance().execute("PF('dlgtoken').show();");
					}
				}
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error verificar matrícula: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Valida el token enviado al correo
	 */
	public void validarToken() {
		try {
			if (getToken() == null || getToken().trim().isEmpty())
				Mensaje.crearMensajeWARN("Debe ingresar su token para continuar.");
			else if (!mngRes.verificarTokenEstudiante(getEstudiante().getId(), getToken()))
				Mensaje.crearMensajeWARN("Su token es incorrecto. Verifíquelo.");
			else {
				reserva = mngRes.buscarReservaPorID(getDniEstudiante(), periodo.getPrdId());
				if (reserva != null && reserva.getResEstado().equals(Funciones.estadoFinalizado))
					Mensaje.crearMensajeWARN("Usted ya posee una reserva finalizada:");
				else {
					tokenOk = true;
					mayorEdad = Funciones.mayorDeEdad(getEstudiante().getMatFechaNacimiento());
					cargarSitiosLibres();
					RequestContext.getCurrentInstance().execute("PF('dlgtoken').hide();");
				}
			}
			setToken("");
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al validar token: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Reenvio de token a estudiante
	 */
	public void reenviarToken() {
		try {
			mngRes.generarEnviarToken(getEstudiante());
			Mensaje.crearMensajeINFO("Token reenviado correctamente.");
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al reenviar token: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Permite reservar un sitio
	 */
	public void reservarSitio() {
		try {
			if (sitioId == null)
				Mensaje.crearMensajeWARN("Debe seleccionar sitio para la reserva.");
			else if (!mayorEdad && (getDniRepresentante() == null || getNombreRepresentante() == null
					|| getDniRepresentante().trim().isEmpty() || getDniRepresentante().length() < 9
					|| !Funciones.isNumeric(getDniRepresentante()) || !Funciones.validacionCedula(getDniRepresentante())
					|| getNombreRepresentante().trim().isEmpty()))
				Mensaje.crearMensajeWARN("Los datos de representante son requeridos, y la cédula debe ser válida.");
			else {
				if (reserva != null) {// POSEE RESERVA
					modificarReserva();
					System.out.println("MODIFICA");
				} else {
					ingresarReserva();
					System.out.println("INGRESA");
				}
				finalizado = true;
				reserva = mngRes.buscarReservaPorID(getDniEstudiante(), periodo.getPrdId());// CARGAR
																							// RESERVA
																							// ACTUAL
				cargarSitiosLibres();
				cargarEstudiantesSitio();
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al realizar reserva: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Ingresar una nueva reserva
	 */
	private void ingresarReserva() throws Exception {
		if (mngRes.existeReservaPeriodo(getSitio().getId())) {
			if (mayorEdad){
				mngRes.crearReserva(getEstudiante(), getSitio(), periodo.getPrdId(), null);
				libres=mngRes.traerLibres(getSitio().getId().getArtId());
			}else{
				mngRes.crearReserva(getEstudiante(), getSitio(), periodo.getPrdId(),
						getDniRepresentante() + ";" + getNombreRepresentante());
				libres=mngRes.traerLibres(getSitio().getId().getArtId());
			}
			Mensaje.crearMensajeINFO("Reserva realizada correctamente, no olvide descargar su contrato.");
		} else {
			Mensaje.crearMensajeWARN("El sitio seleccionado ya esta copado, favor eliga otro.");
		}
	}

	/**
	 * Modificar una reserva existente
	 */
	private void modificarReserva() throws Exception {
		if (mngRes.existeReservaPeriodo(getSitio().getId())) {
			if (mayorEdad){
				mngRes.modificarReserva(getEstudiante(), periodo.getPrdId(), getSitio(), null);
				libres=mngRes.traerLibres(getSitio().getId().getArtId());
			}else{
				mngRes.modificarReserva(getEstudiante(), periodo.getPrdId(), getSitio(),
						getDniRepresentante() + ";" + getNombreRepresentante());
				libres=mngRes.traerLibres(getSitio().getId().getArtId());
				}
			Mensaje.crearMensajeINFO("Reserva realizada correctamente, no olvide descargar su contrato.");
		} else {
			Mensaje.crearMensajeWARN("El sitio seleccionado ya esta copado, favor eliga otro.");
		}
	}

	/**
	 * Cargar List<SelectItem> de sitios disponibles
	 */
	private void cargarSitiosLibres() {
		List<ArrSitioPeriodo> listado = mngRes.sitiosLibresPorPeriodoGenero(periodo.getPrdId(),
				getEstudiante().getMatGenero());
		hashSitios = new HashMap<String, ArrSitioPeriodo>();
		sitiosLibres = new ArrayList<SelectItem>();
		if (listado != null && !listado.isEmpty()) {
			getSitiosLibres().add(new SelectItem(0, "Seleccionar"));
			for (ArrSitioPeriodo sitio : listado) {
				getSitiosLibres().add(new SelectItem(sitio.getId().getArtId(), sitio.getSitNombre()));
				hashSitios.put(sitio.getId().getArtId(), sitio);
			}
		}
	}

	/**
	 * Carga de estudiantes pertenecientes a un sitio
	 */
	public void cargarEstudiantesSitio() {
		List<ArrMatriculado> listado = mngRes.matriculadosEnSitioPorPeriodo(sitioId, periodo.getPrdId());
		getReservasSitio().clear();
		if (listado != null && !listado.isEmpty())
			getReservasSitio().addAll(listado);
	}

	/**
	 * Asignar a la variable de sitio en selecOneMenu
	 */
	public void seleccionSitio() {
		if (sitioId != null) {
			sitio = hashSitios.get(sitioId);
			cargarEstudiantesSitio();
			libres=mngRes.traerLibres(getSitio().getId().getArtId());
			System.out.println(libres);
		}
	}

	/**
	 * Muestra el nombre del sitio reservado
	 * 
	 * @return String
	 */
	public String sitioArrendado() {
		if (reserva == null) {
			reservar = false;
			return "Todavía no posee un sitio reservado";
		} else {
			reservar = true;
			return reserva.getArrSitioPeriodo().getSitNombre();
		}
	}

	/**
	 * Generar PDF de contrato
	 */
	public void generarContrato() {
		try {
			String nombre = Contrato.generarContrato(estudiante, periodo, sitio.getSitNombre());
			setArchivo(new DefaultStreamedContent(new FileInputStream(new File(Funciones.ruta_pdf + nombre)),
					"application/pdf", nombre));
			// MODIFICAR NOMBRE CONTRATO
			mngRes.agregarContratoReserva(estudiante, periodo.getPrdId());
		} catch (FileNotFoundException e) {
			try {
				setArchivo(new DefaultStreamedContent(new FileInputStream(new File(Funciones.ruta_pdf + "error.pdf")),
						"application/pdf", "error.pdf"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Mensaje.crearMensajeERROR("FileNotFoundException: " + e.getMessage());
		} catch (DocumentException e) {
			try {
				setArchivo(new DefaultStreamedContent(new FileInputStream(new File(Funciones.ruta_pdf + "error.pdf")),
						"application/pdf", "error.pdf"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Mensaje.crearMensajeERROR("DocumentException: " + e.getMessage());
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: " + e.getMessage());
		}
	}

	public String cambiarGenero(String genero) {
		if (genero.equals("M")) {
			return "Masculino";
		} else {
			return "Femenino";
		}
	}

}
