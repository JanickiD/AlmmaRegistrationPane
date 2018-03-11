package pl.almma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Attachment {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String orginalName;

	private String size;

	@NotEmpty
	private String mime;

	private String savedName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrginalName() {
		return orginalName;
	}

	public void setOrginalName(String orginalName) {
		this.orginalName = orginalName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getSavedName() {
		return savedName;
	}

	public void setSavedName(String savedName) {
		this.savedName = savedName;
		
	}
	
	

	public Attachment(String orginalName, String size, String mime, String savedName) {
		super();
		this.orginalName = orginalName;
		this.size = size;
		this.mime = mime;
		this.savedName = savedName;
	}
	
	

	public Attachment() {
		super();
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", orginalName=" + orginalName + ", size=" + size + ", mime=" + mime
				+ ", savedName=" + savedName + "]";
	}

}
