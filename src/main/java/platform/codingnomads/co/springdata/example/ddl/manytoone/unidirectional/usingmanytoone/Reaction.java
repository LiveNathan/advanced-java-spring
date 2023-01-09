package platform.codingnomads.co.springdata.example.ddl.manytoone.unidirectional.usingmanytoone;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
@Data
public class Reaction {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String potato;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Post post;
}
