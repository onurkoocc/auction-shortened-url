package com.tapu.auctionshortenedurl.Service;

import com.tapu.auctionshortenedurl.Entity.ERole;
import com.tapu.auctionshortenedurl.Entity.Role;

public interface RoleService {
    Role getRoleByName(ERole eRole);

}
