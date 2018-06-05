package com.silicon.test.testtask.Service;

import com.silicon.test.testtask.Model.User;
import com.silicon.test.testtask.Repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @PostConstruct
    public void init() {
        if (userRepo.findByUsername("iafst") == null)
            saveUser(new User("iafst", "123", "ROLE_ADMIN"));
        if (userRepo.findByUsername("admin") == null)
            saveUser(new User("admin", "password", "ROLE_ADMIN"));
        if (userRepo.findByUsername("user") == null)
            saveUser(new User("user", "password", "ROLE_USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public void saveUser(User user) {
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

}
