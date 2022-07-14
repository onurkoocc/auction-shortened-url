package com.tapu.auctionshortenedurl.Service.Implementation;

import com.tapu.auctionshortenedurl.Entity.ERole;
import com.tapu.auctionshortenedurl.Entity.Role;
import com.tapu.auctionshortenedurl.Repository.RoleRepository;
import com.tapu.auctionshortenedurl.Service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class RoleServiceImp implements RoleService {
    RoleRepository roleRepository;

    public Role getRoleByName(ERole eRole) {
        Optional<Role> role = roleRepository.findByName(eRole);
        return role.get();
    }
}
