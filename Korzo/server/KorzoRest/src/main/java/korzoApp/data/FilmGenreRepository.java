package korzoApp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import korzoApp.model.FilmGenre;

@Component
public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long>{

}
