package com.tapu.auctionshortenedurl.Validation;

import com.tapu.auctionshortenedurl.Entity.ERole;
import com.tapu.auctionshortenedurl.Exception.RoleException;
import com.tapu.auctionshortenedurl.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleValidation {
    RoleRepository roleRepository;

    public Boolean isExist(String name) throws RoleException {
        if (!(ERole.ADMIN.name().equalsIgnoreCase(name) || ERole.USER.name().equalsIgnoreCase(name))) {
            throw new RoleException("Error: Role is not found.");
        }
        return true;
    }
}
