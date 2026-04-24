package com.artur.crypto_portfolio_tracker.dao;

import com.artur.crypto_portfolio_tracker.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
