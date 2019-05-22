package korzoApp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(catalog="korzo", name="filmgenre")
public class FilmGenre {
	
	// https://www.baeldung.com/jpa-many-to-many
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Film film;
	
	@ManyToOne(fetch = FetchType.EAGER) // @JoinColum(name="genre_id")?
	private Genre genre;

	public FilmGenre() {
		super();
	}

	public FilmGenre(long id, Film film, Genre genre) {
		super();
		this.id = id;
		this.film = film;
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	
	
}
