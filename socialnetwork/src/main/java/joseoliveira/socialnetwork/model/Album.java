package joseoliveira.socialnetwork.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "albums")
public class Album implements Serializable {

    @Serial
    private static final long serialVersionUID = -2343243243242432341L;

    @Column(name = "user_id")
    private long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "title")
    private String title;

    public Album() {
    }

    public Album(long userId, String title) {
        this.userId = userId;
        this.title = title;
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
}
