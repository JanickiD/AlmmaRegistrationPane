package pl.almma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long club_id;

	@NotEmpty
	@Column(unique = true)
	private String name;

	@NotEmpty
	private String city;
	
	

	public long getClub_id() {
		return club_id;
	}

	public void setClub_id(long club_id) {
		this.club_id = club_id;
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

	public Club() {
		super();
	}

	public Club(long club_id, String name, String city) {
		super();
		this.club_id = club_id;
		this.name = name;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Club [club_id=" + club_id + ", name=" + name + ", city=" + city + "]";
	}
	

	
}
