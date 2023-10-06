package id.co.mii.serverapp.config;

import id.co.mii.serverapp.models.Privilege;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.repositories.PrivilegeRepository;
import id.co.mii.serverapp.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
@Slf4j
public class RoleConfig implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Transactional
    public void run(String... args) throws Exception {
        log.info("Executing RoleConfig...");

        Stream.of(
            "CREATE_USER", "READ_USER", "UPDATE_USER", "DELETE_USER",
            "CREATE_ADMIN", "READ_ADMIN", "UPDATE_ADMIN", "DELETE_ADMIN"
        ).forEach(privilegeValue ->
            privilegeRepository
                .findByNameIgnoreCase(privilegeValue)
                .orElseGet(() -> {
                        Privilege privilege = new Privilege();
                        privilege.setName(privilegeValue);
                        return privilegeRepository.save(privilege);
                    }
                )
        );

        roleRepository
            .findByNameIgnoreCase("USER")
            .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("USER");
                    role.setPrivileges(privilegeRepository.findByNameIn(new HashSet<String>() {{
                        add("READ_USER");
                    }}));
                    return roleRepository.save(role);
                }
            );

        roleRepository
            .findByNameIgnoreCase("ADMIN")
            .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ADMIN");

                    Set<Privilege> privileges = privilegeRepository.findByNameIn(new HashSet<String>() {{
                        add("CREATE_ADMIN");
                        add("READ_ADMIN");
                        add("UPDATE_ADMIN");
                        add("DELETE_ADMIN");
                        add("CREATE_USER");
                        add("READ_USER");
                        add("UPDATE_USER");
                        add("DELETE_USER");
                    }});

                    log.info("HASIL {}", privileges);

                    role.setPrivileges(privileges);
                    return roleRepository.save(role);
                }
            );

        log.info("Finish executing RoleConfig...");
    }
}
