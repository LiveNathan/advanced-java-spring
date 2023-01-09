package platform.codingnomads.co.springdata.example.ddl.onetoone.bidirectional;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Sponsor {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String brand;
    @OneToOne(
            //used to indicate that this is the child side of a relationship and refer the JPA
            //to the field in the Driver class that defines the relationship
            mappedBy = "sponsor"
    )
    private Driver driver;
}
