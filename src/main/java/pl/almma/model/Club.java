package pl.almma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
	@Column(columnDefinition = "bool default true")
	private Boolean active;
	
	@ManyToOne
	private User trainer;
	

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

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

	public Club(long club_id, String name, String city, Boolean active, User trainer) {
		super();
		this.club_id = club_id;
		this.name = name;
		this.city = city;
		this.active = active;
		this.trainer = trainer;
	}

	@Override
	public String toString() {
		return "Club [club_id=" + club_id + ", name=" + name + ", city=" + city + ", active=" + active + ", trainer="
				+ trainer + "]";
	}

	

	
}
