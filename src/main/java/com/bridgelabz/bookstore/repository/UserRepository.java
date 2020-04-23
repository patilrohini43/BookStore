package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   User findUserByEmail(String email);
}
