package viviendas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the arr_parametros database table.
 * 
 */
@Entity
@Table(name="arr_parametros")
@NamedQuery(name="ArrParametro.findAll", query="SELECT a FROM ArrParametro a")
public class ArrParametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="par_id")
	private String parId;

	@Column(name="par_descripcion")
	private String parDescripcion;

	@Column(name="par_nombre")
	private String parNombre;

	@Column(name="par_valor")
	private String parValor;

	public ArrParametro() {
	}

	public String getParId() {
		return this.parId;
	}

	public void setParId(String parId) {
		this.parId = parId;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public String getParNombre() {
		return this.parNombre;
	}

	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	public String getParValor() {
		return this.parValor;
	}

	public void setParValor(String parValor) {
		this.parValor = parValor;
	}

}