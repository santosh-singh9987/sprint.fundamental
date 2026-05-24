package com.example.dashboard.start.controller.v1.user;

import com.example.dashboard.start.dto.user.UserRequestDTO;
import com.example.dashboard.start.dto.user.UserResponseDTO;
import com.example.dashboard.start.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET all users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // CREATE user
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO response = userService.save(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO,
                                                      @PathVariable Long id
        ) {
        return ResponseEntity.ok(userService.editById(userRequestDTO,id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserForPatchData(@RequestBody UserRequestDTO userRequestDTO, @PathVariable Long id)
    {
        return ResponseEntity.ok(userService.patchUpdate(id,userRequestDTO));
    }
    // DELETE user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // DELETE all users
    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        userService.clear();
        return ResponseEntity.noContent().build();
    }

    //Projection query
    @GetMapping("/Projection")
    public ResponseEntity<List<UserResponseDTO>> getselectedData() {
        List<UserResponseDTO> response = userService.getUserDetail();
        return ResponseEntity.ok(response);
    }
}