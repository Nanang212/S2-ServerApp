package id.co.mii.serverapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "employee_name",
            nullable = false,
            length = 20)
    private String name;

    @Column(name = "employee_email",
            nullable = false,
            unique = true,
            length = 25)
    private String email;

    @Column(name = "employee_phone",
            nullable = false,
            unique = true,
            length = 15)
    private String phone;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id",
                nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
    
}
