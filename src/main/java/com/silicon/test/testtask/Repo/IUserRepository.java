package com.silicon.test.testtask.Repo;

import com.silicon.test.testtask.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
