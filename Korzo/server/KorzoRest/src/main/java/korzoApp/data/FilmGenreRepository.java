package korzoApp.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import korzoApp.model.FilmGenre;

@Component
public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long>{
	
	public Page<FilmGenre> findByGenreIdOrderByFilmTitleAsc(long genreId, Pageable page);
	
	// testing combining film + genre search (see film controller)
	public List<FilmGenre> findByGenreId(Long id);

}
