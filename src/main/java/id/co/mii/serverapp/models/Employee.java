package id.co.mii.serverapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "employee_name", nullable = false, length = 50)
    private String name;

    @Column(name = "employee_email", nullable = false, length = 50)
    private String email;

    @Column(name = "employee_phone", nullable = false, length = 20)
    private String phone;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;
}
