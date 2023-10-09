package id.co.mii.serverapp.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")

public class User {

    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username", unique = true, length = 20)
    private String username;

    @Column(name = "password")
    private String password;

    private Boolean isEnabled = false;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
