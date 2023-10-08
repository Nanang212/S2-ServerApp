package id.co.mii.serverapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.AppUserDetail;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.UserRepositorty;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService{

    private UserRepositorty userRepositorty;

    @Override
    public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException{
        User user = userRepositorty
        .findByUsernameOrEmployeeEmail(username, username)
        .orElseThrow(() ->
        new UsernameNotFoundException("Username or Email not found!!!")
      );

    return new AppUserDetail(user);
  }
}