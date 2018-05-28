package pl.almma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CompetitionStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CompetitionStatus(long id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public CompetitionStatus() {
		super();
	}

	@Override
	public String toString() {
		return "CompetitionStatus [id=" + id + ", status=" + status + "]";
	}
	
	

}
