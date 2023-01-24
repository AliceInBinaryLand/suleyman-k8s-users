package com.epam.suleymank8susers.repository;

import com.epam.suleymank8susers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

