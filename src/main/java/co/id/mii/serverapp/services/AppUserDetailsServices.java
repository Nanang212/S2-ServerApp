package co.id.mii.serverapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.id.mii.serverapp.models.AppUserDetail;
import co.id.mii.serverapp.models.User;
import co.id.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailsServices implements UserDetailsService {
    private UserRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserRepository
                .findByUsernameOrEmployeeEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Username or Email not found!!!"));

        return new AppUserDetail(user);
    }

}
