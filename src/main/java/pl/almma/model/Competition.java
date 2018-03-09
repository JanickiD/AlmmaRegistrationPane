package pl.almma.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Competition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(unique=true)
	private String name;
	
	@NotEmpty
	private String city;
	

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	

	public Competition() {
		super();
	}

	public Competition(long id, String name, String city, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	@Override
	public String toString() {
		return "Competition [id=" + id + ", name=" + name + ", city=" + city + ", date=" + date + "]";
	}
	

}
