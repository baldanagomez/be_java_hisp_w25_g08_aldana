package com.grupo08.socialmeli.controller;

import com.grupo08.socialmeli.dto.response.FollowedDTO;
import com.grupo08.socialmeli.service.IUserService;
import com.grupo08.socialmeli.utils.TestData;
import com.grupo08.socialmeli.dto.response.FollowersCountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        //ARRANGE
        int userId = 1000;
        String order = null;

        FollowedDTO followedDTO = TestData.getFollowedDTOResponse(userId);
        when(userService.getFollowedSellers(userId,order)).thenReturn(followedDTO);

        //ACT
        ResponseEntity<?> response =
                userController.getFollowedSellers(followedDTO.getUserId(),order);

        //ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
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