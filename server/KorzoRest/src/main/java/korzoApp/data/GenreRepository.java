package korzoApp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import korzoApp.model.Genre;

@Component
public interface GenreRepository extends JpaRepository<Genre, Long>{

}
