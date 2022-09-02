package com.api.coding_challenge.repository;

import com.api.coding_challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
