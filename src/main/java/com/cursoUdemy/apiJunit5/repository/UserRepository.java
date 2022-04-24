package com.cursoUdemy.apiJunit5.repository;

import com.cursoUdemy.apiJunit5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findBynameLike(String name);

    public Optional<User> findByEmail(String email);
}
