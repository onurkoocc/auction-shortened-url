package com.tapu.auctionshortenedurl.Repository;

import com.tapu.auctionshortenedurl.Entity.ERole;
import com.tapu.auctionshortenedurl.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole eRole);

    Boolean existsByName(ERole eRole);
}
