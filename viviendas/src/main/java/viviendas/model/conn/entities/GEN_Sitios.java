package viviendas.model.conn.entities;

/***********************************************************************
 * Module:  GEN_Sitios.java
 * Author:  jestevez
 * Purpose: Defines the Class GEN_Sitios
 ***********************************************************************/

/** @pdOid bb8414a9-0ae3-4600-b3b6-83d896b333e5 */
public class GEN_Sitios {

	/** @pdOid d3bcb3f9-5c9c-42b6-b273-fb638a52aae5 */
	public String sit_codigo;
	/** @pdOid 7eff8abd-1d42-476e-9669-7e927ffd63ef */
	public java.lang.String sit_nombre;
	/** @pdOid ffa9bac3-ca68-44dd-ba7e-61ba4efd3103 */
	public double sit_costo_arriendo;
	/** @pdOid edb17163-e54b-472d-8cf6-b2e22a27126f */
	public int sit_capacidad;
	/** @pdOid 2de2ad85-502b-4374-9e1e-827e05d11c8e */
	public char sit_estado;

	public int gEN_Areas;

	public int gEN_TipoSitios;

	/** @pdOid 0efc2de9-2254-40a0-8730-2c86bd012d0d */
	public GEN_Sitios() {
		// TODO: implement
	}

	/**
	 * @return the gEN_Areas
	 */
	public int getgEN_Areas() {
		return gEN_Areas;
	}

	/**
	 * @param gEN_Areas
	 *            the gEN_Areas to set
	 */
	public void setgEN_Areas(int gEN_Areas) {
		this.gEN_Areas = gEN_Areas;
	}

	/**
	 * @return the gEN_TipoSitios
	 */
	public int getgEN_TipoSitios() {
		return gEN_TipoSitios;
	}

	/**
	 * @param gEN_TipoSitios
	 *            the gEN_TipoSitios to set
	 */
	public void setgEN_TipoSitios(int gEN_TipoSitios) {
		this.gEN_TipoSitios = gEN_TipoSitios;
	}

	/**
	 * @return the sit_codigo
	 */
	public String getSit_codigo() {
		return sit_codigo;
	}

	/**
	 * @param sit_codigo
	 *            the sit_codigo to set
	 */
	public void setSit_codigo(String sit_codigo) {
		this.sit_codigo = sit_codigo;
	}

	/** @pdOid b974c732-92ac-4a0b-ae63-09d152dec8f9 */
	public double getSit_costo_arriendo() {
		return sit_costo_arriendo;
	}

	/**
	 * @param newSit_costo_arriendo
	 * @pdOid fe5fe908-0bb3-4f48-b299-f83cb5a8e0ba
	 */
	public void setSit_costo_arriendo(double newSit_costo_arriendo) {
		sit_costo_arriendo = newSit_costo_arriendo;
	}

	/** @pdOid aa7135ee-a826-4577-a94e-0fc936eb9c4a */
	public int getSit_capacidad() {
		return sit_capacidad;
	}

	/**
	 * @param newSit_capacidad
	 * @pdOid 7e875f68-3bf8-40dd-adc0-daf2668bfabe
	 */
	public void setSit_capacidad(int newSit_capacidad) {
		sit_capacidad = newSit_capacidad;
	}

	/** @pdOid 7b2a3075-cd4b-444e-9833-33005803f353 */
	public char getSit_estado() {
		return sit_estado;
	}

	/**
	 * @param newSit_estado
	 * @pdOid 356f37fa-9ec8-4e07-ae4a-9758b6b332ab
	 */
	public void setSit_estado(char newSit_estado) {
		sit_estado = newSit_estado;
	}

	/** @pdOid 6bfdbdca-4d15-4186-9e69-76e699fa7024 */
	public java.lang.String getSit_nombre() {
		return sit_nombre;
	}

	/**
	 * @param newSit_nombre
	 * @pdOid 4b295cbe-ee28-49fb-a69f-6db0f50c2fb1
	 */
	public void setSit_nombre(java.lang.String newSit_nombre) {
		sit_nombre = newSit_nombre;
	}

}