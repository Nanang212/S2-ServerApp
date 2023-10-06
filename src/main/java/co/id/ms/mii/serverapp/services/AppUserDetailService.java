package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.models.AppUserDetail;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrAndEmployee_Email(username,username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User or Email not found!!!")
                );

        return new AppUserDetail(user);
    }
}
