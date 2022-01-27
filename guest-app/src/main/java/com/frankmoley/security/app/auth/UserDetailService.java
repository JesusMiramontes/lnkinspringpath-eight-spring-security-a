package com.frankmoley.security.app.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailService(UserRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(s);
        if(null == user)
            throw new UsernameNotFoundException("Username cannot be found.");

        return new UserPrincipal(user);
    }
}
