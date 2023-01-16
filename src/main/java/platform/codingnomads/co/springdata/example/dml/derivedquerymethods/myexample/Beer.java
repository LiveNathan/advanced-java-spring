package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.myexample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "beers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brewery;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String style;

    @Column(nullable = false)
    private double abv;
}
