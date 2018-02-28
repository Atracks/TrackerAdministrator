package ru.bravery_and_stupidity.trackerAdministrator.security;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;
import ru.bravery_and_stupidity.trackerAdministrator.repository.WorkerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
final public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(SecurityUserDetailsService.class);

    @Autowired
    private WorkerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws IllegalArgumentException {
        Worker worker = repository.getWorker(login);
        Assert.notNull(worker,"user with login: " + login + " not found");
        LOGGER.info("Found user in database: " + worker);
        return new org.springframework.security.core.userdetails.User(login, worker.getPass(), setUserRole(worker.getIsGod()));
    }

    private List<GrantedAuthority>  setUserRole(byte isAdmin) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = "ROLE_USER";
        if ((byte)1 == isAdmin) {
            role = "ROLE_ADMIN";
        }
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
