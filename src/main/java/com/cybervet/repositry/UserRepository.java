package com.cybervet.repositry;

import com.cybervet.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    List<AppUser> findByUsername(String username);
}
