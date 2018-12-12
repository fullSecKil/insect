package com.example.caricaturepip.details;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @file: CustomUserService.class
 * @author: Dusk
 * @since: 2018/12/10 21:05
 * @desc:
 */

@Service
public class CustomUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User(username, "2", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
