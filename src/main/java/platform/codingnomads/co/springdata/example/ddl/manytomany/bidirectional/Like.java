package platform.codingnomads.co.springdata.example.ddl.manytomany.bidirectional;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "likes")
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user;  // This should actually be a User class

    //many to many annotation refers to the likes field in the Post class
    @ManyToMany(mappedBy = "likes")
    private Set<Post> posts;
}
