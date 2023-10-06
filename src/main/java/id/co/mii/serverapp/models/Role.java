package id.co.mii.serverapp.models;

import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tb_tr_role_privilege", 
    joinColumns = @JoinColumn(name = "role_id"), 
    inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private List<Privilege> privileges;
}