package joseoliveira.socialnetwork.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = -2343243243242432341L;

    @Column(name = "userId")
    private long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    public Post() {
    }

    public Post(long userId, long id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(long userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
