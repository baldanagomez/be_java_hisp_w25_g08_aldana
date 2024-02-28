package com.grupo08.socialmeli.controller;

import com.grupo08.socialmeli.dto.response.FollowersCountDto;
import com.grupo08.socialmeli.service.IUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Mock
    IUserService userService;
    @InjectMocks
    UserController userController;

    @Test
    void followSeller() {
    }

    @Test
    void getFollowers() {
    }

    @Test
    void unFollowSeller() {
    }

    @Test
    void getFollowedSellers() {
    }

    @Test
    void followersCount() {
        //ARRANGE
        FollowersCountDto expectedResponse = new FollowersCountDto(1,"sellerName",2L);
        when(userService.countSellerFollowers(anyInt())).thenReturn(expectedResponse);
        //ACT
        ResponseEntity<?> obtainedResponse = userController.followersCount(1);
        //ASSERT
        assertEquals(expectedResponse,obtainedResponse.getBody());
        assertEquals(HttpStatus.OK, obtainedResponse.getStatusCode());
    }
}