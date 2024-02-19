package com.grupo08.socialmeli.controller;

import com.grupo08.socialmeli.dto.response.FollowDto;
import com.grupo08.socialmeli.repository.IBuyerRepository;
import com.grupo08.socialmeli.service.ISellerService;
import com.grupo08.socialmeli.service.SellerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialMeliController {

    ISellerService sellerService;

    public SocialMeliController(ISellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSeller(@PathVariable int userId, @PathVariable int userIdToFollow){
        return new ResponseEntity<>(sellerService.follow(userId, userIdToFollow), HttpStatus.OK);
    }

}
