package platform.codingnomads.co.springdata.example.ddl.manytoone.bidirectional;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String content;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Post post;
}
