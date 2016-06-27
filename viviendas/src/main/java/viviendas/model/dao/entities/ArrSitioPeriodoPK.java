package viviendas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the arr_sitio_periodo database table.
 * 
 */
@Embeddable
public class ArrSitioPeriodoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="prd_id", insertable=false, updatable=false)
	private String prdId;

	@Column(name="art_id")
	private String artId;

	public ArrSitioPeriodoPK() {
	}
	public String getPrdId() {
		return this.prdId;
	}
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}
	public String getArtId() {
		return this.artId;
	}
	public void setArtId(String artId) {
		this.artId = artId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ArrSitioPeriodoPK)) {
			return false;
		}
		ArrSitioPeriodoPK castOther = (ArrSitioPeriodoPK)other;
		return 
			this.prdId.equals(castOther.prdId)
			&& this.artId.equals(castOther.artId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.prdId.hashCode();
		hash = hash * prime + this.artId.hashCode();
		
		return hash;
	}
}