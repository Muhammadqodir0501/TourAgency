package org.example.touragency.controller;

import lombok.RequiredArgsConstructor;
import org.example.touragency.dto.request.UserAddDto;
import org.example.touragency.dto.response.UserUpdateDto;
import org.example.touragency.model.enity.User;
import org.example.touragency.service.abstractions.UserService;
import org.example.touragency.service.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse<?>> addNewUser(@RequestBody UserAddDto userAddDto) {
        User newUser = userService.addNewUser(userAddDto);
        return ResponseEntity.ok(new ApiResponse<>(newUser));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(users));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@RequestParam UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse<>("User has successfully been deleted"));
    }

   @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable UUID userId, @RequestBody UserUpdateDto userUpdateDto) {
       User updatedUser = userService.updateUser(userId, userUpdateDto);
       return ResponseEntity.ok(new ApiResponse<>(updatedUser));
   }

}
