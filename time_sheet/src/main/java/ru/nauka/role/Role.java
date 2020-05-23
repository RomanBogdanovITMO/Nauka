package ru.nauka.role;

import org.springframework.security.core.GrantedAuthority;

public enum  Role implements GrantedAuthority {
    USERTABEL, ADMIN, USERDEPART, USERDIRECTORI;

    @Override
    public String getAuthority() {
        return name();
    }
}
