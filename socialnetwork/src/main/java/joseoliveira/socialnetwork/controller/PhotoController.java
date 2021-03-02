package joseoliveira.socialnetwork.controller;

import joseoliveira.socialnetwork.model.Comment;
import joseoliveira.socialnetwork.model.Photo;
import joseoliveira.socialnetwork.service.AlbumService;
import joseoliveira.socialnetwork.service.PhotoService;
import joseoliveira.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/photo", params = {})
    public List<Photo> findAll() {
        return photoService.findAll();
    }

    @GetMapping(value = "/photo", params = {"albumId"})
    public List<Photo> findByAlbumId(@RequestParam long albumId) {
        if (!albumService.existsById(albumId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Album not found"
            );
        }
        return photoService.findAllByAlbumId(albumId);
    }

    @GetMapping("/photo/{id}")
    public Photo findOneById(@PathVariable long id) {
        Photo photo = photoService.findOneById(id);
        if (photo == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Photo not found"
            );
        }
        return photo;
    }

    @PostMapping("/photo")
    public Boolean save(@RequestBody Map<String, String> body) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userService.findByUsername(userDetails.getUsername()).getId();
        long albumId = Long.parseLong(body.get("albumId"));
        String title = body.get("title");
        String url = body.get("url");
        String thumbnailUrl = body.get("thumbnailUrl");
        if (albumService.findOneById(albumId).getUserId() != userId) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "User is not the owner of the album"
            );
        }
        photoService.save(new Photo(albumId, title, url, thumbnailUrl));
        return true;
    }

}
