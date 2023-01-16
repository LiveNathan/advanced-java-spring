package platform.codingnomads.co.springdata.example.dml.lifecyclecallback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PrintEntity {

    @Id
    @GeneratedValue
    private Long id;

    // write your methods here
    @PrePersist
    private void prePersistMethod() {
        System.out.println("** Helpful PrePersist method **");
    }

    @PostPersist
    private void postPersistMethod() {
        System.out.println("** Helpful PostPersist method **");
    }

    @PreUpdate
    private void preUpdateMethod() {
        System.out.println("** Helpful PrePersist method **");
    }

    @PreRemove
    private void preRemoveMethod() {
        System.out.println("** Helpful PreRemove method **");
    }

    @PostLoad
    private void postLoadMethod() {
        System.out.println("** Helpful PostLoad method **");
    }
}
