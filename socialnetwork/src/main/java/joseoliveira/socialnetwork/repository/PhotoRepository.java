package joseoliveira.socialnetwork.repository;

import joseoliveira.socialnetwork.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    List<Photo> findAllByAlbumId(long albumId);

    Photo findOneById(long id);
}
