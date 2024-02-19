package com.grupo08.socialmeli.controller;

import com.grupo08.socialmeli.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService sellerService;

    public UserController(IUserService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSeller(@PathVariable int userId, @PathVariable int userIdToFollow){
        return new ResponseEntity<>(sellerService.follow(userId, userIdToFollow), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedSellers(@PathVariable int userId){
        return new ResponseEntity<>(sellerService.getFollowedSellers(userId),HttpStatus.OK);
    }

}
