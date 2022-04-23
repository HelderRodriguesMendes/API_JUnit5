package com.cursoUdemy.apiJunit5.repository;

import com.cursoUdemy.apiJunit5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
