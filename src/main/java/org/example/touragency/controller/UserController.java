package org.example.touragency.controller;

import lombok.RequiredArgsConstructor;
import org.example.touragency.dto.request.UserAddDto;
import org.example.touragency.dto.response.UserUpdateDto;
import org.example.touragency.model.enity.User;
import org.example.touragency.service.abstractions.UserService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addNewUser(@RequestBody UserAddDto userAddDto) {
        try{
            userService.addNewUser(userAddDto);
            return ResponseEntity.ok("User has successfully been added");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@RequestParam UUID userId) {
        try{
            userService.deleteUser(userId);
            return ResponseEntity.ok("User has successfully been deleted");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

   @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable UUID userId, @RequestBody UserUpdateDto userUpdateDto) {
        try{
            userService.updateUser(userId, userUpdateDto);
            return ResponseEntity.ok("User has successfully been Updated");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
