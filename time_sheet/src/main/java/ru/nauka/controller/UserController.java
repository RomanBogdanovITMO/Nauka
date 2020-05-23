package ru.nauka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nauka.repos.UserRepo;
import ru.nauka.role.Role;
import ru.nauka.role.User;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    //получаем список пользователей которые прошли регистрацию
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }
    //меняе у пользователей их роли
    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userRepo.findById(id).get();
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam String username, @RequestParam Map<String, String> form, @RequestParam("userId") User user) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.values()) {

            if (roles.contains(key)) {

                user.getRoles().add(Role.valueOf(key));

            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }
}
