package co.id.ms.mii.serverapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name = "user_id")
    private Integer id;
    private String name;
    private String email;
    private Integer phone;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
