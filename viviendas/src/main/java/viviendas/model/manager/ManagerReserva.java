package viviendas.model.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.simple.JSONObject;

import viviendas.model.dao.entities.ArrMatriculado;
import viviendas.model.dao.entities.ArrMatriculadoPK;
import viviendas.model.dao.entities.ArrNegado;
import viviendas.model.dao.entities.ArrNegadoPK;
import viviendas.model.dao.entities.ArrParametro;
import viviendas.model.dao.entities.ArrPeriodo;
import viviendas.model.dao.entities.ArrReserva;
import viviendas.model.dao.entities.ArrSitioPeriodo;
import viviendas.model.dao.entities.ArrSitioPeriodoPK;
import viviendas.model.generic.ConsumeREST;
import viviendas.model.generic.Funciones;
import viviendas.model.generic.Mail;

/**
 * Contiene la lógica de negocio para realizar el proceso de reserva de sitios
 * 
 * @author lcisneros
 *
 */
@Stateless
public class ManagerReserva {

	@EJB
	private ManagerDAO mngDao;

	public ManagerReserva() {
	}

	/**
	 * Devuelve el último periodo activo
	 * 
	 * @return ArrPeriodo
	 */
	@SuppressWarnings("unchecked")
	public ArrPeriodo buscarPeriodoActivo() {
		List<ArrPeriodo> listado = mngDao.findWhere(ArrPeriodo.class, "o.prdEstado='A'", null);
		if (listado.isEmpty())
			return null;
		else
			return listado.get(0);
	}

	/**
	 * Busca un estudiante matriculado en un periodo
	 * 
	 * @param perDNI
	 * @param prdID
	 * @return ArrMatriculado
	 * @throws Exception
	 */
	public ArrMatriculado buscarMatriculadoPeriodo(String perDNI, String prdID) throws Exception {
		ArrMatriculadoPK pk = new ArrMatriculadoPK();
		pk.setPerDni(perDNI);
		pk.setPrdId(prdID);
		return (ArrMatriculado) mngDao.findById(ArrMatriculado.class, pk);
	}

	/**
	 * Busca un estudiante que se encuentra en lista negra en un periodo
	 * 
	 * @param perDNI
	 * @param prdID
	 * @return
	 * @throws Exception
	 */
	public boolean buscarNegadoPeriodo(String perDNI, String prdID) throws Exception {
		ArrNegadoPK pk = new ArrNegadoPK();
		pk.setPerDni(perDNI);
		pk.setPrdId(prdID);
		ArrNegado estudiante = (ArrNegado) mngDao.findById(ArrNegado.class, pk);
		if (estudiante == null)
			return false;
		else
			return true;
	}

	/**
	 * Genera y envía un mail con el token de acceso
	 * 
	 * @param estudiante
	 * @throws Exception
	 */
	public void generarEnviarToken(ArrMatriculado estudiante) throws Exception {
		String token = Funciones.genPass();
		estudiante.setMatToken(token);
		mngDao.actualizar(estudiante);
		StringBuilder destino = new StringBuilder();
		if (estudiante.getMatCorreo() != null && estudiante.getMatCorreoIns() != null) {
			destino.append(estudiante.getMatCorreo());
			destino.append(",");
			destino.append(estudiante.getMatCorreoIns());
		} else if (estudiante.getMatCorreo() != null)
			destino.append(estudiante.getMatCorreo());
		else if (estudiante.getMatCorreoIns() != null)
			destino.append(estudiante.getMatCorreoIns());
		envioMailWS(destino.toString(), "Token para reserva de vivienda", this.mensajeCorreo(token));
//		Mail.generateAndSendEmail(destino.toString(), "Token para reserva de vivienda", this.mensajeCorreo(token));
	}

	public String mensajeCorreo(String token){
		return "Estimado/a estudiante: <br/>"
				+ "<p>La finalidad de éste email es informarte que su clave de acceso es: <strong>" + token
				+ "</strong>, la misma le servirá para ingresar al nuevo sistema de reserva de vivienda. En el mismo usted  deberá descargar el contrato de arrendamiento de la vivienda para el período de clases Septiembre 2016 - Febrero 2017"
				+ "<br/>Recuerde que si usted no realiza a tiempo el ingreso al sistema y entrega su contrato, el espacio que le ha sido asignado se liberará para otorgarle a las personas  cuyas solicitudes están en lista de espera.</p>"
				+ "<br/><br/><br/><br/>" + "Área de Promoción de la Vida Comunitaria<br/>"
				+ "DIRECCIÓN BIENESTAR ESTUDIANTIL";
	}
	
	public boolean verificarTokenEstudiante(ArrMatriculadoPK matriculadoPK, String token) throws Exception {
		ArrMatriculado estudiante = (ArrMatriculado) mngDao.findById(ArrMatriculado.class, matriculadoPK);
		if (estudiante.getMatToken() == null)
			throw new Exception("Usted no posee token");
		if (estudiante.getMatToken().equals(token))
			return true;
		else
			return false;
	}

	/**
	 * Carga los sitios que se encuentran libres en el periodo
	 * 
	 * @param prdID
	 * @return List<ArrSitioPeriodo>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ArrSitioPeriodo> sitiosLibresPorPeriodo(String prdID) throws Exception {
		return mngDao.findWhere(ArrSitioPeriodo.class, "o.id.prdId='" + prdID + "'" + " AND o.sitLibres>0", null);
	}

	/**
	 * Carga los sitios que se encuentran libres en el periodo con un genero
	 * determinado
	 * 
	 * @param prdID
	 * @param genero
	 * @return List<ArrSitioPeriodo>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ArrSitioPeriodo> sitiosLibresPorPeriodoGenero(String prdID, String genero) {
		return mngDao.findWhere(ArrSitioPeriodo.class,
				"o.id.prdId='" + prdID + "'" + " AND o.sitLibres>0 AND o.sitGenero='" + genero + "'", null);
	}

	/**
	 * Valida si aún existe espacio en un sitio
	 * 
	 * @param pkSitio
	 * @return boolean
	 * @throws Exception
	 */
	public boolean existeReservaPeriodo(ArrSitioPeriodoPK pkSitio) throws Exception {
		ArrSitioPeriodo sitio = (ArrSitioPeriodo) mngDao.findById(ArrSitioPeriodo.class, pkSitio);
		System.out.println("LIBRES: " + sitio.getSitLibres());
		if (sitio.getSitLibres() > 0)
			return true;
		else
			return false;
	}

	/**
	 * Crea una reserva de sitio
	 * 
	 * @param estudiante
	 * @param sitioLibre
	 * @param prdID
	 * @param representante
	 * @throws Exception
	 */
	public void crearReserva(ArrMatriculado estudiante, ArrSitioPeriodo sitioLibre, String prdID, String representante)
			throws Exception {
		ArrReserva reserva = new ArrReserva();
		reserva.setResId(estudiante.getId().getPerDni() + prdID);
		reserva.setPerDni(estudiante.getId().getPerDni());
		reserva.setResFechaCreacion(new Date());
		reserva.setResFechaHoraCreacion(new Timestamp(new Date().getTime()));
		reserva.setArrSitioPeriodo(sitioLibre);
		reserva.setResEstado(Funciones.estadoActivo);
		mngDao.insertar(reserva);
		// REDUCIR CAPACIDAD
		reducirCapacidadSitio(sitioLibre);
		// INGRESAR REPRESENTANTE
		ingresarRepresentante(estudiante, representante);
	}

	/**
	 * Actualiza una reserva existente
	 * 
	 * @param estudiante
	 * @param prdID
	 * @param sitioLibre
	 * @param representante
	 * @throws Exception
	 */
	public void modificarReserva(ArrMatriculado estudiante, String prdID, ArrSitioPeriodo sitioLibre,
			String representante) throws Exception {
		ArrReserva reserva = buscarReservaPorID(estudiante.getId().getPerDni(), prdID);
		ArrSitioPeriodo sitioAnterior = reserva.getArrSitioPeriodo();
		if (!sitioAnterior.getSitNombre().equals(sitioLibre.getSitNombre())) {
			reserva.setResFechaCreacion(new Date());
			reserva.setResFechaHoraCreacion(new Timestamp(new Date().getTime()));
			reserva.setArrSitioPeriodo(sitioLibre);
			mngDao.actualizar(reserva);
			// AUMENTAR CAPACIDAD ANTERIOR
			aumentarCapacidadSitio(sitioAnterior);
			// REDUCIR CAPACIDAD NUEVA
			reducirCapacidadSitio(sitioLibre);
			// INGRESAR REPRESENTANTE
			ingresarRepresentante(estudiante, representante);
		}
	}

	/**
	 * Reducir capacidad de sitio
	 * 
	 * @param sitioLibre
	 * @throws Exception
	 */
	public void reducirCapacidadSitio(ArrSitioPeriodo sitioLibre) throws Exception {
		ArrSitioPeriodo sitio = (ArrSitioPeriodo) mngDao.findById(ArrSitioPeriodo.class, sitioLibre.getId());
		sitio.setSitLibres(sitio.getSitLibres() - 1);
		mngDao.actualizar(sitio);
	}

	/**
	 * Aumentar capacidad de sitio
	 * 
	 * @param sitioOcupado
	 * @throws Exception
	 */
	public void aumentarCapacidadSitio(ArrSitioPeriodo sitioOcupado) throws Exception {
		System.out.println("ANTES " + sitioOcupado.getSitLibres());
		sitioOcupado.setSitLibres(sitioOcupado.getSitLibres() + 1);
		System.out.println("DESPUES " + sitioOcupado.getSitLibres());
		mngDao.actualizar(sitioOcupado);
	}

	/**
	 * Permite ingresar el representante de un estudiante
	 * 
	 * @param estudiante
	 * @param representante
	 * @throws Exception
	 */
	public void ingresarRepresentante(ArrMatriculado estudiante, String representante) throws Exception {
		if (representante != null) {
			estudiante.setMatRepresDni((representante.split(";"))[0]);
			estudiante.setMatRepresNombre((representante.split(";"))[1]);
			mngDao.actualizar(estudiante);
		}
	}

	/**
	 * Busca reserva por ID
	 * 
	 * @param perDNI
	 * @param prdID
	 * @return ArrReserva
	 * @throws Exception
	 */
	public ArrReserva buscarReservaPorID(String perDNI, String prdID) throws Exception {
		String pk = perDNI + prdID;
		return (ArrReserva) mngDao.findById(ArrReserva.class, pk);
	}

	/**
	 * Busca matriculados pertenecientes a un sitio por periodo
	 * 
	 * @param artID
	 * @param prdID
	 * @return List<ArrMatriculado>
	 */
	@SuppressWarnings("unchecked")
	public List<ArrMatriculado> matriculadosEnSitioPorPeriodo(String artID, String prdID) {
		return mngDao.findWhere(ArrMatriculado.class,
				"o.id.prdId='" + prdID + "'" + " AND o.id.perDni IN" + " (SELECT r.perDni FROM ArrReserva r"
						+ " WHERE r.arrSitioPeriodo.id.artId='" + artID + "' AND r.arrSitioPeriodo.id.prdId='" + prdID
						+ "')",
				null);
	}

	/**
	 * Permite ingresar el nombre del contrato generado
	 * 
	 * @param estudiante
	 * @param prdID
	 * @throws Exception
	 */
	public void agregarContratoReserva(ArrMatriculado estudiante, String prdID) throws Exception {
		ArrReserva reserva = buscarReservaPorID(estudiante.getId().getPerDni(), prdID);
		reserva.setResContrato(prdID + "_" + estudiante.getId().getPerDni() + ".pdf");
		mngDao.actualizar(reserva);
	}

	@SuppressWarnings("unchecked")
	public Integer traerLibres(String sitio) {
		List<ArrSitioPeriodo> sp = mngDao.findWhere(ArrSitioPeriodo.class, "o.id.artId='" + sitio + "'", null);
		if (sp != null) {
			return sp.get(0).getSitLibres();
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	 public void envioMailWS(String para, String asunto, String body) throws Exception {
	  ArrParametro param= ParametroByIDP("email_ws");
	  ArrParametro idWS = ParametroByIDP("id_email");
	  JSONObject objSalida = new JSONObject();
	  objSalida.put("id", idWS.getParValor());
	  objSalida.put("para", para);
	  objSalida.put("asunto", asunto);
	  objSalida.put("body",body);
	  System.out.println("Envio Mail ---> "+objSalida);
	  String url = param.getParValor();
	  JSONObject respuesta = ConsumeREST.postClientRestEasy(url, objSalida);
	  if (!respuesta.get("respuesta").equals("OK"))
	   throw new Exception("Error al enviar el correo. (WS)");
	 }
	
	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public String ParametroByID(String dni) {
		try {
			ArrParametro p = (ArrParametro) mngDao.findById(ArrParametro.class, dni);
			return p.getParValor();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
	
	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public ArrParametro ParametroByIDP(String dni) {
		try {
			ArrParametro p = (ArrParametro) mngDao.findById(ArrParametro.class, dni);
			return p;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}


}
