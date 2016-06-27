package viviendas.model.dao.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the arr_reserva database table.
 * 
 */
@Entity
@Table(name="arr_reserva")
@NamedQuery(name="ArrReserva.findAll", query="SELECT a FROM ArrReserva a")
public class ArrReserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="res_id", length=40)
	private String resId;

	@Column(name="per_dni", length=20)
	private String perDni;

	@Column(name="res_contrato", columnDefinition="text")
	private String resContrato;

	@Column(name="res_estado", columnDefinition="bpchar", length=1)
	private String resEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="res_fecha_creacion")
	private Date resFechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="res_fecha_finalizacion")
	private Date resFechaFinalizacion;

	@Column(name="res_fecha_hora_creacion")
	private Timestamp resFechaHoraCreacion;

	@Column(name="res_fecha_hora_finalizacion")
	private Timestamp resFechaHoraFinalizacion;

	@Column(name="res_usuario", length=30)
	private String resUsuario;

	//bi-directional many-to-one association to ArrSitioPeriodo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="art_id", referencedColumnName="art_id"),
		@JoinColumn(name="prd_id", referencedColumnName="prd_id")
		})
	private ArrSitioPeriodo arrSitioPeriodo;

	public ArrReserva() {
	}

	public String getResId() {
		return this.resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getPerDni() {
		return this.perDni;
	}

	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	public String getResContrato() {
		return this.resContrato;
	}

	public void setResContrato(String resContrato) {
		this.resContrato = resContrato;
	}

	public String getResEstado() {
		return this.resEstado;
	}

	public void setResEstado(String resEstado) {
		this.resEstado = resEstado;
	}

	public Date getResFechaCreacion() {
		return this.resFechaCreacion;
	}

	public void setResFechaCreacion(Date resFechaCreacion) {
		this.resFechaCreacion = resFechaCreacion;
	}

	public Date getResFechaFinalizacion() {
		return this.resFechaFinalizacion;
	}

	public void setResFechaFinalizacion(Date resFechaFinalizacion) {
		this.resFechaFinalizacion = resFechaFinalizacion;
	}

	public Timestamp getResFechaHoraCreacion() {
		return this.resFechaHoraCreacion;
	}

	public void setResFechaHoraCreacion(Timestamp resFechaHoraCreacion) {
		this.resFechaHoraCreacion = resFechaHoraCreacion;
	}

	public Timestamp getResFechaHoraFinalizacion() {
		return this.resFechaHoraFinalizacion;
	}

	public void setResFechaHoraFinalizacion(Timestamp resFechaHoraFinalizacion) {
		this.resFechaHoraFinalizacion = resFechaHoraFinalizacion;
	}

	public String getResUsuario() {
		return this.resUsuario;
	}

	public void setResUsuario(String resUsuario) {
		this.resUsuario = resUsuario;
	}

	public ArrSitioPeriodo getArrSitioPeriodo() {
		return this.arrSitioPeriodo;
	}

	public void setArrSitioPeriodo(ArrSitioPeriodo arrSitioPeriodo) {
		this.arrSitioPeriodo = arrSitioPeriodo;
	}

}