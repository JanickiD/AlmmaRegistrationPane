package pl.almma.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Article {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date date;
	
	private String title;
	
	@ManyToOne
	private User author;
	
	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Article(long id, Date date, String title, User author, String content) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.author = author;
		this.content = content;
	}

	public Article() {
		super();
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", date=" + date + ", title=" + title + ", author=" + author + ", content="
				+ content + "]";
	}
	
	
	
}
