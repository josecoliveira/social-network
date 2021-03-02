package joseoliveira.socialnetwork.repository;

import joseoliveira.socialnetwork.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>  {
    Album findOneById(long id);
    List<Album> findByUserId(long userId);
}
