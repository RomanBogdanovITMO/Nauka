package ru.nauka.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.nauka.role.User;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}