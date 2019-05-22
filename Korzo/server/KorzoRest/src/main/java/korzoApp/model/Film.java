package korzoApp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(catalog="korzo", name="film")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	private int year;
	
	private boolean domestic;
	
	@Column(nullable = false)
	private String storage;
	
	
	@OneToMany(mappedBy="film", fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	private Set<FilmGenre> genres = new HashSet<FilmGenre>();

	public Film() {
		super();
	}

	public Film(long id, String title, int year, boolean domestic) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.domestic = domestic;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isDomestic() {
		return domestic;
	}

	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}
	
	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public Set<FilmGenre> getGenres() {
		return genres;
	}

	public void setGenres(Set<FilmGenre> genres) {
		this.genres = genres;
	}
	
	
}
