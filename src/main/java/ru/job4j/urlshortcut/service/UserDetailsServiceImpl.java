package ru.job4j.urlshortcut.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;

import static java.util.Collections.emptyList;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private SiteService siteService;

    public UserDetailsServiceImpl(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Site user = siteService.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}
