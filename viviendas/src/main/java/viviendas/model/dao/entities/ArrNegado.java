package viviendas.model.dao.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the arr_negados database table.
 * 
 */
@Entity
@Table(name="arr_negados")
@NamedQuery(name="ArrNegado.findAll", query="SELECT a FROM ArrNegado a")
public class ArrNegado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArrNegadoPK id;

	@Column(name="neg_razon", columnDefinition="text")
	private String negRazon;

	public ArrNegado() {
	}

	public ArrNegadoPK getId() {
		return this.id;
	}

	public void setId(ArrNegadoPK id) {
		this.id = id;
	}

	public String getNegRazon() {
		return this.negRazon;
	}

	public void setNegRazon(String negRazon) {
		this.negRazon = negRazon;
	}

}