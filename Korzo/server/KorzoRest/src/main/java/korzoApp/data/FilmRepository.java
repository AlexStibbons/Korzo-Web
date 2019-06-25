package korzoApp.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import korzoApp.model.Film;

@Component
public interface FilmRepository extends JpaRepository<Film, Long>{

	// page all of these
	public List<Film> findByTitleContainsIgnoreCase(String film);
	public List<Film> findByYear(int year); // order alphabetically
	public List<Film> findByYearGreaterThanAndYearLessThan(int greater, int less); // year range
	
	public Page<Film> findAllByOrderByTitleAsc(Pageable page);
	
	public Page<Film> findByTitleContainsIgnoreCaseOrderByTitleAsc(String title, Pageable page);
}
