package joseoliveira.socialnetwork.service;

import joseoliveira.socialnetwork.model.Album;
import joseoliveira.socialnetwork.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> findAll() {
        Iterable<Album> it = albumRepository.findAll();
        ArrayList<Album> albums = new ArrayList<Album>();
        it.forEach(albums::add);
        return albums;
    }

    public List<Album> findByUserId(long userId) {
        return albumRepository.findByUserId(userId);
    }

    public Album findOneById(long id) {
        return albumRepository.findOneById(id);
    }

    public void save(Album album) {
        albumRepository.save(album);
    }

    public Boolean existsById(long id) {
        return albumRepository.existsById(id);
    }
}
