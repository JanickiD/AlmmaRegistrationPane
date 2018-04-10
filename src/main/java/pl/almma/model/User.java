package pl.almma.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private boolean active;
	
	@NotEmpty(message= "{pl.almma.notEmpty}")
	private String name;
	
	@NotEmpty(message= "{pl.almma.notEmpty}")
	private String lastName;
	
	@NotEmpty(message= "{pl.almma.notEmpty}")
	@Email(message="{pl.almma.email}")
	@Column(unique= true)
	private String email;
	
	@Column(unique= true)
	@NotEmpty(message= "{pl.almma.notEmpty}")
	private String pesel;
	
	@Length(min=4,  message="{pl.almma.min}")
	private String pass;
	
	@JoinColumn(name="role_id")
	@ManyToOne
	private Role role;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	private String profileImageFileName;
	
	@OneToMany(mappedBy="author")
	private Collection<Article> articles;
	
	@ManyToOne
	@JoinColumn(name="club_id")
	private Club club;
	

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public String getProfileImageFileName() {
		return profileImageFileName;
	}

	public void setProfileImageFileName(String profileImageFileName) {
		this.profileImageFileName = profileImageFileName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public User(String name, String lastName, String email, String pesel, String pass, Role role, Boolean active, Club club) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.pesel = pesel;
		this.pass = pass;
		this.role = role;
		this.active = active;
		this.club = club;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", active=" + active + ", name=" + name + ", lastName=" + lastName + ", email="
				+ email + ", pesel=" + pesel + ", pass=" + pass + ", role=" + role + "]";
	}
	
	
}
