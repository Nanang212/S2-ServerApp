package id.co.mii.serverapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;
    @Column(name = "email_address", nullable = false, length = 20)
    private String email;
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phone;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
// Employee = id, name, email, phone