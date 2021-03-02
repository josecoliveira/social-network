package joseoliveira.socialnetwork.controller;

import joseoliveira.socialnetwork.model.Album;
import joseoliveira.socialnetwork.service.AlbumService;
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
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/album", params = {})
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GetMapping(value = "/album", params = {"userId"})
    public List<Album> findByUserId(@RequestParam long userId) {
        if (!userService.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }
        return albumService.findByUserId(userId);
    }

    @GetMapping("/album/{id}")
    public Album findById(@PathVariable long id) {
        Album album = albumService.findOneById(id);
        if (album == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Album not found"
            );
        }
        return album;
    }

    @PostMapping("/album")
    public Boolean save(@RequestBody Map<String, String> body) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userService.findByUsername(userDetails.getUsername()).getId();
        String title = body.get("title");
        albumService.save(new Album(userId, title));
        return true;
    }


}
