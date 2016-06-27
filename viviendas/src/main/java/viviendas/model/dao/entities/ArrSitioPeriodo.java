package viviendas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the arr_sitio_periodo database table.
 * 
 */
@Entity
@Table(name="arr_sitio_periodo")
@NamedQuery(name="ArrSitioPeriodo.findAll", query="SELECT a FROM ArrSitioPeriodo a")
public class ArrSitioPeriodo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArrSitioPeriodoPK id;

	@Column(name="sit_capacidad")
	private Integer sitCapacidad;
	
	@Column(name="sit_libres")
	private Integer sitLibres;

	@Column(name="sit_genero", columnDefinition="bpchar", length=1)
	private String sitGenero;

	@Column(name="sit_nombre", length=100)
	private String sitNombre;

	@Column(name="sit_valor_arriendo")
	private BigDecimal sitValorArriendo;

	//bi-directional many-to-one association to ArrReserva
	@OneToMany(mappedBy="arrSitioPeriodo")
	private List<ArrReserva> arrReservas;

	//bi-directional many-to-one association to ArrPeriodo
	@ManyToOne
	@JoinColumn(name="prd_id",insertable=false, updatable=false)
	private ArrPeriodo arrPeriodo;

	public ArrSitioPeriodo() {
	}

	public ArrSitioPeriodoPK getId() {
		return this.id;
	}

	public void setId(ArrSitioPeriodoPK id) {
		this.id = id;
	}

	public Integer getSitCapacidad() {
		return this.sitCapacidad;
	}

	public void setSitCapacidad(Integer sitCapacidad) {
		this.sitCapacidad = sitCapacidad;
	}
	
	public Integer getSitLibres() {
		return sitLibres;
	}
	
	public void setSitLibres(Integer sitLibres) {
		this.sitLibres = sitLibres;
	}

	public String getSitGenero() {
		return this.sitGenero;
	}

	public void setSitGenero(String sitGenero) {
		this.sitGenero = sitGenero;
	}

	public String getSitNombre() {
		return this.sitNombre;
	}

	public void setSitNombre(String sitNombre) {
		this.sitNombre = sitNombre;
	}

	public BigDecimal getSitValorArriendo() {
		return this.sitValorArriendo;
	}

	public void setSitValorArriendo(BigDecimal sitValorArriendo) {
		this.sitValorArriendo = sitValorArriendo;
	}

	public List<ArrReserva> getArrReservas() {
		return this.arrReservas;
	}

	public void setArrReservas(List<ArrReserva> arrReservas) {
		this.arrReservas = arrReservas;
	}

	public ArrReserva addArrReserva(ArrReserva arrReserva) {
		getArrReservas().add(arrReserva);
		arrReserva.setArrSitioPeriodo(this);

		return arrReserva;
	}

	public ArrReserva removeArrReserva(ArrReserva arrReserva) {
		getArrReservas().remove(arrReserva);
		arrReserva.setArrSitioPeriodo(null);

		return arrReserva;
	}

	public ArrPeriodo getArrPeriodo() {
		return this.arrPeriodo;
	}

	public void setArrPeriodo(ArrPeriodo arrPeriodo) {
		this.arrPeriodo = arrPeriodo;
	}

}