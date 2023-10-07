package co.id.ms.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @Column(length = 50)
    private String name;
    @Column(unique = true)
    private String email;
    @Column(length = 15)
    private String phone;

    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                '}';
    }
}
