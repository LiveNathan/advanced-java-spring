package platform.codingnomads.co.springdata.lab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String thoughts;

    @Column
    private Byte rating;  // 1-5

    @ManyToOne
    Area area;

    @ManyToOne
    Route route;

    // Force rating to remain 1-5
    @PrePersist
    @PreUpdate
    private void validateData() {
        if (rating > 5) {
            rating = 5;
        }
        if (rating < 1) {
            rating = 1;
        }
    }
}
