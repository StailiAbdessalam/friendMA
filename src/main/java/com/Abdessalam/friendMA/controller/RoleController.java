package com.Abdessalam.friendMA.controller;

import com.Abdessalam.friendMA.enumeration.ERole;
import com.Abdessalam.friendMA.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/assign")
    public void assignRole(@RequestParam List<ERole> roles, @RequestParam String uid) throws Exception {
        System.out.println("roes " + roles);
//        ERole role = ERole.valueOf(roles);
        roleService.assignRole(roles, uid);
    }
}
