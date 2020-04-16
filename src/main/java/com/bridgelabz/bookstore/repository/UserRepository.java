package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
