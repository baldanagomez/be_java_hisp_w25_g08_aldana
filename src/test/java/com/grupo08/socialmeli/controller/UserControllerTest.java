package com.grupo08.socialmeli.controller;

import com.grupo08.socialmeli.dto.response.FollowedDTO;
import com.grupo08.socialmeli.service.IUserService;
import com.grupo08.socialmeli.utils.TestData;
import com.grupo08.socialmeli.dto.response.FollowersCountDto;
import com.grupo08.socialmeli.dto.ExceptionDto;
import com.grupo08.socialmeli.dto.response.FollowDto;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    UserController userController;

    @Test
    void followSeller() {

        int buyerId = 1;
        int sellerId = 1;

        FollowDto expected = new FollowDto(sellerId, "Andrés");

        when(userService.follow(buyerId, sellerId)).thenReturn(expected);

        ResponseEntity<?> actual = userController.followSeller(buyerId, sellerId);

        assertEquals(expected, actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());

    }

    @Test
    void getFollowers() {
    }

    @Test
    void unFollowSeller() {

        int buyerId = 1;
        int sellerId = 1;

        doNothing().when(userService).unfollow(buyerId, sellerId);

        ResponseEntity<?> actual = userController.unFollowSeller(buyerId, sellerId);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
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