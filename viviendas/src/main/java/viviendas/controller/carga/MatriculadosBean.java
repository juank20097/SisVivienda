package viviendas.controller.carga;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import viviendas.controller.access.SesionBean;
import viviendas.model.dao.entities.ArrMatriculado;
import viviendas.model.dao.entities.ArrNegado;
import viviendas.model.dao.entities.ArrPeriodo;
import viviendas.model.generic.Funciones;
import viviendas.model.generic.Mensaje;
import viviendas.model.manager.ManagerCarga;

/**
 * @author jestevez
 * 
 */
@ViewScoped
@ManagedBean
public class MatriculadosBean {

	// Atributos de la Clase
	@EJB
	private ManagerCarga manager;
	@Inject
	private SesionBean session;

	// Atributos de PK
	private String prdId;
	private String perId;

	// Atributos de insercion
	private String matNombre;
	private Date matFechaNacimiento;
	private String matCarrera;
	private String matCorreo;
	private String matCorreoIns;
	private String matGenero;
	private String matNivel;
	private String matRepresDni;
	private String matRepresNombre;

	private int NUMERO_COLUMNAS_EXCEL = 8;
	private int NUMERO_COLUMNAS_EXCEL2 = 2;

	// listas de registros
	private List<ArrMatriculado> matriculados;
	private List<ArrNegado> negados;
	private List<String> errores;
	
	//atributo de url_excel
	private String url_excel;

	public MatriculadosBean() {
	}
	
	@PostConstruct
	public void ini() {
		session.validarSesion();
		matriculados = new ArrayList<ArrMatriculado>();
		errores = new ArrayList<String>();
		negados = new ArrayList<ArrNegado>();
		url_excel= manager.ParametroByID("dir_excel");
	}
	
	public void validarSesion(){
		session.validarSesion();
	}

	/**
	 * @return the negados
	 */
	public List<ArrNegado> getNegados() {
		return negados;
	}

	/**
	 * @param negados
	 *            the negados to set
	 */
	public void setNegados(List<ArrNegado> negados) {
		this.negados = negados;
	}

	/**
	 * @return the matriculados
	 */
	public List<ArrMatriculado> getMatriculados() {
		return matriculados;
	}

	/**
	 * @param matriculados
	 *            the matriculados to set
	 */
	public void setMatriculados(List<ArrMatriculado> matriculados) {
		this.matriculados = matriculados;
	}

	/**
	 * @return the errores
	 */
	public List<String> getErrores() {
		return errores;
	}

	/**
	 * @param errores
	 *            the errores to set
	 */
	public void setErrores(List<String> errores) {
		this.errores = errores;
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
	 * @return the perId
	 */
	public String getPerId() {
		return perId;
	}

	/**
	 * @param perId
	 *            the perId to set
	 */
	public void setPerId(String perId) {
		this.perId = perId;
	}

	/**
	 * @return the matNombre
	 */
	public String getMatNombre() {
		return matNombre;
	}

	/**
	 * @param matNombre
	 *            the matNombre to set
	 */
	public void setMatNombre(String matNombre) {
		this.matNombre = matNombre;
	}

	/**
	 * @return the matFechaNacimiento
	 */
	public Date getMatFechaNacimiento() {
		return matFechaNacimiento;
	}

	/**
	 * @param matFechaNacimiento
	 *            the matFechaNacimiento to set
	 */
	public void setMatFechaNacimiento(Date matFechaNacimiento) {
		this.matFechaNacimiento = matFechaNacimiento;
	}

	/**
	 * @return the matCarrera
	 */
	public String getMatCarrera() {
		return matCarrera;
	}

	/**
	 * @param matCarrera
	 *            the matCarrera to set
	 */
	public void setMatCarrera(String matCarrera) {
		this.matCarrera = matCarrera;
	}

	/**
	 * @return the matCorreo
	 */
	public String getMatCorreo() {
		return matCorreo;
	}

	/**
	 * @param matCorreo
	 *            the matCorreo to set
	 */
	public void setMatCorreo(String matCorreo) {
		this.matCorreo = matCorreo;
	}

	/**
	 * @return the matCorreoIns
	 */
	public String getMatCorreoIns() {
		return matCorreoIns;
	}

	/**
	 * @param matCorreoIns
	 *            the matCorreoIns to set
	 */
	public void setMatCorreoIns(String matCorreoIns) {
		this.matCorreoIns = matCorreoIns;
	}

	/**
	 * @return the matGenero
	 */
	public String getMatGenero() {
		return matGenero;
	}

	/**
	 * @param matGenero
	 *            the matGenero to set
	 */
	public void setMatGenero(String matGenero) {
		this.matGenero = matGenero;
	}

	/**
	 * @return the matNivel
	 */
	public String getMatNivel() {
		return matNivel;
	}

	/**
	 * @param matNivel
	 *            the matNivel to set
	 */
	public void setMatNivel(String matNivel) {
		this.matNivel = matNivel;
	}

	/**
	 * @return the matRepresDni
	 */
	public String getMatRepresDni() {
		return matRepresDni;
	}

	/**
	 * @param matRepresDni
	 *            the matRepresDni to set
	 */
	public void setMatRepresDni(String matRepresDni) {
		this.matRepresDni = matRepresDni;
	}

	/**
	 * @return the matRepresNombre
	 */
	public String getMatRepresNombre() {
		return matRepresNombre;
	}

	/**
	 * @param matRepresNombre
	 *            the matRepresNombre to set
	 */
	public void setMatRepresNombre(String matRepresNombre) {
		this.matRepresNombre = matRepresNombre;
	}

	/**
	 * Método para listar negados
	 */
	private void ListNegados() {
		try {
			negados = manager.NegadoByPeriodo(prdId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método para listar matriculados
	 */
	private void ListMatriculados() {
		try {
			matriculados = manager.MatriculadoByPeriodo(prdId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lista de periodos
	 * 
	 * @return lista de items de estados
	 */
	public List<SelectItem> getlistPeriodo() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		ArrPeriodo per = manager.PeriodoAct();
		if (per != null)
			lista.add(new SelectItem(per.getPrdId(), per.getPrdId()));
		return lista;
	}

	

	/**
	 * Maneja el proceso de selección, carga e inserción de datos de personas,
	 * partiendo de un archivo excel .XLS
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (prdId == null || prdId.isEmpty() || prdId.equals("-1")) {
				Mensaje.crearMensajeWARN("Debe seleccionar obligatoriamente un periodo");
			} else {

				if (event.getFile() == null)
					throw new Exception("No se ha seleccionado archivo");
				else {
					validarGuardarDatosExcel(event.getFile());
				}
			}
			this.ListMatriculados();
			this.ListNegados();
		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	/**
	 * Maneja el proceso de selección, carga e inserción de datos de personas,
	 * partiendo de un archivo excel .XLS
	 * 
	 * @param event
	 */
	public void handleFileUpload2(FileUploadEvent event) {
		try {
			if (prdId == null || prdId.isEmpty() || prdId.equals("-1")) {
				Mensaje.crearMensajeWARN("Debe seleccionar obligatoriamente un periodo");
			} else {

				if (event.getFile() == null)
					throw new Exception("No se ha seleccionado archivo");
				else {
					validarGuardarDatosExcel2(event.getFile());
				}
			}
			this.ListMatriculados();
			this.ListNegados();
		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	/**
	 * Valida y Almacena los datos de excel
	 * 
	 * @param archivo
	 * @throws Exception
	 */
	public void validarGuardarDatosExcel(UploadedFile archivo) throws Exception {
		matriculados = new ArrayList<>();
		errores = new ArrayList<>();
		List<String> datosFila = new ArrayList<String>();
		// Toma la primera hoja
		Sheet hoja = Workbook.getWorkbook(archivo.getInputstream()).getSheet(0);
		// Validar estructura de excel
		if (!poseeEstructuraValida(hoja))
			throw new Exception("El archivo no posee la estructura correcta.");
		// Recorre todas las filas y columnas
		for (int i = 1; i < hoja.getRows(); i++) {
			if (filaValida(hoja.getRow(i), i + 1)) {
				datosFila.clear();
				for (int j = 0; j < NUMERO_COLUMNAS_EXCEL; j++) {
					datosFila.add(hoja.getCell(j, i).getContents().trim());
						System.out.println("Dato "+j+"-->"+hoja.getCell(j,i).getContents());
				}
				// Guardar datos en array
				matriculados.add(manager.crearMatriculado(datosFila, prdId));
			}
		}
		// ingresar personas
		manager.ingresarMatriculado(matriculados);
		// mostrar errores
		if (errores.size() > 0) {
			mostrarListaErrores();
			Mensaje.crearMensajeWARN("Existió errores dentro del archivo, "
					+ "pero los datos sin error fueron guardados.");
		} else
			Mensaje.crearMensajeINFO("Datos ingresados correctamente");

	}

	/**
	 * Valida y Almacena los datos de excel
	 * 
	 * @param archivo
	 * @throws Exception
	 */
	public void validarGuardarDatosExcel2(UploadedFile archivo)
			throws Exception {
		negados = new ArrayList<>();
		errores = new ArrayList<>();
		List<String> datosFila = new ArrayList<String>();
		// Toma la primera hoja
		Sheet hoja = Workbook.getWorkbook(archivo.getInputstream()).getSheet(0);
		// Validar estructura de excel
		if (!poseeEstructuraValida2(hoja))
			throw new Exception("El archivo no posee la estructura correcta.");
		// Recorre todas las filas y columnas
		for (int i = 1; i < hoja.getRows(); i++) {
			if (filaValida2(hoja.getRow(i), i + 1)) {
				datosFila.clear();
				for (int j = 0; j < NUMERO_COLUMNAS_EXCEL2; j++) {
					datosFila.add(hoja.getCell(j, i).getContents().trim());
				}
				// Guardar datos en array
				negados.add(manager.crearNegado(datosFila, prdId));
			}
		}
		// ingresar personas
		manager.ingresarNegado(negados);
		// mostrar errores
		if (errores.size() > 0) {
			mostrarListaErrores();
			Mensaje.crearMensajeWARN("Existió errores dentro del archivo, "
					+ "pero los datos sin error fueron guardados.");
		} else
			Mensaje.crearMensajeINFO("Datos ingresados correctamente");

	}

	/**
	 * Abre un popup con la lista de errores
	 */
	private void mostrarListaErrores() {
		for (String error : errores) {
			System.out.println(error);
		}
		RequestContext.getCurrentInstance().execute("PF('dlgerr').show()");
	}

	/**
	 * Valida la extructura de Excel
	 * 
	 * @param hoja
	 * @return boolean
	 */
	private boolean poseeEstructuraValida2(Sheet hoja) {
		return manager.validarEncabezadosExcel2(hoja.getRow(0));
	}

	/**
	 * Valida la extructura de Excel
	 * 
	 * @param hoja
	 * @return boolean
	 */
	private boolean poseeEstructuraValida(Sheet hoja) {
		return manager.validarEncabezadosExcel(hoja.getRow(0));
	}

	/**
	 * Valida los datos de una fila
	 * 
	 * @param column
	 * @param nroFila
	 * @return boolean
	 */
	private boolean filaValida(Cell[] column, int nroFila) {
		String error = manager.validarFilaExcel(column);
		if (error.isEmpty())
			return true;
		else {
			errores.add("Fila NRO " + nroFila + " : " + error);
			return false;
		}
	}

	/**
	 * Valida los datos de una fila
	 * 
	 * @param column
	 * @param nroFila
	 * @return boolean
	 */
	private boolean filaValida2(Cell[] column, int nroFila) {
		String error = manager.validarFilaExcel2(column);
		if (error.isEmpty())
			return true;
		else {
			errores.add("Fila NRO " + nroFila + " : " + error);
			return false;
		}
	}

	/**
	 * Método para ver periodo
	 */
	public void verPeriodo() {
		System.out.println(prdId);
		this.ListMatriculados();
		this.ListNegados();
	}
	
	/**
	 * Método para descargar los archivos de ejemplo
	 */
	public void descargarArchivoEjemplo() {
//		ServletContext servletContext = (ServletContext) FacesContext
//				.getCurrentInstance().getExternalContext().getContext();
//		String contextPath = servletContext.getRealPath(File.separator
//				+ "resources/excel/Ejemplo_Base_Matriculados.xls");
		Funciones.descargarExcel(url_excel+"Ejemplo_Base_Matriculados.xls");
	}
	
	/**
	 * Método para descargar los archivos de ejemplo
	 */
	public void descargarArchivoEjemplo2() {
//		ServletContext servletContext = (ServletContext) FacesContext
//				.getCurrentInstance().getExternalContext().getContext();
//		String contextPath = servletContext.getRealPath(File.separator
//				+ "resources/excel/Ejemplo_Base_Lista_Negra.xls");
		Funciones.descargarExcel(url_excel+"Ejemplo_Base_Lista_Negra.xls");
	}

}
