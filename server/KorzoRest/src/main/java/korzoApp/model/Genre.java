package korzoApp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(catalog="korzo", name="genre")
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String genre;
	
	
	@OneToMany(mappedBy="genre")
	private Set<FilmGenre> films = new HashSet<FilmGenre>();

	public Genre() {
		super();
	}

	public Genre(long id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<FilmGenre> getFilms() {
		return films;
	}

	public void setFilms(Set<FilmGenre> films) {
		this.films = films;
	}

	
	
}
