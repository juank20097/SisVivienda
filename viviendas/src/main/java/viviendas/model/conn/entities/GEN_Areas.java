package viviendas.model.conn.entities;

/***********************************************************************
 * Module:  GEN_Areas.java
 * Author:  jestevez
 * Purpose: Defines the Class GEN_Areas
 ***********************************************************************/


/** @pdOid 28bcb252-2199-453b-ac31-ccea5f57158e */
public class GEN_Areas {

	/** @pdOid 04467253-2905-4808-a683-e8956a33d419 */
	public int are_id;
	/** @pdOid b55c8151-ddc0-49bb-b8fc-e79338acb28c */
	public String are_nombre;
	/** @pdOid 8096517e-f89d-4489-a4f7-33197c5b7971 */
	public String are_descripcion;
	/** @pdOid 76de0f9a-24d5-4a3e-b2bb-6302dba7804e */
	public char are_estado;

	public int gen_sector;

	/** @pdOid 3d3a5585-6c76-41e8-a754-2e9e41761c93 */
	public GEN_Areas() {
		// TODO: implement
	}

	/**
	 * @return the gen_sector
	 */
	public int getGen_sector() {
		return gen_sector;
	}

	/**
	 * @param gen_sector
	 *            the gen_sector to set
	 */
	public void setGen_sector(int gen_sector) {
		this.gen_sector = gen_sector;
	}

	/** @pdOid cba04d60-a97e-4e76-821a-d29857b6e2f1 */
	public int getAre_id() {
		return are_id;
	}

	/**
	 * @param newAre_id
	 * @pdOid da335aaf-e7c5-4bcb-a472-c4b082f50f90
	 */
	public void setAre_id(int newAre_id) {
		are_id = newAre_id;
	}

	/** @pdOid 5be7d8a4-c736-4ae7-bc42-1c2f787d5c69 */
	public String getAre_nombre() {
		return are_nombre;
	}

	/**
	 * @param newAre_nombre
	 * @pdOid 1ce8d38a-6c88-4152-bb0e-9beadb66a0c8
	 */
	public void setAre_nombre(String newAre_nombre) {
		are_nombre = newAre_nombre;
	}

	/** @pdOid 04713b94-a087-4eac-bcfe-3b3485eafaf2 */
	public String getAre_descripcion() {
		return are_descripcion;
	}

	/**
	 * @param newAre_descripcion
	 * @pdOid 2aed4dd4-6f27-4715-94cb-981a873d0962
	 */
	public void setAre_descripcion(String newAre_descripcion) {
		are_descripcion = newAre_descripcion;
	}

	/** @pdOid 4b05f476-7b47-43fd-b400-a142180b4969 */
	public char getAre_estado() {
		return are_estado;
	}

	/**
	 * @param newAre_estado
	 * @pdOid 286095a4-827b-4926-809b-4e17c8390125
	 */
	public void setAre_estado(char newAre_estado) {
		are_estado = newAre_estado;
	}

}