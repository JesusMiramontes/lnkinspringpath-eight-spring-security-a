package com.frankmoley.security.app.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository repository;
    private final AuthGroupRepository authGroupRepository;

    public UserDetailService(UserRepository repository, AuthGroupRepository authGroupRepository) {
        super();
        this.repository = repository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(s);
        if(null == user)
            throw new UsernameNotFoundException("Username cannot be found.");

        List<AuthGroup> authGroups = authGroupRepository.findByUsername(s);
        return new UserPrincipal(user, authGroups);
    }
}
