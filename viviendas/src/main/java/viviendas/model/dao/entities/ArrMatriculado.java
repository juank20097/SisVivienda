package viviendas.model.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the arr_matriculados database table.
 * 
 */
@Entity
@Table(name="arr_matriculados")
@NamedQuery(name="ArrMatriculado.findAll", query="SELECT a FROM ArrMatriculado a")
public class ArrMatriculado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArrMatriculadoPK id;
	
	@Column(name="mat_nombre", length=300)
	private String matNombre;
	
	@Temporal(TemporalType.DATE)
	@Column(name="mat_fecha_nacimiento")
	private Date matFechaNacimiento;
	
	@Column(name="mat_carrera", length=150)
	private String matCarrera;

	@Column(name="mat_correo", length=150)
	private String matCorreo;

	@Column(name="mat_correo_ins", length=150)
	private String matCorreoIns;

	@Column(name="mat_genero", columnDefinition="bpchar" , length=1)
	private String matGenero;

	@Column(name="mat_nivel", length=150)
	private String matNivel;

	@Column(name="mat_repres_dni", length=20)
	private String matRepresDni;

	@Column(name="mat_repres_nombre", length=300)
	private String matRepresNombre;

	@Column(name="mat_token", length=10)
	private String matToken;

	//bi-directional many-to-one association to ArrPeriodo
	@ManyToOne
	@JoinColumn(name="prd_id", insertable=false, updatable=false)
	private ArrPeriodo arrPeriodo;

	public ArrMatriculado() {
	}

	public ArrMatriculadoPK getId() {
		return this.id;
	}

	public void setId(ArrMatriculadoPK id) {
		this.id = id;
	}
	
	public String getMatNombre() {
		return matNombre;
	}
	
	public void setMatNombre(String matNombre) {
		this.matNombre = matNombre;
	}
	
	public Date getMatFechaNacimiento() {
		return matFechaNacimiento;
	}
	
	public void setMatFechaNacimiento(Date matFechaNacimiento) {
		this.matFechaNacimiento = matFechaNacimiento;
	}
	
	public String getMatCarrera() {
		return this.matCarrera;
	}

	public void setMatCarrera(String matCarrera) {
		this.matCarrera = matCarrera;
	}

	public String getMatCorreo() {
		return this.matCorreo;
	}

	public void setMatCorreo(String matCorreo) {
		this.matCorreo = matCorreo;
	}

	public String getMatCorreoIns() {
		return this.matCorreoIns;
	}

	public void setMatCorreoIns(String matCorreoIns) {
		this.matCorreoIns = matCorreoIns;
	}

	public String getMatGenero() {
		return this.matGenero;
	}

	public void setMatGenero(String matGenero) {
		this.matGenero = matGenero;
	}

	public String getMatNivel() {
		return this.matNivel;
	}

	public void setMatNivel(String matNivel) {
		this.matNivel = matNivel;
	}

	public String getMatRepresDni() {
		return this.matRepresDni;
	}

	public void setMatRepresDni(String matRepresDni) {
		this.matRepresDni = matRepresDni;
	}

	public String getMatRepresNombre() {
		return this.matRepresNombre;
	}

	public void setMatRepresNombre(String matRepresNombre) {
		this.matRepresNombre = matRepresNombre;
	}

	public String getMatToken() {
		return this.matToken;
	}

	public void setMatToken(String matToken) {
		this.matToken = matToken;
	}

	public ArrPeriodo getArrPeriodo() {
		return this.arrPeriodo;
	}

	public void setArrPeriodo(ArrPeriodo arrPeriodo) {
		this.arrPeriodo = arrPeriodo;
	}

}