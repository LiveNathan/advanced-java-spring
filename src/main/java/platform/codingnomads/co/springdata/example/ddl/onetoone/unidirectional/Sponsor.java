package platform.codingnomads.co.springdata.example.ddl.onetoone.unidirectional;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Sponsor {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String brand;
}
