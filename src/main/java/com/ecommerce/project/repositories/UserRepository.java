package com.ecommerce.project.repositories;

import com.ecommerce.project.entities.AppUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {


    Optional<AppUser> findByUserName(String username);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
