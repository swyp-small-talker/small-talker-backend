package com.swygbr.backend.controller;

import com.swygbr.backend.entity.Userinfo;
import com.swygbr.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Userinfo> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Userinfo getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Userinfo createUser(@RequestBody Userinfo userinfo) {
        return userService.createUser(userinfo);
    }

    @PutMapping("/{id}")
    public Userinfo updateUser(@PathVariable Long id, @RequestBody Userinfo userinfo) {
        userinfo.setId(id);
        return userService.updateUser(userinfo);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
