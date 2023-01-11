package platform.codingnomads.co.springdata.example.ddl.joincolumn;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "assets")
@Data
@AllArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private int value;

    // Bidirectional relationship where a User can be found through their Asset
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Custom join column called owner
    @JoinColumn(name = "owner", unique = true, referencedColumnName = "email")
    private User user;
}
