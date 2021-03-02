package joseoliveira.socialnetwork.service;

import joseoliveira.socialnetwork.model.Photo;
import joseoliveira.socialnetwork.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoService {
    
    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> findAll() {
        Iterable<Photo> it = photoRepository.findAll();
        ArrayList<Photo> photos = new ArrayList<Photo>();
        it.forEach(photos::add);
        return photos;
    }

    public List<Photo> findAllByAlbumId(long albumId) {
        return photoRepository.findAllByAlbumId(albumId);
    }

    public Photo findOneById(long id) {
        return photoRepository.findOneById(id);
    }

    public void save(Photo photo) {
        photoRepository.save(photo);
    }
}
