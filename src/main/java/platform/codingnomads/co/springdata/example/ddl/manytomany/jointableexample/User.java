package platform.codingnomads.co.springdata.example.ddl.manytomany.jointableexample;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> posts;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)  // If a User is deleted, the friend relationship should be deleted, but not the other User.
    @JoinTable(
            // Change the join table name
            name = "friendships",
            //specify a new column name referencing the email column in the User table
            joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "email"))
    private Set<User> friends;

}
