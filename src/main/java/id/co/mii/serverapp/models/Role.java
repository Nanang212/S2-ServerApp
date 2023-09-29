package id.co.mii.serverapp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "hasRoles")
    private List<User> users;
}
