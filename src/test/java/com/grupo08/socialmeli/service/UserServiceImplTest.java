package com.grupo08.socialmeli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo08.socialmeli.repository.BuyerRepositoryImpl;
import com.grupo08.socialmeli.repository.IBuyerRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    BuyerRepositoryImpl buyerRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void follow() {
    }

    @Test
    @DisplayName("Get users sorted by name asc or desc")
    void getFollowersbyId() {
               

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
    void countSellerFollowers() {
    }

    @Test
    void postSortDate() {
    }

    @Test
    void postSortWeeks() {
    }
}