package com.moneymatters.MoneyMattersBackend.repository;

import com.moneymatters.MoneyMattersBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
}
