package platform.codingnomads.co.springdata.lab.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "routes")
//@Builder  // Removing this to force everyone to use the constructor to avoid invalid objects.
@Data
public class Route implements Serializable {

    private static final long serialVersionUID = -2624055642258734917L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)  // Had to change these to EAGER to fix a session issue. Is there a better way?
    @JoinColumn(
            name = "origin_area_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_routes_origin_area_id")
    )
    private Area origin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "destination_area_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_routes_destination_area_id")
    )
    private Area destination;

    @Column(unique = true)
    private String code;

    public Route(Area origin, Area destination) {
        this.origin = origin;
        this.destination = destination;
        this.code = origin.getCode() + "-" + destination.getCode();  // automatically generate its code based on the specified origin and destination (Example: "G-H").
    }

}
