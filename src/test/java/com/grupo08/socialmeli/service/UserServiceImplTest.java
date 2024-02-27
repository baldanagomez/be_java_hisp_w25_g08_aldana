package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowersCountDto;
import com.grupo08.socialmeli.entity.Post;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.entity.User;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.repository.ISellerRepository;
import com.grupo08.socialmeli.repository.SellerRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    ISellerRepository sellerRepository;
    @InjectMocks
    UserServiceImpl userService;


    @Test
    void follow() {
    }

    @Test
    void getFollowers() {
    }

    @Test
    void unfollow() {
    }

    @Test
    void getFollowedSellers() {
    }

    @Test
    void testGetFollowedSellers() {
    }

    @Test
    @DisplayName("T0007| followersCount ok case")
    void countSellerFollowersOkTest() {
        //ARRANGE
        int sellerId = 1;
        when(sellerRepository.findById(sellerId)).thenReturn(Optional.of(new Seller(1, "Brayan", new ArrayList<>(), List.of(new User(1,"follower")))));
        //ACT
        FollowersCountDto countDto = userService.countSellerFollowers(sellerId);
        //ASSERT
        assertEquals(1L,countDto.getFollowersCount());
    }

    @Test
    @DisplayName("T0007| followersCount throwError case")
    void countSellerFollowersThrowErrorTest() {
        //ARRANGE
        when(sellerRepository.findById(anyInt())).thenReturn(Optional.empty());
        //ACT & ASSERT
        assertThrows(NotFoundException.class,() -> userService.countSellerFollowers(anyInt()));
    }

    @Test
    void postSortDate() {
    }

    @Test
    void postSortWeeks() {
    }
}