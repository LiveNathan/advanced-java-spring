package platform.codingnomads.co.springdata.example.ddl.manytomany.unidirectional;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user;  // This should actually be a User class
}
