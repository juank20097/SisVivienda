package viviendas.controller.carga;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import viviendas.controller.access.SesionBean;
import viviendas.model.dao.entities.ArrMatriculado;
import viviendas.model.dao.entities.ArrPeriodo;
import viviendas.model.dao.entities.ArrReserva;
import viviendas.model.generic.Funciones;
import viviendas.model.generic.Mensaje;
import viviendas.model.manager.ManagerCarga;
import viviendas.model.manager.ManagerReserva;

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
	@EJB
	private ManagerReserva managerReserva;
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
			if (!getPrdId().equals("Abril2016-Agosto2016")){
				reservas = manager.ReservaByPeriodo(prdId);
				this.crearExcel(reservas);
			}
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
	
	// ////////////////////////////////////////(Métodos_creación_excel_imprimir)///////////////////////////////////////////////////////

			public void crearExcel(List<ArrReserva> reserva) {
				try {
					ServletContext servletContext = (ServletContext) FacesContext
							.getCurrentInstance().getExternalContext().getContext();
					String url = servletContext.getRealPath(File.separator
							+ "resources/excel");
//					String url=url_doc+"/descarga";
					System.out.println(url);
					HSSFWorkbook libro = new HSSFWorkbook();
					HSSFSheet hoja = libro.createSheet("Datos");
					if (reserva!=null){
					for (int i = 0; i <= reserva.size() - 1; i++) {
						HSSFRow row = hoja.createRow(i);
						llenarFilaExcel(reserva.get(i), row);
					}
					}
					OutputStream out = new FileOutputStream(url+File.separator
							+ "Reporte_Reservas.xls");
					libro.write(out);
					libro.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			public void llenarFilaExcel(ArrReserva reserva, HSSFRow row) {
				if (row.getRowNum() == 0) {
					HSSFCell celda0 = row.createCell(0);
					celda0.setCellValue("CÉDULA");
					HSSFCell celda1 = row.createCell(1);
					celda1.setCellValue("USUARIO");
					HSSFCell celda2 = row.createCell(2);
					celda2.setCellValue("PERIODO");
					HSSFCell celda3 = row.createCell(3);
					celda3.setCellValue("SITIO");
					HSSFCell celda4 = row.createCell(4);
					celda4.setCellValue("ESTADO");
				} else {
					HSSFCell celda0 = row.createCell(0);
					celda0.setCellValue(reserva.getPerDni());
					HSSFCell celda1 = row.createCell(1);
					celda1.setCellValue(reserva.getResUsuario());
					HSSFCell celda2 = row.createCell(2);
					celda2.setCellValue(reserva.getArrSitioPeriodo().getId().getPrdId());
					HSSFCell celda3 = row.createCell(3);
					celda3.setCellValue(reserva.getArrSitioPeriodo().getSitNombre());
					HSSFCell celda4 = row.createCell(4);
					celda4.setCellValue(reserva.getResEstado());
				}
			}

			
			public void descargarReservas() {
				if (getPrdId()==null || getPrdId().equals("-1")){
					Mensaje.crearMensajeWARN("No se puede realizar la exportación del archivo porque el periodo está vacío o nulo.");
				}else{
					ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
							.getContext();
					String contextPath = servletContext
							.getRealPath(File.separator + "resources/excel/Reporte_Reservas.xls");
//				Funciones.descargarExcel(url_doc+"/descargaDatosExcel_Estudiante.xls");
				Funciones.descargarExcel(contextPath);
				}
			}

}
