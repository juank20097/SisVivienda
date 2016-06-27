package viviendas.model.dao.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the arr_periodo database table.
 * 
 */
@Entity
@Table(name="arr_periodo")
@NamedQuery(name="ArrPeriodo.findAll", query="SELECT a FROM ArrPeriodo a")
public class ArrPeriodo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="prd_id", length=50)
	private String prdId;

	@Column(name="prd_descripcion", columnDefinition="text")
	private String prdDescripcion;

	@Column(name="prd_estado", columnDefinition="bpchar", length=1)
	private String prdEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="prd_fecha_fin")
	private Date prdFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="prd_fecha_inicio")
	private Date prdFechaInicio;

	//bi-directional many-to-one association to ArrMatriculado
	@OneToMany(mappedBy="arrPeriodo")
	private List<ArrMatriculado> arrMatriculados;

	//bi-directional many-to-one association to ArrSitioPeriodo
	@OneToMany(mappedBy="arrPeriodo")
	private List<ArrSitioPeriodo> arrSitioPeriodos;

	public ArrPeriodo() {
	}

	public String getPrdId() {
		return this.prdId;
	}

	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	public String getPrdDescripcion() {
		return this.prdDescripcion;
	}

	public void setPrdDescripcion(String prdDescripcion) {
		this.prdDescripcion = prdDescripcion;
	}

	public String getPrdEstado() {
		return this.prdEstado;
	}

	public void setPrdEstado(String prdEstado) {
		this.prdEstado = prdEstado;
	}

	public Date getPrdFechaFin() {
		return this.prdFechaFin;
	}

	public void setPrdFechaFin(Date prdFechaFin) {
		this.prdFechaFin = prdFechaFin;
	}

	public Date getPrdFechaInicio() {
		return this.prdFechaInicio;
	}

	public void setPrdFechaInicio(Date prdFechaInicio) {
		this.prdFechaInicio = prdFechaInicio;
	}

	public List<ArrMatriculado> getArrMatriculados() {
		return this.arrMatriculados;
	}

	public void setArrMatriculados(List<ArrMatriculado> arrMatriculados) {
		this.arrMatriculados = arrMatriculados;
	}

	public ArrMatriculado addArrMatriculado(ArrMatriculado arrMatriculado) {
		getArrMatriculados().add(arrMatriculado);
		arrMatriculado.setArrPeriodo(this);

		return arrMatriculado;
	}

	public ArrMatriculado removeArrMatriculado(ArrMatriculado arrMatriculado) {
		getArrMatriculados().remove(arrMatriculado);
		arrMatriculado.setArrPeriodo(null);

		return arrMatriculado;
	}

	public List<ArrSitioPeriodo> getArrSitioPeriodos() {
		return this.arrSitioPeriodos;
	}

	public void setArrSitioPeriodos(List<ArrSitioPeriodo> arrSitioPeriodos) {
		this.arrSitioPeriodos = arrSitioPeriodos;
	}

	public ArrSitioPeriodo addArrSitioPeriodo(ArrSitioPeriodo arrSitioPeriodo) {
		getArrSitioPeriodos().add(arrSitioPeriodo);
		arrSitioPeriodo.setArrPeriodo(this);

		return arrSitioPeriodo;
	}

	public ArrSitioPeriodo removeArrSitioPeriodo(ArrSitioPeriodo arrSitioPeriodo) {
		getArrSitioPeriodos().remove(arrSitioPeriodo);
		arrSitioPeriodo.setArrPeriodo(null);

		return arrSitioPeriodo;
	}

}