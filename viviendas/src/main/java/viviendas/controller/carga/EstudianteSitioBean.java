package viviendas.controller.carga;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import viviendas.model.dao.entities.ArrPeriodo;
import viviendas.model.dao.entities.ArrReserva;
import viviendas.model.generic.Mensaje;
import viviendas.model.manager.ManagerCarga;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @author jestevez
 * 
 */
@ViewScoped
@ManagedBean
public class EstudianteSitioBean {

	// Atributos de la Clase
	@EJB
	private ManagerCarga manager;
//	@Inject
//	private SesionBean session;

	private int NUMERO_COLUMNAS_EXCEL = 2;
	
	private String prdId;

	// listas de registros
	private List<ArrReserva> l_estudiantes;
	private List<String> errores;

	// string con todos los errores
	private String error;

	public EstudianteSitioBean() {
	}

	@PostConstruct
	public void ini() {
		l_estudiantes = new ArrayList<ArrReserva>();
		errores = new ArrayList<String>();
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
	 * @return the l_estudiantes
	 */
	public List<ArrReserva> getL_estudiantes() {
		return l_estudiantes;
	}

	/**
	 * @param l_estudiantes
	 *            the l_estudiantes to set
	 */
	public void setL_estudiantes(List<ArrReserva> l_estudiantes) {
		this.l_estudiantes = l_estudiantes;
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
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	// MÉTODOS_DE_EXCEL///////////////////////////////////////////////////////////////////////////////

	/**
	 * Maneja el proceso de selección, carga e inserción de datos de
	 * estudiantes, partiendo de un archivo excel .XLS
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (getPrdId()==null || getPrdId().equals("-1")){
				Mensaje.crearMensajeWARN("Debe seleccionar un periodo para cargar el archivo.");
			}else{
			if (event.getFile() == null)
				throw new Exception("No se ha seleccionado archivo");
			else {
				validarGuardarDatosExcel(event.getFile());
				// this.ListEstudiantes();
			}
			}
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
		l_estudiantes = new ArrayList<ArrReserva>();
		errores = new ArrayList<String>();
		List<String> datosFila = new ArrayList<String>();
		// Toma la primera hoja
		Sheet hoja = Workbook.getWorkbook(archivo.getInputstream()).getSheet(0);
		// Validar estructura de excel
		if (!poseeEstructuraValidaExternos(hoja))
			throw new Exception("El archivo no posee la estructura correcta.");
		// Recorre todas las filas y columnas
		for (int i = 1; i < hoja.getRows(); i++) {
			if (filaValida(hoja.getRow(i), i+1)) {
				datosFila.clear();
				// Método para saber los datos de todo el excel
				for (int j = 0; j < NUMERO_COLUMNAS_EXCEL; j++) {
					datosFila.add(hoja.getCell(j, i).getContents().trim());
					System.out.println("fila:" + i + " ,columna:" + j + " dato:" + hoja.getCell(j, i).getContents());
				}
				l_estudiantes.add(manager.crearReserva(datosFila,prdId));
			}
		}
		// ingreso de estudiantes
		manager.ingresarEstudiante(l_estudiantes);
		// mostrar errores
		if (errores.size() > 0) {
			mostrarListaErrores();
			Mensaje.crearMensajeWARN(
					"Existió errores dentro del archivo, " + "pero los datos sin error fueron guardados. ");
		} else
			// Método para cargar Registro de Excel
			Mensaje.crearMensajeINFO("Datos ingresados correctamente. \n");
	}

	/**
	 * Abre un popup con la lista de errores
	 */
	private void mostrarListaErrores() {
		RequestContext.getCurrentInstance().execute("PF('dlgerr').show()");
		for (String e : errores) {
			System.out.println(e);
		}
		
	}

	/**
	 * Valida la extructura de Excel
	 * 
	 * @param hoja
	 * @return boolean
	 */
	private boolean poseeEstructuraValidaExternos(Sheet hoja) {
		return manager.validarEncabezadosExcel3(hoja.getRow(0));
	}

	/**
	 * Valida los datos de una fila
	 * 
	 * @param column
	 * @param nroFila
	 * @return boolean
	 */
	private boolean filaValida(Cell[] column, int nroFila) {
		String error = manager.validarFilaExcel3(column);
		if (error.isEmpty())
			return true;
		else {
			errores.add("Fila NRO " + nroFila + " : " + error);
			return false;
		}
	}
	
	/**
	 * Método para verificar el periodo sleccionado
	 */
	public void verPeriodo() {
		System.out.println(prdId);
		l_estudiantes = new ArrayList<ArrReserva>();
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
	
	//proceso de sitios Libres
}
