package com.study.mothr.web.controller;


import com.study.ddd.application.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController extends ApiResponse{

    private final UserAppService userAppService;

    public UserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO, @RequestParam String password) {
        UserDTO createdUser = userAppService.registerUser(userDTO, password);
        return ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id) {
            UserDTO userDTO = userAppService.getUserById(id);
            return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO) {
            UserDTO updatedUser = userAppService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
            userAppService.deleteUser(id);
            return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<UserDTO> changePassword(@PathVariable UUID id, @RequestParam String newPassword) {
            UserDTO updatedUser = userAppService.changeUserPassword(id, newPassword);
            return ResponseEntity.ok(updatedUser);
    }
}
