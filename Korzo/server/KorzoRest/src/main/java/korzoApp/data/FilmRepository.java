package korzoApp.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import korzoApp.model.Film;

@Component
public interface FilmRepository extends JpaRepository<Film, Long>{ 
	
	public Page<Film> findAllByOrderByTitleAsc(Pageable page);
	
	public Page<Film> findByTitleContainsIgnoreCaseOrderByTitleAsc(String title, Pageable page);
	
	// find by year and year range --> why search films by year range at all? these are not needed 
	public Page<Film> findByYear(int year, Pageable page);
	public Page<Film> findByYearGreaterThanAndYearLessThan(int greater, int less, Pageable page);
	
	// testing combining film + genre search (see film controller)
	public List<Film> findByTitleContainsIgnoreCase(String title);
}
