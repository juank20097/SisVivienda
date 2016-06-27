package viviendas.model.dao.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the arr_matriculados database table.
 * 
 */
@Embeddable
public class ArrMatriculadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="per_dni", length=20)
	private String perDni;

	@Column(name="prd_id", insertable=false, updatable=false)
	private String prdId;

	public ArrMatriculadoPK() {
	}
	public String getPerDni() {
		return this.perDni;
	}
	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}
	public String getPrdId() {
		return this.prdId;
	}
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ArrMatriculadoPK)) {
			return false;
		}
		ArrMatriculadoPK castOther = (ArrMatriculadoPK)other;
		return 
			this.perDni.equals(castOther.perDni)
			&& this.prdId.equals(castOther.prdId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.perDni.hashCode();
		hash = hash * prime + this.prdId.hashCode();
		
		return hash;
	}
}